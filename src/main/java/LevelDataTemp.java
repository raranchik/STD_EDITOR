import java.util.ArrayList;

public class LevelDataTemp {
    public LevelDataTemp(String firstPath, String secondPath) {
        firstCardAbsolutePath = firstPath;
        secondCardAbsolutePath = secondPath;
    }
    public ArrayList<DifferenceTemp> differences = new ArrayList<DifferenceTemp>();
    public String firstCardAbsolutePath = "";
    public String secondCardAbsolutePath = "";
}
