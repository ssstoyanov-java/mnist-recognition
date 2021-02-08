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
    private final double[][] data;

    public DrawPanel() {
        setPreferredSize(new Dimension(280, 280));
        setBounds(10, 10, 280, 280);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.p = false;
        this.painting = false;
        this.px = 0;
        this.py = 0;
        this.data = new double[280][280];
        Random rnd = new Random();
        for (int i = 0; i < 280; i++) for (int j = 0; j < 280; j++) data[i][j] = rnd.nextDouble();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public double[][] getData() {
        return this.data;
    }

    public void mousePressed(MouseEvent e) {
        this.p = true;
        this.painting = true;
    }

    public void mouseDragged(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        Graphics graphics = getGraphics();
        graphics.setColor(Color.BLACK);
        if (this.painting && this.p) {
            graphics.drawLine(x, y, x, y);
            this.p = false;
        } else if (this.painting) {
            for (int i = -12; i <= 12; i++) {
                graphics.drawLine(this.px + i, this.py + i, x + i, y + i);
                graphics.drawLine(this.px - i, this.py + i, x + i, y + i);
                graphics.drawLine(this.px + i, this.py - i, x + i, y + i);
                graphics.drawLine(this.px - i, this.py - i, x - i, y - i);
                this.data[x + i][y + i] = 255 - i * i;
                this.data[x - i][y + i] = 255 - i * i;
                this.data[x + i][y - i] = 255 - i * i;
                this.data[x - i][y - i] = 255 - i * i;
            }
        }
        this.px = x;
        this.py = y;
    }

    public void mouseExited(MouseEvent e) {
        this.painting = false;
    }

    public void mouseEntered(MouseEvent e) {
        this.painting = true;
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

}
