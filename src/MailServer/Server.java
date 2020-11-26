package MailServer;

import MailModel.Mail;
import MailModel.ServerRequest;
import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Server extends Thread {
    static boolean serverFlag = false;
    static boolean serverInitFlag = true;

    ObservableList<String> logList;

    private static final int NUM_THREAD = 10;

    private ExecutorService threadPool;

    ServerSocket serverSocket;

    public Server(ObservableList<String> logList){
        super();
        this.logList=logList;
    }

    public void run(){
        //creo il pool
        wrtiteLog("Server pronto");
        threadPool = Executors.newFixedThreadPool(NUM_THREAD);
        try {
            serverSocket = new ServerSocket(8189);
            serverSocket.setSoTimeout(8000);

            ReadWriteLock rwl = new ReentrantReadWriteLock();
            Lock rl = rwl.readLock();
            Lock wl = rwl.writeLock();


            while(serverInitFlag){

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while(serverFlag){
                    try {
                        wrtiteLog("OP");
                        Socket incoming= serverSocket.accept();

                        Thread.sleep(500);
                        wrtiteLog("OP");

                        Runnable task = new ServerTask(incoming, rl, wl, logList);
                        threadPool.execute(task);
                    }
                    catch( InterruptedException exc) {System.out.println("Eccezione: " + exc.getMessage());}
                    catch(SocketTimeoutException ignored){}
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            threadPool.shutdown();
        }

    }

    public void startBTN(){
        wrtiteLog("Server Starting...");
        serverFlag=true;
    }

    public void pauseBTN(){
        wrtiteLog("Server Stop");
        serverFlag= false;
    }

    public void quitBTN(){
        pauseBTN();
        serverInitFlag=false;
        wrtiteLog("Server Quit...");

    }

    private void wrtiteLog(String str){
        Platform.runLater(new Runnable() {
            public void run() {
                logList.add(str);
            }
        });
    }

}

class ServerTask implements Runnable{
    ObservableList<String> logList;

    private Socket incoming;

    private String user;

    ObjectInputStream inObjStream;
    ObjectOutputStream outStream;

    Lock readLock ,writelock;

    public ServerTask(Socket in,Lock rl, Lock wl, ObservableList<String> logList) {
        incoming = in;
        this.readLock=rl;
        this.writelock=rl;
        this.logList=logList;
    }

    @Override
    public void run(){
        try {
            try {
                inObjStream = new ObjectInputStream(incoming.getInputStream());
                outStream = new ObjectOutputStream(incoming.getOutputStream());

                try {
                    System.out.println("Server aspetta");
                    ServerRequest req = ((ServerRequest)inObjStream.readObject());
                    System.out.println("Server ha : " + req.getUser());
                    user=req.getUser();
                    switch (req.getRequest()){
                        case "RM": readMail();
                        case "WM": writeMail();
                        case "NM": break;
                    }

                } catch (ClassNotFoundException e) {System.out.println(e.getMessage());}


            }finally {
                incoming.close();
            }
        }catch (IOException e) {e.printStackTrace();}
    }

    private void readMail(){
        try {
            //leggo mail
            readLock.lock();
            wrtiteLog(user +"sto leggendo");
        } finally {
            readLock.unlock();
        }
    }

    private void writeMail(){
        try {
            Mail mail = ((Mail)inObjStream.readObject());
            //scrivo mail
            writelock.lock();
            wrtiteLog(user +"sto scrivendo");
        } catch (ClassNotFoundException | IOException e) {System.out.println(e.getMessage());}
        finally {
            writelock.unlock();
        }
    }

    private void wrtiteLog(String str){
        Platform.runLater(new Runnable() {
            public void run() {
                logList.add(str);
            }
        });
    }


}
