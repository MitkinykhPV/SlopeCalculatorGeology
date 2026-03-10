package Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javax.sound.sampled.*;
import java.io.InputStream;
import java.io.BufferedInputStream;


    public class GeologyController implements Initializable {
        @FXML
        private Group GeoGroup;

        @FXML
        private Polygon igneousRock;
        @FXML
        private Polygon sedimentaryRock;
        @FXML
        private Polygon metamorphicRock;
        @FXML
        private Polygon backgroundTriangle;
        // КНОПКИ НАЗАД И ВЫХОД
        @FXML
        private Button backButton;
        @FXML
        private Button exitButton;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            loadTextures();
            setupInteractivity();
        }

        private void loadTextures() {
            try {
//                 Загружаем изображения через InputStream
                InputStream igneousStream = getClass().getResourceAsStream("/images/igneous.jpg");
                if (igneousStream == null) {
                    throw new Exception("Не найден файл: /images/igneous.jpg");
                }
                Image igneousImage = new Image(igneousStream);

                InputStream sedimentaryStream = getClass().getResourceAsStream("/images/sedimentary.jpg");
                if (sedimentaryStream == null) {
                    throw new Exception("Не найден файл: /images/sedimentary.jpg");
                }
                Image sedimentaryImage = new Image(sedimentaryStream);

                InputStream metamorphicStream = getClass().getResourceAsStream("/images/metamorphic.jpg");
                if (metamorphicStream == null) {
                    throw new Exception("Не найден файл: /images/metamorphic.jpg");
                }
                Image metamorphicImage = new Image(metamorphicStream);

                // Применяем к полигонам
                igneousRock.setFill(new ImagePattern(igneousImage));
                sedimentaryRock.setFill(new ImagePattern(sedimentaryImage));
                metamorphicRock.setFill(new ImagePattern(metamorphicImage));

                System.out.println("Текстуры успешно загружены!");

            } catch (Exception e) {
                System.err.println("Ошибка загрузки текстур: " + e.getMessage());
                e.printStackTrace();

                // Запасные цвета
                igneousRock.setFill(Color.web("#f4c0b6"));
                sedimentaryRock.setFill(Color.web("#f6e0aa"));
                metamorphicRock.setFill(Color.web("#cef6bc"));
            }
        }

        private void setupInteractivity() {
           //             Эффекты при наведении
            setupHoverEffect(igneousRock);
            setupHoverEffect(sedimentaryRock);
            setupHoverEffect(metamorphicRock);

//             Обработчики кликов
            igneousRock.setOnMouseClicked(event -> handleRockClick("Магматическая порода", igneousRock));
            sedimentaryRock.setOnMouseClicked(event -> handleRockClick("Осадочная порода", sedimentaryRock));
            metamorphicRock.setOnMouseClicked(event -> handleRockClick("Метаморфическая порода", metamorphicRock));
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
            double currentScaleX = GeoGroup.getScaleX();
            double currentScaleY = GeoGroup.getScaleY();

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
//        private void playBackgroundMusic() {
//            try {
//                // Путь к вашему MP3 файлу
//                String musicPath = "/sounds/background_music.mp3";
//                URL musicUrl = getClass().getResource(musicPath);
//
//                if (musicUrl == null) {
//                    System.err.println("Не найден файл музыки: " + musicPath);
//                    return;
//                }
//
//                Media media = new Media(musicUrl.toString());
//                mediaPlayer = new MediaPlayer(media);
//
//                // Настройки воспроизведения
//                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Бесконечный повтор
//                mediaPlayer.setVolume(0.5); // Громкость 50%
//
//                // Запускаем музыку
//                mediaPlayer.play();
//
//                System.out.println("Фоновая музыка запущена");
//
//            } catch (Exception e) {
//                System.err.println("Ошибка при запуске музыки: " + e.getMessage());
//                e.printStackTrace();
//            }
//        }

        @FXML
        private void goBack(ActionEvent event) {  // Добавляем параметр event
            try {
                // Закрываем текущее окно
                Stage geologyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                geologyStage.close();

                // Открываем окно выбора
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/viwes/DungeonCommander.fxml"));
                Parent root = loader.load();

                Stage choiceStage = new Stage();
                choiceStage.setTitle("Пехотинец - Выбор расчета");
                choiceStage.setScene(new Scene(root, 600, 400));
                choiceStage.setResizable(false);
                choiceStage.show();

            } catch (Exception e) {
                e.printStackTrace();
                // Можно показать alert об ошибке
                showErrorAlert("Ошибка", "Не удалось открыть окно выбора: " + e.getMessage());
            }
        }
        private void showErrorAlert(String title, String content) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        }



        @FXML
        private void exitApplication() {
            Platform.exit();
        }
    }

