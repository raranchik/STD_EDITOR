import javax.swing.*;
import java.awt.*;
import java.io.File;

public class LevelsDataPreparer {
    public static final String NOT_SELECTED = "NOT SELECTED";
    public static final String NOT_EXISTS = "FILE FOLDER NOT EXISTS";
    public static final Color COLOR_IF_NOT_EXISTS = Color.RED;
    public static final String COUNT_LEVELS_DATA_LABEL_TEMPLATE = "Count levels data: ";
    public static final String MAIN_DIRECTORY = "STD"; // Assets
    public static final String SCRIPTABLE_OBJECT_FOLDER_NAME = "ScriptableObject";
    public static final String LEVEL_DATA_FOLDER_NAME = "LevelData";
    private JList source;
    private JLabel sourceLabel;
    private String workDirectory = "";
    private DefaultListModel<String> model = new DefaultListModel<String>();

    public LevelsDataPreparer(JList aSource, JLabel aLabel, String aWorkDirectory) {
        source = aSource;
        sourceLabel = aLabel;
        workDirectory = aWorkDirectory;
        prepareLevelsDataFolder();
    }

    public void prepareLevelsDataFolder() {
        int startLevelsDataFolder = workDirectory.indexOf(MAIN_DIRECTORY) + MAIN_DIRECTORY.length();
        String levelsDataAbsolutePath =
                workDirectory.substring(0, startLevelsDataFolder)
                        + File.separator
                        + SCRIPTABLE_OBJECT_FOLDER_NAME
                        + File.separator
                        + LEVEL_DATA_FOLDER_NAME;
        File folder = new File(levelsDataAbsolutePath);
        if (!folder.exists()) {
            model = new DefaultListModel<String>();
            source.setModel(model);
            sourceLabel.setText(COUNT_LEVELS_DATA_LABEL_TEMPLATE + NOT_EXISTS);
            sourceLabel.setForeground(COLOR_IF_NOT_EXISTS);

            return;
        }

        FileFilterExt ffe = new FileFilterExt("asset", "Assets");
        File[] listAssets = folder.listFiles(ffe);

        for (int i = 0; i < listAssets.length; i++) {
            String name = listAssets[i].getName();
            model.add(i, name.replace(".asset", ""));
        }

        source.setModel(model);
        sourceLabel.setText(COUNT_LEVELS_DATA_LABEL_TEMPLATE + model.size());
        sourceLabel.setForeground(COLOR_IF_NOT_EXISTS);
    }
}
