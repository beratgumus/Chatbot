
import apis.TwitterAPI;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import twitter4j.TwitterException;

import products.*;
import apis.*;


public class Controller {

    @FXML
    public Label messageLabel;
    public Rectangle messageBox;

    @FXML //Product fields
    public Button closeBtn;
    public TextField brandTF;
    public TextField modelTF;
    public TextField priceTF;
    public TextField widthTF;
    public TextField heightTF;
    public TextField depthTF;
    public TextField weightTF;

    @FXML //Consumer Electronics fields
    public TextField screenTF;
    public TextField storageTF;
    public TextField osTF;


    @FXML //Mobile Phone fields
    public TextField cameraTF;
    public TextField ramTF;

    @FXML //Laptop fields
    public TextField cpuTF;

    @FXML //Vehicle fields
    public TextField powerTF;
    public TextField fuelTypeTF;

    @FXML //Car fields
    public TextField airConditionerTF;
    public TextField numberOfSeatsTF;

    @FXML //Motorcycle fields
    public TextField windshieldTF;
    public TextField carrierBoxTF;

    @FXML //Major Appliance fields
    public TextField capacityTF;
    public TextField energyEfficiencyTF;

    @FXML//Refrigerator fields
    public TextField refrigeratorTypeTF;
    public TextField iceMakerTF;
    public TextField frostFreeTF;
    public TextField doorOpenAlarmTF;


    private static final String infoColor = "#3c9ae1";
    private static final String errorColor = "#dd3b3b";
    private static final String successColor = "#3ce07e";

    /**
     * This function is called by close button at the top right
     */
    @FXML
    public void close() {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    /**
     * this function is called by save button of AddPhone form
     */
    @FXML
    public void savePhone(Event event) {

        if (!isSet(brandTF) || !isSet(modelTF) || !isSet(priceTF) || !isSet(osTF)
                || !isSet(heightTF) || !isSet(widthTF) || !isSet(depthTF) || !isSet(weightTF)
                || !isSet(screenTF) || !isSet(storageTF) || !isSet(cameraTF) || !isSet(ramTF)) {
            messageLabel.setText("You must fill the form.");
            messageBox.setFill(Color.web(errorColor));
            return;
        } else {
            messageBox.setFill(Color.web(infoColor));
        }

        MobilePhone newMobilePhone;
        try {
            newMobilePhone = new MobilePhone(
                    brandTF.getText(),
                    modelTF.getText(),
                    Double.parseDouble(priceTF.getText()),
                    Double.parseDouble(heightTF.getText()),
                    Double.parseDouble(widthTF.getText()),
                    Double.parseDouble(depthTF.getText()),
                    Double.parseDouble(weightTF.getText()),
                    Double.parseDouble(screenTF.getText()),
                    Integer.parseInt(storageTF.getText()),
                    Integer.parseInt(cameraTF.getText()),
                    osTF.getText(),
                    Integer.parseInt(ramTF.getText()));
        } catch (Exception e) {
            messageLabel.setText("One of values is not acceptable");
            messageBox.setFill(Color.web(errorColor));
            return;
        }

        newMobilePhone.setReviewPoint(getPointFromLatestTweets());

        ProductDB mongoDB = new ProductDB();
        mongoDB.insert(newMobilePhone);
        mongoDB.close(); //close db connection

        this.close();
    }

    /**
     * this function is called by save button of AddLaptop form
     */
    @FXML
    public void saveLaptop(Event event) {

        if (!isSet(brandTF) || !isSet(modelTF) || !isSet(priceTF) || !isSet(osTF)
                || !isSet(heightTF) || !isSet(widthTF) || !isSet(depthTF) || !isSet(weightTF)
                || !isSet(screenTF) || !isSet(storageTF) || !isSet(ramTF) || !isSet(cpuTF)) {
            messageLabel.setText("You must fill the form.");
            messageBox.setFill(Color.web(errorColor));
            return;
        } else {
            messageBox.setFill(Color.web(infoColor));
        }

        Laptop newLaptop;
        try {
            newLaptop = new Laptop(
                    brandTF.getText(),
                    modelTF.getText(),
                    Double.parseDouble(priceTF.getText()),
                    Double.parseDouble(heightTF.getText()),
                    Double.parseDouble(widthTF.getText()),
                    Double.parseDouble(depthTF.getText()),
                    Double.parseDouble(weightTF.getText()),
                    Double.parseDouble(screenTF.getText()),
                    Integer.parseInt(storageTF.getText()),
                    Integer.parseInt(ramTF.getText()),
                    cpuTF.getText(),
                    osTF.getText());
        } catch (Exception e) {
            messageLabel.setText("One of values is not acceptable");
            messageBox.setFill(Color.web(errorColor));
            return;
        }

        newLaptop.setReviewPoint(getPointFromLatestTweets());

        ProductDB mongoDB = new ProductDB();
        mongoDB.insert(newLaptop);
        mongoDB.close(); //close db connection

        this.close();
    }

    public void saveCar(Event event) {
        if (!isSet(brandTF) || !isSet(modelTF) || !isSet(priceTF)
                || !isSet(heightTF) || !isSet(widthTF) || !isSet(depthTF) || !isSet(weightTF)
                || !isSet(powerTF) || !isSet(fuelTypeTF) || !isSet(airConditionerTF) || !isSet(numberOfSeatsTF)) {
            messageLabel.setText("You must fill the form.");
            messageBox.setFill(Color.web(errorColor));
            return;
        } else {
            messageBox.setFill(Color.web(infoColor));
        }

        Car newCar;
        try {
            newCar = new Car(
                    brandTF.getText(),
                    modelTF.getText(),
                    Double.parseDouble(priceTF.getText()),
                    Double.parseDouble(heightTF.getText()),
                    Double.parseDouble(widthTF.getText()),
                    Double.parseDouble(depthTF.getText()),
                    Double.parseDouble(weightTF.getText()),
                    Integer.parseInt(powerTF.getText()),
                    fuelTypeTF.getText(),
                    Integer.parseInt(numberOfSeatsTF.getText()),
                    airConditionerTF.getText());
        } catch (Exception e) {
            messageLabel.setText("One of values is not acceptable");
            messageBox.setFill(Color.web(errorColor));
            return;
        }

        newCar.setReviewPoint(getPointFromLatestTweets());

        ProductDB mongoDB = new ProductDB();
        mongoDB.insert(newCar);
        mongoDB.close(); //close db connection

        this.close();

    }

    public void saveMotorcycle(Event event) {
        if (!isSet(brandTF) || !isSet(modelTF) || !isSet(priceTF)
                || !isSet(heightTF) || !isSet(widthTF) || !isSet(depthTF) || !isSet(weightTF)
                || !isSet(powerTF) || !isSet(fuelTypeTF) || !isSet(windshieldTF) || !isSet(carrierBoxTF)) {
            messageLabel.setText("You must fill the form.");
            messageBox.setFill(Color.web(errorColor));
            return;
        } else {
            messageBox.setFill(Color.web(infoColor));
        }

        Motorcycle newMotorcycle;
        try {
            newMotorcycle = new Motorcycle(
                    brandTF.getText(),
                    modelTF.getText(),
                    Double.parseDouble(priceTF.getText()),
                    Double.parseDouble(heightTF.getText()),
                    Double.parseDouble(widthTF.getText()),
                    Double.parseDouble(depthTF.getText()),
                    Double.parseDouble(weightTF.getText()),
                    Integer.parseInt(powerTF.getText()),
                    fuelTypeTF.getText(),
                    numberOfSeatsTF.getText().toLowerCase().contains("yes"),
                    airConditionerTF.getText().toLowerCase().contains("yes"));
        } catch (Exception e) {
            messageLabel.setText("One of values is not acceptable");
            messageBox.setFill(Color.web(errorColor));
            return;
        }

        newMotorcycle.setReviewPoint(getPointFromLatestTweets());

        ProductDB mongoDB = new ProductDB();
        mongoDB.insert(newMotorcycle);
        mongoDB.close(); //close db connection

        this.close();

    }

    public void saveRefrigerator(Event event) {
        if (!isSet(brandTF) || !isSet(modelTF) || !isSet(priceTF)
                || !isSet(heightTF) || !isSet(widthTF) || !isSet(depthTF) || !isSet(weightTF)
                || !isSet(capacityTF) || !isSet(energyEfficiencyTF) || !isSet(refrigeratorTypeTF) || !isSet(iceMakerTF)
                || !isSet(frostFreeTF) || !isSet(doorOpenAlarmTF)) {
            messageLabel.setText("You must fill the form.");
            messageBox.setFill(Color.web(errorColor));
            return;
        } else {
            messageBox.setFill(Color.web(infoColor));
        }

        Refrigerator newRfrigerator;
        try {
            newRfrigerator = new Refrigerator(
                    brandTF.getText(),
                    modelTF.getText(),
                    Double.parseDouble(priceTF.getText()),
                    Double.parseDouble(heightTF.getText()),
                    Double.parseDouble(widthTF.getText()),
                    Double.parseDouble(depthTF.getText()),
                    Double.parseDouble(weightTF.getText()),
                    Integer.parseInt(capacityTF.getText()),
                    energyEfficiencyTF.getText(),
                    refrigeratorTypeTF.getText(),
                    iceMakerTF.getText().toLowerCase().contains("yes"),
                    frostFreeTF.getText().toLowerCase().contains("yes"),
                    doorOpenAlarmTF.getText().toLowerCase().contains("yes")
                    );
        } catch (Exception e) {
            messageLabel.setText("One of values is not acceptable");
            messageBox.setFill(Color.web(errorColor));
            return;
        }

        newRfrigerator.setReviewPoint(getPointFromLatestTweets());

        ProductDB mongoDB = new ProductDB();
        mongoDB.insert(newRfrigerator);
        mongoDB.close(); //close db connection

        this.close();

    }

    private Double getPointFromLatestTweets() {
        try {
            TwitterAPI twitterAPI = new TwitterAPI();
            double reviewPoint = twitterAPI.getReviewPoint(modelTF.getText());
            System.out.println("Mean of review points of ten tweets: " + reviewPoint);
            return reviewPoint;
        } catch (TwitterException e) {
            System.out.println("An error ocurred while getting tweets.");
            return 0.0;
        }
    }

    private boolean isSet(TextField tf) {
        return !(tf.getText().isEmpty() || tf.getText().equals("") || !tf.getText().matches(".*[a-zA-Z0-9]+.*"));
    }
}
