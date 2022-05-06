import LevelDataWrapper.Difference;
import LevelDataWrapper.MonoBehaviour;
import SpriteWrapper.Sprite;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExistLevelsDataPreparer extends LevelsDataPreparerBase {
    public static final String MAIN_DIRECTORY = "STD";
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
            var content = YAMLmapper.readValue(levelsFiles[i], MonoBehaviour.class);
            var firstCard = findRefCardByGUID(content.monoBehaviour.pictureFirst.guid);
            var secondCard = findRefCardByGUID(content.monoBehaviour.pictureSecond.guid);
            var differences = RetrieveDifferences(content.monoBehaviour.differences);
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

    private ArrayList<DifferenceTemp> RetrieveDifferences(List<Difference> differencesWrap) {
        var differences = new ArrayList<DifferenceTemp>();
        for (var d : differencesWrap) {
            var difference = new DifferenceTemp();
            var sizeWrap = d.size;
            difference.size = new Vector2DPixel((int) sizeWrap.x, (int) sizeWrap.y);
            var posWrap = d.position;
            difference.position = new Vector2DPixel((int) posWrap.x, (int) posWrap.y);
            differences.add(difference);
        }

        return differences;
    }

}
