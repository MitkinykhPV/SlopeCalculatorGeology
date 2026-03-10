package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.application.Platform;

import java.io.IOException;

public class DungeonCommanderController {

    @FXML
    private Button thicknessCalculationBtn;

    @FXML
    private Button otherCalculationBtn;

    @FXML
    private Button exitButton;

    @FXML
    private Button otherGeology;

    @FXML
    void initialize() {
        // Инициализация при необходимости
        System.out.println("Окно выбора загружено");
    }

    @FXML
    private void openThicknessCalculation() {
        try {
            // Закрываем текущее окно выбора
            Stage choiceStage = (Stage) thicknessCalculationBtn.getScene().getWindow();
            choiceStage.close();

            // Открываем окно с расчетом толщины подушки
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/viwes/calculator.fxml"));
            Parent root = loader.load();

            Stage calculatorStage = new Stage();
            calculatorStage.setTitle("Расчет толщины подушки");
            calculatorStage.setScene(new Scene(root, 1716, 1160));
            calculatorStage.setResizable(false);
            calculatorStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Не удалось загрузить окно калькулятора");
        }
    }

    @FXML
    private void openOtherCalculation() {
        try {
        // Закрываем текущее окно выбора
        Stage choiceStage = (Stage) thicknessCalculationBtn.getScene().getWindow();
        choiceStage.close();

        // Открываем окно с расчетом высота обрушения
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viwes/Caving.fxml"));
        Parent root = loader.load();

        Stage calculatorStage = new Stage();
        calculatorStage.setTitle("Пехотинец - Расчет толщины подушки");
        calculatorStage.setScene(new Scene(root, 920, 587));
        calculatorStage.setResizable(false);
        calculatorStage.show();

    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Не удалось загрузить окно калькулятора");
    }

    }
    @FXML
    private void openOtherGyology() {
        try {
            // Закрываем текущее окно выбора
            Stage choiceStage = (Stage) thicknessCalculationBtn.getScene().getWindow();
            choiceStage.close();

            // Открываем окно геология
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/viwes/Geology.fxml"));
            Parent root = loader.load();

            Stage calculatorStage = new Stage();
            calculatorStage.setTitle("Геология");
            calculatorStage.setScene(new Scene(root, 1500, 1100));
            calculatorStage.setResizable(false);
            calculatorStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Не удалось загрузить окно калькулятора");
        }
    }

    @FXML
    private void exitApplication() {
        Platform.exit();
    }
}
