package com.example.wordle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class funciones {

    //recoger los textField que forman las palabras.

    @FXML private TextField input1;
    @FXML private TextField input2;
    @FXML private TextField input3;
    @FXML private TextField input4;
    @FXML private TextField input5;

    // cada huevo de los intentos.

    @FXML
    private TextField palabra1letra1, palabra1letra2, palabra1letra3, palabra1letra4, palabra1letra5;
    @FXML
    private TextField palabra2letra1, palabra2letra2, palabra2letra3, palabra2letra4, palabra2letra5;
    @FXML
    private TextField palabra3letra1, palabra3letra2, palabra3letra3, palabra3letra4, palabra3letra5;
    @FXML
    private TextField palabra4letra1, palabra4letra2, palabra4letra3, palabra4letra4, palabra4letra5;




    // Botones
    @FXML
    private Button botonAceptar;
    @FXML
    private Button botonBorrar;


    //la clase palabras.
    private Palabras palabras;
    //en donde guardar la palabra secreta
    private String palabraSecreta;  //palabra oculta
    //guardar las dferentes letras de los campos
    private TextField[] CombinacionLetras; //

    private TextField[][] fila_Palabra;
    private int numerosDeLetras = 0;
    private int intentoActual = 0;



    @FXML
    public void initialize() {

        //RECOGEMOS LASLETRAS PARA FORMAR LA PALABRA.
        CombinacionLetras = new TextField[]{ input1, input2, input3, input4, input5 };
        //crear un array bidimensional, de 4 x 5 , 2 palabras de 5 huecos cada una.
        fila_Palabra = new TextField[4][5];
        //cada una de las palabras
        fila_Palabra[0] = new TextField[]{ palabra1letra1, palabra1letra2, palabra1letra3, palabra1letra4, palabra1letra5 };
        fila_Palabra[1] = new TextField[]{ palabra2letra1, palabra2letra2, palabra2letra3, palabra2letra4, palabra2letra5 };
        fila_Palabra[2] = new TextField[]{ palabra3letra1, palabra3letra2, palabra3letra3, palabra3letra4, palabra3letra5 };
        fila_Palabra[3] = new TextField[]{ palabra4letra1, palabra4letra2, palabra4letra3, palabra4letra4, palabra4letra5 };

        //llamar a la clase palabra.
        palabras = new Palabras();

        //seleccionar una palabra aleatoria del array
        palabraSecreta = palabras.palabraAleatoria().toUpperCase();


        System.out.println("Palabra secreta (shhhh....🤫): " + palabraSecreta);



    }


    //USO DEL """""TECLADO"""" PARA IR AÑADIENDO LAS LETRAS
    @FXML
    private void manejarLetra(ActionEvent event) {
        //controlar que no se pueda meter mas de 5 letras
        if (numerosDeLetras >= 5) return;

        //recoger los clics del raton:
        Button boton = (Button) event.getSource();
        //recoger las letras del valor de los botones
        String letra = boton.getText();

        // añadir la letra a los textField.

        CombinacionLetras[numerosDeLetras].setText(letra.toUpperCase());
        //sumar la cantidad de letras.
        numerosDeLetras++;
    }

    // FUNCION DE BORRAR LETRA.
    @FXML
    private void borrarLetra() {

        if (numerosDeLetras <= 0){
            return;
        }

        numerosDeLetras--;
        CombinacionLetras[numerosDeLetras].setText("");
    }
    //limpiar la letra del TEXTFIELD
    @FXML
    private void limpiarLetra() {
        for (TextField letra : CombinacionLetras){
            //System.out.println(letra);
            letra.setText("");
        }
    }

    @FXML
    private void aceptarBoton() {

        // Construimos la palabra a partir de los TextFields
        StringBuilder PalabraCombinada = new StringBuilder();
        for (TextField letra : CombinacionLetras) {
            PalabraCombinada.append(letra.getText().toUpperCase());
        }

        if (PalabraCombinada.length() < 5) {
            System.out.println("La palabra enviada tiene menos de 5 letras");
            return;
        }

        // Procesar la fila actual
        for (int i = 0; i < 5; i++) {
            TextField cuadradito = fila_Palabra[intentoActual][i];
            String letra = PalabraCombinada.substring(i, i + 1);
            cuadradito.setText(letra);

            if (letra.equals(String.valueOf(palabraSecreta.charAt(i)))) {
                cuadradito.setStyle("-fx-background-color: green; -fx-text-fill: white;");
            } else if (palabraSecreta.contains(letra)) {
                cuadradito.setStyle("-fx-background-color: gold; -fx-text-fill: black;");
            } else {
                cuadradito.setStyle("-fx-background-color: grey; -fx-text-fill: white;");
            }
        }

        // Si acertó toda la palabra
        if (palabraSecreta.equals(PalabraCombinada.toString())) {
            String mensaje="Me jode, pero has acertado!";
            System.out.println("Me jode, pero has acertado!");
            ventana1(mensaje); // muestra modal de victoria
            return;
        }

        // Pasar al siguiente intento
        intentoActual++;
        numerosDeLetras = 0;
        limpiarLetra();

        System.out.println("Intentos usados: " + intentoActual);
        if (intentoActual >= 4) {
            String mensaje="Se acabaron los intentos, perdiste";
            System.out.println("Se acabaron los intentos, perdiste");
            ventana1(mensaje); // muestra el modal
            return;
        }
    }

    @FXML
    private Label mensajeLabel;

    private void ventana1(String mensaje) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mensaje.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 300, 300);
            stage.setTitle("Modal!");
            // Obtener el controlador del modal
            MensajeControlador mensajePerso = fxmlLoader.getController();
            mensajePerso.setMensaje(mensaje);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); // <-- hace que sea un modal
            stage.showAndWait(); // bloquea hasta que se cierre

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
