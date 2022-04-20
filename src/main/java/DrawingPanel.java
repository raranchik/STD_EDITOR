import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class DrawingPanel extends JLabel {
    private final String COUNT_ITEMS_LABEL_TEMPLATE = "Count differences: ";
    private final Color DRAWING_PANEL_BORDER_COLOR = Color.BLACK;
    private final Color DIFFERENCES_DRAWING_BORDER_COLOR = Color.GREEN;
    private final int DRAWING_PANEL_BORDER_THICKNESS = 5;

    public DrawingListener drawingListener = new DrawingListener();

    private JList sourceList = null;
    private JLabel countLabel = null;
    private JButton removeDsButton = null;
    private int lastIndex = 0;
    private ArrayList<DraggableAndResizableComponent> components = null;
    private BasicStroke dragCompStroke = new BasicStroke(DraggableAndResizableComponent.thickness);
    private LineBorder border = new LineBorder(DRAWING_PANEL_BORDER_COLOR, DRAWING_PANEL_BORDER_THICKNESS);

    DrawingPanel(JList aSourceList, JLabel aCountLabel, ArrayList<DifferenceTemp> aDifferences, JButton aRemoveDsButton) {
        removeDsButton = aRemoveDsButton;
        removeDsButton.setEnabled(false);
        sourceList = aSourceList;
        countLabel = aCountLabel;
        clearList();

        if (aDifferences != null && aDifferences.size() > 0) {
            createComponents(aDifferences);
        }
        setBorder(border);
        addMouseListener(drawingListener);
        addMouseMotionListener(drawingListener);
    }

    public void setNewBorderSize(int s) {
        DraggableAndResizableComponent.thickness = s;
        dragCompStroke = new BasicStroke(s);
        for (DraggableAndResizableComponent d : components) {
            d.updateThickness();
        }
    }

    public ArrayList<DifferenceTemp> getDifferences() {
        var ds = new ArrayList<DifferenceTemp>();
        for (var c: components) {
            ds.add(c.getDifference());
        }

        return ds;
    }

    public int getLastIndex() {
        return ++lastIndex;
    }

    public void addComponent(DraggableAndResizableComponent component) {
        add(component);
        components.add(component);
        updateList();
    }

    public void removeComponent(DraggableAndResizableComponent component) {
        components.remove(component);
        remove(component);
        updateList();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!drawingListener.isDrawing) {
            return;
        }

        g.setColor(DIFFERENCES_DRAWING_BORDER_COLOR);
        Vector2DPixel start = drawingListener.startDraw;
        Vector2DPixel end = drawingListener.endDraw;
        drawPerfectRect((Graphics2D) g, start, end);
    }

    private void createComponents(ArrayList<DifferenceTemp> differences) {
        for (var d : differences) {
            var component = new DraggableAndResizableComponent();
            component.setDifferencePosition(d.position);
            component.setDifferenceSize(d.size);
            component.release(this);
        }
    }

    private void drawPerfectRect(Graphics2D g2D, Vector2DPixel v1, Vector2DPixel v2) {
        int px = Math.min(v1.x, v2.x);
        int py = Math.min(v1.y, v2.y);
        int pw = Math.abs(v1.x - v2.x);
        int ph = Math.abs(v1.y - v2.y);
        drawingListener.differenceSize = new Vector2DPixel(pw, ph);
        g2D.setStroke(dragCompStroke);
        g2D.drawRect(px, py, pw, ph);
    }

    private void updateList() {
        updateModel();
        updateCountLabel();
    }

    protected void clearList() {
        if (components != null && components.size() > 0) {
            for (var c : components) {
                remove(c);
            }
        }

        components = new ArrayList<DraggableAndResizableComponent>();
        updateList();
    }

    private void updateModel() {
        var model = new DefaultListModel<String>();
        if (components.size() == 0) {
            sourceList.setModel(model);

            return;
        }

        for (var c : components) {
            var d = c.getDifference();
            model.addElement(d.toString());
        }
        sourceList.setModel(model);
        removeDsButton.setEnabled(true);
    }

    private void updateCountLabel() {
        countLabel.setText(COUNT_ITEMS_LABEL_TEMPLATE + components.size());
    }

}