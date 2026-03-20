package Controllers;

import Services.ServisCaving;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Polygon;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.InputStream;
import java.io.IOException;
import javafx.application.Platform;

public class CavingController {

    // ПОЛЯ ВВОДА ДАННЫХ
    @FXML
    private TextField hoField;
    @FXML
    private TextField krField;

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

    @FXML
    private Polygon Spravka;
    //
    private NomogrammaController nomogrammaController;

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

    public void setClassValues(double kr) {
        if (krField != null) {
            // Форматируем с двумя знаками после запятой
            krField.setText(String.format("%.2f", kr));

            // Подсвечиваем измененные поля (опционально)
            // highlightField(krField);


        }
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

    private void loadSpravkaTexture() {
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

            NomogrammaController nomogrammaController = loader.getController();
            nomogrammaController.setCavingController(this);

            Stage spravkaStage = new Stage();
            spravkaStage.setTitle("Справка");
            spravkaStage.setScene(new Scene(root, 1300, 1040));
            spravkaStage.setResizable(false);

            // Устанавливаем модальность, чтобы блокировать основное окно (опционально)
            spravkaStage.initModality(Modality.APPLICATION_MODAL);
            spravkaStage.initOwner(Spravka.getScene().getWindow());

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
            if (hoField == null || krField == null) {
                System.out.println("Ошибка: поля не инициализированы");
                return;
            }

            // Универсальный парсер, который работает и с точкой, и с запятой
            double ho = parseDouble(hoField.getText());
            double kp = parseDouble(krField.getText());

            ServisCaving calculator = new ServisCaving(ho, kp);

            double h = calculator.calculateH();
            double hp = calculator.calculateHp();

            hResult.setText(String.format("%.2f", h));
            hpResult.setText(String.format("%.2f", hp));

        } catch (NumberFormatException e) {
            hResult.setText("Ошибка");
            hpResult.setText("Ошибка");
            System.out.println("Ошибка формата числа: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка расчета: " + e.getMessage());
        }
    }

    // Вспомогательный метод для парсинга с запятой или точкой
    private double parseDouble(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new NumberFormatException("Пустое значение");
        }

        // Заменяем запятую на точку
        String normalized = value.trim().replace(',', '.');
        return Double.parseDouble(normalized);
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
    private void Spravka() {

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
        if (krField != null) krField.setText("0.0");
    }

    @FXML
    private void exitApplication() {
        Platform.exit();
    }
}
