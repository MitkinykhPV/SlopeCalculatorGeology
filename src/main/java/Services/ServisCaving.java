package Services;

import Models.ModelCaving;

public class ServisCaving extends ModelCaving {

    public ServisCaving(double ho, double kp) {
        super(ho, kp);
    }
    double H = Ho * Kp / (Kp - 1);
    public double calculateH(){
        return Ho * Kp / (Kp - 1);


    }

    public void displayAllCalculationHo() {
        double Ho = calculateH();
    }
    double Hp = H - Ho;
    public double calculateHp(){
        return H - Ho;
    }
    public void displayAllCalculationHp() {
        double Hp = calculateH();
    }
}