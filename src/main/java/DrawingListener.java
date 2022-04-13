import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class DrawingListener extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
        var curDragComponent = new DraggableAndResizableComponent();
        DrawingPanel source = (DrawingPanel) e.getSource();
        source.curDragComponent = curDragComponent;
        source.isDrawing = true;
        if (!source.dragComponents.contains(curDragComponent)) {
            source.dragComponents.add(curDragComponent);
        }
        curDragComponent.setStartDraw(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        DrawingPanel source = (DrawingPanel) e.getSource();
        source.curDragComponent.setEndDraw(e.getX(), e.getY());
        source.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        DrawingPanel source = (DrawingPanel) e.getSource();
        source.curDragComponent.setEndDraw(e.getX(), e.getY());
        source.curDragComponent.release();
        source.add(source.curDragComponent);
        source.isDrawing = false;
        source.repaint();
    }

}