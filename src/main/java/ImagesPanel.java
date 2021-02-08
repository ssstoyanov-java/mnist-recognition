
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagesPanel extends JPanel {

    /**
     * Creates a panel where the image drawn by the user
     * and the 10x10 binarized image obtained from it are
     * shown.
     */
    public ImagesPanel() {
        setPreferredSize(new Dimension(710, 430));
        setBounds(0, 0, 710, 430);
    }

    
}
