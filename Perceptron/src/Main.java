import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Flower> trainingData = new ArrayList<>();
    static List<float[]> inputs = new ArrayList<>();
    static List<Integer> rightOutputs = new ArrayList<>();

    public static void main(String[] args) {
        List<Integer> outputs = new ArrayList<>();
        Perceptron p = new Perceptron();
        trainingData = loadTrainingData("..\\Perceptron\\iris_test.txt");
        for (int i = 0; i < trainingData.size(); i++) {
            inputs.add(trainingData.get(i).getParameters());
        }
        for (int i = 0; i < trainingData.size(); i++) {
            if (trainingData.get(i).getFlowerName().equals("Iris-setosa")) rightOutputs.add(1);
            else rightOutputs.add(0);
        }

        System.out.println(rightOutputs);

        trainPerceptron(p,4);
        testPerceptron(p,inputs,rightOutputs);

    }
    public static void testPerceptron(Perceptron perceptron, List<float[]> testData, List<Integer> testLabels) {
        int correctCount = 0;
        for (int i = 0; i < testData.size(); i++) {
            int prediction = perceptron.compute(testData.get(i));
            if (prediction == testLabels.get(i)) {
                correctCount++;
            }
        }
        double accuracy = (double) correctCount / testData.size() * 100;
        System.out.println("Liczba poprawnie zaklasyfikowanych przykładów: " + correctCount);
        System.out.println("Dokładność: " + accuracy + "%");
    }


    public static void trainPerceptron(Perceptron p, int epoka){
        for (int i = 0; i < epoka; i++) {
            for (int j = 0; j < inputs.size(); j++) {
                p.learn(p.compute(inputs.get(j)),rightOutputs.get(j));
            }
        }
    }


    public static List<Flower> loadTrainingData(String path) {
        List<Flower> flowersDataBase = new ArrayList<>();
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(path));
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                String[] dataParts = line.split("\\s+");
                float[] params = new float[4];
                for (int i = 0; i < 4; i++) {
                    params[i] = Float.parseFloat(dataParts[i].replace(",", "."));
                }
                flowersDataBase.add(new Flower(params, dataParts[dataParts.length - 1]));
            }
        } catch (Exception e) {
            System.out.println("There is no File");
        }
        return flowersDataBase;
    }

    public static List<Flower> loadTestingDataWitoutAnswers(String path) {
        List<Flower> flowersTestingDataBase = new ArrayList<>();
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(path));
             BufferedReader br = new BufferedReader(isr)) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                String[] dataParts = line.split("\\s+");
                float[] params = new float[4];
                for (int i = 0; i < 4; i++) {

                    params[i] = Float.parseFloat(dataParts[i].replace(",", "."));
                }
                flowersTestingDataBase.add(new Flower(params));

            }
        } catch (Exception e) {
            System.out.println("There is no File");
        }
        return flowersTestingDataBase;
    }
}

