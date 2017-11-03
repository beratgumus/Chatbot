
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


    private static double xOffset = 0;
    private static double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        Parent anchorPane = FXMLLoader.load(getClass().getResource("sample.fxml"));
        root = (AnchorPane) anchorPane;
        root.setBackground(Background.EMPTY);
        header = ((Rectangle) root.lookup("#header"));

        header.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = primaryStage.getX() - event.getScreenX();
                yOffset = primaryStage.getY() - event.getScreenY();
            }
        });

        header.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() + xOffset);
                primaryStage.setY(event.getScreenY() + yOffset);
            }
        });


        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(anchorPane,  root.getPrefWidth(), root.getPrefHeight());
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void runApp() {
        launch();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
