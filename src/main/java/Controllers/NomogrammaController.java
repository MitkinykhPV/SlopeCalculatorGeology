package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Polygon;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.InputStream;
import javafx.scene.Group;
import Models.ModelNomogramma.GROUP;
import java.util.List;
import Models.ModelNomogramma;
// импорты для анимации
import javafx.animation.ScaleTransition;  // Для ScaleTransition
import javafx.util.Duration;  // Для Duration



public class NomogrammaController implements Initializable {
    @FXML
    private Group scaleGroup;
//КЛАССЫ
    @FXML
    private Polygon HClass1;
    @FXML
    private Polygon HClass2;
    @FXML
    private Polygon HClass3;
    @FXML
    private Polygon HClass4;
    @FXML
    private Polygon HClass5;
    @FXML
    private Polygon HClass6;
    @FXML
    private Polygon HClass7;
    @FXML
    private Polygon HClass8;
    @FXML
    private Polygon HClass9;
    @FXML
    private Polygon HClass10;
    @FXML
    private Polygon HClass11;
    @FXML
    private Polygon HClass12;
    @FXML
    private Polygon HClass13;
    @FXML
    private Polygon HClass14;
    @FXML
    private Polygon HClass15;
    @FXML
    private Polygon backgroundTriangle;
    // КНОПКИ НАЗАД И ВЫХОД
//    @FXML
//    private Button backButton;
//    @FXML
//    private Button exitButton;

    private CalculatorController calculatorController;

    private CavingController cavingController;

@Override
public void initialize(URL location, ResourceBundle resources) {
    loadTextures();
    setupInteractivity();
}
    public void setCalculatorController(CalculatorController controller) {
        this.calculatorController = controller;
    }
    public void setCavingController(CavingController controller){
        this.cavingController = controller;
    }
private void loadTextures() {
    try {
        Image[] geoImages = new Image[16]; // индексы 1-15

//                 Загружаем изображения через InputStream
        for (int i = 1; i <= 15; i++) {
            String path = "/images/RockClass/GeoClass" + i + ".jpg";
            InputStream stream = getClass().getResourceAsStream(path);

            geoImages[i] = new Image(stream);
        }
        Polygon[] hClasses = {null, HClass1, HClass2, HClass3, HClass4, HClass5,
                HClass6, HClass7, HClass8, HClass9, HClass10,
                HClass11, HClass12, HClass13, HClass14, HClass15};


        // Применяем к полигонам
        for (int i = 1; i <= 15; i++) {
            hClasses[i].setFill(new ImagePattern(geoImages[i]));
        }

        System.out.println("Текстуры успешно загружены!");

    } catch (Exception e) {
        System.err.println("Ошибка загрузки текстур: " + e.getMessage());
        e.printStackTrace();

        // Запасные цвета
        Polygon[] hClasses = {null, HClass1, HClass2, HClass3, HClass4, HClass5,
                HClass6, HClass7, HClass8, HClass9, HClass10,
                HClass11, HClass12, HClass13, HClass14, HClass15};

        Color fallbackColor = Color.web("#f4c0b6");
        for (int i = 1; i <= 15; i++) {
            hClasses[i].setFill(fallbackColor);
        }
    }
}

    private void setupInteractivity() {
        Polygon[] hClasses = {null, HClass1, HClass2, HClass3, HClass4, HClass5,
                HClass6, HClass7, HClass8, HClass9, HClass10,
                HClass11, HClass12, HClass13, HClass14, HClass15};

        // Эффекты при наведении для всех
        for (int i = 1; i <= 15; i++) {
            if (hClasses[i] != null) {
                setupHoverEffect(hClasses[i]);
            }
        }

        // Обработчики кликов - передаем номер класса
        for (int i = 1; i <= 15; i++) {
            final int classNumber = i;  // Переименовываем для ясности
            if (hClasses[i] != null) {
                hClasses[i].setOnMouseClicked(event ->
                        handleRockClick(classNumber, hClasses[classNumber]));  // Используем classNumber
            }
        }
    }

private void setupHoverEffect(Polygon rock) {
    DropShadow dropShadow = new DropShadow();
    dropShadow.setRadius(5);
    dropShadow.setOffsetX(2);
    dropShadow.setOffsetY(2);
    dropShadow.setColor(Color.rgb(0, 0, 0, 0.3));

    rock.setOnMouseEntered(event -> {
        rock.setEffect(dropShadow);
        rock.setScaleX(1.02);
        rock.setScaleY(1.02);
        rock.toFront(); // Поднимаем поверх других элементов
    });

    rock.setOnMouseExited(event -> {
        rock.setEffect(null);
        rock.setScaleX(1.0);
        rock.setScaleY(1.0);
        // Убираем rock.setTranslateZ(0);
    });
}

    private void handleRockClick(int classNum, Polygon rock) {  // Переименовываем параметр
        System.out.println("Выбран класс: " + classNum);  // Используем classNum

        // Анимация клика
        ScaleTransition scaleTransition =
                new ScaleTransition(
                        Duration.millis(100),
                        rock
                );
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(0.95);
        scaleTransition.setToY(0.95);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(2);
        scaleTransition.play();

        // Получаем значения для выбранного класса из модели
        GROUP selectedGroup = getGroupByNumber(classNum);  // Используем classNum
        List<String> values = ModelNomogramma.getGroupValues(selectedGroup);

        if (values != null && values.size() >= 2) {
            System.out.println("  Коэф разрыхления: " + values.get(0));
            System.out.println("  Плотность: " + values.get(1));

            // Передаем значения в калькулятор
            if (calculatorController != null) {
                calculatorController.setClassValues(
                        Double.parseDouble(values.get(0)), // kr
                        Double.parseDouble(values.get(1))  // yo
                );

                System.out.println("Значения переданы в первый калькулятор!");
            } else {
                System.out.println("Калькулятор не найден (окно закрыто?)");
            }
            if (cavingController != null) {
                cavingController.setClassValues(
                        Double.parseDouble(values.get(0))

                );

                System.out.println("Значения переданы во второй калькулятор!");
            } else {
                System.out.println("Калькулятор не найден (окно закрыто?)");
            }


            // Показываем всплывающее уведомление
            showClassSelectedNotification(classNum, values);  // Используем classNum
        }
    }
    private GROUP getGroupByNumber(int classNumber) {
        switch (classNumber) {
            case 1: return GROUP.GROUP_1;
            case 2: return GROUP.GROUP_2;
            case 3: return GROUP.GROUP_3;
            case 4: return GROUP.GROUP_4;
            case 5: return GROUP.GROUP_5;
            case 6: return GROUP.GROUP_6;
            case 7: return GROUP.GROUP_7;
            case 8: return GROUP.GROUP_8;
            case 9: return GROUP.GROUP_9;
            case 10: return GROUP.GROUP_10;
            case 11: return GROUP.GROUP_11;
            case 12: return GROUP.GROUP_12;
            case 13: return GROUP.GROUP_13;
            case 14: return GROUP.GROUP_14;
            case 15: return GROUP.GROUP_15;
            default: return GROUP.GROUP_1;
        }
    }

    private void showClassSelectedNotification(int classNumber, List<String> values) {
        // Создаем временное уведомление
        javafx.scene.control.Label notification = new javafx.scene.control.Label(
                String.format("Класс %d: Kр=%s, ρ=%s т/м³",
                        classNumber, values.get(0), values.get(1))
        );
        notification.setStyle(
                "-fx-background-color: rgba(0,100,0,0.8);" +
                        "-fx-text-fill: white;" +
                        "-fx-padding: 10;" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;" +
                        "-fx-font-size: 14;"
        );

        // Добавляем уведомление в сцену
        javafx.scene.Parent root = scaleGroup.getScene().getRoot();
        javafx.scene.layout.StackPane stackPane = new javafx.scene.layout.StackPane(notification);
        stackPane.setLayoutX(root.getScene().getWidth() - 250);
        stackPane.setLayoutY(20);

        if (root instanceof javafx.scene.layout.Pane) {
            ((javafx.scene.layout.Pane) root).getChildren().add(stackPane);

            // Удаляем через 2 секунды
            javafx.animation.PauseTransition pause =
                    new javafx.animation.PauseTransition(javafx.util.Duration.seconds(2));
            pause.setOnFinished(event ->
                    ((javafx.scene.layout.Pane) root).getChildren().remove(stackPane));
            pause.play();
        }
    }
}