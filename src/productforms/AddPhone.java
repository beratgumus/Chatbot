package productforms;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AddPhone extends Application {
    @FXML
    public Rectangle header;

    @FXML
    public AnchorPane root;

    //for draggable window
    private static double xOffset = 0;
    private static double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        AddPhoneController controller = new AddPhoneController();
        Parent anchorPane = FXMLLoader.load(getClass().getResource("AddPhone.fxml"));
        root = (AnchorPane) anchorPane;
        root.setBackground(Background.EMPTY);
        header = ((Rectangle) root.lookup("#header"));

        //fix for draggable window with custom title bar
        header.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = primaryStage.getX() - event.getScreenX();
                yOffset = primaryStage.getY() - event.getScreenY();
            }
        });

        //fix for draggable window with custom title bar
        header.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() + xOffset);
                primaryStage.setY(event.getScreenY() + yOffset);
            }
        });

        //make background transparent for window shadow
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(anchorPane,  root.getPrefWidth(), root.getPrefHeight());
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void runApp() {
        launch();
    }

    //for test purposes
    public static void main(String[] args) {
        launch(args);
    }
}
