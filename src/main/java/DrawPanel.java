import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

public class DrawPanel extends JPanel implements MouseMotionListener, MouseListener {

    private boolean p;
    private boolean painting;
    private int px, py;
    private double[][] data;
    private final Random rnd = new Random();

    public DrawPanel() {
        setPreferredSize(new Dimension(280, 280));
        setBounds(10, 10, 280, 280);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        p = false;
        painting = false;
        px = 0;
        py = 0;
        data = new double[280][280];
        for (int i = 0; i < 280; i++) for (int j = 0; j < 280; j++) data[i][j] = rnd.nextDouble();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public double[][] getData() {
        return data;
    }

    public void mousePressed(MouseEvent e) {
        p = true;
        painting = true;
    }

    public void mouseDragged(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        Graphics graphics = getGraphics();
        graphics.setColor(Color.BLACK);
        if (painting && p) {
            graphics.drawLine(x, y, x, y);
            p = false;
        } else if (painting) {
            for (int i = -10; i <= 10; i++) {
                graphics.drawLine(px + i, py + i, x + i, y + i);
                graphics.drawLine(px - i, py + i, x + i, y + i);
                graphics.drawLine(px + i, py - i, x + i, y + i);
                graphics.drawLine(px - i, py - i, x - i, y - i);
                data[x + i][y + i] = 255 - i * i;
                data[x - i][y + i] = 255 - i * i;
                data[x + i][y - i] = 255 - i * i;
                data[x - i][y - i] = 255 - i * i;
            }
        }
        px = x;
        py = y;
    }

    public void mouseExited(MouseEvent e) {
        painting = false;
    }

    public void mouseEntered(MouseEvent e) {
        painting = true;
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

}
