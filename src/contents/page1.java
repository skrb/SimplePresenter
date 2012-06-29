package contents;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import net.javainthebox.jfx.simplepresenter.PageController;
import net.javainthebox.jfx.simplepresenter.SimplePresenter;

public class page1 implements PageController, Initializable {

    @FXML
    ImageView p0;
    @FXML
    Text p1;
    @FXML
    Pane p2;
    private int index = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        p0.setOpacity(0.0);
        p1.setOpacity(0.0);
        p2.setTranslateY(SimplePresenter.HEIGHT);
    }

    @Override
    public boolean doAction() {
        switch (index) {
            case 0:
                // Slide the title out
                TranslateTransition slideout = new TranslateTransition(new Duration(1000L));
                slideout.setNode(p1);
                slideout.setToY(-SimplePresenter.HEIGHT);
                slideout.play();

                // Slide agenda in
                TranslateTransition slidein = new TranslateTransition(new Duration(1000L));
                slidein.setNode(p2);
                slidein.setToY(0.0);
                slidein.play();

                index++;
                
                return true;
            default:
                return false;
        }
    }

    @Override
    public void doPageEnteredAction() {
        FadeTransition fadein = new FadeTransition(new Duration(4000));
        fadein.setNode(p0);
        fadein.setToValue(1.0);
        fadein.play();
        
        fadein = new FadeTransition(new Duration(4000));
        fadein.setNode(p1);
        fadein.setToValue(1.0);
        new SequentialTransition(
            new PauseTransition(new Duration(2000)),
            fadein
        ).play();
    }
}
