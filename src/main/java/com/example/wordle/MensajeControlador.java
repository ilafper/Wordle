package com.example.wordle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MensajeControlador {

    @FXML
    private Label mensajeLabel;


    // Instancia de la clase funciones
    private funciones funciones = new funciones(); // ✅ forma correcta


    // referencia al controlador principal


    public void setMensaje(String mensaje) {
        mensajeLabel.setText(mensaje);
    }


    @FXML
    private void cerrar() {
        Stage stage = (Stage) mensajeLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void reiniciar() {
        System.out.println("hola");
        funciones.initialize();
    }


}
