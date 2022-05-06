import javax.swing.*;
import java.util.ArrayList;

public class LevelsDataPreparerBase {
    protected String COUNT_ITEMS_LABEL_TEMPLATE = "";
    protected String SELECT_LABEL_TEMPLATE = "";
    protected String NOT_SELECTED = "NOT SELECTED";
    protected JList sourceList = null;
    protected JLabel countLabel = null;
    protected JLabel selectLabel = null;
    protected ArrayList<LevelDataTemp> levels = null;
    protected int lastIndex = 0;
    protected int selectIndex = -1;

    public LevelsDataPreparerBase(JList aSourceList, JLabel aCountLabel, JLabel aSelectLabel) {
        sourceList = aSourceList;
        countLabel = aCountLabel;
        selectLabel = aSelectLabel;
        clearList();
    }

    public Boolean sourceEquals(JList anotherList) {
        return sourceList.equals(anotherList);
    }

    public int getSelectIndex() {
        return selectIndex;
    }

    public void setLastSelectIndex() {
        var _lastIndex = levels.size() - 1;
        sourceList.setSelectedIndex(_lastIndex);
    }

    public void addItem(LevelDataTemp data) {
        sourceList.setEnabled(true);
        clearSelection();
        lastIndex++;
        data.index = lastIndex;
        levels.add(data);
        updateList();
    }

    public void clearSelection() {
        selectIndex = -1;
        sourceList.clearSelection();
        updateSelectLabel();
    }

    public Boolean hasSelectionItem() {
        return selectIndex >= 0;
    }

    public LevelDataTemp getSelectionItem() {
        return levels.get(selectIndex);
    }

    public LevelDataTemp getItemBySelectIndex(int index) {
        selectIndex = index;
        if (inListBounds(index)) {
            updateSelectLabel();

            return levels.get(selectIndex);
        }

        return null;
    }

    public void removeItemBySelectIndex(int index) {
        selectIndex = index;
        if (inListBounds(index)) {
            levels.remove(selectIndex);
            updateList();
        }
        else {
            clearSelection();
        }
    }

    public ArrayList<LevelDataTemp> getLevels() {
        return levels;
    }

    public void removeItem(LevelDataTemp data) {
        levels.remove(data);
        updateList();
    }

    public void updateList() {
        clearSelection();
        updateModel();
        updateSelectLabel();
        updateCountLabel();
    }

    protected Boolean inListBounds(int index) {
        return index >= 0 && index <= levels.size();
    }

    protected void clearList() {
        levels = new ArrayList<LevelDataTemp>();
        updateList();
    }

    protected void updateCountLabel() {
        countLabel.setText(COUNT_ITEMS_LABEL_TEMPLATE + levels.size());
    }

    protected void updateSelectLabel() {
        String target;
        if (inListBounds(selectIndex)) {
            var l = levels.get(selectIndex);
            target = l.toString();
        }
        else {
            target = NOT_SELECTED;
        }

        selectLabel.setText(SELECT_LABEL_TEMPLATE + target);
    }

    protected void updateModel() {
        var model = new DefaultListModel<String>();
        if (levels.size() == 0) {
            sourceList.setEnabled(false);
            sourceList.setModel(model);

            return;
        }

        sourceList.setEnabled(true);
        for (var l : levels) {
            model.addElement(l.toString());
        }
        sourceList.setModel(model);
    }

}
