package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import javax.ws.rs.core.MediaType;

public class Controller {

    private final String GET_URL = "http://127.0.0.1:8080/message-app/webapi/messages/2";

    @FXML
    TextField pathField;

    public void btnGET(ActionEvent actionEvent) {
        Client client = ClientBuilder.newClient();
        String entity = client.target(GET_URL).request(MediaType.APPLICATION_JSON).get(String.class);
        pathField.setText(entity);
    }

}