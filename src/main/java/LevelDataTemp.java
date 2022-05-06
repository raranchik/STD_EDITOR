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
    @JsonIgnore
    private String fCardName = "";
    @JsonIgnore
    private String sCardName = "";

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
        if (fileName.isEmpty() || fileName.equals("NOT SELECTED")) {
            if (fCardAbsPath != null) {
                fCardName = fCardAbsPath.replaceAll("^.*[\\/\\\\]", "");
                fCardName = fCardName.replaceAll("(?<!^)[.][^.]*$", "");
            }
            if (sCardAbsPath != null) {
                sCardName = sCardAbsPath.replaceAll("^.*[\\/\\\\]", "");
                sCardName = sCardName.replaceAll("(?<!^)[.][^.]*$", "");
            }

            if (fCardName.isEmpty() && sCardName.isEmpty()) {
                fileName = "NOT SELECTED";
            }
            else {
                fileName = String.format("%s_%s", fCardName, sCardName);
            }
        }

        if (isEdit) {
            nameSuffix = String.format(" (Edit: %s)", fileName);
        }
        else if (isNew) {
            nameSuffix = String.format(" (New: %s)", fileName);
        }
        else {
            nameSuffix = String.format(" (%s)", fileName);
        }

        return LVL_DATA_NAME_TEMPLATE + index + nameSuffix;
    }

}
