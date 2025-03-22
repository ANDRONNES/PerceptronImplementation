import java.util.Arrays;

public class Flower implements Comparable<Flower>{
    private float[] parameters;
    private String flowerName;

    public Flower(float[] parameters, String flowerName){
        this.parameters = parameters;
        this.flowerName = flowerName;
    }

    public Flower(float[] parameters) {
        this.parameters = parameters;
    }

    public float[] getParameters() {
        return parameters;
    }

    public void setParameters(float[] parameters) {
        this.parameters = parameters;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "parameters=" + Arrays.toString(parameters) +
                ", flowerName='" + flowerName + '\'' +
                '}';
    }

    @Override
    public int compareTo(Flower o) {
        if(sum(this.getParameters()) == sum(o.getParameters()) && flowerName.equals(o.getFlowerName())){
            return 1;
        } else return 0;
    }

    public float sum(float[] params){
        float sum = 0;
        for(Float d : params){
            sum+=d;
        }
        return sum;
    }
}
