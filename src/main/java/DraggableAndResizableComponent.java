import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DraggableAndResizableComponent extends JComponent {
    public static int thickness = 5;

    public DifferenceTemp difference = new DifferenceTemp();
    public Vector2DPixel position = new Vector2DPixel(0, 0);
    public Vector2DPixel differenceSize = new Vector2DPixel(0,0);
    public Vector2DPixel startDraw = new Vector2DPixel(0, 0);
    public Vector2DPixel endDraw = new Vector2DPixel(0,0);
    public ResizableBorder border = new ResizableBorder(thickness);

    public DraggableAndResizableComponent() {
        setEnabled(false);
        setVisible(false);
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        setBorder(border);
        setMinimumSize(new Dimension(30, 30));
        setMaximumSize(new Dimension(4096, 4096));

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

    public void release(DrawingPanel source) {
        calculatePositionOnRelease();
        if (differenceSize == null || differenceSize.x <= 30 || differenceSize.y <= 30) {
            return;
        }

        source.add(this);
        source.dragComponents.add(this);
        source.lastIndex++;

        setBounds(startDraw.x, startDraw.y, differenceSize.x, differenceSize.y);
        setEnabled(true);
        setVisible(true);

        difference.index = source.lastIndex;
        difference.size = differenceSize;
        difference.position = position;
        source.updateList();

        validate();
        repaint();
    }

    public void setStartDraw(Vector2DPixel v) {
        startDraw = v;
    }

    public void setEndDraw(Vector2DPixel v) {
        endDraw = v;
    }

    public void setDifferenceSize(Vector2DPixel v) {
        differenceSize = v;
    }

    public void updateThickness() {
        border = new ResizableBorder(thickness);
        setBorder(border);
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
            case KeyEvent.VK_DELETE, KeyEvent.VK_BACK_SPACE -> remove();
            default -> {
            }
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
