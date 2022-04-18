import java.util.ArrayList;

public class LevelDataTemp {
    public String fCardAbsPath = "";
    public String sCardAbsPath = "";
    public ArrayList<DifferenceTemp> differences = new ArrayList<DifferenceTemp>();

    public LevelDataTemp(String aFCardAbsPath, String aSCardAbsPath, ArrayList<DifferenceTemp> aDifferences) {
        fCardAbsPath = aFCardAbsPath;
        sCardAbsPath = aSCardAbsPath;
        differences = aDifferences;
    }
}
