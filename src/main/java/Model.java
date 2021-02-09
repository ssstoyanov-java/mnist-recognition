import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.cpu.nativecpu.NDArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Model {

    private static volatile Model instance;
    private MultiLayerNetwork model;

    public static Model getInstance() {
        if (instance == null) {
            synchronized (Model.class) {
                if (instance == null) {
                    instance = new Model();
                }
            }
        }
        return instance;
    }

    private Model() {
        try {
            this.model = KerasModelImport.
                    importKerasSequentialModelAndWeights(getClass().getResourceAsStream("/model.h5"), true);
        } catch (InvalidKerasConfigurationException | UnsupportedKerasConfigurationException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public int recognize(double[][] originalImage) {
        double[][] compressedImage = new double[28][28];
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                compressedImage[i][j] = originalImage[i * 10][j * 10];
            }
        }
        double[][] array = new double[1][784];
        int k = 0;
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                array[0][k++] = compressedImage[j][i];
            }
        }
        return model.predict(new NDArray(array))[0];
    }

}
