import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DraggableAndResizableComponent extends JComponent {
    public static int thickness = 5;

    private DifferenceTemp difference = new DifferenceTemp();
    private Vector2DPixel position = null;
    private Vector2DPixel size = null;
    private ResizableBorder border = new ResizableBorder(thickness);

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
        difference.index = source.getLastIndex();
        difference.position = position;
        difference.size = size;

        source.addComponent(this);
        setBounds(position.x, position.y, size.x, size.y);
        setEnabled(true);
        setVisible(true);


        validate();
        repaint();
    }

    public void setDifferencePosition(Vector2DPixel v) {
        difference.position = v;
        position = v;
    }

    public void setDifferenceSize(Vector2DPixel v) {
        difference.position = v;
        size = v;
    }

    public DifferenceTemp getDifference() {
        return difference;
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

    private void onRemove(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DELETE, KeyEvent.VK_BACK_SPACE -> remove();
            default -> {
            }
        }
    }

    private void remove() {
        var parent = (DrawingPanel) getParent();
        parent.removeComponent(this);
    }

}
