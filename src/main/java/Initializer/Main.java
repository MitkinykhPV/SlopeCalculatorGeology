
package Initializer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    //Комент ради комента
    @Override
    public void start(Stage primaryStage) throws Exception {
        // загружаем окно главное
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viwes/DungeonCommander.fxml"));
        Parent root = loader.load();

        // Настраиваем сцену для окна выбора
        Scene scene = new Scene(root, 600, 400);

        // Настраиваем stage
        primaryStage.setTitle("Пехотинец - Выбор расчета");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}