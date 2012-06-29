package contents;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import net.javainthebox.jfx.simplepresenter.PageController;
import net.javainthebox.jfx.simplepresenter.SimplePresenter;

public class page2 implements PageController, Initializable {

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
    private int index = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        p1.setOpacity(0.0);
        p2.setTranslateY(SimplePresenter.HEIGHT);
        p3.setOpacity(0.0);
        p4.setOpacity(0.0);
    }

    @Override
    public boolean doAction() {
        switch (index) {
            case 0:
                TranslateTransition translte = new TranslateTransition(new Duration(1000L));
                translte.setNode(p2);
                translte.setToY(0.0);
                translte.play();

                index++;
                
                return true;
            case 1:
                p3.setOpacity(1.0);
                ScaleTransition scale = new ScaleTransition(new Duration(1000L));
                scale.setNode(p3);
                scale.setFromX(0.01);
                scale.setFromY(0.01);
                scale.setToX(1.0);
                scale.setToY(1.0);
                scale.play();

                index++;
                
                return true;
            case 2:
                p4.setOpacity(1.0);
                scale = new ScaleTransition(new Duration(1000L));
                scale.setNode(p4);
                scale.setFromX(0.01);
                scale.setFromY(0.01);
                scale.setToX(1.0);
                scale.setToY(1.0);

                RotateTransition rotate = new RotateTransition(new Duration(1000));
                rotate.setNode(p4);
                rotate.setByAngle(360*3);
                rotate.setInterpolator(Interpolator.EASE_IN);
                
                new ParallelTransition(scale, rotate).play();
                
                index++;
                
                return true;
            default:
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
