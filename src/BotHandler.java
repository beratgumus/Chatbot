
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import products.*;


public class BotHandler extends Application {
    private List<Product> products;
    private HashMap<String, String[]> botAnswers;
    private TreeNode rootNode;
    private TreeNode currentNode;
    private String[] options;
    private String state;

    private static final String adminName = "admin";
    private static final String adminPass = "pw123";

    @FXML
    public Rectangle header;

    @FXML
    public AnchorPane botAnchor;

    @FXML
    public ScrollPane scrollPane;

    @FXML
    public TextArea chatArea;

    @FXML
    public TextField inputBox;

    private static double xOffset = 0;
    private static double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        botAnswers = new HashMap<>();
        String messages[] = new String[]{"Hello.", "Hi.", "Hey there."};
        botAnswers.put("greeting", messages);
        messages = new String[]{"I'm fine, thank you, and you?", "Great! You?", "Pretty good, and you?"};
        botAnswers.put("ask_about", messages);
        messages = new String[]{"Sorry. I can't understand your message.", "Ugh! I can't understand this.", "I can't understand this. May you repeat?"};
        botAnswers.put("unknown", messages);
        state = "";

        List<Product> tempProducts = new ArrayList<>();

        tempProducts.add(new Laptop("Lenovo", "G5080A", 1450.0, 26.0, 51.0, 8.0, 2560, 15.6, 500, 4, "i3 4030u", "Windows 10") );
        tempProducts.add(new Laptop("Lenovo", "G5080B", 1600.0, 26.0, 51.0, 8.0, 2560, 15.6, 500, 4, "i3 4035u", "Windows 10") );
        tempProducts.add(new Laptop("Lenovo", "G5080C", 1799.0, 26.0, 51.0, 8.0, 2560, 15.6, 750, 4, "i5 4060u", "Windows 10") );
//
        tempProducts.add(new MobilePhone("MyPhone", "Vision", 800.0, 6.25, 1.01, 2.23, 98, 5.2, 16, 13, "Android 7.0", 2) );
        tempProducts.add(new MobilePhone("Apple", "Iphone X 16GB", 372253.0, 7.12, 5.23, 2.22, 111, 5.325, 16, 13, "iOS 11", 2) );
        tempProducts.add(new MobilePhone("Apple", "Iphone X 32GB", 462451.0, 7.12, 5.23, 2.22, 111, 5.325, 32, 13, "iOS 11", 2) );

        rootNode = new TreeNode(tempProducts);

        Parent botFxml = FXMLLoader.load(getClass().getResource("Bot.fxml"));
        botAnchor = (AnchorPane) botFxml;
        initForm(primaryStage, botFxml, false);

        chatArea = (TextArea) botAnchor.lookup("#chatBox");
        inputBox = (TextField) botAnchor.lookup("#inputBox");

        inputBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (chatArea == null)
                    chatArea = (TextArea) botAnchor.lookup("#chatBox");

                String uText = inputBox.getText();

                if (uText.length() < 1){
                    //do not trigger answering logic if message is empty
                    return;
                }
                if (!state.equals("admin_login")) {
                    //normal answer. print it to chatbox
                    chatArea.setText(chatArea.getText() + "You: " + uText + "\n");
                }
                else {
                    // lets dont show password
                    chatArea.setText(chatArea.getText() + "You: " + String.join("", Collections.nCopies(uText.length(), "*")) + "\n");
                }
                inputBox.setText("");

                if (uText.contains(adminName)){
                    //admin login logic starts here
                    answer("Enter password : ");
                    state = "admin_login";
                } else if ( state.equals("admin_login")) {
                    if (uText.equals(adminPass)){
                        answer("Welcome "+adminName+". What kind of product do you want to add?");
                        answer("1: Mobile Phone");
                        answer("2: Laptop");
                        state = "admin_choose_product";
                    } else {
                        answer("Wrong password!");
                        state = "";
                    }

                } else if (state.equals("admin_choose_product")){
                    if ( !uText.matches("[0-9]+") || Integer.parseInt(uText) > 1 ) {
                        answer("Thats not a valid selection.");
                        state = ""; //reset state;
                        return;
                    }
                    int selection = Integer.parseInt(uText);
                    if (selection == 1){
                        answer("Opening new form...");
                        newForm("AddPhone.fxml");
                        state = "";
                    }
                } else if (uText.contains("hello") || uText.contains("hi") || uText.contains("hey there")) {
                    decideRandom("greeting");
                } else if((uText.contains("how") && uText.contains("you") ) || (uText.contains("what") && uText.contains("up"))){
                    decideRandom("ask_about");
                } else if (uText.contains("buy something") || uText.contains("product") ) {

                    currentNode = rootNode;
                    printOptions(currentNode);
                    state = "select_category";

                } else if (state.equals("select_category")) {
                    if ( !uText.matches("[0-9]+")) {
                        answer("Thats not a valid selection.");
                        state = ""; //reset state;
                        return;
                    }

                    int selectedIndex = Integer.parseInt(uText) - 1; //user selection starts from 1, arrays starts from 0
                    currentNode = currentNode.getNextNode(options[selectedIndex]);
                    printOptions(currentNode);
                    state = "select_product_type"; //set next state
                } else if (state.equals("select_product_type")) {
                    if ( !uText.matches("[0-9]+") || Integer.parseInt(uText) > options.length) {
                        answer("Thats not a valid selection.");
                        state = ""; //reset state;
                        return;
                    }

                    int selectedIndex = Integer.parseInt(uText) - 1; //user selection starts from 1, arrays starts from 0
                    currentNode = currentNode.getNextNode(options[selectedIndex]);
                    products = currentNode.getProductList();

                    for (int i = 0; i < products.size(); i++){
                        int j = i + 1; // visual only!
                        answer(j+ ": "+ products.get(i).toShortString() );
                    }
                    state = "select_product"; //set next state
                } else if (state.equals("select_product")) {
                    if ( !uText.matches("[0-9]+") || Integer.parseInt(uText) > products.size()) {
                        answer("Thats not a valid selection.");
                        state = ""; //reset state;
                        return;
                    }
                    int selectedIndex = Integer.parseInt(uText) - 1; //user selection starts from 1, arrays starts from 0
                    Product selectedProduct = products.get(selectedIndex);

                    // TODO: show product info
                    answer(" TODO: I should show you information about product you have choosen but its not implemented yet. Sorry.");

                    state = ""; //end of product selection cycle
                } else {
                    decideRandom("unknown");
                }

            }
        });
        primaryStage.setTitle("Chatbot");
        primaryStage.show();
    }

    /**
     * Main function of ChatBot
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void answer(String message){
        chatArea.setText(chatArea.getText() + "AI: " + message + "\n");

        //hotfix for autoscroll chatArea
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(25),
                ae -> chatArea.setScrollTop(Double.MAX_VALUE) ));
        timeline.play();

    }

    /**
     * Prints options in given node.
     * @param node
     */
    private void printOptions(TreeNode node){
        options = node.getAllKeys();
        answer("Please select a " + node.nodeType + ":");
        for (int i = 0; i < options.length; i++){
            int j = i + 1; // visual only!
            answer(j+ ": "+ options[i]);
        }
    }

    /**
     * Selects one random string from messagelist and prints it to chatArea
     * @param key
     */
    private void decideRandom(String key) {
        String messageList[] = botAnswers.get(key);
        int randomIndex = ThreadLocalRandom.current().nextInt(0, messageList.length);
        answer(messageList[randomIndex]);
    }

    /**
     * Fixes autoscrolling of chatArea
     */
    private void fixScroll() {
        chatArea.setScrollTop(Double.MAX_VALUE);
    }

    /**
     * This function is used to create new modal forms (subforms, adding new producs).
     * @param formFxml name of fxml file
     */
    private void newForm(String formFxml){
        try{
            Parent newFxml = FXMLLoader.load(getClass().getResource(formFxml));
            Stage newStage = new Stage();
            initForm(newStage, newFxml, true);
            newStage.show();
        } catch (Exception e){
            System.out.println("Error ocurred when creating new form: " + e.getMessage());
        }
    }

    /**
     * This function is used to initialize any forms we create.
     * Every fxml must start with AnchorPane as a container!
     * @param stage form stage
     * @param fxml form fxml file
     * @param isModalForm true if we are creating subforms
     */
    private void initForm(Stage stage, Parent fxml, boolean isModalForm){
        //FXML must have anchorpane as container!
        AnchorPane rootAnchor = (AnchorPane) fxml;
        rootAnchor.setBackground(Background.EMPTY);
        Rectangle header = ((Rectangle) rootAnchor.lookup("#header"));

        //fix for draggable window with custom title bar
        header.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = stage.getX() - event.getScreenX();
                yOffset = stage.getY() - event.getScreenY();
            }
        });

        //fix for draggable window with custom title bar
        header.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() + xOffset);
                stage.setY(event.getScreenY() + yOffset);
            }
        });

        Scene scene = new Scene(rootAnchor,  rootAnchor.getPrefWidth(), rootAnchor.getPrefHeight());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);

        //make background transparent for window shadow
        stage.initStyle(StageStyle.TRANSPARENT);
        if (isModalForm) {
            stage.initModality(Modality.APPLICATION_MODAL);
        }
    }

}
