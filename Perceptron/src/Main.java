import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Flower> trainingData = new ArrayList<>();
    public static void main(String[] args) {
        trainingData = loadTrainingData("Perceptron/iris_test.txt");
    }
    public static List<Flower> loadTrainingData(String path) {
        List<Flower> flowersDataBase = new ArrayList<>();
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(path));
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                String[] dataParts = line.split("\\s+");
                double[] params = new double[4];
                for (int i = 0; i < 4; i++) {
                    params[i] = Double.parseDouble(dataParts[i].replace(",", "."));
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
                double[] params = new double[4];
                for (int i = 0; i < 4; i++) {

                    params[i] = Double.parseDouble(dataParts[i].replace(",", "."));
                }
                flowersTestingDataBase.add(new Flower(params));

            }
        } catch (Exception e) {
            System.out.println("There is no File");
        }
        return flowersTestingDataBase;
    }
}

