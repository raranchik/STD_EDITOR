import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class LevelDataTemp {
    @JsonIgnore
    public final String LVL_DATA_NAME_TEMPLATE = "LevelData_";
    @JsonIgnore
    public int index = -1;
    @JsonIgnore
    public Boolean isEdit = false;
    @JsonIgnore
    public Boolean isNew = false;
    @JsonIgnore
    public String fileName = "";

    public String fCardAbsPath = "";
    public String sCardAbsPath = "";
    public ArrayList<DifferenceTemp> differences = new ArrayList<DifferenceTemp>();

    public LevelDataTemp(String aFCardAbsPath, String aSCardAbsPath, ArrayList<DifferenceTemp> aDifferences) {
        fCardAbsPath = aFCardAbsPath;
        sCardAbsPath = aSCardAbsPath;
        differences = aDifferences;
    }

    @Override
    public String toString() {
        String nameSuffix = "";
        if (isEdit && !isNew) {
            nameSuffix = String.format(" (Edit: %s)", fileName);
        }
        else if (isNew && !isEdit) {
            nameSuffix = " (New)";
        }
        else if (isEdit && isNew) {
            nameSuffix = " (Edit new)";
        }
        else {
            nameSuffix = String.format(" (%s)", fileName);
        }

        return LVL_DATA_NAME_TEMPLATE + index + nameSuffix;
    }

}
