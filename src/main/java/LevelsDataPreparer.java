import LevelData.MonoBehaviour;
import Sprite.Sprite;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LevelsDataPreparer {
    public static final String NOT_EXISTS = "FILE FOLDER NOT EXISTS";
    public static final Color COLOR_IF_NOT_EXISTS = Color.RED;
    public static final Color COLOR_IF_EXISTS = Color.BLACK;
    public static final String COUNT_LEVELS_DATA_LABEL_TEMPLATE = "Count levels data: ";
    public static final String MAIN_DIRECTORY = "STD"; // Assets // STD
    public static final String SCRIPTABLE_OBJECT_FOLDER_NAME = "ScriptableObject";
    public static final String LEVEL_DATA_FOLDER_NAME = "LevelData";
    public ArrayList<LevelDataTemp> levels = new ArrayList<LevelDataTemp>();
    public int currentSelect = -1;
    private JList source;
    private JLabel sourceLabel;
    private String workDirectory = "";
    private DefaultListModel<String> model = new DefaultListModel<String>();
    private File folder = null;
    private String levelsDataAbsolutePath = "";
    private File[] levelsFiles = null;
    private File[][] cardsFiles = null;
    private ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    public LevelsDataPreparer(JList aSource, JLabel aLabel, String aWorkDirectory) {
        source = aSource;
        sourceLabel = aLabel;
        workDirectory = aWorkDirectory;
        mapper.findAndRegisterModules();
        prepareLevelsDataFolder();
    }

    private void prepareLevelsDataFolder() {
        int startLevelsDataFolder = workDirectory.indexOf(MAIN_DIRECTORY) + MAIN_DIRECTORY.length();
        levelsDataAbsolutePath =
                workDirectory.substring(0, startLevelsDataFolder)
                        + File.separator
                        + SCRIPTABLE_OBJECT_FOLDER_NAME
                        + File.separator
                        + LEVEL_DATA_FOLDER_NAME;
        folder = new File(levelsDataAbsolutePath);
        if (!folder.exists()) {
            setEmpty();
            return;
        }

        try {
            FileFilterExt ffe = new FileFilterExt("asset", "Assets", true);
            levelsFiles = folder.listFiles(ffe);
            for (int i = 0; i < levelsFiles.length; i++) {
                String name = levelsFiles[i].getName();
                model.add(i, name.replace(".asset", ""));
            }
            source.setModel(model);
            sourceLabel.setText(COUNT_LEVELS_DATA_LABEL_TEMPLATE + model.size());
            sourceLabel.setForeground(COLOR_IF_EXISTS);
            matchDataCard();
        } catch (IOException e) {
            setEmpty();
        }
    }

    private void matchDataCard() throws IOException {
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
            cardsFiles[i][1] = cardsMeta[cardMeta];
        }
        for (int i = 0; i < levelsFiles.length; i++) {
            MonoBehaviour content = mapper.readValue(levelsFiles[i], MonoBehaviour.class);
            File firstCard = findRefCardByGUID(content.monoBehaviour.pictureFirst.guid);
            File secondCard = findRefCardByGUID(content.monoBehaviour.pictureSecond.guid);
            ArrayList<DifferenceTemp> differences = new ArrayList<DifferenceTemp>();
            var data = new LevelDataTemp(firstCard.getAbsolutePath(), secondCard.getAbsolutePath(), differences);
            levels.add(data);
        }
    }

    private int findCardMetaIndexByName(String name, File metas[]) {
        for (int i = 0; i < metas.length; i++) {
            if (metas[i].getName().contains(name)) {
                return i;
            }
        }
        return -1;
    }

    private File findRefCardByGUID(String guid) throws IOException {
        for (int i = 0; i < cardsFiles.length; i++) {
            var content = mapper.readValue(cardsFiles[i][1], Sprite.class);
            var contentGUID = content.guid;
            if (guid.equals(contentGUID)) {
                return cardsFiles[i][0];
            }
        }
        return null;
    }

    private void setEmpty() {
        model = new DefaultListModel<String>();
        source.setModel(model);
        sourceLabel.setText(COUNT_LEVELS_DATA_LABEL_TEMPLATE + NOT_EXISTS);
        sourceLabel.setForeground(COLOR_IF_NOT_EXISTS);
    }
}
