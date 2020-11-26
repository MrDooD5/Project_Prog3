package MailClient;

import MailModel.Mail;
import MailModel.ServerRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class LoginController {

    DataModel model;

    @FXML
    public TextField mailinput;

    @FXML
    public void login(ActionEvent actionEvent){
        String user = mailinput.getText();
        if(user!= null && user.length() != 0 && verifyMail(user) ) {

            ServerRequest rqac= new ServerRequest(user ,1);
            //crea una connessione
            try {
                String nomeHost = InetAddress.getLocalHost().getHostName();
                Socket acc = new Socket(nomeHost, 8189);
                try {
                    ObjectOutputStream outStream = new ObjectOutputStream(acc.getOutputStream());
                    ObjectInputStream inStream = new ObjectInputStream(acc.getInputStream());

                    //invia la richiesta

                    outStream.writeObject(rqac);

                }
                finally {
                    acc.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            //aspetta la risposte con l'array list
            ArrayList<Mail> tmp= new ArrayList<Mail>();

            model.setMailList(tmp);
            model.setAccount(user);


        }
    }

    public void initModel(DataModel model){
        this.model= model;
    }

    public boolean verifyMail(String tmp){
        return true;
    }
}
