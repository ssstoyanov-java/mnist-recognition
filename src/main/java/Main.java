import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.cpu.nativecpu.NDArray;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

    public static void main(String... args) throws UnsupportedKerasConfigurationException, IOException, InvalidKerasConfigurationException {
        double[] doubles = toArray(ImageIO.read(new File("/home/stanislav/IdeaProjects/digitml/src/main/resources/image3.jpg")));
        predictRandom(doubles);
    }

    private static void predictRandom(double[] doubles) throws IOException, InvalidKerasConfigurationException, UnsupportedKerasConfigurationException {
        MultiLayerNetwork model = KerasModelImport.
                importKerasSequentialModelAndWeights("/home/stanislav/IdeaProjects/digitml/src/main/resources/model/model.h5");
        // model.init();

        double[][] array = new double[1][784];
        IntStream.range(0, 784).forEach(j -> array[0][j] = doubles[j]);

        NDArray ndArray = new NDArray(array);
        System.out.println(model.output(ndArray));
        System.out.print(Arrays.toString(model.predict(ndArray)));
    }

    private static double[] toArray(BufferedImage image) {
        double[][] picture = new double[image.getWidth()][image.getHeight()];
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                double longDouble = (double) image.getRGB(x, y) / 10000000L;
                int temp = (int) (longDouble * 100.0);
                picture[x][y] = ((double) temp) / 100.0;
            }
        }
        System.out.println(Arrays.deepToString(picture));
        double[] out = new double[picture.length * picture[0].length];
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[i].length; j++) {
                out[i + (j * picture.length)] = picture[i][j];
            }
        }
        return out;
    }

}
