public class Method {

    public String name;
    public long startTime = 0;
    public long endTime = 0;
    public boolean isComplete = false;
    public boolean isComment = false;
    public int tier = 0;

    public Method(String comment){
        name = comment;
    }

}