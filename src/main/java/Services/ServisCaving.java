package Services;

import Models.ModelCaving;

public class ServisCaving extends ModelCaving {

    public ServisCaving(double ho, double kp) {
        super(ho, kp);  // Передаем в родительский конструктор
    }

    // Метод для расчета H
    public double calculateH() {
        // Используем геттеры родительского класса
        return getHo() * getKp() / (getKp() - 1);
    }

    // Метод для расчета Hp
    public double calculateHp() {
        double H = calculateH();  // Сначала вычисляем H
        return H - getHo();        // Hp = H - Ho
    }
}