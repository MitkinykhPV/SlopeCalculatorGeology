package Controllers;

import Services.ServisCaving;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.application.Platform;

public class CavingController {

    // ПОЛЯ ВВОДА ДАННЫХ
    @FXML
    private TextField hoField;
    @FXML
    private TextField kpField;

    // ПОЛЯ ВЫВОДА РЕЗУЛЬТАТА
    @FXML
    private Label hResult;
    @FXML
    private Label hpResult;

    // КНОПКИ НАЗАД И ВЫХОД
    @FXML
    private Button backButton;
    @FXML
    private Button exitButton;
//
@FXML
private void initialize() {
    // Добавьте проверку на null
    if (kpField != null) {
        setDefaultValues();
    } else {
        System.out.println("Ошибка: поля не инициализированы");
    }
}

    @FXML
    private void calculate() {
        try {
            // Проверка на null перед использованием
            if (hoField == null || kpField == null) {
                System.out.println("Ошибка: поля не инициализированы");
                return;
            }

            double ho = Double.parseDouble(hoField.getText());
            double kp = Double.parseDouble(kpField.getText());

            ServisCaving calculator = new ServisCaving(ho, kp);

            double h = calculator.calculateH();
            double hp = calculator.calculateHp();

//
            hResult.setText(String.format("%.2f", h));
            hpResult.setText(String.format("%.2f", hp));

//
        } catch (NumberFormatException e) {
            hResult.setText("Ошибка");
            hpResult.setText("Ошибка");
        } catch (Exception e) {
            System.out.println("Ошибка расчета: " + e.getMessage());
        }
    }

    @FXML
    private void clearFields() {
        setDefaultValues();
        if (hResult != null && hpResult != null) {
            hResult.setText("-");
            hpResult.setText("-");
            }
    }

    @FXML
    private void goBack() {
        try {
            // Закрываем текущее окно калькулятора
            Stage calculatorStage = (Stage) hoField.getScene().getWindow();
            calculatorStage.close();

            // Открываем окно выбора
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/viwes/DungeonCommander.fxml"));
            Parent root = loader.load();

            Stage choiceStage = new Stage();
            choiceStage.setTitle("Пехотинец - Выбор расчета");
            choiceStage.setScene(new Scene(root, 600, 400));
            choiceStage.setResizable(false);
            choiceStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setDefaultValues() {
        // Добавьте проверки на null
        if (hoField != null) hoField.setText("0.0");
        if (kpField != null) kpField.setText("0.0");
    }
    @FXML
    private void exitApplication() {
        Platform.exit();
    }
}