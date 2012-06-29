package contents;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import net.javainthebox.jfx.simplepresenter.PageController;

public class page3 implements PageController, Initializable {

    @FXML
    AnchorPane pane;

    @FXML
    ImageView p0;
    
    @FXML
    Text p1;
    
    @FXML
    Button p2;
    
    @FXML
    TextArea p3;

    @FXML
    Button p4;
    
    private int index = 0;

    // Javaをコンパイルして、実行するためのスクリプトエンジン
    private ScriptEngine engine;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ScriptEngineManager manager = new ScriptEngineManager();
        engine = manager.getEngineByName("java");
        ScriptContext context = engine.getContext();
        // メインクラスとファイル名を設定
        context.setAttribute("mainClass",
                "AnimationDemo",
                ScriptContext.ENGINE_SCOPE);
        context.setAttribute(ScriptEngine.FILENAME,
                "AnimationDemo.java",
                ScriptContext.ENGINE_SCOPE);
        
        p1.setOpacity(0.0);
        p2.setOpacity(0.0);
        p2.setDisable(true);
        p3.setOpacity(0.0);
        p3.setDisable(true);
        p4.setOpacity(0.0);
        p4.setDisable(true);
    }

    @Override
    public boolean doAction() {
        switch (index) {
            case 0:
                FadeTransition fadein = new FadeTransition(new Duration(1000L));
                fadein.setNode(p2);
                fadein.setToValue(1.0);
                fadein.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        p2.setDisable(false);
                    }
                });
                fadein.play();
                index++;

                return true;
            case 1:
                FadeTransition fadeout = new FadeTransition(new Duration(500L));
                fadeout.setNode(p2);
                fadeout.setToValue(0.0);
                fadeout.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        p2.setDisable(true);
                    }
                });
                fadeout.play();
                
                fadein = new FadeTransition(new Duration(1000L));
                fadein.setNode(p3);
                fadein.setToValue(1.0);
                fadein.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        p3.setDisable(false);
                    }
                });
                fadein.play();
                
                fadein = new FadeTransition(new Duration(1000L));
                fadein.setNode(p4);
                fadein.setToValue(1.0);
                fadein.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        p4.setDisable(false);
                    }
                });
                fadein.play();

                index++;

                return true;
            case 2:
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
    
    public void executeDemo(ActionEvent event) {
        // デモの実行
        AnimationDemo demo = new AnimationDemo();
        Stage stage = new Stage();
        demo.start(stage);
    }

    public void compileAndExecuteDemo(ActionEvent event) {
        try {
            // スクリプトの実行
            engine.eval(p3.getText());
        } catch (ScriptException ex) {
            System.err.println("スクリプトの実行に失敗しました");
            ex.printStackTrace();
        }
    }
}
