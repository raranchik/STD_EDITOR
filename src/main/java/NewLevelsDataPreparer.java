import javax.swing.*;
import java.util.ArrayList;

public class NewLevelsDataPreparer extends LevelsDataPreparerBase {
    public NewLevelsDataPreparer(JList aSourceList, JLabel aCountLabel, JLabel aSelectLabel) {
        super(aSourceList, aCountLabel, aSelectLabel);

        COUNT_ITEMS_LABEL_TEMPLATE = "Count new data: ";
        SELECT_LABEL_TEMPLATE = "Selected new data: ";
    }

}
