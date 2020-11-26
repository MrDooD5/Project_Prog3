package MailModel;

import java.io.Serializable;

public class ServerRequest implements Serializable {
    private String request = "";
    private String user = "";
    public ServerRequest(String username, int NumReq){
        switch (NumReq){
            case 1: request= "RM";
            case 2: request= "WM";
            case 3: request= "NM";
        }
        this.user=username;
    }

    public String getRequest(){
        return request;
    }

    public String getUser(){
        return user;
    }
}
