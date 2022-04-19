import java.util.ArrayList;

public class LevelDataTemp {
    public final String LVL_DATA_NAME_TEMPLATE = "LevelData_";
    public int index = -1;
    public String fCardAbsPath = "";
    public String sCardAbsPath = "";
    public ArrayList<DifferenceTemp> differences = new ArrayList<DifferenceTemp>();

    public LevelDataTemp(String aFCardAbsPath, String aSCardAbsPath, ArrayList<DifferenceTemp> aDifferences, int aIndex) {
        fCardAbsPath = aFCardAbsPath;
        sCardAbsPath = aSCardAbsPath;
        differences = aDifferences;
        index = aIndex;
    }

    @Override
    public String toString() {
        return String.format("%s%s", LVL_DATA_NAME_TEMPLATE, index);
    }

}
