package MailModel;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.*;

public class MailBox {

    private  String account= new String();

    public String getaccount() {
        return this.account;
    }


    public void setAccount(String account) {
        this.account=account;
    }

    private ArrayList<Mail> mailList = new ArrayList<>();

    public ArrayList<Mail> getMailList() {return mailList;}

    public void addMail(Mail msn){ mailList.add(msn); }

    public void removeMail(Mail msn){
        mailList.remove(msn);
    }

    /*
    private final StringProperty account = new SimpleStringProperty();

    public  final StringProperty accountProperty() {
        return this.account;
    }

    public  final String getAccount() {
        return this.accountProperty().get();
    }

    public  final void setAccount(final String account) {
        this.accountProperty().set(account);
    }


    private  final ObservableList<Mail> mailList = FXCollections.observableArrayList(mail -> new Observable[] {mail.senderProperty(), mail.objectProperty(), mail.dateProperty()});

    public ObservableList<Mail> getMailList() {return mailList;}

    public final void addMail(Mail msn){ mailList.add(msn); }

    public final void removeMail(Mail msn){
        mailList.remove(msn);
    }
    */

}
