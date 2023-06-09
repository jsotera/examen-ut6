package es.mariaanasanz.proyecto6.simulador;

import es.mariaanasanz.proyecto6.ejercicios.Estadisticas;
import javafx.scene.input.KeyCode;

import java.util.Map;

public class Simulador {

    public static void main(String[] args2) {
        String[] args = {"1","2","3","4","5","6","7","8","9","10","11","2","3"};
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            Estadisticas.capturarEventoTeclado(KeyCode.LEFT);
        }
        for (int i = 0; i < Integer.parseInt(args[1]); i++) {
            Estadisticas.capturarEventoTeclado(KeyCode.RIGHT);
        }
        for (int i = 0; i < Integer.parseInt(args[2]); i++) {
            Estadisticas.capturarEventoTeclado(KeyCode.SHIFT);
        }
        for (int i = 0; i < Integer.parseInt(args[3]); i++) {
            Estadisticas.capturarEventoTeclado(KeyCode.ESCAPE);
        }
        for (int i = 0; i < Integer.parseInt(args[4]); i++) {
            Estadisticas.capturarEventoTeclado(KeyCode.values()[(int) (Math.random()*KeyCode.values().length)]);
        }
        for (int i = 0; i < Integer.parseInt(args[5]); i++) {
            Estadisticas.objetoRecogido("jugador", "comida");
        }
        for (int i = 0; i < Integer.parseInt(args[6]); i++) {
            Estadisticas.objetoRecogido("jugador", "gema");
        }
        for (int i = 0; i < Integer.parseInt(args[7]); i++) {
            Estadisticas.objetoRecogido("zarigueya", "comida");
        }
        for (int i = 0; i < Integer.parseInt(args[8]); i++) {
            Estadisticas.objetoRecogido("zarigueya", "gema");
        }
        for (int i = 0; i < Integer.parseInt(args[9]); i++) {
            Estadisticas.capturarDisparo(true);
        }
        for (int i = 0; i < Integer.parseInt(args[10]); i++) {
            Estadisticas.capturarDisparo(false);
        }
        for (int i = 0; i < Integer.parseInt(args[11]); i++) {
            Estadisticas.borrarDisparo(true);
        }
        for (int i = 0; i < Integer.parseInt(args[12]); i++) {
            Estadisticas.borrarDisparo(false);
        }
        System.out.println("****************************************");
        Estadisticas.mostrarEventosTeclado();
        System.out.println("****************************************");
        Boolean enGeneralHaIdoBien = Estadisticas.teclasPulsadasCorrectamente();
        if(enGeneralHaIdoBien) {
            System.out.println("En general no te has equivocado pulsando teclas.");
        } else {
            System.out.println("Te has estado equivocando un poco, las teclas del juego son: IZQUIERDA, DERECHA, SHIFT y ESCAPE");
        }
        Estadisticas.mostrarObjetosRecogidos();
        System.out.println("****************************************");
        Estadisticas.mostrarElObjetoMenosRecogido();
        System.out.println("****************************************");
        Estadisticas.mostrarInformacionDisparos();
        System.out.println("****************************************");
    }

}
