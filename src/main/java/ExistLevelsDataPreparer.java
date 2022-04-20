import LevelData.MonoBehaviour;
import Sprite.Sprite;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ExistLevelsDataPreparer extends LevelsDataPreparerBase {
    public static final String NOT_EXISTS = "data folder not exist";
    public static final Color COLOR_IF_NOT_EXISTS = Color.RED;
    public static final Color COLOR_IF_EXISTS = Color.BLACK;

    public static final String MAIN_DIRECTORY = "Assets"; // Assets // STD
    public static final String SCRIPTABLE_OBJECT_FOLDER_NAME = "ScriptableObject";
    public static final String LEVEL_DATA_FOLDER_NAME = "LevelData";

    private String workDirectory = "";
    private File folder = null;
    private String levelsDataAbsolutePath = "";
    private File[] levelsFiles = null;
    private File[][] cardsFiles = null;
    private ObjectMapper YAMLmapper = new ObjectMapper(new YAMLFactory());

    public ExistLevelsDataPreparer(JList aSourceList, JLabel aCountLabel, JLabel aSelectLabel, String aWorkDirectory) {
        super(aSourceList, aCountLabel, aSelectLabel);

        COUNT_ITEMS_LABEL_TEMPLATE = "Count existing data: ";
        SELECT_LABEL_TEMPLATE = "Selected existing data: ";
        workDirectory = aWorkDirectory;
        YAMLmapper.findAndRegisterModules();
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
            // TODO Красить в красный сообщение
            clearList();
            return;
        }

        try {
            FileFilterExt ffe = new FileFilterExt("asset", "Assets", true);
            levelsFiles = folder.listFiles(ffe);
            matchDataCard();
            createLevelsData();
            updateList();
        }
        catch (IOException e) {
            // TODO Красить в красный сообщение
            clearList();
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
    }

    private void createLevelsData() throws IOException {
        for (int i = 0; i < levelsFiles.length; i++) {
            MonoBehaviour content = YAMLmapper.readValue(levelsFiles[i], MonoBehaviour.class);
            File firstCard = findRefCardByGUID(content.monoBehaviour.pictureFirst.guid);
            File secondCard = findRefCardByGUID(content.monoBehaviour.pictureSecond.guid);
            // TODO ADD DIFFERENCES
            ArrayList<DifferenceTemp> differences = new ArrayList<DifferenceTemp>();
            lastIndex++;
            var data = new LevelDataTemp(firstCard.getAbsolutePath(), secondCard.getAbsolutePath(), differences);
            data.index = lastIndex;
            data.isEdit = false;
            data.isNew = false;
            data.fileName = levelsFiles[i].getName();
            levels.add(data);
        }
    }

    private int findCardMetaIndexByName(String name, File[] metas) {
        for (int i = 0; i < metas.length; i++) {
            if (metas[i].getName().contains(name)) {
                return i;
            }
        }

        return -1;
    }

    private File findRefCardByGUID(String guid) throws IOException {
        for (int i = 0; i < cardsFiles.length; i++) {
            var content = YAMLmapper.readValue(cardsFiles[i][1], Sprite.class);
            var contentGUID = content.guid;
            if (guid.equals(contentGUID)) {
                return cardsFiles[i][0];
            }
        }

        return null;
    }

}
