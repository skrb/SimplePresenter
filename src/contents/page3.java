package contents;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import net.javainthebox.jfx.simplepresenter.PageController;
import net.javainthebox.jfx.simplepresenter.SimplePresenter;

public class page3 implements PageController, Initializable {

    @FXML
    AnchorPane pane;

    @FXML
    ImageView p0;
    
    @FXML
    Text p1;
    
    @FXML
    Text p2;
    
    private int index = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        p1.setOpacity(0.0);
        p2.setOpacity(0.0);
    }

    @Override
    public boolean doAction() {
        switch (index) {
            case 0:
                FadeTransition fadein = new FadeTransition(new Duration(1000L));
                fadein.setNode(p1);
                fadein.setToValue(1.0);
                fadein.play();
                index++;

                return true;
            case 1:
                FadeTransition fadein2 = new FadeTransition(new Duration(1000L));
                fadein2.setNode(p2);
                fadein2.setToValue(1.0);
                fadein2.play();
                
                index++;

                return true;
            case 2:
                Rectangle rect = new Rectangle(0.0, 0.0, SimplePresenter.WIDTH, SimplePresenter.HEIGHT);
                rect.setFill(null);
                pane.getChildren().add(rect);
                
                FillTransition fill
                        = new FillTransition(new Duration(5000L), rect,
                                             Color.rgb(0x00, 0x00, 0x00, 0.0), Color.web("#000033"));
                
                FadeTransition fadeout = new FadeTransition(new Duration(1000L));
                fadeout.setNode(pane);
                fadeout.setToValue(0.0);
                
                SequentialTransition sequential = new SequentialTransition(fill, fadeout);
                sequential.play();
                
                index++;
                
                return true;
            default:
                return false;
        }
    }
}
