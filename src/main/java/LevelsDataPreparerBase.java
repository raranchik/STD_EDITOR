import javax.swing.*;
import java.util.ArrayList;

public class LevelsDataPreparerBase {
    public String COUNT_ITEMS_LABEL_TEMPLATE = "";
    public String NOT_SELECTED = "NOT SELECTED";
    public JList sourceList = null;
    public JLabel countLabel = null;
    public JLabel selectLabel = null;
    public ArrayList<LevelDataTemp> levels = null;
    public int lastIndex = -1;
    public int selectIndex = -1;

    public LevelsDataPreparerBase(JList aSourceList, JLabel aCountLabel, JLabel aSelectLabel) {
        sourceList = aSourceList;
        countLabel = aCountLabel;
        selectLabel = aSelectLabel;
    }

    public void updateList() {
        var model = new DefaultListModel<String>();
        for (var l : levels) {
            model.addElement(l.toString());
        }
        sourceList.setModel(model);
        countLabel.setText(COUNT_ITEMS_LABEL_TEMPLATE + levels.size());
    }

    public void clear() {
        var model = new DefaultListModel<String>();
        sourceList.setModel(model);
        countLabel.setText(COUNT_ITEMS_LABEL_TEMPLATE + levels.size());
    }

}
