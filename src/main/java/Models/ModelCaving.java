package Models;


public class ModelCaving {
    protected double Ho;
    protected double Kp;

    public ModelCaving() {
        this.Ho = 0.0;
        this.Kp = 0.0;

    }
    public ModelCaving(double ho, double kp) {
        this.Ho=ho;
        this.Kp=kp;

    }
    public double getHo() { return Ho; }
    public void setHo(double ho) { Ho = ho; }

    public double getKp() { return Kp; }
    public void setKp(double kp) { Kp = kp; }
}
