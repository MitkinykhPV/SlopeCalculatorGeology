package Controllers;

import Services.ServisCalculator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;


import javafx.fxml.Initializable;
import javafx.scene.shape.Polygon;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;
import java.util.ResourceBundle;
import java.io.InputStream;
import javafx.scene.Group;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;

import java.io.IOException;

public class CalculatorController {
    //Комент ради комента
    // Все поля должны быть объявлены с @FXML
    @FXML
    private TextField soField;
    @FXML
    private TextField krField;
    @FXML
    private TextField yoField;
    @FXML
    private TextField hField;
    @FXML
    private TextField yField;
    @FXML
    private TextField fField;
    @FXML
    private TextField fiField;
    @FXML
    private TextField kzField;

    @FXML
    private Label sResult;
    @FXML
    private Label dResult;
    @FXML
    private Label hResult;
    @FXML
    private Polygon Spravka;
    @FXML
    private Button backButton; // Добавьте эту кнопку в FXML если хотите


    @FXML
    private void initialize() {
        // Добавьте проверку на null
        if (krField != null) {
            setDefaultValues();
        } else {
            System.out.println("Ошибка: поля не инициализированы");
        }
            setupSpravkaButton();
        }
    private void setupSpravkaButton() {
        if (Spravka != null) {
            // Сохраняем оригинальное заполнение (текстуру)
            ImagePattern originalTexture = null;
            if (Spravka.getFill() instanceof ImagePattern) {
                originalTexture = (ImagePattern) Spravka.getFill();
            }

            // Делаем полигон кликабельным
            Spravka.setOnMouseClicked(event -> openSpravkaWindow());

            // Добавляем эффекты при наведении
            Spravka.setOnMouseEntered(event -> {
                Spravka.setScaleX(1.1);
                Spravka.setScaleY(1.1);

                // ВАЖНО: Не меняем fill на сплошной цвет, а только добавляем эффект
                // Просто меняем яркость или добавляем эффект свечения
                DropShadow glow = new DropShadow(15, Color.SKYBLUE);
                glow.setSpread(0.3);
                Spravka.setEffect(glow);
            });

            Spravka.setOnMouseExited(event -> {
                Spravka.setScaleX(1.0);
                Spravka.setScaleY(1.0);

                // Убираем эффект, но оставляем текстуру
                Spravka.setEffect(null);
            });

            // Загружаем текстуру
            loadSpravkaTexture();
        }
    }
        private void loadSpravkaTexture () {
            try {
                // Загружаем изображение для кнопки справки
                InputStream imageStream = getClass().getResourceAsStream("/images/RockClass/ClassificationLabel.jpg");
                if (imageStream == null) {
                    // Если файл не найден, используем цвет
                    System.out.println("Изображение для справки не найдено, используется цвет");
                    return;
                }
                Image infoImage = new Image(imageStream);
                Spravka.setFill(new ImagePattern(infoImage));
            } catch (Exception e) {
                System.out.println("Не удалось загрузить текстуру для справки: " + e.getMessage());
            }
        }

        @FXML
        private void openSpravkaWindow() {
            try {
                // Загружаем окно справки (не закрывая текущее)
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/viwes/Nomogramma.fxml"));
                Parent root = loader.load();

                Stage spravkaStage = new Stage();
                spravkaStage.setTitle("Справка");
                spravkaStage.setScene(new Scene(root, 1300, 1040));
                spravkaStage.setResizable(false);

                // Устанавливаем модальность, чтобы блокировать основное окно (опционально)
                // spravkaStage.initModality(Modality.APPLICATION_MODAL);
                // spravkaStage.initOwner(Spravka.getScene().getWindow());

                spravkaStage.show();

            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Не удалось загрузить окно справки");

                // Показываем alert об ошибке
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Не удалось открыть справку");
                alert.setContentText("Файл справки не найден: /viwes/Nomogramma.fxml");
                alert.showAndWait();
            }
        }

    @FXML
    private void calculate() {
        try {
            // Проверка на null перед использованием
            if (soField == null || krField == null) {
                System.out.println("Ошибка: поля не инициализированы");
                return;
            }

            double so = Double.parseDouble(soField.getText());
            double kr = Double.parseDouble(krField.getText());
            double yo = Double.parseDouble(yoField.getText());
            double h = Double.parseDouble(hField.getText());
            double y = Double.parseDouble(yField.getText());
            double f = Double.parseDouble(fField.getText());
            double fi = Double.parseDouble(fiField.getText());
            double kz = Double.parseDouble(kzField.getText());

            ServisCalculator calculator = new ServisCalculator(so, kr, yo, h, y, f, fi, kz);

            double s = calculator.calculateS();
            double d = calculator.calculateD();
            double hCalc = calculator.calculateH();

            sResult.setText(String.format("%.2f", s));
            dResult.setText(String.format("%.2f", d));
            hResult.setText(String.format("%.2f", hCalc));

        } catch (NumberFormatException e) {
            sResult.setText("Ошибка");
            dResult.setText("Ошибка");
            hResult.setText("Ошибка");
        } catch (Exception e) {
            System.out.println("Ошибка расчета: " + e.getMessage());
        }
    }

    @FXML
    private void clearFields() {
        setDefaultValues();
        if (sResult != null && dResult != null && hResult != null) {
            sResult.setText("-");
            dResult.setText("-");
            hResult.setText("-");
        }
    }
    @FXML
    private void Spravka() {

    }
    @FXML
    private void goBack() {
        try {
            // Закрываем текущее окно калькулятора
            Stage calculatorStage = (Stage) soField.getScene().getWindow();
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
        if (soField != null) soField.setText("0.0");
        if (krField != null) krField.setText("0.0");
        if (yoField != null) yoField.setText("0.0");
        if (hField != null) hField.setText("0.0");
        if (yField != null) yField.setText("0.0");
        if (fField != null) fField.setText("0.0");
        if (fiField != null) fiField.setText("0.0");
        if (kzField != null) kzField.setText("0.0");
    }
    @FXML
    private void exitApplication() {
        Platform.exit();
    }
}