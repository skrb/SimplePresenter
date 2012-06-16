package contents;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import net.javainthebox.jfx.simplepresenter.PageController;

public class page1 implements PageController, Initializable {
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
            FadeTransition fadein = new FadeTransition(new Duration(1000L));
            fadein.setNode(p1);
            fadein.setToValue(1.0);
            fadein.play();

            return true;
        } else {
            return false;
        }
    }
}
