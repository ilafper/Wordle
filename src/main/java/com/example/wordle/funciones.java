package com.example.wordle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

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

    // Lógica del juego
    private Palabras palabras;
    private String palabraSecreta;  //palabra oculta
    private TextField[] CombinacionLetras; //
    private TextField[][] fila_Palabra;
    private int letraIndex = 0;
    private int intentoActual = 0;
    private boolean juegoTerminado=false;


    @FXML
    public void initialize() {


        CombinacionLetras = new TextField[]{ input1, input2, input3, input4, input5 };
        fila_Palabra = new TextField[4][5];

        fila_Palabra[0] = new TextField[]{ palabra1letra1, palabra1letra2, palabra1letra3, palabra1letra4, palabra1letra5 };
        fila_Palabra[1] = new TextField[]{ palabra2letra1, palabra2letra2, palabra2letra3, palabra2letra4, palabra2letra5 };
        fila_Palabra[2] = new TextField[]{ palabra3letra1, palabra3letra2, palabra3letra3, palabra3letra4, palabra3letra5 };
        fila_Palabra[3] = new TextField[]{ palabra4letra1, palabra4letra2, palabra4letra3, palabra4letra4, palabra4letra5 };

        // Seleccionar palabra secreta
        palabras = new Palabras();
        palabraSecreta = palabras.palabraAleatoria().toUpperCase();
        System.out.println("Palabra secreta (shhhh....🤫): " + palabraSecreta);



    }


    //USO DEL """""TECLADO""""  PARA IR AÑADIENDO LAS LETRAS
    @FXML
    private void manejarLetra(ActionEvent event) {
        if (letraIndex >= 5) return; // máximo 5 letras
        Button boton = (Button) event.getSource();
        String letra = boton.getText();

        // poner letra en mayúscula
        CombinacionLetras[letraIndex].setText(letra.toUpperCase());
        letraIndex++;
    }

    // FUNCION DE BORRAR LETRA.
    @FXML
    private void borrarLetra() {
        if (letraIndex <= 0) return;
        letraIndex--;
        CombinacionLetras[letraIndex].setText("");
    }

    @FXML
    private void limpiarInputs() {
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

        System.out.println("Palabra ingresada: " + PalabraCombinada.toString());
        System.out.println("Palabra Secreta: " + palabraSecreta);

        if (PalabraCombinada.length()<5){
            System.out.println("la palabra enviada tiene menos de 5 letras ");
            return;
        }



        if (intentoActual < fila_Palabra.length) {
            // procesar fila actual
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

            limpiarInputs();
            letraIndex = 0;
            intentoActual++; // mover a la siguiente fila
            System.out.println("intentos que llevo: " + intentoActual);

            if (palabraSecreta.equals(PalabraCombinada.toString())){
                System.out.println("Aacertaste la palabra, por lo tanto ganaste, pero me ofende");
                System.out.println("palabra secreta: "+ palabraSecreta + "     " +" palabra que meti: "+PalabraCombinada);
                juegoTerminado=true;
            }else{

            }
        } else {
            System.out.println("Se acabaron los intentos, perdiste");
        }
    }



}
