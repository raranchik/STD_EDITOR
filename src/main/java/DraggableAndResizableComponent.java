import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DraggableAndResizableComponent extends JComponent {
    public static int thickness = 5;

    public DifferenceTemp difference = new DifferenceTemp();
    public Vector2DPixel position = new Vector2DPixel(0, 0);
    public Vector2DPixel size = new Vector2DPixel(0,0);
    public Vector2DPixel startDraw = new Vector2DPixel(0, 0);
    public Vector2DPixel endDraw = new Vector2DPixel(0,0);
    public ResizableBorder border = new ResizableBorder(8);

    public boolean opaque = false;

    public DraggableAndResizableComponent() {
        setEnabled(false);
        setVisible(false);
        setOpaque(opaque);
        setBackground(new Color(0, 0, 0, 0));
        setBorder(border);
        setMinimumSize(new Dimension(1, 1));
        setMaximumSize(new Dimension(4096, 4096));
        setSize(1, 1);

        DraggableAndResizableListener resizeListener = new DraggableAndResizableListener();
        addMouseListener(resizeListener);
        addMouseMotionListener(resizeListener);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                onRemove(e);
            }
        });
    }

    public void release() {
        calculatePositionOnRelease();
        setBounds(startDraw.x, startDraw.y, size.x, size.y);
        setEnabled(true);
        setVisible(true);
        validate();
        repaint();

        var source = (DrawingPanel) getParent();
        source.lastIndex++;
        difference.index = source.lastIndex;
        difference.size = size;
        difference.position = position;
        source.updateList();
    }

    public void setStartDraw(int x, int y) {
        startDraw.x = x;
        startDraw.y = y;
    }

    public void setEndDraw(int x, int y) {
        endDraw.x = x;
        endDraw.y = y;
    }

    public void setSize(int x, int y) {
        size.x = x;
        size.y = y;
    }

    public void updateThickness() {
        border = new ResizableBorder(8);
        setBorder(border);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public void resize() {
        if (getParent() != null) {
            getParent().revalidate();
        }
    }

    private void calculatePositionOnRelease() {
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

        position.x = (startDraw.x + endDraw.x) / 2;
        position.y = (startDraw.y + endDraw.y) / 2;
    }

    private void onRemove(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DELETE:
                remove();
                break;

            case KeyEvent.VK_BACK_SPACE:
                remove();
                break;

            default:
                break;
        }
    }

    private void remove() {
        var parent = (DrawingPanel) getParent();
        parent.dragComponents.remove(this);
        parent.remove(this);
        parent.updateList();
        parent.repaint();
    }

}
