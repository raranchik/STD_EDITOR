import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class DrawingListener extends MouseAdapter {
    public Boolean isDrawing = false;
    public Vector2DPixel start = null;
    public Vector2DPixel end = null;
    public Vector2DPixel size = null;

    @Override
    public void mousePressed(MouseEvent e) {
        start = new Vector2DPixel(e.getX(), e.getY());
        isDrawing = true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        end = new Vector2DPixel(e.getX(), e.getY());
        isDrawing = true;
        DrawingPanel source = (DrawingPanel) e.getSource();

        source.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        end = new Vector2DPixel(e.getX(), e.getY());
        isDrawing = false;
        DrawingPanel source = (DrawingPanel) e.getSource();

        var d = new DraggableAndResizableComponent();
        d.setStartDraw(start);
        d.setEndDraw(end);
        d.setSize(size);
        d.release(source);

        start = null;
        end = null;

        source.repaint();
    }

}