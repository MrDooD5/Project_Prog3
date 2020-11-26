package MailModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Mail implements Serializable {
    private final StringProperty ID = new SimpleStringProperty();

    public final StringProperty IDProperty() {
        return this.ID;
    }

    public final String getID() {
        return this.IDProperty().get();
    }

    public final void setID(final String ID) {
        this.IDProperty().set(ID);
    }


    private final StringProperty sender = new SimpleStringProperty();

    public final StringProperty senderProperty() {
        return this.sender;
    }

    public final String getSender() {
        return this.senderProperty().get();
    }

    public final void setSender(final String sender) {
        this.senderProperty().set(sender);
    }


    private final StringProperty reciver = new SimpleStringProperty();

    public final StringProperty reciverProperty() {
        return this.reciver;
    }

    public final String getReciver() {
        return this.reciverProperty().get();
    }

    public final void setReciver(final String reciver) {
        this.reciverProperty().set(reciver);
    }


    private final StringProperty object = new SimpleStringProperty();

    public final StringProperty objectProperty() {
        return this.object;
    }

    public final String getObject() {
        return this.objectProperty().get();
    }

    public final void setObject(final String object) {
        this.objectProperty().set(object);
    }


    private final StringProperty text = new SimpleStringProperty();

    public final StringProperty textProperty() {
        return this.text;
    }

    public final String getText() {
        return this.textProperty().get();
    }

    public final void setText(final String text) {
        this.textProperty().set(text);
    }


    private final StringProperty date = new SimpleStringProperty();

    public final StringProperty dateProperty() {
        return this.date;
    }

    public final String getDate() {
        return this.dateProperty().get();
    }

    public final void setDate(final String date) {
        this.dateProperty().set(date);
    }

    public boolean equals(Mail tmp){
        if (tmp != null)
            return ID.equals(tmp.getID());
        return false;
    }

}
