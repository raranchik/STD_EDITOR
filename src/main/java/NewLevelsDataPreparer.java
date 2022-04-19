import javax.swing.*;
import java.util.ArrayList;

public class NewLevelsDataPreparer extends LevelsDataPreparerBase {
    private final String COUNT_NEW_DATA_TEMPLATE = "Count new data: ";

    public NewLevelsDataPreparer(JList aListSource, JLabel aCountLabel) {
        COUNT_ITEMS_LABEL_TEMPLATE = "Count new data: ";
        sourceList = aListSource;
        sourceList.setModel(new DefaultListModel());
        sourceList.clearSelection();
        countLabel = aCountLabel;
        levels = new ArrayList<LevelDataTemp>();
    }

    public void addItem(String firstImagePath, String secondImagePath, ArrayList<DifferenceTemp> differences) {
        lastIndex++;
        var level = new LevelDataTemp(firstImagePath, secondImagePath, differences, lastIndex);
        levels.add(level);
        updateList();
    }

    public void updateList() {
        var model = new DefaultListModel<String>();
        for (var l : newLevels) {
            model.addElement(l.toString());
        }
        listSource.setModel(model);
        countLabel.setText(COUNT_NEW_DATA_TEMPLATE + newLevels.size());
    }

}
