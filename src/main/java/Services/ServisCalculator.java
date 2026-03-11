package Services;

import Models.ModelCalculator;

public class ServisCalculator extends ModelCalculator {

       public ServisCalculator(double so, double kr, double yo, double h,
                            double y, double f, double fi, double kz) {
        super(so, kr, yo, h, y, f, fi, kz);
    }
    double S = Math.sqrt((2 * So * Kr) / Math.tan(Math.toRadians(f)));
    public double calculateS(){
             return Math.sqrt((2 * So * Kr) / Math.tan(Math.toRadians(f)));


    }
    public double calculateD(){
        double S = calculateS();
        return Math.sqrt((So*Yo*H)/(Y*S*(1+(Math.sin(Math.toRadians(fi))))/(1-(Math.sin(Math.toRadians(fi))))));

    }
    public double calculateH(){
        double D = calculateD();
        return D*Kz;

    }
    public void displayAllCalculationD() {
        double D = calculateD();
        double S = calculateS();
        double H = calculateH();
       // System.out.println("Площадь погашения");
        //System.out.printf("S = %.3f%n", S);
        //System.out.println("Деформация подушки");
       // System.out.printf("D = %.3f%n", D);
       // System.out.println("Коэффициент запаса");
        //System.out.printf("H = %.3f%n", H);
    }
}

//Комент ради комента