package com.example.wordle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MensajeControlador {

    @FXML
    private Label mensajeLabel;

    private funciones controladorPrincipal; // referencia al controlador principal

    public void setMensaje(String mensaje) {
        mensajeLabel.setText(mensaje);
    }

    public void setControladorPrincipal(funciones controlador) {
        this.controladorPrincipal = controlador;
    }

    @FXML
    private void cerrar() {
        Stage stage = (Stage) mensajeLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void reiniciar() {
        if (controladorPrincipal != null) {
            controladorPrincipal.reiniciarJuego();
        }
        System.out.println("pulsar boton reiniciar");
        cerrar();
    }
}
