import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.*;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class WorkSpace extends JFrame {
    // region workSpace CONST
    public static final boolean IS_TEST = true;
    public static final String CARDS_FOLDER_NAME = "Cards";
    public static final String NOT_SELECTED = "NOT SELECTED";
    public static final String DIRECTORY_CHOOSER_TITLE = "Select work directory";
    public static final String CARD_CHOOSER_TITLE = "Select card";
    public static final String DIRECTORY_LABEL_TEMPLATE = "Work directory: ";
    public static final String FIRST_CARD_LABEL_TEMPLATE = "First card: ";
    public static final String SECOND_CARD_LABEL_TEMPLATE = "Second card: ";
    public static final String CURRENT_CARD_LABEL_TEMPLATE = "Current card: ";
    public static final String COUNT_DIFFERENCES_LABEL_TEMPLATE = "Count differences: ";
    public static final String WARNING_TITLE_IF_EQUALS_FILES = "Identical files";
    public static final String WARNING_MESSAGE_IF_EQUALS_FILES = "You selected the same image.";
    public static final String WARNING_TITLE_IF_NOT_CARDS_FOLDER = "Incorrect directory";
    public static final String WARNING_MESSAGE_IF_NOT_CARDS_FOLDER = "You have selected the wrong directory." +
            " Select the directory with the cards.";
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
    private BufferedImage firstCardImage = null;
    private BufferedImage secondCardImage = null;
    private String secondCard = "";
    private String secondCardAbsolutePath = "";
    private DrawingPanel cardIconLabel = null;
    private int currentViewImage = -1;
    // endregion

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton selectWorkDirectory;
    private JPanel body;
    private JPanel levelsDataView;
    private JLabel selectedExistingLevelData;
    private JScrollPane scrollPane1;
    private JList listExistingLevelsData;
    private JLabel countLevelsDataLabel;
    private JPanel dashboard;
    private JPanel dashboardHeader;
    private JButton switchCard;
    private JButton returnToFirstCard;
    private JLabel currentCardLabel;
    private JScrollPane dashboardBody;
    private JPanel cardBody;
    private JPanel dashboardFooter;
    private JLabel diffrenceBorderSizeLabel;
    private JSpinner borderSizeSpinner;
    private JButton button1;
    private JPanel levelDataManegment;
    private JButton selectFirstCard;
    private JLabel firstCardLabel;
    private JButton selectSecondCard;
    private JLabel secondCardLabel;
    private JScrollPane levelDataView;
    private JList differencesList;
    private JLabel countDifferencesLabel;
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
        countLevelsDataLabel = new JLabel();
        dashboard = new JPanel();
        dashboardHeader = new JPanel();
        switchCard = new JButton();
        returnToFirstCard = new JButton();
        currentCardLabel = new JLabel();
        dashboardBody = new JScrollPane();
        cardBody = new JPanel();
        dashboardFooter = new JPanel();
        diffrenceBorderSizeLabel = new JLabel();
        borderSizeSpinner = new JSpinner();
        button1 = new JButton();
        levelDataManegment = new JPanel();
        selectFirstCard = new JButton();
        firstCardLabel = new JLabel();
        selectSecondCard = new JButton();
        secondCardLabel = new JLabel();
        levelDataView = new JScrollPane();
        differencesList = new JList();
        countDifferencesLabel = new JLabel();
        workDirectoryLabel = new JLabel();

        //======== this ========
        setVisible(true);
        setTitle("Spot the difference - EDITOR");
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
                ((GridBagLayout)levelsDataView.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
                ((GridBagLayout)levelsDataView.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
                ((GridBagLayout)levelsDataView.getLayout()).rowWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};

                //---- selectedExistingLevelData ----
                selectedExistingLevelData.setText("List of existing levels data: NOT SELECTED");
                selectedExistingLevelData.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
                levelsDataView.add(selectedExistingLevelData, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 0), 0, 0));

                //======== scrollPane1 ========
                {
                    scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                    scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                    //---- listExistingLevelsData ----
                    listExistingLevelsData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    listExistingLevelsData.setVisibleRowCount(26);
                    scrollPane1.setViewportView(listExistingLevelsData);
                }
                levelsDataView.add(scrollPane1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- countLevelsDataLabel ----
                countLevelsDataLabel.setText("Count levels data: 0");
                countLevelsDataLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
                levelsDataView.add(countLevelsDataLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            body.add(levelsDataView, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 10, 0, 15), 0, 0));

            //======== dashboard ========
            {
                dashboard.setBorder(new TitledBorder(null, "Cards and differences view", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,
                    new Font("Dialog", Font.BOLD, 14)));
                dashboard.setLayout(new GridBagLayout());
                ((GridBagLayout)dashboard.getLayout()).columnWeights = new double[] {1.0};
                ((GridBagLayout)dashboard.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0, 0.0};

                //======== dashboardHeader ========
                {
                    dashboardHeader.setMinimumSize(new Dimension(400, 50));
                    dashboardHeader.setPreferredSize(new Dimension(450, 50));
                    dashboardHeader.setLayout(new GridBagLayout());
                    ((GridBagLayout)dashboardHeader.getLayout()).columnWidths = new int[] {0, 0, 0};
                    ((GridBagLayout)dashboardHeader.getLayout()).rowHeights = new int[] {0, 0};
                    ((GridBagLayout)dashboardHeader.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                    ((GridBagLayout)dashboardHeader.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

                    //---- switchCard ----
                    switchCard.setText("Second card preview");
                    switchCard.setEnabled(false);
                    switchCard.setMaximumSize(new Dimension(170, 40));
                    switchCard.setMinimumSize(new Dimension(170, 40));
                    switchCard.setPreferredSize(new Dimension(170, 40));
                    switchCard.addActionListener(e -> switchCardToSecond(e));
                    dashboardHeader.add(switchCard, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                    //---- returnToFirstCard ----
                    returnToFirstCard.setText("Go to the first card");
                    returnToFirstCard.setMaximumSize(new Dimension(150, 40));
                    returnToFirstCard.setMinimumSize(new Dimension(150, 40));
                    returnToFirstCard.setPreferredSize(new Dimension(150, 40));
                    returnToFirstCard.setEnabled(false);
                    returnToFirstCard.addActionListener(e -> returnToFirstCard(e));
                    dashboardHeader.add(returnToFirstCard, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                dashboard.add(dashboardHeader, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 10, 5, 10), 0, 0));

                //---- currentCardLabel ----
                currentCardLabel.setText("Currrent card:");
                currentCardLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
                dashboard.add(currentCardLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 0), 0, 0));

                //======== dashboardBody ========
                {
                    dashboardBody.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                    dashboardBody.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                    //======== cardBody ========
                    {
                        cardBody.setLayout(new GridBagLayout());
                    }
                    dashboardBody.setViewportView(cardBody);
                }
                dashboard.add(dashboardBody, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //======== dashboardFooter ========
                {
                    dashboardFooter.setMinimumSize(new Dimension(30, 50));
                    dashboardFooter.setPreferredSize(new Dimension(30, 50));
                    dashboardFooter.setLayout(new GridBagLayout());
                    ((GridBagLayout)dashboardFooter.getLayout()).columnWidths = new int[] {140, 55, 175, 0};
                    ((GridBagLayout)dashboardFooter.getLayout()).rowHeights = new int[] {0, 0};
                    ((GridBagLayout)dashboardFooter.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
                    ((GridBagLayout)dashboardFooter.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

                    //---- diffrenceBorderSizeLabel ----
                    diffrenceBorderSizeLabel.setText("Differences border size:");
                    diffrenceBorderSizeLabel.setMaximumSize(new Dimension(160, 40));
                    diffrenceBorderSizeLabel.setMinimumSize(new Dimension(0, 40));
                    diffrenceBorderSizeLabel.setPreferredSize(new Dimension(145, 40));
                    diffrenceBorderSizeLabel.setAlignmentX(0.5F);
                    diffrenceBorderSizeLabel.setHorizontalAlignment(SwingConstants.LEFT);
                    diffrenceBorderSizeLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
                    dashboardFooter.add(diffrenceBorderSizeLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                    //---- borderSizeSpinner ----
                    borderSizeSpinner.setModel(new SpinnerNumberModel(5, 1, 10, 1));
                    borderSizeSpinner.setEnabled(false);
                    borderSizeSpinner.addChangeListener(e -> borderSizeSpinnerStateChanged(e));
                    dashboardFooter.add(borderSizeSpinner, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                    //---- button1 ----
                    button1.setText("Remove all differences");
                    button1.setMaximumSize(new Dimension(78, 40));
                    button1.setMinimumSize(new Dimension(78, 40));
                    button1.setPreferredSize(new Dimension(78, 40));
                    button1.setEnabled(false);
                    dashboardFooter.add(button1, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                dashboard.add(dashboardFooter, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 10, 0, 10), 0, 0));
            }
            body.add(dashboard, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));

            //======== levelDataManegment ========
            {
                levelDataManegment.setMaximumSize(new Dimension(350, 2147483647));
                levelDataManegment.setMinimumSize(new Dimension(350, 165));
                levelDataManegment.setPreferredSize(new Dimension(350, 167));
                levelDataManegment.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 14));
                levelDataManegment.setLayout(new GridBagLayout());
                ((GridBagLayout)levelDataManegment.getLayout()).columnWidths = new int[] {0, 0};
                ((GridBagLayout)levelDataManegment.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)levelDataManegment.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
                ((GridBagLayout)levelDataManegment.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0E-4};

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
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- countDifferencesLabel ----
                countDifferencesLabel.setText("Count differences: 0");
                countDifferencesLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
                levelDataManegment.add(countDifferencesLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            body.add(levelDataManegment, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                new Insets(0, 10, 0, 10), 0, 0));
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
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(size.width / 2, size.height / 2);
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
            FileChooserFilterExt eff = new FileChooserFilterExt(CARD_CHOOSER_FILTERS[i][0],
                    CARD_CHOOSER_FILTERS[i][1]);
            cardChooser.addChoosableFileFilter(eff);
        }
    }
    // endregion

    // region BUTTONS
    private void selectWorkDirectory(ActionEvent e) {
        if (IS_TEST) {
//            currentDirectoryAbsolutePath = "C:\\prj\\spot-the-difference\\Assets\\Sprites\\Cards";
            currentDirectoryAbsolutePath = "/Users/ds27/Documents/GIT/Logic/Assets/STD/Sprites/Cards";
            workDirectoryLabel.setText(DIRECTORY_LABEL_TEMPLATE + currentDirectoryAbsolutePath);
            selectFirstCard.setEnabled(true);
            cardChooser.setCurrentDirectory(new File(currentDirectoryAbsolutePath));
            loadExistingLevelsData(true);
            return;
        }
        if (directoryChooser.showOpenDialog(selectWorkDirectory) == JFileChooser.APPROVE_OPTION) {
            if (!directoryChooser.getSelectedFile().toString().endsWith(CARDS_FOLDER_NAME)) {
                JOptionPane.showMessageDialog(this, WARNING_MESSAGE_IF_NOT_CARDS_FOLDER,
                        WARNING_TITLE_IF_NOT_CARDS_FOLDER, JOptionPane.ERROR_MESSAGE);
                disableComponentsForSelectWorkDirectory();
                return;
            }
            currentDirectoryAbsolutePath = directoryChooser.getSelectedFile().toString();
            workDirectoryLabel.setText(DIRECTORY_LABEL_TEMPLATE + currentDirectoryAbsolutePath);
            selectFirstCard.setEnabled(true);
            cardChooser.setCurrentDirectory(new File(currentDirectoryAbsolutePath));
            loadExistingLevelsData(true);
        } else {
            disableComponentsForSelectWorkDirectory();
        }
        repaint();
    }

    private void disableComponentsForSelectWorkDirectory() {
        selectFirstCard.setEnabled(false);
        workDirectoryLabel.setText(DIRECTORY_LABEL_TEMPLATE + NOT_SELECTED);
        currentDirectoryAbsolutePath = "";
        loadExistingLevelsData(false);
    }

    private void loadExistingLevelsData(boolean status) {
        DefaultListModel<String> model = new DefaultListModel<String>();
        if (!status) {
            listExistingLevelsData.setModel(model);
            return;
        }

        new LevelsDataPreparer(listExistingLevelsData, countLevelsDataLabel, currentDirectoryAbsolutePath);
    }

    private void selectFirstCard(ActionEvent e) {
        if (cardChooser.showOpenDialog(cardChooser) == JFileChooser.APPROVE_OPTION) {
            if (isIdenticalFiles(secondCard, cardChooser.getSelectedFile().getName())) {
                disableComponentsForSelectFirstCard();
                return;
            }
            firstCard = cardChooser.getSelectedFile().getName();
            firstCardAbsolutePath = cardChooser.getSelectedFile().toString();
            firstCardLabel.setText(FIRST_CARD_LABEL_TEMPLATE + firstCard);
            try {
                firstCardImage = ImageIO.read(cardChooser.getSelectedFile());
                drawCard();
                selectSecondCard.setEnabled(true);
                borderSizeSpinner.setEnabled(true);
                if (secondCardImage != null) {
                    switchCard.setEnabled(true);
                }
            } catch (IOException ignored) { }
        }
        else {
            disableComponentsForSelectFirstCard();
        }
        repaint();
    }

    private void disableComponentsForSelectFirstCard() {
        firstCardLabel.setText(FIRST_CARD_LABEL_TEMPLATE + NOT_SELECTED);
        firstCard = "";
        firstCardAbsolutePath = "";
        firstCardImage = null;
        cardBody.remove(cardIconLabel);
        cardBody.validate();
        cardIconLabel.setIcon(null);
        cardIconLabel = null;
        switchCard.setEnabled(false);
        selectSecondCard.setEnabled(false);
        borderSizeSpinner.setEnabled(false);
        returnToFirstCard.setEnabled(false);
        currentViewImage = -1;
    }

    private void selectSecondCard(ActionEvent e) {
        if (cardChooser.showOpenDialog(cardChooser) == JFileChooser.APPROVE_OPTION) {
            if (isIdenticalFiles(firstCard, cardChooser.getSelectedFile().getName())) {
                disableComponentsForSelectSecondCard();
                return;
            }
            secondCard = cardChooser.getSelectedFile().getName();
            secondCardAbsolutePath = cardChooser.getSelectedFile().toString();
            secondCardLabel.setText(SECOND_CARD_LABEL_TEMPLATE + secondCard);
            try {
                secondCardImage = ImageIO.read(cardChooser.getSelectedFile());
                switchCard.setEnabled(true);
            } catch (IOException ignored) { }
        }
        else {
            disableComponentsForSelectSecondCard();
        }
        repaint();
    }

    private void disableComponentsForSelectSecondCard() {
        secondCardLabel.setText(SECOND_CARD_LABEL_TEMPLATE + NOT_SELECTED);
        secondCard = "";
        secondCardAbsolutePath = "";
        switchCard.setEnabled(false);
        returnToFirstCard.setEnabled(false);
        if (firstCardImage != null && currentViewImage != 0 && cardIconLabel != null) {
            currentViewImage = 0;
            cardIconLabel.setIcon(new ImageIcon(firstCardImage));
            cardIconLabel.setSize(firstCardImage.getWidth(), firstCardImage.getHeight());
            currentCardLabel.setText(CURRENT_CARD_LABEL_TEMPLATE + firstCard);
        }
    }

    private boolean isIdenticalFiles(String firstFile, String secondFile) {
        if (firstFile.equals(secondFile)) {
            JOptionPane.showMessageDialog(this, WARNING_MESSAGE_IF_EQUALS_FILES,
                    WARNING_TITLE_IF_EQUALS_FILES, JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }

    private void borderSizeSpinnerStateChanged(ChangeEvent e) {
        if (cardIconLabel == null) {
            return;
        }
        var source = (JSpinner) e.getSource();
        var value = (int) source.getValue();
        cardIconLabel.setNewBorderSize(value);
        repaint();
    }

    private void switchCardToSecond(ActionEvent e) {
        if (cardIconLabel == null) {
            return;
        }
        ImageIcon cardIcon = new ImageIcon(secondCardImage);
        cardIconLabel.setIcon(cardIcon);
        cardIconLabel.setSize(secondCardImage.getWidth(), secondCardImage.getHeight());
        returnToFirstCard.setEnabled(true);
        switchCard.setEnabled(false);
        currentCardLabel.setText(CURRENT_CARD_LABEL_TEMPLATE + secondCard);
        currentViewImage = 1;
        repaint();
    }

    private void returnToFirstCard(ActionEvent e) {
        if (cardIconLabel == null) {
            return;
        }
        ImageIcon cardIcon = new ImageIcon(firstCardImage);
        cardIconLabel.setIcon(cardIcon);
        cardIconLabel.setSize(firstCardImage.getWidth(), firstCardImage.getHeight());
        returnToFirstCard.setEnabled(false);
        switchCard.setEnabled(true);
        currentCardLabel.setText(CURRENT_CARD_LABEL_TEMPLATE + firstCard);
        currentViewImage = 0;
        repaint();
    }
    // endregion

    // region CARD_VIEW
    private void drawCard() {
        cardIconLabel = new DrawingPanel();
        DraggableAndResizableComponent.thickness = (int) borderSizeSpinner.getValue();
        cardIconLabel.setAlignmentX(0.5F);
        cardBody.add(
                cardIconLabel,
                new GridBagConstraints(
                    0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0
                )
        );
        ImageIcon cardIcon = new ImageIcon(firstCardImage);
        cardIconLabel.setIcon(cardIcon);
        cardIconLabel.setSize(firstCardImage.getWidth(), firstCardImage.getHeight());
        repaint();
    }
    // endregion

}
