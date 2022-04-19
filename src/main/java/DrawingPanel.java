import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class DrawingPanel extends JLabel {
    public final Color DRAWING_PANEL_BORDER_COLOR = Color.BLACK;
    public final Color DIFFERENCES_DRAWING_BORDER_COLOR = Color.GREEN;
    public final int DRAWING_PANEL_BORDER_THICKNESS = 5;

    public ArrayList<DraggableAndResizableComponent> dragComponents = new ArrayList<DraggableAndResizableComponent>();

    public JList listComponents;
    private JLabel countLabel;
    public int lastIndex = 0;

    public BasicStroke dragCompStroke = new BasicStroke(DraggableAndResizableComponent.thickness);
    public LineBorder border = new LineBorder(DRAWING_PANEL_BORDER_COLOR, DRAWING_PANEL_BORDER_THICKNESS);
    public DrawingListener drawingListener = new DrawingListener();

    DrawingPanel(JList aList, JLabel aLabel) {
        countLabel = aLabel;
        listComponents = aList;
        listComponents.clearSelection();
        listComponents.setModel(new DefaultListModel());

        setBorder(border);
        addMouseListener(drawingListener);
        addMouseMotionListener(drawingListener);
    }

    public void setNewBorderSize(int s) {
        DraggableAndResizableComponent.thickness = s;
        dragCompStroke = new BasicStroke(s);
        for (DraggableAndResizableComponent d : this.dragComponents) {
            d.updateThickness();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!drawingListener.isDrawing) {
            return;
        }

        g.setColor(DIFFERENCES_DRAWING_BORDER_COLOR);
        Vector2DPixel start = drawingListener.startDraw;
        Vector2DPixel end = drawingListener.endDraw;
        drawPerfectRect((Graphics2D) g, start, end);
    }

    public void drawPerfectRect(Graphics2D g2D, Vector2DPixel v1, Vector2DPixel v2) {
        int px = Math.min(v1.x, v2.x);
        int py = Math.min(v1.y, v2.y);
        int pw = Math.abs(v1.x - v2.x);
        int ph = Math.abs(v1.y - v2.y);
        drawingListener.differenceSize = new Vector2DPixel(pw, ph);
        g2D.setStroke(dragCompStroke);
        g2D.drawRect(px, py, pw, ph);
    }

    public void updateList() {
        var newModel = new DefaultListModel<String>();
        for (int i = 0; i < dragComponents.size(); i++) {
            var c = dragComponents.get(i);
            var d = c.difference;
            var dName = String.format("%s%s", d.DIFFERENCE_NAME_TEMPLATE, d.index);
            newModel.addElement(dName);
        }
        listComponents.setModel(newModel);
        var message = String.format("%s%s", WorkSpace.COUNT_DIFFERENCES_TEMPLATE, dragComponents.size());
        countLabel.setText(message);
    }

    public ArrayList<DifferenceTemp> getDifferences() {
        var ds = new ArrayList<DifferenceTemp>();
        for (var c: dragComponents) {
            ds.add(c.difference);
        }

        return ds;
    }

}