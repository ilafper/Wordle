package com.example.wordle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Palabras {

    //lista de palabras.
    private static final String[] lista_palabras = {
            "CASAS","PERRO","GATOS","LIMON","FRESA"

    };


    // Para validar rápido si un intento existe
    private final Set<String> setPalabras;

    public Palabras() {
        setPalabras = new HashSet<>();
        setPalabras.addAll(Arrays.asList(lista_palabras));
    }


    // Devuelve una palabra aleatoria (en mayúsculas).

    public String palabraAleatoria() {
        Random palabraAleatoria = new Random();
        return lista_palabras[palabraAleatoria.nextInt(lista_palabras.length)];
    }

    // Comprueba si la palabra es válida (está en la lista)
    public boolean esPalabraValida(String intento) {
        if (intento == null) return false;
        return setPalabras.contains(intento.toUpperCase());
    }


}
