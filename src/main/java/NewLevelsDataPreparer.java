import javax.swing.*;
import java.util.ArrayList;

public class NewLevelsDataPreparer extends LevelsDataPreparerBase {
    private JButton saveAllButton;

    public NewLevelsDataPreparer(JList aSourceList, JLabel aCountLabel, JLabel aSelectLabel, JButton aSaveAllButton) {
        super(aSourceList, aCountLabel, aSelectLabel);

        saveAllButton = aSaveAllButton;
        COUNT_ITEMS_LABEL_TEMPLATE = "Count new data: ";
        SELECT_LABEL_TEMPLATE = "Selected new data: ";
    }

    @Override
    public void updateList() {
        super.updateList();

        if (levels == null || levels.size() == 0 && saveAllButton != null) {
            saveAllButton.setEnabled(false);
        }
        else if (saveAllButton != null){
            saveAllButton.setEnabled(true);
        }
    }

}
