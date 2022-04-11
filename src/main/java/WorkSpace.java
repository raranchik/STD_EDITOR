import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class WorkSpace extends JFrame {
    // region workSpace CONST
    public static final String NOT_SELECTED = "NOT SELECTED";
    public static final String DIRECTORY_CHOOSER_TITLE = "Select work directory";
    public static final String CARD_CHOOSER_TITLE = "Select card";
    public static final String DIRECTORY_LABEL_TEMPLATE = "Work directory: ";
    public static final String FIRST_CARD_LABEL_TEMPLATE = "First card: ";
    public static final String SECOND_CARD_LABEL_TEMPLATE = "Second card: ";
    public final String[][] CARD_CHOOSER_FILTERS = {
            {"png", "PNG files (*.png)"},
            {"jpeg" , "JPEG files (*.jpeg)"}
    };
    // endregion

    // region WorkSpace VAR
    private JFileChooser directoryChooser;
    private String currentDirectoryAbsolutePath = "";
    private JFileChooser cardChooser;
    private String firstCard = "";
    private String firstCardAbsolutePath = "";
    private Image firstCardImage = null;
    private String secondCard = "";
    private String secondCardAbsolutePath = "";
    // endregion

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton selectWorkDirectory;
    private JPanel body;
    private JPanel levelsDataView;
    private JLabel selectedExistingLevelData;
    private JScrollPane scrollPane1;
    private JList listExistingLevelsData;
    private JScrollPane cardView;
    private JLabel currentWorkCard;
    private JPanel levelDataManegment;
    private JButton selectFirstCard;
    private JLabel firstCardLabel;
    private JButton selectSecondCard;
    private JLabel secondCardLabel;
    private JScrollPane levelDataView;
    private JList differencesList;
    private JLabel workDirectoryLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public WorkSpace() {
        initComponents();
        prepareComponents();
        prepareChoosers();
    }

    // region COMPONENTS
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        selectWorkDirectory = new JButton();
        body = new JPanel();
        levelsDataView = new JPanel();
        selectedExistingLevelData = new JLabel();
        scrollPane1 = new JScrollPane();
        listExistingLevelsData = new JList();
        cardView = new JScrollPane();
        currentWorkCard = new JLabel();
        levelDataManegment = new JPanel();
        selectFirstCard = new JButton();
        firstCardLabel = new JLabel();
        selectSecondCard = new JButton();
        secondCardLabel = new JLabel();
        levelDataView = new JScrollPane();
        differencesList = new JList();
        workDirectoryLabel = new JLabel();

        //======== this ========
        setVisible(true);
        setTitle("Spot the difference - EDITOR");
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {1004, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {65, 0, 40, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};

        //---- selectWorkDirectory ----
        selectWorkDirectory.setText("Select work directory");
        selectWorkDirectory.setMaximumSize(new Dimension(200, 40));
        selectWorkDirectory.setPreferredSize(new Dimension(200, 40));
        selectWorkDirectory.setMinimumSize(new Dimension(200, 40));
        selectWorkDirectory.addActionListener(e -> selectWorkDirectory(e));
        contentPane.add(selectWorkDirectory, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== body ========
        {
            body.setLayout(new GridBagLayout());
            ((GridBagLayout)body.getLayout()).columnWidths = new int[] {355, 0, 350, 0};
            ((GridBagLayout)body.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)body.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
            ((GridBagLayout)body.getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

            //======== levelsDataView ========
            {
                levelsDataView.setMaximumSize(new Dimension(350, 2147483647));
                levelsDataView.setMinimumSize(new Dimension(350, 40));
                levelsDataView.setPreferredSize(new Dimension(350, 40));
                levelsDataView.setLayout(new GridBagLayout());
                ((GridBagLayout)levelsDataView.getLayout()).columnWidths = new int[] {0, 0};
                ((GridBagLayout)levelsDataView.getLayout()).rowHeights = new int[] {0, 0, 0};
                ((GridBagLayout)levelsDataView.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
                ((GridBagLayout)levelsDataView.getLayout()).rowWeights = new double[] {0.0, 1.0, 1.0E-4};

                //---- selectedExistingLevelData ----
                selectedExistingLevelData.setText("List of existing levels data: NOT SELECTED");
                selectedExistingLevelData.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
                levelsDataView.add(selectedExistingLevelData, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(0, 10, 5, 0), 0, 0));

                //======== scrollPane1 ========
                {
                    scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                    scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                    scrollPane1.setViewportView(listExistingLevelsData);
                }
                levelsDataView.add(scrollPane1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            body.add(levelsDataView, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));

            //======== cardView ========
            {
                cardView.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                cardView.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                cardView.setViewportView(currentWorkCard);
            }
            body.add(cardView, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));

            //======== levelDataManegment ========
            {
                levelDataManegment.setMaximumSize(new Dimension(350, 2147483647));
                levelDataManegment.setMinimumSize(new Dimension(350, 165));
                levelDataManegment.setPreferredSize(new Dimension(350, 167));
                levelDataManegment.setLayout(new GridBagLayout());
                ((GridBagLayout)levelDataManegment.getLayout()).columnWidths = new int[] {0, 0};
                ((GridBagLayout)levelDataManegment.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0};
                ((GridBagLayout)levelDataManegment.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
                ((GridBagLayout)levelDataManegment.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0, 1.0E-4};

                //---- selectFirstCard ----
                selectFirstCard.setText("Select first card");
                selectFirstCard.setMaximumSize(new Dimension(140, 40));
                selectFirstCard.setMinimumSize(new Dimension(140, 40));
                selectFirstCard.setPreferredSize(new Dimension(140, 40));
                selectFirstCard.setEnabled(false);
                selectFirstCard.addActionListener(e -> selectFirstCard(e));
                levelDataManegment.add(selectFirstCard, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.NONE,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- firstCardLabel ----
                firstCardLabel.setText("First card: NOT SELECTED");
                firstCardLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
                levelDataManegment.add(firstCardLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- selectSecondCard ----
                selectSecondCard.setText("Select second card");
                selectSecondCard.setMaximumSize(new Dimension(140, 40));
                selectSecondCard.setMinimumSize(new Dimension(140, 40));
                selectSecondCard.setPreferredSize(new Dimension(140, 40));
                selectSecondCard.setEnabled(false);
                selectSecondCard.addActionListener(e -> selectSecondCard(e));
                levelDataManegment.add(selectSecondCard, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.NONE,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- secondCardLabel ----
                secondCardLabel.setText("Second card: NOT SELECTED");
                secondCardLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
                levelDataManegment.add(secondCardLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 0), 0, 0));

                //======== levelDataView ========
                {
                    levelDataView.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                    levelDataView.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                    levelDataView.setViewportView(differencesList);
                }
                levelDataManegment.add(levelDataView, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            body.add(levelDataManegment, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        contentPane.add(body, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- workDirectoryLabel ----
        workDirectoryLabel.setText("Work directory: NOT SELECTED");
        workDirectoryLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        contentPane.add(workDirectoryLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
            new Insets(0, 10, 0, 0), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void prepareComponents() {
        prepareMainFrame();
    }

    private void prepareMainFrame() {
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);
    }
    // endregion

    // region CHOOSERS
    private void prepareChoosers() {
        directoryChooser = new JFileChooser();
        prepareWorkDirectoryChooser();

        cardChooser = new JFileChooser();
        prepareCardChooser();
    }

    private void prepareWorkDirectoryChooser() {
        directoryChooser = new JFileChooser();
        directoryChooser.setCurrentDirectory(new File("."));
        directoryChooser.setDialogTitle(DIRECTORY_CHOOSER_TITLE);
        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        directoryChooser.setAcceptAllFileFilterUsed(false);
    }

    private void prepareCardChooser() {
        cardChooser.setDialogTitle(CARD_CHOOSER_TITLE);
        cardChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        cardChooser.setAcceptAllFileFilterUsed(false);
        addCardFileFilter();
    }

    private void addCardFileFilter() {
        for (int i = 0; i < CARD_CHOOSER_FILTERS[0].length; i++) {
            FileFilterExt eff = new FileFilterExt(CARD_CHOOSER_FILTERS[i][0],
                    CARD_CHOOSER_FILTERS[i][1]);
            cardChooser.addChoosableFileFilter(eff);
        }
    }
    // endregion

    // region BUTTONS
    private void selectWorkDirectory(ActionEvent e) {
        if (directoryChooser.showOpenDialog(selectWorkDirectory) == JFileChooser.APPROVE_OPTION) {
            currentDirectoryAbsolutePath = directoryChooser.getSelectedFile().toString();
            workDirectoryLabel.setText(DIRECTORY_LABEL_TEMPLATE + currentDirectoryAbsolutePath);
            selectFirstCard.setEnabled(true);
            cardChooser.setCurrentDirectory(new File(currentDirectoryAbsolutePath));
//            prepareListExistingItems();
        } else {
            selectFirstCard.setEnabled(false);
            workDirectoryLabel.setText(DIRECTORY_LABEL_TEMPLATE + NOT_SELECTED);
            currentDirectoryAbsolutePath = "";
        }

        repaint();
    }

    private void selectFirstCard(ActionEvent e) {
        if (cardChooser.showOpenDialog(cardChooser) == JFileChooser.APPROVE_OPTION) {
            firstCard = cardChooser.getSelectedFile().getName();
            firstCardAbsolutePath = cardChooser.getSelectedFile().toString();
            firstCardLabel.setText(FIRST_CARD_LABEL_TEMPLATE + firstCard);
            try {
                firstCardImage = ImageIO.read(cardChooser.getSelectedFile());
//                drawCard();
                selectSecondCard.setEnabled(true);
//                cardIconLabel.setEnabled(true);
//                addSecondCardChooser();
//                prepareLevelData();
            } catch (IOException ex) {
                //
            }
        }
        else {
            firstCardLabel.setText(FIRST_CARD_LABEL_TEMPLATE + NOT_SELECTED);
            firstCard = "";
            firstCardAbsolutePath = "";
            firstCardImage = null;
        }

        repaint();
    }

    private void selectSecondCard(ActionEvent e) {
        if (cardChooser.showOpenDialog(cardChooser) == JFileChooser.APPROVE_OPTION) {
            secondCard = cardChooser.getSelectedFile().getName();
            secondCardAbsolutePath = cardChooser.getSelectedFile().toString();
            secondCardLabel.setText(SECOND_CARD_LABEL_TEMPLATE + secondCard);
//            try {
//                firstCardImage = ImageIO.read(cardChooser.getSelectedFile());
//                drawCard();
//                selectSecondCard.setEnabled(true);
//                cardIconLabel.setEnabled(true);
//                addSecondCardChooser();
//                prepareLevelData();
//            } catch (IOException ex) {
                //
//            }
        }
        else {
            secondCardLabel.setText(SECOND_CARD_LABEL_TEMPLATE + NOT_SELECTED);
            secondCard = "";
            secondCardAbsolutePath = "";
        }

        repaint();
    }
    // endregion

}
