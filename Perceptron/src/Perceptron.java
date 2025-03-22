public class Perceptron {
    private float[] weights;
    private float threshould;
    private float alpha;
    private int dimension;
    private float[]inputs;
    private int decision;

    public Perceptron(float[] weights, double threshould) {
        this.weights = randomArray();
        this.threshould = (float)(-1 + (Math.random() * 2));
    }

    public int Compute(float[] inputs) {
        float result = 0;
        this.inputs = inputs;
        for (int i = 0; i < weights.length; i++) {
            result += inputs[i] * weights[i];
        }
        if (result >= threshould) {
            return 1;
        } else
            return 0;
    }

    public float[] Learn(float[] oldWeights){
        float[] newWeightVector = new float[4];
//        newWeightVector =
    }
    public float[] randomArray(){
        float[] randomValuesArray = new float[4];
        for (int i = 0; i < randomValuesArray.length; i++) {
            randomValuesArray[i] = (float) (-1 + (Math.random() * 2));
        }
        return randomValuesArray;
    }
}