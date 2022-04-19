import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class DrawingListener extends MouseAdapter {
    public Boolean isDrawing = false;
    public Vector2DPixel start = null;
    public Vector2DPixel end = null;
    public Vector2DPixel size = null;

    @Override
    public void mousePressed(MouseEvent e) {
        clear();

        start = new Vector2DPixel(e.getX(), e.getY());
        isDrawing = true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        end = new Vector2DPixel(e.getX(), e.getY());

        DrawingPanel source = (DrawingPanel) e.getSource();
        source.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        end = new Vector2DPixel(e.getX(), e.getY());
        DrawingPanel source = (DrawingPanel) e.getSource();

        var d = new DraggableAndResizableComponent();
        d.setStartDraw(start);
        d.setEndDraw(end);
        d.setSize(size);
        d.release(source);

        clear();
        isDrawing = false;

        source.repaint();
    }

    private void clear() {
        start = null;
        end = null;
        size = null;
    }

}