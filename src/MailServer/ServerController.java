package MailServer;

import MailModel.Mail;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.List;

public class ServerController {

    ObservableList<String> logList;
    Server serverT;

    @FXML
    private void handleStartBtnAction(ActionEvent event){
        //serverT.notify();
        serverT.startBTN();
    }

    @FXML
    public void handleStopBtnAction(ActionEvent actionEvent){
        serverT.pauseBTN();
    }

    @FXML
    public void handleQuitBtnAction(ActionEvent actionEvent){
        serverT.quitBTN();
        MainServer.quit();
    }

    @FXML
    private ListView<String> logView;

    public void initModel(ObservableList<String> tmp){
        logList=tmp;

        logView.setItems(logList);

        serverT= new Server(tmp);
        serverT.start();

    }


}
