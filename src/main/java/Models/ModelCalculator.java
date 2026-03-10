package Models;

public class ModelCalculator {
    protected double So;
    protected double Kr;
    protected double Yo;
    protected double H;
    protected double Y;
    protected double f;
    protected double fi;
    protected double Kz;

    // Конструктор по умолчанию
    public ModelCalculator() {
        this.So = 0.0;
        this.Kr = 0.0;
        this.Yo = 0.0;
        this.H = 0.0;
        this.Y = 0.0;
        this.f = 0.0;
        this.fi = 0.0;
        this.Kz = 0.0;
    }

    // Конструктор с параметрами
    public ModelCalculator(double so, double kr, double yo, double h,
                           double y, double f, double fi, double kz) {
        this.So = so;
        this.Kr = kr;
        this.Yo = yo;
        this.H = h;
        this.Y = y;
        this.f = f;
        this.fi = fi;
        this.Kz = kz;
    }

    // Геттеры и сеттеры
    public double getSo() { return So; }
    public void setSo(double so) { So = so; }

    public double getKr() { return Kr; }
    public void setKr(double kr) { Kr = kr; }

    public double getYo() { return Yo; }
    public void setYo(double yo) { Yo = yo; }

    public double getH() { return H; }
    public void setH(double h) { H = h; }

    public double getY() { return Y; }
    public void setY(double y) { Y = y; }

    public double getF() { return f; }
    public void setF(double f) { this.f = f; }

    public double getFi() { return fi; }
    public void setFi(double fi) { this.fi = fi; }

    public double getKz() { return Kz; }
    public void setKz(double kz) { Kz = kz; }
}