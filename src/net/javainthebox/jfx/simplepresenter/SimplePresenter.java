package net.javainthebox.jfx.simplepresenter;

import java.io.IOException;
import java.net.URL;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class SimplePresenter extends Application {
    public static final double WIDTH = 800.0;
    public static final double HEIGHT = 600.0;
    
    // 表示するページ群
    private String[] pages = {
        "/contents/page1.fxml",
        "/contents/page2.fxml",
        "/contents/page3.fxml",
        "/contents/page4.fxml"
    };
    
    // 現在表示しているページ番号
    private int pageIndex;
    
    // 現在表示しているページのコントローラ
    private PageController pageController;
    
    // シーングラフのルート要素
    private Group root;
    
    // ポップアップメニュー
    private ContextMenu menu;
    
    @Override
    public void start(Stage stage) throws Exception {
        // ステージを透明にする
        stage.initStyle(StageStyle.TRANSPARENT);
        
        root = new Group();
        root.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    try {
                        // マウスクリックされたら、進める
                        doAction();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    menu.show((Node)event.getSource(), event.getScreenX(), event.getScreenY());                    
                }
            }
        });
        
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(null);
        stage.setScene(scene);
        stage.show();
        
        // ポップアップメニューの初期化
        initContextMenu();
        
        // 最初のページを表示する
        goForward();
    }
    
    // ポップアップメニューの初期化
    private void initContextMenu() {
        MenuItem item = new MenuItem("Exit");
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
        menu = new ContextMenu(item);
    }
    
    private void doAction() throws IOException {
        // コントローラ側でページ内の動きをつける
        // これ以上アクションがなければ、falseが戻るので
        // ページを進める
        if (!pageController.doAction()) {
            goForward();
        }
    }
    
    // ページを進める
    private void goForward() throws IOException {
        // 次のページをロードして、表示する
        URL url = getClass().getResource(pages[pageIndex]);
        FXMLLoader loader  = new FXMLLoader(url);
        Node next = (Node)loader.load();
        root.getChildren().add(next);

        // ページのコントローラを取得
        pageController = loader.getController();
        
        Node present = null;
        if (root.getChildren().size() > 1) {
            // 前のページが存在していれば、presentに代入
            present = root.getChildren().get(0);

            // ページ遷移のアニメーションを行う
            translatePage(next, present);
        } else {
            // 一番始めはページ遷移がないので、ページ側のアクションを呼ぶ
            pageController.doPageEnteredAction();
        }
        
        
        // ページインデックスを進める
        // 最後までいったら最初に戻す
        pageIndex++;
        if (pageIndex >= pages.length) {
            pageIndex = 0;
        }
    }

    // ページ遷移アニメーション
    private void translatePage(Node next, final Node present) {
        // 新しいページを右からスライドさせるアニメーション
        TranslateTransition slidein 
                = new TranslateTransition(new Duration(1000));
        slidein.setNode(next);
        slidein.setFromX(WIDTH);
        slidein.setToX(0);
        slidein.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // ページ遷移アニメーションが終了した時にページで行う処理
                pageController.doPageEnteredAction();
            }
        });
        slidein.play();
        
        if (present != null) {
            // 現在表示しているページがあれば、
            // 左にスライドさせる
            TranslateTransition slideout 
                    = new TranslateTransition(new Duration(1000));
            slideout.setNode(present);
            slideout.setToX(-WIDTH);
            slideout.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // アニメーションが終了したら、
                    // シーングラフから削除する
                    root.getChildren().remove(present);
                }
            });
            slideout.play();
        }
    }

    public void main(String... args) {
        launch(args);
    }
}
