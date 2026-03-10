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


@Override
public void initialize(URL location, ResourceBundle resources) {
    loadTextures();
    setupInteractivity();
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
        setupHoverEffect(hClasses[i]);
    }

    // Обработчики кликов
    for (int i = 1; i <= 15; i++) {
        final int index = i; // нужно для лямбды
        hClasses[i].setOnMouseClicked(event ->
                handleRockClick("Класс" + index, hClasses[index]));
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

private void handleRockClick(String rockType, Polygon rock) {
    System.out.println("Выбрана: " + rockType);

    // Сохраняем текущий масштаб группы
    double currentScaleX = scaleGroup.getScaleX();
    double currentScaleY = scaleGroup.getScaleY();

    // Анимируем только сам полигон, не влияя на остальное
    javafx.animation.ScaleTransition scaleTransition =
            new javafx.animation.ScaleTransition(
                    javafx.util.Duration.millis(100),
                    rock
            );
    scaleTransition.setFromX(1.0);
    scaleTransition.setFromY(1.0);
    scaleTransition.setToX(0.95);
    scaleTransition.setToY(0.95);
    scaleTransition.setAutoReverse(true);
    scaleTransition.setCycleCount(2);
    scaleTransition.play();
}
}