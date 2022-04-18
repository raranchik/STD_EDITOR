import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class WorkSpace extends JFrame {
    public WorkSpace() {
        initComponents();
        prepareComponents();
    }

    // region CONSTANTS
    public static final String COUNT_DIFFERENCES_TEMPLATE = "Count differences: ";

    private final String NOT_SELECTED_MESSAGE = "NOT SELECTED";

    private final String FOLDER_CHOOSER_TITLE = "Select work directory";
    private final String CARDS_CHOOSER_TITLE = "Select work directory";

    private final String CARDS_FOLDER_NAME = "Cards";

    private final String WORK_DIRECTORY_TEMPLATE = "Work directory: ";
    private final String FIRST_CARD_TEMPLATE = "First card: ";
    private final String SECOND_CARD_TEMPLATE = "Second card: ";
    private final String CURRENT_CARD_TEMPLATE = "Current card: ";
    private final String SELECT_EXIST_DATA_TEMPLATE = "Selected existing data: ";
    private final String COUNT_EXIST_DATA_TEMPLATE = "Count existing data: ";
    private final String SELECT_NEW_DATA_TEMPLATE = "Selected new data: ";
    private final String COUNT_NEW_DATA_TEMPLATE = "Count new data: ";

    private final String WARNING_TITLE_IF_NOT_CARDS_FOLDER = "Incorrect cards directory";
    private final String WARNING_MESSAGE_IF_NOT_CARDS_FOLDER = "You have selected the wrong directory." +
            " Select the directory with the cards.";

    public final String WARNING_TITLE_IF_EQUALS_FILES = "Identical files";
    public final String WARNING_MESSAGE_IF_EQUALS_FILES = "You selected the same image.";

    public final String WARNING_TITLE_IF_SELECT_LEVEL_EMPTY = "Level data empty";
    public final String WARNING_MESSAGE_IF_SELECT_LEVEL_EMPTY = "There are no images for the selected data";

    public final String WARNING_TITLE_IF_EMPTY_SAVE = "Nothing to save";
    public final String WARNING_MESSAGE_IF_EMPTY_SAVE = "Differences are not created.";

    public final String[][] CARD_CHOOSER_FILTERS = {
            {"png", "PNG files (*.png)"}
    };
    // endregion

    // region VARIABLES
    private JFileChooser folderChooser = new JFileChooser();
    private String folderAbsPath = "";
    public String getFolderAbsPath() {
        return folderAbsPath;
    }

    private JFileChooser cardsChooser = new JFileChooser();
    private String fCardAbsPath = "";
    private String fCardFileName = "";
    private BufferedImage fCardImage = null;
    private String sCardAbsPath = "";
    private String sCardFileName = "";
    private BufferedImage sCardImage = null;

    private LevelsDataPreparer lvlsPrep = null;
    private DrawingPanel cardView = null;

    private byte currentViewImage = -1;

    // endregion

    // region COMPONENTS
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        workDirectoryContainer = new JPanel();
        workDirectoryButton = new JButton();
        workDerectoryLabel = new JLabel();
        levelsDataContainer = new JPanel();
        splitExistAndNewLevelDataPane = new JSplitPane();
        existLevelDataContainer = new JPanel();
        selectedExistLevelDataLabel = new JLabel();
        scrollListExistLevelData = new JScrollPane();
        listExistLevelData = new JList();
        countExistLevelDataLabel = new JLabel();
        newLevelDataContainer = new JPanel();
        selectedNewLevelDataLabel = new JLabel();
        scrollListNewLevelData = new JScrollPane();
        listExistLevelData2 = new JList();
        countNewLevelDataLabel = new JLabel();
        saveAllButton = new JButton();
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
        selectSecondCardLabel = new JLabel();
        scrollListDifferences = new JScrollPane();
        listDifferences = new JList();
        countDifferencesLabel = new JLabel();
        saveNewDataButton = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Spot the Difference - EDITOR");
        setName("mainFrame");
        setVisible(true);
        var contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {255, 0, 250, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {105, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 1.0, 1.0E-4};

        //======== workDirectoryContainer ========
        {
            workDirectoryContainer.setLayout(new GridBagLayout());
            ((GridBagLayout)workDirectoryContainer.getLayout()).rowHeights = new int[] {40, 0};
            ((GridBagLayout)workDirectoryContainer.getLayout()).columnWeights = new double[] {1.0};

            //---- workDirectoryButton ----
            workDirectoryButton.setText("Select work directory");
            workDirectoryButton.setPreferredSize(new Dimension(200, 40));
            workDirectoryButton.addActionListener(e -> selectWorkDirectory(e));
            workDirectoryContainer.add(workDirectoryButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));

            //---- workDerectoryLabel ----
            workDerectoryLabel.setText("Work directory: NOT SELECTED");
            workDerectoryLabel.setVerticalAlignment(SwingConstants.TOP);
            workDirectoryContainer.add(workDerectoryLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        contentPane.add(workDirectoryContainer, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== levelsDataContainer ========
        {
            levelsDataContainer.setBorder(new TitledBorder(null, "Levels data", TitledBorder.LEFT, TitledBorder.TOP, null, Color.black));
            levelsDataContainer.setLayout(new GridBagLayout());
            ((GridBagLayout)levelsDataContainer.getLayout()).columnWeights = new double[] {1.0};
            ((GridBagLayout)levelsDataContainer.getLayout()).rowWeights = new double[] {1.0};

            //======== splitExistAndNewLevelDataPane ========
            {
                splitExistAndNewLevelDataPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
                splitExistAndNewLevelDataPane.setResizeWeight(0.5);
                splitExistAndNewLevelDataPane.setBorder(null);

                //======== existLevelDataContainer ========
                {
                    existLevelDataContainer.setLayout(new GridBagLayout());
                    ((GridBagLayout)existLevelDataContainer.getLayout()).columnWeights = new double[] {1.0};
                    ((GridBagLayout)existLevelDataContainer.getLayout()).rowWeights = new double[] {0.0, 1.0, 0.0};

                    //---- selectedExistLevelDataLabel ----
                    selectedExistLevelDataLabel.setText("Selected existing data: NOT SELECTED");
                    selectedExistLevelDataLabel.setMaximumSize(new Dimension(24, 16));
                    selectedExistLevelDataLabel.setMinimumSize(new Dimension(24, 16));
                    selectedExistLevelDataLabel.setPreferredSize(new Dimension(24, 16));
                    existLevelDataContainer.add(selectedExistLevelDataLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(5, 0, 5, 0), 0, 0));

                    //======== scrollListExistLevelData ========
                    {
                        scrollListExistLevelData.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                        scrollListExistLevelData.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                        //---- listExistLevelData ----
                        listExistLevelData.setEnabled(false);
                        listExistLevelData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        listExistLevelData.addListSelectionListener(e -> selectLevelData(e));
                        scrollListExistLevelData.setViewportView(listExistLevelData);
                    }
                    existLevelDataContainer.add(scrollListExistLevelData, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //---- countExistLevelDataLabel ----
                    countExistLevelDataLabel.setText("Count existing data: 0");
                    countExistLevelDataLabel.setMaximumSize(new Dimension(24, 16));
                    countExistLevelDataLabel.setMinimumSize(new Dimension(24, 16));
                    countExistLevelDataLabel.setPreferredSize(new Dimension(24, 16));
                    existLevelDataContainer.add(countExistLevelDataLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));
                }
                splitExistAndNewLevelDataPane.setTopComponent(existLevelDataContainer);

                //======== newLevelDataContainer ========
                {
                    newLevelDataContainer.setLayout(new GridBagLayout());
                    ((GridBagLayout)newLevelDataContainer.getLayout()).rowHeights = new int[] {0, 0, 0, 40};
                    ((GridBagLayout)newLevelDataContainer.getLayout()).columnWeights = new double[] {1.0};
                    ((GridBagLayout)newLevelDataContainer.getLayout()).rowWeights = new double[] {0.0, 1.0, 0.0, 0.0};

                    //---- selectedNewLevelDataLabel ----
                    selectedNewLevelDataLabel.setText("Selected new data: NOT SELECTED");
                    selectedNewLevelDataLabel.setMaximumSize(new Dimension(24, 16));
                    selectedNewLevelDataLabel.setMinimumSize(new Dimension(24, 16));
                    selectedNewLevelDataLabel.setPreferredSize(new Dimension(24, 16));
                    newLevelDataContainer.add(selectedNewLevelDataLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(5, 0, 5, 0), 0, 0));

                    //======== scrollListNewLevelData ========
                    {
                        scrollListNewLevelData.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                        scrollListNewLevelData.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                        //---- listExistLevelData2 ----
                        listExistLevelData2.setEnabled(false);
                        listExistLevelData2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        listExistLevelData2.addListSelectionListener(e -> selectLevelData(e));
                        scrollListNewLevelData.setViewportView(listExistLevelData2);
                    }
                    newLevelDataContainer.add(scrollListNewLevelData, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //---- countNewLevelDataLabel ----
                    countNewLevelDataLabel.setText("Count new data: 0");
                    countNewLevelDataLabel.setMaximumSize(new Dimension(24, 16));
                    countNewLevelDataLabel.setMinimumSize(new Dimension(24, 16));
                    countNewLevelDataLabel.setPreferredSize(new Dimension(24, 16));
                    newLevelDataContainer.add(countNewLevelDataLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //---- saveAllButton ----
                    saveAllButton.setText("Save all");
                    saveAllButton.setMaximumSize(new Dimension(200, 40));
                    saveAllButton.setMinimumSize(new Dimension(200, 40));
                    saveAllButton.setPreferredSize(new Dimension(200, 40));
                    saveAllButton.setEnabled(false);
                    saveAllButton.addActionListener(e -> saveAll(e));
                    newLevelDataContainer.add(saveAllButton, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 5, 0), 0, 0));
                }
                splitExistAndNewLevelDataPane.setBottomComponent(newLevelDataContainer);
            }
            levelsDataContainer.add(splitExistAndNewLevelDataPane, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 4, 0, 4), 0, 0));
        }
        contentPane.add(levelsDataContainer, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(10, 10, 10, 15), 0, 0));

        //======== cardViewContainer ========
        {
            cardViewContainer.setBorder(new TitledBorder(null, "Card view", TitledBorder.LEFT, TitledBorder.TOP, null, Color.black));
            cardViewContainer.setLayout(new GridBagLayout());
            ((GridBagLayout)cardViewContainer.getLayout()).rowHeights = new int[] {55, 0, 50};
            ((GridBagLayout)cardViewContainer.getLayout()).columnWeights = new double[] {1.0};
            ((GridBagLayout)cardViewContainer.getLayout()).rowWeights = new double[] {0.0, 1.0, 0.0};

            //======== cardViewHeaderContainer ========
            {
                cardViewHeaderContainer.setLayout(new GridBagLayout());
                ((GridBagLayout)cardViewHeaderContainer.getLayout()).columnWidths = new int[] {0, 0};
                ((GridBagLayout)cardViewHeaderContainer.getLayout()).rowHeights = new int[] {0, 0, 0};
                ((GridBagLayout)cardViewHeaderContainer.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                ((GridBagLayout)cardViewHeaderContainer.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

                //---- switchCardButton ----
                switchCardButton.setText("Switch card");
                switchCardButton.setMaximumSize(new Dimension(200, 40));
                switchCardButton.setMinimumSize(new Dimension(200, 40));
                switchCardButton.setPreferredSize(new Dimension(200, 40));
                switchCardButton.setEnabled(false);
                switchCardButton.addActionListener(e -> switchCard(e));
                cardViewHeaderContainer.add(switchCardButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.NONE,
                    new Insets(10, 0, 0, 0), 0, 0));

                //---- currentViewCardLabel ----
                currentViewCardLabel.setText("Current card: NOT SELECTED");
                currentViewCardLabel.setMaximumSize(new Dimension(24, 16));
                currentViewCardLabel.setMinimumSize(new Dimension(24, 16));
                currentViewCardLabel.setPreferredSize(new Dimension(24, 16));
                currentViewCardLabel.setVerticalAlignment(SwingConstants.TOP);
                cardViewHeaderContainer.add(currentViewCardLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            cardViewContainer.add(cardViewHeaderContainer, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 4, 5, 4), 0, 0));

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
            cardViewContainer.add(cardViewScroll, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //======== cardViewFooterContainer ========
            {
                cardViewFooterContainer.setLayout(new GridBagLayout());
                ((GridBagLayout)cardViewFooterContainer.getLayout()).columnWidths = new int[] {0, 0, 0};
                ((GridBagLayout)cardViewFooterContainer.getLayout()).rowHeights = new int[] {0, 0};
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
                    differencesBorderSizeSlider.addChangeListener(e -> borderSizeUpdate(e));
                    differencesBorderToolContainer.add(differencesBorderSizeSlider, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //---- differencesBorderLabel ----
                    differencesBorderLabel.setText("Differences bordere size");
                    differencesBorderToolContainer.add(differencesBorderLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 10, 0), 0, 0));
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
            cardViewContainer.add(cardViewFooterContainer, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        contentPane.add(cardViewContainer, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(10, 10, 10, 15), 0, 0));

        //======== selectCardAndDifferencesContainer ========
        {
            selectCardAndDifferencesContainer.setBorder(new TitledBorder(null, "Select card and list differences", TitledBorder.LEFT, TitledBorder.TOP, null, Color.black));
            selectCardAndDifferencesContainer.setLayout(new GridBagLayout());
            ((GridBagLayout)selectCardAndDifferencesContainer.getLayout()).columnWidths = new int[] {0, 0};
            ((GridBagLayout)selectCardAndDifferencesContainer.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0};
            ((GridBagLayout)selectCardAndDifferencesContainer.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
            ((GridBagLayout)selectCardAndDifferencesContainer.getLayout()).rowWeights = new double[] {0.0, 1.0, 0.0, 0.0, 1.0E-4};

            //======== selectCardContainer ========
            {
                selectCardContainer.setLayout(new GridBagLayout());
                ((GridBagLayout)selectCardContainer.getLayout()).columnWidths = new int[] {0, 0};
                ((GridBagLayout)selectCardContainer.getLayout()).rowHeights = new int[] {0, 0, 0};
                ((GridBagLayout)selectCardContainer.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
                ((GridBagLayout)selectCardContainer.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

                //======== selectFirstCardContainer ========
                {
                    selectFirstCardContainer.setLayout(new GridBagLayout());
                    ((GridBagLayout)selectFirstCardContainer.getLayout()).columnWidths = new int[] {0, 0};
                    ((GridBagLayout)selectFirstCardContainer.getLayout()).rowHeights = new int[] {0, 0, 0};
                    ((GridBagLayout)selectFirstCardContainer.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
                    ((GridBagLayout)selectFirstCardContainer.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

                    //---- selectFirstCardButton ----
                    selectFirstCardButton.setText("Select first card");
                    selectFirstCardButton.setPreferredSize(new Dimension(200, 40));
                    selectFirstCardButton.setMinimumSize(new Dimension(200, 40));
                    selectFirstCardButton.setMaximumSize(new Dimension(200, 40));
                    selectFirstCardButton.setEnabled(false);
                    selectFirstCardButton.addActionListener(e -> selectFirstCard(e));
                    selectFirstCardContainer.add(selectFirstCardButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(10, 0, 5, 0), 0, 0));

                    //---- selectFirstCardLabel ----
                    selectFirstCardLabel.setText("First card: NOT SELECTED");
                    selectFirstCardLabel.setMaximumSize(new Dimension(24, 16));
                    selectFirstCardLabel.setMinimumSize(new Dimension(24, 16));
                    selectFirstCardLabel.setPreferredSize(new Dimension(24, 16));
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
                    ((GridBagLayout)selectSecondCardContainer.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

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

                    //---- selectSecondCardLabel ----
                    selectSecondCardLabel.setText("Second card: NOT SELECTED");
                    selectSecondCardLabel.setMaximumSize(new Dimension(24, 16));
                    selectSecondCardLabel.setMinimumSize(new Dimension(24, 16));
                    selectSecondCardLabel.setPreferredSize(new Dimension(24, 16));
                    selectSecondCardContainer.add(selectSecondCardLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
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
                new Insets(0, 4, 5, 4), 0, 0));

            //---- countDifferencesLabel ----
            countDifferencesLabel.setText("Count differences: 0");
            selectCardAndDifferencesContainer.add(countDifferencesLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- saveNewDataButton ----
            saveNewDataButton.setText("Save new data");
            saveNewDataButton.setMaximumSize(new Dimension(200, 40));
            saveNewDataButton.setMinimumSize(new Dimension(200, 40));
            saveNewDataButton.setPreferredSize(new Dimension(200, 40));
            saveNewDataButton.setEnabled(false);
            saveNewDataButton.addActionListener(e -> saveNewData(e));
            selectCardAndDifferencesContainer.add(saveNewDataButton, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 10, 0), 0, 0));
        }
        contentPane.add(selectCardAndDifferencesContainer, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(10, 10, 10, 10), 0, 0));
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void prepareComponents() {
        prepareMainFrame();
        prepareChoosers();
    }
    // endregion

    // region LISTS
    private void selectLevelData(ListSelectionEvent e) {
//        if (!e.getValueIsAdjusting()) {
//            try {
//                var source = (JList) e.getSource();
//                int i = source.getSelectedIndex();
//                if (levelsPreparer.currentSelect == i || i == -1) {
//                    return;
//                }
//                levelsPreparer.currentSelect = i;
//                var l = levelsPreparer.levels.get(i);
//                firstCardAbsolutePath = l.firstCardAbsolutePath;
//                secondCardAbsolutePath = l.secondCardAbsolutePath;
//                if (firstCardAbsolutePath.isEmpty() || secondCardAbsolutePath.isEmpty()) {
//                    JOptionPane.showMessageDialog(this, WARNING_MESSAGE_IF_SELECT_LEVEL_EMPTY,
//                            WARNING_TITLE_IF_SELECT_LEVEL_EMPTY, JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//                File file = null;
//                if (!secondCardAbsolutePath.isEmpty()) {
//                    file = new File(secondCardAbsolutePath);
//                    secondCardImage = ImageIO.read(file);
//                    secondCard = file.getName();
//                }
//                else {
//                    disableComponentsForSelectSecondCard();
//                }
//                file = new File(firstCardAbsolutePath);
//                firstCardImage = ImageIO.read(file);
//                firstCard = file.getName();
//                drawCard();
//                enableComponentsForSelectFirstCard(true);
//                enableComponentsForSelectSecondCard(true);
//            }
//            catch (IOException ignored) { }
//        }
    }
    // endregion

    // region BUTTONS
    private void selectWorkDirectory(ActionEvent e) {
        enableComponentsFolderChooser();

//        if (folderChooser.showOpenDialog(workDirectoryButton) == JFileChooser.APPROVE_OPTION) {
//            if (!folderChooser.getSelectedFile().toString().endsWith(CARDS_FOLDER_NAME)) {
//                showError(this, WARNING_TITLE_IF_NOT_CARDS_FOLDER, WARNING_MESSAGE_IF_NOT_CARDS_FOLDER);
//                disableComponentsFolderChooser();
//
//                return;
//            }
//
//            enableComponentsFolderChooser();
//        }
//        else {
//            disableComponentsFolderChooser();
//        }

        repaint();
    }

    private void enableComponentsFolderChooser() {
//        folderAbsPath = folderChooser.getSelectedFile().toString();
        folderAbsPath = "C:\\prj\\spot-the-difference\\Assets\\Sprites\\Cards";
        updateLabel(workDerectoryLabel, WORK_DIRECTORY_TEMPLATE, folderAbsPath);
        selectFirstCardButton.setEnabled(true);
        cardsChooser.setCurrentDirectory(new File(folderAbsPath));
        loadExistLevelData(true);
    }

    private void disableComponentsFolderChooser() {
        folderAbsPath = "";
        updateLabel(workDerectoryLabel, WORK_DIRECTORY_TEMPLATE, NOT_SELECTED_MESSAGE);
        loadExistLevelData(false);
        disableComponentsFirstCard();
        disableComponentsSecondCard();
    }

    private void selectFirstCard(ActionEvent e) {
        if (cardsChooser.showOpenDialog(cardsChooser) == JFileChooser.APPROVE_OPTION) {
            if (isIdenticalFiles(sCardFileName, cardsChooser.getSelectedFile().getName())) {
                disableComponentsFirstCard();
                disableComponentsSecondCard();

                return;
            }

            if (!listExistLevelData.isSelectionEmpty()) {
                listExistLevelData.clearSelection();
            }

            if (sCardFileName != null) {
                disableComponentsFirstCard();
                disableComponentsSecondCard();
            }

            enableComponentsFirstCard(false);
        }
        else {
            disableComponentsFirstCard();
            disableComponentsSecondCard();
        }

        repaint();
    }

    private void enableComponentsFirstCard(Boolean dontOverride) {
        selectSecondCardButton.setEnabled(true);
        saveNewDataButton.setEnabled(true);
        differencesBorderSizeSlider.setEnabled(true);
        if (sCardImage != null) {
            switchCardButton.setEnabled(true);
        }

        if (dontOverride) {
            updateLabel(currentViewCardLabel, CURRENT_CARD_TEMPLATE, fCardFileName);
            updateLabel(selectFirstCardLabel, FIRST_CARD_TEMPLATE, fCardFileName);

            return;
        }

        fCardFileName = cardsChooser.getSelectedFile().getName();
        fCardAbsPath = cardsChooser.getSelectedFile().toString();
        try {
            fCardImage = ImageIO.read(cardsChooser.getSelectedFile());
            currentViewImage = 1;
            drawCard();
            updateLabel(currentViewCardLabel, CURRENT_CARD_TEMPLATE, fCardFileName);
            updateLabel(selectFirstCardLabel, FIRST_CARD_TEMPLATE, fCardFileName);
        }
        catch (IOException ignored) { }
    }

    private void disableComponentsFirstCard() {
        updateLabel(selectFirstCardLabel, FIRST_CARD_TEMPLATE, NOT_SELECTED_MESSAGE);
        fCardFileName = "";
        fCardAbsPath = "";
        fCardImage = null;
        if (cardView != null) {
            cardContainer.remove(cardView);
            cardView = null;
        }
        switchCardButton.setEnabled(false);
        selectSecondCardButton.setEnabled(false);
        differencesBorderSizeSlider.setEnabled(false);
        saveNewDataButton.setEnabled(false);
        currentViewImage = -1;
    }

    private void selectSecondCard(ActionEvent e) {
        if (cardsChooser.showOpenDialog(cardsChooser) == JFileChooser.APPROVE_OPTION) {
            if (isIdenticalFiles(fCardFileName, cardsChooser.getSelectedFile().getName())) {
                disableComponentsSecondCard();

                return;
            }

            enableComponentsSecondCard(false);
        }
        else {
            disableComponentsSecondCard();
        }

        repaint();
    }

    private void enableComponentsSecondCard(Boolean dontOverride) {
        switchCardButton.setEnabled(true);
        if (dontOverride) {
            updateLabel(selectSecondCardLabel, SECOND_CARD_TEMPLATE, sCardFileName);

            return;
        }

        sCardFileName = cardsChooser.getSelectedFile().getName();
        sCardAbsPath = cardsChooser.getSelectedFile().toString();
        try {
            updateLabel(selectSecondCardLabel, SECOND_CARD_TEMPLATE, sCardFileName);
            sCardImage = ImageIO.read(cardsChooser.getSelectedFile());
        }
        catch (IOException ignored) { }
    }

    private void disableComponentsSecondCard() {
        updateLabel(selectSecondCardLabel, SECOND_CARD_TEMPLATE, NOT_SELECTED_MESSAGE);
        sCardFileName = "";
        sCardAbsPath = "";
        sCardImage = null;
        switchCardButton.setEnabled(false);
        differencesBorderSizeSlider.setEnabled(false);
        if (fCardImage != null && currentViewImage != 0 && cardView != null) {
            currentViewImage = 0;
            cardView.setIcon(new ImageIcon(fCardImage));
            cardView.setSize(fCardImage.getWidth(), fCardImage.getHeight());
            updateLabel(currentViewCardLabel, CURRENT_CARD_TEMPLATE, fCardFileName);
        }
    }

    private void switchCard(ActionEvent e) {
        if (cardView == null) {
            return;
        }

        ImageIcon icon;
        BufferedImage source;
        String currentCardName;
        if (currentViewImage == 1) {
            source = sCardImage;
            icon = new ImageIcon(sCardImage);
            currentViewImage = 0;
            currentCardName = sCardFileName;
        }
        else if (currentViewImage == 0) {
            source = fCardImage;
            icon = new ImageIcon(fCardImage);
            currentViewImage = 1;
            currentCardName = fCardFileName;
        }
        else {
            return;
        }

        cardView.setIcon(icon);
        cardView.setSize(source.getWidth(), source.getHeight());
        updateLabel(currentViewCardLabel, CURRENT_CARD_TEMPLATE, currentCardName);

        repaint();
    }

    private void saveAll(ActionEvent e) {
        // TODO add your code here
    }

    private void saveNewData(ActionEvent e) {
        saveAllButton.setEnabled(true);
    }

    private void removeAllDifferences(ActionEvent e) {
        // TODO add your code here
    }
    // endregion

    // region SLIDERS
    private void borderSizeUpdate(ChangeEvent e) {
        if (cardView == null) {
            return;
        }
        var source = (JSlider) e.getSource();
        var value = (int) source.getValue();
        if (value <= 0) {
            return;
        }
        cardView.setNewBorderSize(value);

        repaint();
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

        lvlsPrep = new LevelsDataPreparer(listExistLevelData, countExistLevelDataLabel, folderAbsPath);
    }

    private boolean isIdenticalFiles(String firstFile, String secondFile) {
        if (firstFile.equals(secondFile) && listExistLevelData.isSelectionEmpty()) {
            showError(this, WARNING_TITLE_IF_EQUALS_FILES, WARNING_MESSAGE_IF_EQUALS_FILES);

            return true;
        }

        return false;
    }

    private void drawCard() {
        if (cardView != null) {
            cardContainer.remove(cardView);
        }

        cardView = new DrawingPanel(listDifferences, countDifferencesLabel);
        DraggableAndResizableComponent.thickness = (int) differencesBorderSizeSlider.getValue();
        cardView.setAlignmentX(0.5F);
        cardContainer.add(
                cardView,
                new GridBagConstraints(
                        0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER,
                        GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0
                )
        );

        ImageIcon icon = new ImageIcon(fCardImage);
        cardView.setIcon(icon);
        cardView.setSize(fCardImage.getWidth(), fCardImage.getHeight());
        saveNewDataButton.setEnabled(true);

        repaint();
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
        for (int i = 0; i < CARD_CHOOSER_FILTERS.length; i++) {
            FileChooserFilterExt eff = new FileChooserFilterExt(CARD_CHOOSER_FILTERS[i][0],
                    CARD_CHOOSER_FILTERS[i][1]);
            cardsChooser.addChoosableFileFilter(eff);
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel workDirectoryContainer;
    private JButton workDirectoryButton;
    private JLabel workDerectoryLabel;
    private JPanel levelsDataContainer;
    private JSplitPane splitExistAndNewLevelDataPane;
    private JPanel existLevelDataContainer;
    private JLabel selectedExistLevelDataLabel;
    private JScrollPane scrollListExistLevelData;
    private JList listExistLevelData;
    private JLabel countExistLevelDataLabel;
    private JPanel newLevelDataContainer;
    private JLabel selectedNewLevelDataLabel;
    private JScrollPane scrollListNewLevelData;
    private JList listExistLevelData2;
    private JLabel countNewLevelDataLabel;
    private JButton saveAllButton;
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
    private JLabel selectSecondCardLabel;
    private JScrollPane scrollListDifferences;
    private JList listDifferences;
    private JLabel countDifferencesLabel;
    private JButton saveNewDataButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
