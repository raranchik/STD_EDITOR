import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class DrawingListener extends MouseAdapter {
    public Boolean isDrawing = false;
    public Vector2DPixel startDraw = null;
    public Vector2DPixel endDraw = null;
    public Vector2DPixel differenceSize = null;

    @Override
    public void mousePressed(MouseEvent e) {
        clear();

        startDraw = new Vector2DPixel(e.getX(), e.getY());
        isDrawing = true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        endDraw = new Vector2DPixel(e.getX(), e.getY());

        DrawingPanel source = (DrawingPanel) e.getSource();
        source.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endDraw = new Vector2DPixel(e.getX(), e.getY());
        DrawingPanel source = (DrawingPanel) e.getSource();

        var d = new DraggableAndResizableComponent();
        d.setStartDraw(startDraw);
        d.setEndDraw(endDraw);
        d.setDifferenceSize(differenceSize);
        d.release(source);

        clear();
        isDrawing = false;

        source.repaint();
    }

    private void clear() {
        startDraw = null;
        endDraw = null;
        differenceSize = null;
    }

}