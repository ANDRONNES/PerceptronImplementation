public class Perceptron {
    private float[] weights;
    private float threshould;
    private float alpha = 0.1f;
    private int dimension;
    private float[] inputs;
    private int decision;

    public float[] getWeights() {
        return weights;
    }

    public Perceptron() {
        this.weights = randomArray();
        this.threshould = (float) (-1 + (Math.random() * 2));
    }

    public int compute(float[] inputs) {
        float result = 0;
        this.inputs = inputs;
        for (int i = 0; i < weights.length; i++) {
            result += inputs[i] * weights[i];
        }
        result += threshould;
        if (result >= 0) {
            return 1;
        } else
            return 0;
    }

    public void learn(int decision,int rightDecision) {
        for (int i = 0; i < weights.length; i++) {
            weights[i] += (rightDecision - decision) * inputs[i] /* * alpha*/;
        }
//        threshould += (rightDecision - decision) * alpha;
    }

    public float[] randomArray() {
        float[] randomValuesArray = new float[4];
        for (int i = 0; i < randomValuesArray.length; i++) {
            randomValuesArray[i] = (float) (-1 + (Math.random() * 2));
        }
        return randomValuesArray;
    }
}