import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DraggableAndResizableListener extends MouseAdapter {
    private int cursor;
    private Point startPos = null;
    private Boolean isFirstTap = false;
    private int x;
    private int y;
    private int w;
    private int h;

    @Override
    public void mouseMoved(MouseEvent me) {
        DraggableAndResizableComponent source = (DraggableAndResizableComponent) me.getSource();
        if (source.hasFocus()) {
            var resizableBorder = (ResizableBorder) source.getBorder();
            var cursor = resizableBorder.getCursor(me);
            source.setCursor(Cursor.getPredefinedCursor(cursor));
        }
    }

    @Override
    public void mouseExited(MouseEvent me) {
        DraggableAndResizableComponent source = (DraggableAndResizableComponent) me.getSource();
        source.setCursor(Cursor.getDefaultCursor());
    }

    @Override
    public void mousePressed(MouseEvent me) {
        isFirstTap = true;
        DraggableAndResizableComponent source = (DraggableAndResizableComponent) me.getSource();
        var resizableBorder = (ResizableBorder) source.getBorder();
        cursor = resizableBorder.getCursor(me);
        startPos = me.getPoint();
        source.requestFocus();
        source.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        isFirstTap = false;
        DraggableAndResizableComponent source = (DraggableAndResizableComponent) me.getSource();
        if (startPos != null) {
            x = source.getX();
            y = source.getY();
            w = source.getWidth();
            h = source.getHeight();
            int dx = me.getX() - startPos.x;
            int dy = me.getY() - startPos.y;

            switch (cursor) {
                case Cursor.N_RESIZE_CURSOR -> {
                    if (!(h - dy < 50)) {
                        y += dy;
                        h -= dy;
                        source.setBounds(x, y, w, h);
                        source.resize();
                    }
                }

                case Cursor.S_RESIZE_CURSOR -> {
                    if (!(h + dy < 50)) {
                        h += dy;
                        source.setBounds(x, y, w, h);
                        startPos = me.getPoint();
                        source.resize();
                    }
                }

                case Cursor.W_RESIZE_CURSOR -> {
                    if (!(w - dx < 50)) {
                        x += dx;
                        w -= dx;
                        source.setBounds(x, y, w, h);
                        source.resize();
                    }
                }


                case Cursor.E_RESIZE_CURSOR -> {
                    if (!(w + dx < 50)) {
                        w += dx;
                        source.setBounds(x, y, w, h);
                        startPos = me.getPoint();
                        source.resize();
                    }
                }

                case Cursor.NW_RESIZE_CURSOR -> {
                    if (!(w - dx < 50) && !(h - dy < 50)) {
                        x += dx;
                        y += dy;
                        w -= dx;
                        h -= dy;
                        source.setBounds(x, y, w, h);
                        source.resize();
                    }
                }

                case Cursor.NE_RESIZE_CURSOR -> {
                    if (!(w + dx < 50) && !(h - dy < 50)) {
                        y += dy;
                        w += dx;
                        h -= dy;
                        source.setBounds(x, y, w, h);
                        startPos = new Point(me.getX(), startPos.y);
                        source.resize();
                    }
                }

                case Cursor.SW_RESIZE_CURSOR -> {
                    if (!(w - dx < 50) && !(h + dy < 50)) {
                        x += dx;
                        w -= dx;
                        h += dy;
                        source.setBounds(x, y, w, h);
                        startPos = new Point(startPos.x, me.getY());
                        source.resize();
                    }
                }

                case Cursor.SE_RESIZE_CURSOR -> {
                    if (!(w + dx < 50) && !(h + dy < 50)) {
                        w += dx;
                        h += dy;
                        source.setBounds(x, y, w, h);
                        startPos = me.getPoint();
                        source.resize();
                    }
                }

                case Cursor.MOVE_CURSOR -> {
                    var bounds = source.getBounds();
                    bounds.translate(dx, dy);
                    source.setBounds(bounds);
                    x = bounds.x;
                    y = bounds.y;
                    w = bounds.width;
                    h = bounds.height;
                    source.resize();
                }
            }

            source.setCursor(Cursor.getPredefinedCursor(cursor));
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        startPos = null;
        DraggableAndResizableComponent source = (DraggableAndResizableComponent) me.getSource();
        if (isFirstTap) {
            x = source.getX();
            y = source.getY();
            w = source.getWidth();
            h = source.getHeight();
            isFirstTap = false;
        }
        source.difference.size = new Vector2DPixel(w, h);
        source.difference.position = new Vector2DPixel(x, y);
    }

}
