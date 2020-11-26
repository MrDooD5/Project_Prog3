package MailClient;

import MailServer.ServerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainClient extends Application {
    static Stage main;

    static Scene homeS, loginS, readS, writeS;
    @Override
    public void start(Stage primaryStage) throws Exception{
        main= primaryStage;
        primaryStage.setTitle("MailCenter");

        FXMLLoader loaderSceneLogin  = new FXMLLoader(getClass().getResource("loginView.fxml"));
        FXMLLoader loaderSceneHome   = new FXMLLoader(getClass().getResource("homeView.fxml"));
        FXMLLoader loaderSceneRead   = new FXMLLoader(getClass().getResource("readView.fxml"));
        FXMLLoader loaderSceneWrite  = new FXMLLoader(getClass().getResource("writeView.fxml"));


        //Parent root = FXMLLoader.load(getClass().getResource("loginView.fxml"));
        loginS= new Scene(loaderSceneLogin.load());
        homeS  = new Scene(loaderSceneHome.load());
        readS  = new Scene(loaderSceneRead.load());
        writeS = new Scene(loaderSceneWrite.load());

        /*FXMLLoader loaderScene = new FXMLLoader(getClass().getResource("serverView.fxml"));
        serverScene = new Scene(loaderScene.load());
        ServerController serverController = loaderScene.getController();
         */


        LoginController loginController = loaderSceneLogin.getController();
        HomeController homeController   = loaderSceneHome.getController();
        ReadController readController   = loaderSceneRead.getController();
        WriteController writeController = loaderSceneWrite.getController();

        DataModel model= new DataModel();

        loginController.initModel(model);
        homeController.initModel(model);

        primaryStage.setScene(loginS);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void home(){

    }
}