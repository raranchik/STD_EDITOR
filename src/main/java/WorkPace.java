import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class WorkPace extends JFrame {
    public WorkPace() {
        initComponents();
        prepareComponents();
    }

    // region CONSTANTS
    private final String NOT_SELECTED_MESSAGE = "NOT SELECTED";
    private final String FOLDER_CHOOSER_TITLE = "Select work directory";
    private final String CARDS_CHOOSER_TITLE = "Select work directory";
    private final String CARDS_FOLDER_NAME = "Cards";
    private final String FOLDER_CHOOSER_TEMPLATE = "Work directory: ";
    private final String WARNING_TITLE_IF_NOT_CARDS_FOLDER = "Incorrect cards directory";
    private final String WARNING_MESSAGE_IF_NOT_CARDS_FOLDER = "You have selected the wrong directory." +
            " Select the directory with the cards.";
    public final String[][] CARD_CHOOSER_FILTERS = {
            {"png", "PNG files (*.png)"}
    };
    // endregion

    // region VARIABLES
    private JFileChooser folderChooser = new JFileChooser();
    private JFileChooser cardsChooser = new JFileChooser();

    private String folderAbsPath = "";
    public String getFolderAbsPath() {
        return folderAbsPath;
    }
    // endregion

    // region COMPONENTS
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        workDirectoryContainer = new JPanel();
        workDirectoryButton = new JButton();
        workDerectoryLabel = new JLabel();
        levelsDataContainer = new JPanel();
        splitPane1 = new JSplitPane();
        existLevelDataContainer = new JPanel();
        selectedExistLevelDataLabel = new JLabel();
        scrollListExistLevelData = new JScrollPane();
        listExistLevelData = new JList();
        countExistLevelDataLabel2 = new JLabel();
        newLevelDataContainer = new JPanel();
        selectedNewLevelDataLabel = new JLabel();
        scrollListNewLevelData = new JScrollPane();
        listExistLevelData2 = new JList();
        countNewLevelDataLabel = new JLabel();
        saveData = new JButton();
        cardViewContainer = new JPanel();
        cardViewHeaderContainer = new JPanel();
        switchCardButton = new JButton();
        currentViewCardLabel = new JLabel();
        cardViewScroll = new JScrollPane();
        cardContainer = new JPanel();
        cardViewFooterContainer = new JPanel();
        differencesBorderToolContainer = new JPanel();
        differencesBorderSizeSlider = new JSlider();
        differencesBorderLabel = new JLabel();
        removeAllDifferencesButton = new JButton();
        selectCardAndDifferencesContainer = new JPanel();
        selectCardContainer = new JPanel();
        selectFirstCardContainer = new JPanel();
        selectFirstCardButton = new JButton();
        selectFirstCardLabel = new JLabel();
        selectSecondCardContainer = new JPanel();
        selectSecondCardButton = new JButton();
        label1 = new JLabel();
        scrollListDifferences = new JScrollPane();
        listDifferences = new JList();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Spot the Difference - EDITOR");
        setName("mainFrame");
        setVisible(true);
        var contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {255, 0, 250, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {105, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};

        //======== workDirectoryContainer ========
        {
            workDirectoryContainer.setLayout(new GridBagLayout());
            ((GridBagLayout)workDirectoryContainer.getLayout()).rowHeights = new int[] {45, 40};
            ((GridBagLayout)workDirectoryContainer.getLayout()).columnWeights = new double[] {1.0};

            //---- workDirectoryButton ----
            workDirectoryButton.setText("Select work directory");
            workDirectoryButton.setPreferredSize(new Dimension(200, 40));
            workDirectoryButton.addActionListener(e -> selectWorkDirectory(e));
            workDirectoryContainer.add(workDirectoryButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- workDerectoryLabel ----
            workDerectoryLabel.setText("Work directory: NOT SELECTED");
            workDirectoryContainer.add(workDerectoryLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        contentPane.add(workDirectoryContainer, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(10, 10, 15, 15), 0, 0));

        //======== levelsDataContainer ========
        {
            levelsDataContainer.setBorder(new TitledBorder(null, "Levels data", TitledBorder.LEFT, TitledBorder.TOP, null, Color.black));
            levelsDataContainer.setLayout(new GridBagLayout());
            ((GridBagLayout)levelsDataContainer.getLayout()).columnWeights = new double[] {1.0};
            ((GridBagLayout)levelsDataContainer.getLayout()).rowWeights = new double[] {1.0};

            //======== splitPane1 ========
            {
                splitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
                splitPane1.setResizeWeight(0.5);
                splitPane1.setBorder(null);

                //======== existLevelDataContainer ========
                {
                    existLevelDataContainer.setLayout(new GridBagLayout());
                    ((GridBagLayout)existLevelDataContainer.getLayout()).rowHeights = new int[] {45, 0, 40};
                    ((GridBagLayout)existLevelDataContainer.getLayout()).columnWeights = new double[] {1.0};
                    ((GridBagLayout)existLevelDataContainer.getLayout()).rowWeights = new double[] {0.0, 1.0, 0.0};

                    //---- selectedExistLevelDataLabel ----
                    selectedExistLevelDataLabel.setText("Selected existing data: NOT SELECTED");
                    selectedExistLevelDataLabel.setMaximumSize(new Dimension(24, 40));
                    selectedExistLevelDataLabel.setMinimumSize(new Dimension(24, 40));
                    selectedExistLevelDataLabel.setPreferredSize(new Dimension(24, 40));
                    existLevelDataContainer.add(selectedExistLevelDataLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //======== scrollListExistLevelData ========
                    {
                        scrollListExistLevelData.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                        scrollListExistLevelData.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                        //---- listExistLevelData ----
                        listExistLevelData.setEnabled(false);
                        listExistLevelData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        listExistLevelData.addListSelectionListener(e -> selectExistData(e));
                        scrollListExistLevelData.setViewportView(listExistLevelData);
                    }
                    existLevelDataContainer.add(scrollListExistLevelData, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //---- countExistLevelDataLabel2 ----
                    countExistLevelDataLabel2.setText("Count existing data: 0");
                    countExistLevelDataLabel2.setMaximumSize(new Dimension(24, 40));
                    countExistLevelDataLabel2.setMinimumSize(new Dimension(24, 40));
                    countExistLevelDataLabel2.setPreferredSize(new Dimension(24, 40));
                    existLevelDataContainer.add(countExistLevelDataLabel2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                splitPane1.setTopComponent(existLevelDataContainer);

                //======== newLevelDataContainer ========
                {
                    newLevelDataContainer.setLayout(new GridBagLayout());
                    ((GridBagLayout)newLevelDataContainer.getLayout()).rowHeights = new int[] {45, 0, 45, 40};
                    ((GridBagLayout)newLevelDataContainer.getLayout()).columnWeights = new double[] {1.0};
                    ((GridBagLayout)newLevelDataContainer.getLayout()).rowWeights = new double[] {0.0, 1.0, 0.0, 0.0};

                    //---- selectedNewLevelDataLabel ----
                    selectedNewLevelDataLabel.setText("New level data: NOT SELECTED");
                    selectedNewLevelDataLabel.setMaximumSize(new Dimension(24, 40));
                    selectedNewLevelDataLabel.setMinimumSize(new Dimension(24, 40));
                    selectedNewLevelDataLabel.setPreferredSize(new Dimension(24, 40));
                    newLevelDataContainer.add(selectedNewLevelDataLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //======== scrollListNewLevelData ========
                    {
                        scrollListNewLevelData.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                        scrollListNewLevelData.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                        //---- listExistLevelData2 ----
                        listExistLevelData2.setEnabled(false);
                        listExistLevelData2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        listExistLevelData2.addListSelectionListener(e -> selectNewData(e));
                        scrollListNewLevelData.setViewportView(listExistLevelData2);
                    }
                    newLevelDataContainer.add(scrollListNewLevelData, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //---- countNewLevelDataLabel ----
                    countNewLevelDataLabel.setText("Count new data: 0");
                    countNewLevelDataLabel.setMaximumSize(new Dimension(24, 40));
                    countNewLevelDataLabel.setMinimumSize(new Dimension(24, 40));
                    countNewLevelDataLabel.setPreferredSize(new Dimension(24, 40));
                    newLevelDataContainer.add(countNewLevelDataLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //---- saveData ----
                    saveData.setText("Save new data");
                    saveData.setMaximumSize(new Dimension(200, 40));
                    saveData.setMinimumSize(new Dimension(200, 40));
                    saveData.setPreferredSize(new Dimension(200, 40));
                    saveData.setEnabled(false);
                    saveData.addActionListener(e -> saveData(e));
                    newLevelDataContainer.add(saveData, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                splitPane1.setBottomComponent(newLevelDataContainer);
            }
            levelsDataContainer.add(splitPane1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 4, 0, 4), 0, 0));
        }
        contentPane.add(levelsDataContainer, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 10, 5, 5), 0, 0));

        //======== cardViewContainer ========
        {
            cardViewContainer.setBorder(new TitledBorder(null, "Card view", TitledBorder.LEFT, TitledBorder.TOP, null, Color.black));
            cardViewContainer.setLayout(new GridBagLayout());
            ((GridBagLayout)cardViewContainer.getLayout()).rowHeights = new int[] {55, 55, 0, 50};
            ((GridBagLayout)cardViewContainer.getLayout()).columnWeights = new double[] {1.0};
            ((GridBagLayout)cardViewContainer.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0, 0.0};

            //======== cardViewHeaderContainer ========
            {
                cardViewHeaderContainer.setLayout(new GridBagLayout());
                ((GridBagLayout)cardViewHeaderContainer.getLayout()).columnWidths = new int[] {0, 0};
                ((GridBagLayout)cardViewHeaderContainer.getLayout()).rowHeights = new int[] {0, 0};
                ((GridBagLayout)cardViewHeaderContainer.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                ((GridBagLayout)cardViewHeaderContainer.getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

                //---- switchCardButton ----
                switchCardButton.setText("Switch card");
                switchCardButton.setMaximumSize(new Dimension(200, 40));
                switchCardButton.setMinimumSize(new Dimension(200, 40));
                switchCardButton.setPreferredSize(new Dimension(200, 40));
                switchCardButton.setEnabled(false);
                switchCardButton.addActionListener(e -> switchCard(e));
                cardViewHeaderContainer.add(switchCardButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.NONE,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            cardViewContainer.add(cardViewHeaderContainer, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 4, 5, 4), 0, 0));

            //---- currentViewCardLabel ----
            currentViewCardLabel.setText("Current card: NOT SELECTED");
            currentViewCardLabel.setMaximumSize(new Dimension(24, 40));
            currentViewCardLabel.setMinimumSize(new Dimension(24, 40));
            currentViewCardLabel.setPreferredSize(new Dimension(24, 40));
            cardViewContainer.add(currentViewCardLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //======== cardViewScroll ========
            {
                cardViewScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                cardViewScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                //======== cardContainer ========
                {
                    cardContainer.setBorder(null);
                    cardContainer.setLayout(new GridBagLayout());
                }
                cardViewScroll.setViewportView(cardContainer);
            }
            cardViewContainer.add(cardViewScroll, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //======== cardViewFooterContainer ========
            {
                cardViewFooterContainer.setLayout(new GridBagLayout());
                ((GridBagLayout)cardViewFooterContainer.getLayout()).columnWidths = new int[] {0, 0, 0};
                ((GridBagLayout)cardViewFooterContainer.getLayout()).rowHeights = new int[] {50, 0};
                ((GridBagLayout)cardViewFooterContainer.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                ((GridBagLayout)cardViewFooterContainer.getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

                //======== differencesBorderToolContainer ========
                {
                    differencesBorderToolContainer.setLayout(new GridBagLayout());

                    //---- differencesBorderSizeSlider ----
                    differencesBorderSizeSlider.setPaintLabels(true);
                    differencesBorderSizeSlider.setPaintTicks(true);
                    differencesBorderSizeSlider.setSnapToTicks(true);
                    differencesBorderSizeSlider.setMaximum(15);
                    differencesBorderSizeSlider.setValue(5);
                    differencesBorderSizeSlider.setMajorTickSpacing(5);
                    differencesBorderSizeSlider.setMinorTickSpacing(1);
                    differencesBorderSizeSlider.setEnabled(false);
                    differencesBorderSizeSlider.addPropertyChangeListener(e -> borderSizeUpdate(e));
                    differencesBorderToolContainer.add(differencesBorderSizeSlider, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //---- differencesBorderLabel ----
                    differencesBorderLabel.setText("Differences bordere size");
                    differencesBorderToolContainer.add(differencesBorderLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                cardViewFooterContainer.add(differencesBorderToolContainer, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- removeAllDifferencesButton ----
                removeAllDifferencesButton.setText("Remove differences");
                removeAllDifferencesButton.setMaximumSize(new Dimension(200, 40));
                removeAllDifferencesButton.setMinimumSize(new Dimension(200, 40));
                removeAllDifferencesButton.setPreferredSize(new Dimension(200, 40));
                removeAllDifferencesButton.setEnabled(false);
                removeAllDifferencesButton.addActionListener(e -> removeAllDifferences(e));
                cardViewFooterContainer.add(removeAllDifferencesButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.NONE,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            cardViewContainer.add(cardViewFooterContainer, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        contentPane.add(cardViewContainer, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== selectCardAndDifferencesContainer ========
        {
            selectCardAndDifferencesContainer.setBorder(new TitledBorder(null, "Select card and list differences", TitledBorder.LEFT, TitledBorder.TOP, null, Color.black));
            selectCardAndDifferencesContainer.setLayout(new GridBagLayout());
            ((GridBagLayout)selectCardAndDifferencesContainer.getLayout()).columnWidths = new int[] {0, 0};
            ((GridBagLayout)selectCardAndDifferencesContainer.getLayout()).rowHeights = new int[] {205, 0, 0};
            ((GridBagLayout)selectCardAndDifferencesContainer.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
            ((GridBagLayout)selectCardAndDifferencesContainer.getLayout()).rowWeights = new double[] {0.0, 1.0, 1.0E-4};

            //======== selectCardContainer ========
            {
                selectCardContainer.setLayout(new GridBagLayout());
                ((GridBagLayout)selectCardContainer.getLayout()).columnWidths = new int[] {0, 0};
                ((GridBagLayout)selectCardContainer.getLayout()).rowHeights = new int[] {0, 0, 0};
                ((GridBagLayout)selectCardContainer.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
                ((GridBagLayout)selectCardContainer.getLayout()).rowWeights = new double[] {1.0, 1.0, 1.0E-4};

                //======== selectFirstCardContainer ========
                {
                    selectFirstCardContainer.setLayout(new GridBagLayout());
                    ((GridBagLayout)selectFirstCardContainer.getLayout()).columnWidths = new int[] {0, 0};
                    ((GridBagLayout)selectFirstCardContainer.getLayout()).rowHeights = new int[] {0, 0, 0};
                    ((GridBagLayout)selectFirstCardContainer.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
                    ((GridBagLayout)selectFirstCardContainer.getLayout()).rowWeights = new double[] {1.0, 1.0, 1.0E-4};

                    //---- selectFirstCardButton ----
                    selectFirstCardButton.setText("Select first card");
                    selectFirstCardButton.setPreferredSize(new Dimension(200, 40));
                    selectFirstCardButton.setMinimumSize(new Dimension(200, 40));
                    selectFirstCardButton.setMaximumSize(new Dimension(200, 40));
                    selectFirstCardButton.setEnabled(false);
                    selectFirstCardButton.addActionListener(e -> selectFirstCard(e));
                    selectFirstCardContainer.add(selectFirstCardButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //---- selectFirstCardLabel ----
                    selectFirstCardLabel.setText("First card: NOT SELECTED");
                    selectFirstCardLabel.setMaximumSize(new Dimension(24, 40));
                    selectFirstCardLabel.setMinimumSize(new Dimension(24, 40));
                    selectFirstCardLabel.setPreferredSize(new Dimension(24, 40));
                    selectFirstCardContainer.add(selectFirstCardLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                selectCardContainer.add(selectFirstCardContainer, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 4, 5, 4), 0, 0));

                //======== selectSecondCardContainer ========
                {
                    selectSecondCardContainer.setLayout(new GridBagLayout());
                    ((GridBagLayout)selectSecondCardContainer.getLayout()).columnWidths = new int[] {0, 0};
                    ((GridBagLayout)selectSecondCardContainer.getLayout()).rowHeights = new int[] {0, 0, 0};
                    ((GridBagLayout)selectSecondCardContainer.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
                    ((GridBagLayout)selectSecondCardContainer.getLayout()).rowWeights = new double[] {1.0, 1.0, 1.0E-4};

                    //---- selectSecondCardButton ----
                    selectSecondCardButton.setText("Select second card");
                    selectSecondCardButton.setMaximumSize(new Dimension(200, 40));
                    selectSecondCardButton.setMinimumSize(new Dimension(200, 40));
                    selectSecondCardButton.setPreferredSize(new Dimension(200, 40));
                    selectSecondCardButton.setEnabled(false);
                    selectSecondCardButton.addActionListener(e -> selectSecondCard(e));
                    selectSecondCardContainer.add(selectSecondCardButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //---- label1 ----
                    label1.setText("Second card: NOT SELECTED");
                    label1.setMaximumSize(new Dimension(24, 40));
                    label1.setMinimumSize(new Dimension(24, 40));
                    label1.setPreferredSize(new Dimension(24, 40));
                    selectSecondCardContainer.add(label1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                selectCardContainer.add(selectSecondCardContainer, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 4, 0, 4), 0, 0));
            }
            selectCardAndDifferencesContainer.add(selectCardContainer, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //======== scrollListDifferences ========
            {
                scrollListDifferences.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrollListDifferences.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                //---- listDifferences ----
                listDifferences.setEnabled(false);
                listDifferences.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                scrollListDifferences.setViewportView(listDifferences);
            }
            selectCardAndDifferencesContainer.add(scrollListDifferences, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 4, 0, 4), 0, 0));
        }
        contentPane.add(selectCardAndDifferencesContainer, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 10), 0, 0));
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void prepareComponents() {
        prepareMainFrame();
        prepareChoosers();
    }

    private void prepareMainFrame() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (size.width * 0.75f), (int) (size.height * 0.85f));
        setLocationRelativeTo(null);
    }

    private void prepareChoosers() {
        folderChooser.setCurrentDirectory(new File("."));
        folderChooser.setDialogTitle(FOLDER_CHOOSER_TITLE);
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        folderChooser.setAcceptAllFileFilterUsed(false);

        cardsChooser.setDialogTitle(CARDS_CHOOSER_TITLE);
        cardsChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        cardsChooser.setAcceptAllFileFilterUsed(false);
        for (int i = 0; i < CARD_CHOOSER_FILTERS[0].length; i++) {
            FileChooserFilterExt eff = new FileChooserFilterExt(CARD_CHOOSER_FILTERS[i][0],
                    CARD_CHOOSER_FILTERS[i][1]);
            cardsChooser.addChoosableFileFilter(eff);
        }
    }
    // endregion

    // region LISTS
    private void selectExistData(ListSelectionEvent e) {
        // TODO add your code here
    }

    private void selectNewData(ListSelectionEvent e) {
        // TODO add your code here
    }
    // endregion

    // region BUTTONS
    private void selectWorkDirectory(ActionEvent e) {
        if (folderChooser.showOpenDialog(workDirectoryButton) == JFileChooser.APPROVE_OPTION) {
            if (!folderChooser.getSelectedFile().toString().endsWith(CARDS_FOLDER_NAME)) {
                showError(this, WARNING_TITLE_IF_NOT_CARDS_FOLDER, WARNING_MESSAGE_IF_NOT_CARDS_FOLDER);
                disableComponentsFolderChooser();

                return;
            }
            enableComponentsFolderChooser();
        } else {
            disableComponentsFolderChooser();
        }

        repaint();
    }

    private void enableComponentsFolderChooser() {
        folderAbsPath = folderChooser.getSelectedFile().toString();
        updateLabel(workDerectoryLabel, FOLDER_CHOOSER_TEMPLATE, folderAbsPath);
        selectFirstCardButton.setEnabled(true);
        cardsChooser.setCurrentDirectory(new File(folderAbsPath));
        loadExistLevelData(true);
    }

    private void disableComponentsFolderChooser() {
        folderAbsPath = "";
        updateLabel(workDerectoryLabel, FOLDER_CHOOSER_TEMPLATE, NOT_SELECTED_MESSAGE);
        loadExistLevelData(false);
        disableComponentsFirstCard();
        disableComponentsSecondCard();
    }

    private void selectFirstCard(ActionEvent e) {
    }

    private void enableComponentsFirstCard() {
    }

    private void disableComponentsFirstCard() {
    }

    private void selectSecondCard(ActionEvent e) {
    }

    private void enableComponentsSecondCard() {
    }

    private void disableComponentsSecondCard() {
    }

    private void switchCard(ActionEvent e) {
        // TODO add your code here
    }

    private void saveData(ActionEvent e) {
        // TODO add your code here
    }

    private void removeAllDifferences(ActionEvent e) {
        // TODO add your code here
    }
    // endregion

    // region SLIDERS
    private void borderSizeUpdate(PropertyChangeEvent e) {
        // TODO add your code here
    }
    // endregion

    private void showError(Component parent, String title, String message) {
        JOptionPane.showMessageDialog(parent, message,
                title, JOptionPane.ERROR_MESSAGE);
    }

    private void updateLabel(JLabel label, String template, String message) {
        label.setText(template + message);
    }

    private void loadExistLevelData(boolean clear) {
        DefaultListModel<String> model = new DefaultListModel<String>();
        if (!clear) {
            listExistLevelData.setModel(model);
            return;
        }

        levelsPreparer = new LevelsDataPreparer(listExistingLevelsData, countLevelsDataLabel, currentDirectoryAbsolutePath);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel workDirectoryContainer;
    private JButton workDirectoryButton;
    private JLabel workDerectoryLabel;
    private JPanel levelsDataContainer;
    private JSplitPane splitPane1;
    private JPanel existLevelDataContainer;
    private JLabel selectedExistLevelDataLabel;
    private JScrollPane scrollListExistLevelData;
    private JList listExistLevelData;
    private JLabel countExistLevelDataLabel2;
    private JPanel newLevelDataContainer;
    private JLabel selectedNewLevelDataLabel;
    private JScrollPane scrollListNewLevelData;
    private JList listExistLevelData2;
    private JLabel countNewLevelDataLabel;
    private JButton saveData;
    private JPanel cardViewContainer;
    private JPanel cardViewHeaderContainer;
    private JButton switchCardButton;
    private JLabel currentViewCardLabel;
    private JScrollPane cardViewScroll;
    private JPanel cardContainer;
    private JPanel cardViewFooterContainer;
    private JPanel differencesBorderToolContainer;
    private JSlider differencesBorderSizeSlider;
    private JLabel differencesBorderLabel;
    private JButton removeAllDifferencesButton;
    private JPanel selectCardAndDifferencesContainer;
    private JPanel selectCardContainer;
    private JPanel selectFirstCardContainer;
    private JButton selectFirstCardButton;
    private JLabel selectFirstCardLabel;
    private JPanel selectSecondCardContainer;
    private JButton selectSecondCardButton;
    private JLabel label1;
    private JScrollPane scrollListDifferences;
    private JList listDifferences;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
