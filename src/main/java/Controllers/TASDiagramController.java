package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

public class TASDiagramController implements Initializable {


    @FXML
    private Group TAS;

    @FXML
    private Polygon ultramafikPoligon;

    @FXML
    private Polygon maficPolygonLower;
    @FXML
    private Polygon maficPolygonMiddle;
    @FXML
    private Polygon maficPolygonUpper;

    @FXML
    private Polygon intermediatePolygonLower;
    @FXML
    private Polygon intermediatePolygonMiddle;
    @FXML
    private Polygon intermediatePolygonUpper;

    @FXML
    private Polygon AcidPolygonLower;
    @FXML
    private Polygon AcidPolygonMiddle;
    @FXML
    private Polygon AcidPolygonUpper;

    @FXML
    private Button backButton;
    @FXML
    private Button exitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupInteractivity();
//      playBackgroundMusic();

    }

    private void setupInteractivity() {
        //             Эффекты при наведении
        setupHoverEffect(ultramafikPoligon);

        setupHoverEffect(maficPolygonLower);
        setupHoverEffect(maficPolygonMiddle);
        setupHoverEffect(maficPolygonUpper);

        setupHoverEffect(intermediatePolygonLower);
        setupHoverEffect(intermediatePolygonMiddle);
        setupHoverEffect(intermediatePolygonUpper);

        setupHoverEffect(AcidPolygonLower);
        setupHoverEffect(AcidPolygonMiddle);
        setupHoverEffect(AcidPolygonUpper);

//             Обработчики кликов
        ultramafikPoligon.setOnMouseClicked(event -> handleRockClick("Ультраосновные порода", ultramafikPoligon));

        maficPolygonLower.setOnMouseClicked(event -> handleRockClick("Основная нормальная", maficPolygonLower));
        maficPolygonMiddle.setOnMouseClicked(event -> handleRockClick("Основная транзитная", maficPolygonMiddle));
        maficPolygonUpper.setOnMouseClicked(event -> handleRockClick("Основная щелочная", maficPolygonUpper));

        intermediatePolygonLower.setOnMouseClicked(event -> handleRockClick("Средняя нормальная", intermediatePolygonLower));
        intermediatePolygonMiddle.setOnMouseClicked(event -> handleRockClick("Средняя транзитная", intermediatePolygonMiddle));
        intermediatePolygonUpper.setOnMouseClicked(event -> handleRockClick("Средняя щелочная", intermediatePolygonUpper));

        AcidPolygonLower.setOnMouseClicked(event -> handleRockClick("Кислая нормальная", AcidPolygonLower));
        AcidPolygonMiddle.setOnMouseClicked(event -> handleRockClick("Кислая транзитная", AcidPolygonMiddle));
        AcidPolygonUpper.setOnMouseClicked(event -> handleRockClick("Кислая щелочная", AcidPolygonUpper));
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
        double currentScaleX = TAS.getScaleX();
        double currentScaleY = TAS.getScaleY();

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
