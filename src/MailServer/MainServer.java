package MailServer;

import MailModel.Mail;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.*;

import static java.lang.Thread.sleep;

public class MainServer extends Application{

    static Stage main;
    Scene serverScene;

    private static ObservableList<String> logView;


    @Override
    public void start(Stage primaryStage) throws Exception{
        main =primaryStage;

        primaryStage.setTitle("Mail Server");

        //crete and setting scene and controller
        FXMLLoader loaderScene = new FXMLLoader(getClass().getResource("serverView.fxml"));
        serverScene = new Scene(loaderScene.load());
        FXMLLoader.load(getClass().getResource("serverView.fxml"));
        ServerController serverController = loaderScene.getController();

        //dichiarazione del modello mostrato dalla view (Stringhe di Log)
        logView=  FXCollections.observableArrayList(new ArrayList<>());
        serverController.initModel(logView);

        primaryStage.setScene(serverScene);

        primaryStage.show();
        main.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override public void handle(WindowEvent t) {
                quit();
            }
        });



    }


    public static void main(String[] args) {
        launch(args);
    }



    public static void quit(){
        main.close();
    }



}


