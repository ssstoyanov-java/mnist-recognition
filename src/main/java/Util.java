import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Util {

    public static double[][] imageToDoubleMatrix(BufferedImage image) {
        BufferedImage dimg = new BufferedImage(28, 28, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(image.getScaledInstance(28, 28, Image.SCALE_SMOOTH), 0, 0, null);
        g2d.dispose();
        image = dimg;
        double[][] picture = new double[image.getWidth()][image.getHeight()];
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color c = new Color(image.getRGB(x, y));
                int red = (int) (c.getRed() * 0.299);
                int green = (int) (c.getGreen() * 0.587);
                int blue = (int) (c.getBlue() * 0.114);
                picture[x][y] = new Color(red + green + blue,
                        red + green + blue,
                        red + green + blue).getRGB() / 65794.0 + 1;
                if (picture[x][y] < 0) picture[x][y] = -picture[x][y];
            }
        }
        System.out.println(Arrays.deepToString(picture));
        return picture;
    }

}
