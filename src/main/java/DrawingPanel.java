import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DrawingPanel extends JLabel {
    public final String COUNT_DIFFERENCES_LABEL_TEMPLATE = "Count differences: ";
    public final Color DRAWING_PANEL_BORDER_COLOR = Color.BLACK;
    public final Color DIFFERENCES_DRAWING_BORDER_COLOR = Color.GREEN;
    public final int DRAWING_PANEL_BORDER_THICKNESS = 5;

    public DraggableAndResizableComponent curDragComponent = null;
    public ArrayList<DraggableAndResizableComponent> dragComponents = new ArrayList<DraggableAndResizableComponent>();
    public JList listComponents;
    public int lastIndex = 0;
    public BasicStroke dragCompStroke = new BasicStroke(DraggableAndResizableComponent.thickness);
    public LineBorder border = new LineBorder(DRAWING_PANEL_BORDER_COLOR, DRAWING_PANEL_BORDER_THICKNESS);
    public boolean isDrawing = false;

    DrawingPanel(JList aListComponents) {
        listComponents = aListComponents;
        listComponents.clearSelection();
        listComponents.setModel(new DefaultListModel());

        setBorder(border);
        DrawingListener drawingListener = new DrawingListener();
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

    public void drawPerfectRect(Graphics2D g2D, Vector2DPixel v1, Vector2DPixel v2) {
        int px = Math.min(v1.x, v2.x);
        int py = Math.min(v1.y, v2.y);
        int pw = Math.abs(v1.x - v2.x);
        int ph = Math.abs(v1.y - v2.y);
        curDragComponent.setSize(pw, ph);
        g2D.setStroke(dragCompStroke);
        g2D.drawRect(px, py, pw, ph);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (curDragComponent == null) {
            return;
        }

        g.setColor(DIFFERENCES_DRAWING_BORDER_COLOR);
        Vector2DPixel start = curDragComponent.startDraw;
        Vector2DPixel end = curDragComponent.endDraw;
        drawPerfectRect((Graphics2D) g, start, end);
        if (!isDrawing) {
            super.paintComponent(g);
        }
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
    }

    public ArrayList<DifferenceTemp> getDifferences() {
        var ds = new ArrayList<DifferenceTemp>();
        for (var c: dragComponents) {
            ds.add(c.difference);
        }
        return ds;
    }

}