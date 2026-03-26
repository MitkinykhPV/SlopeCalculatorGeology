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

public class IgnousFormationController implements Initializable{

    @FXML
    private Polygon tuffites;
    @FXML
    private Polygon tuffs;
    @FXML
    private Polygon lavabreccias;
    @FXML
    private Polygon lavas;
    @FXML
    private Polygon subvolcanic;
    @FXML
    private Polygon intrusive;

    @FXML
    private Polygon goBack;
    @FXML
    private Polygon goNext;

    @Override
    public void initialize(URL Location, ResourceBundle resources){
        loadTextures();
        setupInteractivity();
    }
    private void loadTextures(){

    }
    private void setupInteractivity(){

    }
}
