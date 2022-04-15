import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.yaml.snakeyaml.Yaml;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Map;

public class LevelsDataPreparer {
    public static final String NOT_SELECTED = "NOT SELECTED";
    public static final String NOT_EXISTS = "FILE FOLDER NOT EXISTS";
    public static final Color COLOR_IF_NOT_EXISTS = Color.RED;
    public static final Color COLOR_IF_EXISTS = Color.BLACK;
    public static final String COUNT_LEVELS_DATA_LABEL_TEMPLATE = "Count levels data: ";
    public static final String MAIN_DIRECTORY = "Assets"; // Assets // STD
    public static final String SCRIPTABLE_OBJECT_FOLDER_NAME = "ScriptableObject";
    public static final String LEVEL_DATA_FOLDER_NAME = "LevelData";
    public static final String CARDS_FOLDER_NAME = "Cads";
    private JList source;
    private JLabel sourceLabel;
    private String workDirectory = "";
    private DefaultListModel<String> model = new DefaultListModel<String>();
    private ArrayList<LevelDataTemp> levels = new ArrayList<LevelDataTemp>();
    private File folder = null;
    private String levelsDataAbsolutePath = "";
    private File[] levelsFiles = null;
    private File[][] cardsFiles = null;

    public LevelsDataPreparer(JList aSource, JLabel aLabel, String aWorkDirectory) {
        source = aSource;
        sourceLabel = aLabel;
        workDirectory = aWorkDirectory;
        prepareLevelsDataFolder();
    }

    public void prepareLevelsDataFolder() {
        int startLevelsDataFolder = workDirectory.indexOf(MAIN_DIRECTORY) + MAIN_DIRECTORY.length();
        levelsDataAbsolutePath =
                workDirectory.substring(0, startLevelsDataFolder)
                        + File.separator
                        + SCRIPTABLE_OBJECT_FOLDER_NAME
                        + File.separator
                        + LEVEL_DATA_FOLDER_NAME;
        folder = new File(levelsDataAbsolutePath);
        if (!folder.exists()) {
            model = new DefaultListModel<String>();
            source.setModel(model);
            sourceLabel.setText(COUNT_LEVELS_DATA_LABEL_TEMPLATE + NOT_EXISTS);
            sourceLabel.setForeground(COLOR_IF_NOT_EXISTS);

            return;
        }

        FileFilterExt ffe = new FileFilterExt("asset", "Assets", true);
        levelsFiles = folder.listFiles(ffe);

        for (int i = 0; i < levelsFiles.length; i++) {
            String name = levelsFiles[i].getName();
            model.add(i, name.replace(".asset", ""));
        }

        source.setModel(model);
        sourceLabel.setText(COUNT_LEVELS_DATA_LABEL_TEMPLATE + model.size());
        sourceLabel.setForeground(COLOR_IF_EXISTS);

        try {
            matchDataCard();
        } catch (IOException ignored) {
        }
    }

    public void matchDataCard() throws IOException {
        folder = new File(workDirectory);
        FileFilterExt ffe = new FileFilterExt("png", "Meta", true);
        File[] cards = folder.listFiles(ffe);
        ffe = new FileFilterExt("meta", "Meta", false);
        File[] cardsMeta = folder.listFiles(ffe);
        cardsFiles = new File[cards.length][2];
        for (int i = 0; i < cards.length; i++) {
            String cardName = cards[i].getName().replaceAll("(?<!^)[.][^.]*$", "");
            int cardMeta = findCardMetaIndexByName(cardName, cardsMeta);
            cardsFiles[i][0] = cards[i];
            cardsFiles[i][1] = cardsMeta[i];
            var a = 1;
        }
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
//        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        for (int i = 0; i < levelsFiles.length; i++) {
            var content = Files.readString(levelsFiles[i].toPath());
            var index = Files.readString(levelsFiles[i].toPath()).indexOf("m_ObjectHideFlags");
            content = content.substring(index);
            var a = mapper.readValue(levelsFiles[i], MonoBehaviour.class);
            var b = 1;
        }
    }

    public int findCardMetaIndexByName(String name, File metas[]) {
        for (int i = 0; i < metas.length; i++) {
            if (metas[i].getName().contains(name)) {
                return i;
            }
        }

        return -1;
    }
}
