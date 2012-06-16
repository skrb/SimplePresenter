package contents;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        p1.setOpacity(0.0);
    }    

    @Override
    public boolean doAction() {
        if (p1.getOpacity() == 0.0) {
            p1.setOpacity(1.0);
            
            TranslateTransition translte = new TranslateTransition(new Duration(1000L));
            translte.setNode(p1);
            translte.setFromY(SimplePresenter.HEIGHT);
            translte.setToY(0.0);
            translte.play();
            
            return true;
        } else {
            return false;
        }
    }
}
