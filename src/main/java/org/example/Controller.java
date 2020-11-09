package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.model.Patient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URL;

public class Controller {

    @FXML
    TextField lastname;
    @FXML
    TextField firstname;
    @FXML
    TextField middlename;
    @FXML
    TextField result;
    @FXML
    TextField clientId;

    String server = "http://127.0.0.1:8080";
    String war = "/api/demo";
    String GetBuFIOMethod = "/patients/searchone";

    public void btnGET(ActionEvent actionEvent) {
        String filtering = "?lastname="+lastname.getText()+"&firstname="+firstname.getText()+"&middlename=" + middlename.getText();
        String url = server+war+GetBuFIOMethod+filtering;

        Client client = ClientBuilder.newClient();
        String json = client.target(url).request(MediaType.APPLICATION_JSON).get(String.class);
        result.setText(json);

        ObjectMapper mapper = new ObjectMapper();
        Patient patient = null;
        try {
            patient = mapper.readValue(new URL(url), Patient.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String id = String.valueOf(patient.getId());
        clientId.setText(id);
    }

}