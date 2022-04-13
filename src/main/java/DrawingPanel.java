import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class DrawingPanel extends JLabel {
    public final Color DRAWING_PANEL_BORDER_COLOR = Color.BLACK;
    public final Color DIFFERENCES_DRAWING_BORDER_COLOR = Color.GREEN;
    public final Color DIFFERENCES_DRAGGABLE_BORDER_COLOR = Color.BLACK;
    public final int DRAWING_PANEL_BORDER_THICKNESS = 5;

    public ArrayList<DraggableAndResizableComponent> dragComponents = new ArrayList<DraggableAndResizableComponent>();
    public DraggableAndResizableComponent curDragComponent = null;
    public BasicStroke dragCompStroke = new BasicStroke(DraggableAndResizableComponent.thickness);
    public LineBorder border = new LineBorder(DRAWING_PANEL_BORDER_COLOR, DRAWING_PANEL_BORDER_THICKNESS);
    public boolean isDrawing = false;

    DrawingPanel() {
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

}