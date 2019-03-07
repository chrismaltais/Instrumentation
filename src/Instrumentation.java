
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Instrumentation {

    private static Instrumentation instance = new Instrumentation();

    private Boolean isActive = false;

    ArrayList<String> dumpOutput = new ArrayList<>();
    Stack<Method> timingMatcher = new Stack<>();

    private Instrumentation(){}

    public static Instrumentation getInstance(){
        return instance;
    }

    public void startTiming(String comment){
        Method m = new Method(comment);
        m.startTime = System.currentTimeMillis();
        int tier = timingMatcher.size();
        timingMatcher.push(m);
        if (isActive) {
            m = new Method(comment);
            m.startTime = System.currentTimeMillis();
            tier = timingMatcher.size();
            timingMatcher.push(m);

            // generate log for start event for later dumping
            int numTabs = tier;
            String tabs = "";
            for (int i = 0; i < numTabs; ++i) {
                tabs += "|\t";
            }
            //System.out.println(tabs + "STARTTIMING: " + comment);
            dumpOutput.add(tabs + "STARTTIMING: " + comment);
        }
        m = timingMatcher.pop();
        m.endTime = System.currentTimeMillis();
    }

    public void stopTiming(String comment){
        Method m = new Method(comment);
        m.startTime = System.currentTimeMillis();
        int tier = timingMatcher.size();
        timingMatcher.push(m);
        if (isActive) {
            m = timingMatcher.pop();
            m.endTime = System.currentTimeMillis();

            // generate log for stop event for later dumping
            int numTabs = timingMatcher.size();
            String tabs = "";
            for (int i = 0; i < numTabs; ++i) {
                tabs += "|\t";
            }
            long duration = m.endTime - m.startTime;
            //System.out.println(tabs + "STOPTIMING: " + comment + " " + duration + " ms");
            dumpOutput.add(tabs + "STOPTIMING: " + comment + " " + duration + " ms");
        }
        m = timingMatcher.pop();
        m.endTime = System.currentTimeMillis();
    }

    public void comment(String comment){
        if (isActive) {
            int numTabs = timingMatcher.size();
            String tabs = "";
            for (int i = 0; i < numTabs; ++i) {
                tabs += "|\t";
            }
            //System.out.println(tabs + "COMMENT: " + comment);
            dumpOutput.add(tabs + "COMMENT: " + comment);
        }
    }

    public void dump(String filename) {
        String fileContent = "";
        for (String s : dumpOutput) {
            System.out.println(s);
            fileContent += s + "\n";
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dump() {
        dump("log.txt");
    }

    public void activate (Boolean onoff) {
        isActive = onoff;
    }

}
