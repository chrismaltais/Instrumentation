
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
        if (isActive) {
            Method m = new Method(comment);
            m.startTime = System.currentTimeMillis();
            int tier = timingMatcher.size();
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
    }

    public void stopTiming(String comment){
        if (isActive) {
            Method m = timingMatcher.pop();
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
//            System.out.println(s);
            fileContent += s + "\n";
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dump() {
        Calendar now = Calendar.getInstance();
        String year = String.valueOf(now.get(Calendar.YEAR)).substring(2);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        String filename = String.format("instrumentation%02d%02d%02d%02d%02d%02d.log", day, year, month, hour, minute, second);
        dump(filename);
    }

    public void activate (Boolean onoff) {
        isActive = onoff;
    }

}
