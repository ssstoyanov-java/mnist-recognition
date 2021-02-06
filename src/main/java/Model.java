import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.cpu.nativecpu.NDArray;

import java.io.IOException;

public class Model {

    private final double[][] array = new double[1][784];
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
            MultiLayerNetwork model = KerasModelImport.
                    importKerasSequentialModelAndWeights(
                            "/home/stanislav/IdeaProjects/mnist-recognition/src/main/resources/model/model.h5");
            model.init();
            this.model = model;
        } catch (InvalidKerasConfigurationException | UnsupportedKerasConfigurationException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public int recognize(double[][] doubles) {
        for (int i = 0; i < doubles.length; i++) {
            for (int j = 0; j < doubles[i].length; j++) {
                this.array[0][i + (j * doubles.length)] = doubles[i][j];
            }
        }
        return model.predict(new NDArray(this.array))[0];
    }

    public int recognize(double[] doubles) {
        System.arraycopy(doubles, 0, this.array[0], 0, this.array.length);
        return model.predict(new NDArray(this.array))[0];
    }

}
