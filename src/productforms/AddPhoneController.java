package productforms;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import products.*;

public class AddPhoneController {

    @FXML
    public Button closeBtn;
    public TextField brandTF;
    public TextField priceTF;
    public TextField widthTF;
    public TextField weightTF;
    public TextField storageTF;
    public TextField osTF;
    public TextField modelTF;
    public TextField heightTF;
    public TextField depthTF;
    public TextField screenTF;
    public TextField cameraTF;
    public TextField ramTF;


    @FXML
    public void close() {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void save(Event event) {
        MobilePhone newMobilePhone = new MobilePhone("1",
                brandTF.getText(),
                modelTF.getText(),
                Double.parseDouble(priceTF.getText()),
                Double.parseDouble(heightTF.getText()),
                Double.parseDouble(widthTF.getText()),
                Double.parseDouble(depthTF.getText()),
                Integer.parseInt(weightTF.getText()),
                Double.parseDouble(screenTF.getText()),
                Integer.parseInt(storageTF.getText()),
                Integer.parseInt(cameraTF.getText()),
                osTF.getText(),
                Integer.parseInt(ramTF.getText()));
        /**
         * ToDo: insert the object to mongoDB
         */
        System.out.println(newMobilePhone);
    }

}
