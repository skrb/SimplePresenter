package contents;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import net.javainthebox.jfx.simplepresenter.PageController;
import net.javainthebox.jfx.simplepresenter.SimplePresenter;

public class page4 implements PageController, Initializable {

    @FXML
    ImageView p0;
    @FXML
    Text p1;
    @FXML
    Text p2;
    @FXML
    Text p3;
    @FXML
    Text p4;
    @FXML
    ImageView p5;

    private int index = 0;
    private Timeline shakeAnimation;
    private Image[] images;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        p1.setOpacity(0.0);
        p2.setTranslateX(SimplePresenter.WIDTH);
        p3.setTranslateX(SimplePresenter.WIDTH);
        p4.setOpacity(0.0);
        p5.setOpacity(0.0);
        
        images = new Image[4];
        for (int i = 0; i < 4; i++) {
            final Image image = new Image("contents/dukewave" + i + ".png");
            images[i] = image;
        }
        
        p5.setImage(images[0]);

        shakeAnimation = new Timeline(
            new KeyFrame(
                new Duration(0),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        p5.setImage(images[0]);
                    }
                }
            ),
            new KeyFrame(
                new Duration(100),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        p5.setImage(images[1]);
                    }
                }
            ),
            new KeyFrame(
                new Duration(200),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        p5.setImage(images[2]);
                    }
                }
            ),
            new KeyFrame(
                new Duration(300),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        p5.setImage(images[3]);
                    }
                }
            ),
            new KeyFrame(
                new Duration(400),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        p5.setImage(images[3]);
                    }
                }
            ),
            new KeyFrame(
                new Duration(500),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        p5.setImage(images[2]);
                    }
                }
            ),
            new KeyFrame(
                new Duration(600),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        p5.setImage(images[1]);
                    }
                }
            ),
            new KeyFrame(
                new Duration(700),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        p5.setImage(images[0]);
                    }
                }
            )
        );
        shakeAnimation.setCycleCount(Timeline.INDEFINITE);
    }

    @Override
    public boolean doAction() {
        switch (index) {
            case 0:
                TranslateTransition translte = new TranslateTransition(new Duration(1000L));
                translte.setNode(p2);
                translte.setToX(0.0);
                translte.play();

                index++;
                
                return true;
            case 1:
                translte = new TranslateTransition(new Duration(1000L));
                translte.setNode(p3);
                translte.setToX(0.0);
                translte.play();

                index++;
                
                return true;
            case 2:
                FadeTransition fadein = new FadeTransition(new Duration(1000L));
                fadein.setNode(p4);
                fadein.setToValue(1.0);
                fadein.play();
                fadein = new FadeTransition(new Duration(1000L));
                fadein.setNode(p5);
                fadein.setToValue(1.0);
                fadein.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        shakeAnimation.play();
                    }
                });
                fadein.play();
                
                index++;
                
                return true;
            default:
                shakeAnimation.stop();
                return false;
        }
    }

    @Override
    public void doPageEnteredAction() {
        FadeTransition fadein = new FadeTransition(new Duration(1000L));
        fadein.setNode(p1);
        fadein.setToValue(1.0);
        fadein.play();
    }
}
