import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Flower> trainingData = new ArrayList<>();
    static List<Flower> testingData = new ArrayList<>();
    static List<float[]> inputs = new ArrayList<>();
    static List<Integer> rightOutputs = new ArrayList<>();

    public static void main(String[] args) {
        Perceptron p = new Perceptron();
        Scanner scanner = new Scanner(System.in);
        trainingData = loadTrainingData("..\\Perceptron\\iris_test.txt");
        testingData = loadTestingDataWitoutAnswers("..\\Perceptron\\iris_test.txt");

        for (int i = 0; i < trainingData.size(); i++) {
            inputs.add(trainingData.get(i).getParameters());
            if (trainingData.get(i).getFlowerName().equals("Iris-setosa")) rightOutputs.add(1);
            else rightOutputs.add(0);
        }

        trainPerceptron(p, 1);
//        testPerceptron(p,inputs,rightOutputs);
        //resetujemy wszystko
        inputs.clear();
        rightOutputs.clear();
        for (int i = 0; i < testingData.size(); i++) {
            inputs.add(testingData.get(i).getParameters());
            if (trainingData.get(i).getFlowerName().equals("Iris-setosa")) rightOutputs.add(1);
            else rightOutputs.add(0);
        }
        testPerceptron(p, inputs, rightOutputs);

        System.out.println("Now you can enter yours flower to check the type of this flower" +
                "\n" + "Enter parameters(sizes) into console one-by-one" + "\n" +
                "Do you want to continue? type \"yes\" or \"no\"");
        while (!scanner.nextLine().equals("!")) {
            float[] newVec = new float[4];
            String s1 = scanner.nextLine();
            if (s1.equals("no")) break;
            else {
                System.out.println("Start entering the parameters");
                for (int i = 0; i < newVec.length; i++) {
                    newVec[i] = scanner.nextFloat();
                }
                testPerceptronOnOneFlower(p, newVec);
//                Flower f = new Flower(newVec);
//                classifyOneFlower(f, k, trainingDataBase);
//                System.out.println("Yours iris type is " + f.getFlowerName());
                System.out.println("Do you want to continue? type \"yes\" or \"no\"");
            }
        }


    }

    public static void testPerceptronOnOneFlower(Perceptron p, float[] input){
        if(p.compute(input) == 1) System.out.println("Your flower is Iris-Setosa");
        else System.out.println("Your flower is NOT Iris-Setosa");
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
        System.out.println("Number of correct ANSWERS: " + correctCount);
        System.out.println("The percent of experiment accuracy: " + accuracy + "%");

    }


    public static void trainPerceptron(Perceptron p, int epoka) {
        for (int i = 0; i < epoka; i++) {
            for (int j = 0; j < inputs.size(); j++) {
                p.learn(p.compute(inputs.get(j)), rightOutputs.get(j));
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

