package contents;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimationDemo {
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 500);
        
        ImageView image = new ImageView(new Image(this.getClass().getResourceAsStream("DukeWithHelmet.png")));
        image.setLayoutX(50); image.setLayoutY(20);
        root.getChildren().add(image);
        
        RotateTransition rotate
            = new RotateTransition(new Duration(3000));
        rotate.setNode(image);
        rotate.setToAngle(360);
        rotate.setAutoReverse(true);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.EASE_BOTH);
        rotate.play();
        
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        AnimationDemo demo = new AnimationDemo();
        Stage stage = new Stage();
        demo.start(stage);
    }
}