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

        if (sizeInRange()) {
            correctStartAndEndDrawPoint();
            var d = new DraggableAndResizableComponent();
            d.setDifferencePosition(startDraw);
            d.setDifferenceSize(differenceSize);
            d.release(source);
        }

        clear();
        isDrawing = false;

        source.repaint();
    }

    private Boolean sizeInRange() {
        if (differenceSize != null) {
            return differenceSize.x >= 60 || differenceSize.y >= 60;
        }

        return false;
    }

    private void correctStartAndEndDrawPoint() {
        if (endDraw.x < startDraw.x && endDraw.y < startDraw.y) {
            Vector2DPixel temp = new Vector2DPixel(endDraw.x, endDraw.y);
            endDraw = startDraw;
            startDraw = temp;
        }
        else if (endDraw.x > startDraw.x && endDraw.y < startDraw.y) {
            Vector2DPixel temp = new Vector2DPixel(startDraw.x, endDraw.y);
            Vector2DPixel temp1 = new Vector2DPixel(endDraw.x, startDraw.y);
            startDraw = temp;
            endDraw = temp1;
        }
        else if (endDraw.x < startDraw.x && endDraw.y > startDraw.y) {
            Vector2DPixel temp = new Vector2DPixel(endDraw.x, startDraw.y);
            Vector2DPixel temp1 = new Vector2DPixel(startDraw.x, endDraw.y);
            startDraw = temp;
            endDraw = temp1;
        }
    }

    private void clear() {
        startDraw = null;
        endDraw = null;
        differenceSize = null;
    }

}