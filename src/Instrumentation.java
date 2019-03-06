import javax.sound.midi.Instrument;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;

public class Instrumentation {

    private static Instrumentation instance = new Instrumentation();

    private Boolean isActive = false;

    ArrayList<Method> methodArrayList = new ArrayList<>();
    Stack<Method> timingMatcher = new Stack<>();
    HashMap<String, Method> methodHashMap = new HashMap<>();

    private Instrumentation(){}

    public static Instrumentation getInstance(){
        return instance;
    }

    public void startTiming(String comment){

//        if(isActive){
//            long startTime = System.currentTimeMillis();
//
//            // Assumption: Two methods cannot exist with the same name
//            if(methodHashMap.containsKey(comment)){
//
//            }
//            else{
//                Method newMethod = new Method(comment);
//                newMethod.startTime = startTime;
//
//                // Assign method "tier"
//                if(methodArrayList.size() > 0){
//                    Method prevMethod = methodArrayList.get(methodArrayList.size() - 1);
//                    // If the previous method is not complete, assign a new tier.
//                    if(prevMethod.isComplete == false){
//                        newMethod.tier = prevMethod.tier + 1;
//                    }
//                    else{
//                        newMethod.tier = prevMethod.tier;
//                    }
//
//                }
//
//                methodArrayList.add(newMethod);
//                methodHashMap.put(comment, newMethod);
//            }
//        }
        if (isActive) {
            Method m = new Method(comment);
            m.startTime = System.currentTimeMillis();
            timingMatcher.push(m);
            // append log to string/file: |\tSTARTTIMING: method name
            int numTabs = timingMatcher.size() - 1;
            String tabs = "";
            for (int i = 0; i < numTabs; ++i) {
                tabs += "\t";
            }
            if (tabs != "") {
                tabs = "|" + tabs;
            }
            System.out.println(tabs + "STARTTIMING: " + comment);
        }

    }

    public void stopTiming(String comment){

        if(isActive) {
//            long endTime = System.currentTimeMillis();
//
//            // Assumption: Method must exist
//            if (methodHashMap.containsKey(comment)) {
//                Method currentMethod = methodHashMap.get(comment);
//                currentMethod.endTime = endTime;
//                currentMethod.isComplete = true;
//            } else {
//                // Illegal stopTiming
//            }
            Method m = timingMatcher.pop();
            m.endTime = System.currentTimeMillis();
            long duration = m.endTime - m.startTime;
            // write to log file the stop event and duration: |\tSTOPTIMING: method name duration ms
            int numTabs = timingMatcher.size();
            String tabs = "";
            for (int i = 0; i < numTabs; ++i) {
                tabs += "\t";
            }
            if (tabs != "") {
                tabs = "|" + tabs;
            }
            System.out.println(tabs + "STOPTIMING: " + comment + " " + duration + " ms");
        }

    }

    public void comment(String comment){

        if(isActive){
//            // Assumption: comments can only occur after a Start/Stop pair is complete
//            Method newComment = new Method(comment);
//            newComment.isComment = true;
//            newComment.isComplete = true;
//            // Assign method "tier"
//            if(methodArrayList.size() > 0){
//                Method prevMethod = methodArrayList.get(methodArrayList.size() - 1);
//                // If the previous method is not complete, assign a new tier.
//                if(prevMethod.isComplete == false){
//                    newComment.tier = prevMethod.tier + 1;
//                }
//                else{
//                    newComment.tier = prevMethod.tier;
//                }
//            }
//            methodArrayList.add(newComment);
            // write comment to log file of the format: COMMENT: this is an example of a comment!
            int numTabs = timingMatcher.size();
            String tabs = "";
            for (int i = 0; i < numTabs; ++i) {
                tabs += "\t";
            }
            if (tabs != "") {
                tabs = "|" + tabs;
            }
            System.out.println(tabs + "COMMENT: " + comment);
        }

    }

    public void dump(String filename){

    }

    public void activate (Boolean onoff){
        isActive = onoff;
    }

}
