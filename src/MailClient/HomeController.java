package MailClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class HomeController {

    DataModel model;

    @FXML
    public Label UserName;
    @FXML
    public ListView MailView;

    @FXML
    public void writemail(ActionEvent actionEvent){
    }

    @FXML
    public void quit(ActionEvent actionEvent){
    }

    @FXML
    public void deleteMail(ActionEvent actionEvent){
    }

    public void initModel(DataModel model){
        this.model= model;
    }
}
