package com.example.wordle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MensajeControlador {

    @FXML
    private Label mensajeLabel; // Este Label está en mensaje.fxml

    public void setMensaje(String mensaje) {
        mensajeLabel.setText(mensaje);
    }

    @FXML
    private void cerrar() {
        Stage stage = (Stage) mensajeLabel.getScene().getWindow();
        stage.close();
    }
}

