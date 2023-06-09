package es.mariaanasanz.proyecto6.ejercicios;

import javafx.scene.input.KeyCode;

import java.util.*;

/**
 * @author unknown unknown
 */
public class Estadisticas {

    // TODO: inicializar correctamente las variables (6 puntos)
    private static TreeMap<String, Integer> contadorEventosTeclado;
    private static HashMap<String, HashMap<String, Integer>> contadorObjetosRecogidos;
    private static HashMap<Boolean, Integer> historicoDisparos;

    public static void mostrarEstadisticasSeguro(){
        try {
            System.out.println("****************************************");
            mostrarEventosTeclado();
            System.out.println("****************************************");
            Boolean enGeneralHaIdoBien = teclasPulsadasCorrectamente();
            if(enGeneralHaIdoBien) {
                System.out.println("En general no te has equivocado pulsando teclas.");
            } else {
                System.out.println("Te has estado equivocando un poco, las teclas del juego son: IZQUIERDA, DERECHA, SHIFT y ESCAPE");
            }
            System.out.println("****************************************");
            mostrarObjetosRecogidos();
            System.out.println("****************************************");
            mostrarElObjetoMenosRecogido();
            System.out.println("****************************************");
            mostrarInformacionDisparos();
            System.out.println("****************************************");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void capturarEventoTecladoSeguro(KeyCode code) {
        try {
            capturarEventoTeclado(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void objetoRecogidoSeguro(String actor, String objeto) {
        try {
            objetoRecogido(actor, objeto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * TODO: Captura todos los eventos que se produzcan durante la partida en el TreeMap contadorEventosTeclado (10 puntos)
     *  Acabaremos poblando un TreeMap que contenga la cantidad de veces que ha ocurrido cada evento del teclado, ordenada alfabeticamente
     *  Se deberan almacenar todos los tipos de eventos que lleguen, por muy distintos que sean. Por ejemplo, puede llegar:
     *      - KeyCode.RIGHT
     *      - KeyCode.LEFT
     *      - KeyCode.SHIFT
     *      - KeyCode.ESCAPE
     *      - KeyCode.CONTROL
     *      - KeyCode.A
     *      - KeyCode.S
     *      - ...
     *  IMPORTANTE: Se deberan almacenar todos los eventos (teclas pulsadas) con su contador asociado
     *  IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     * @param code el cogido de evento capturado
     */
    public static void capturarEventoTeclado(KeyCode code) {

    }

    /**
     * TODO: Se debera mostrar por consola toda la informacion del TreeMap contadorEventosTeclado en orden alfabetico descendente
     *  (el orden que se muestra en el ejemplo es el orden que hay que seguir) (14 puntos)
     *  Teclas pulsadas durante la partida:
     *      - SHIFT: 5 veces
     *      - RIGHT: 43 veces
     *      - LEFT: 55 veces
     *      - ESCAPE: 1 vez
     *      - DEMAS: 243 veces
     *  IMPORTANTE: Si no tenemos informacion de una tecla, no se mostrara
     *  IMPORTANTE: En DEMAS se mostrara el sumatorio del resto de teclas pulsadas
     *  IMPORTANTE: NO SE PODRAN EMPLEAR textos estáticos a pelo (strings) para obtener informacion
     *  IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     *  IMPORTANTE: Se debera emplear StringBuilder para construir la cadena a mostrar
     */
    public static void mostrarEventosTeclado(){

    }

    /**
     * TODO: debera devolver verdadero si se han pulsado mas teclas correctas que fallidas y false si han sido mas las incorrectas que las correctas (10 puntos)
     *  IMPORTANTE: Las teclas correctas son LEFT, RIGTH, SHIFT y ESCAPE
     *  IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     *  IMPORTANTE: Si el juego se cierra sin pulsar ninguna tecla, debera devolver true
     * @return true o false
     */
    public static boolean teclasPulsadasCorrectamente(){
        return false;
    }

    /**
     * TODO: se debera almacenar la relacion de objetos que son recogidos por cada actor en el HashMap contadorObjetosRecogidos (10 puntos)
     *  IMPORTANTE: la primera clave del HashMap contadorObjetosRecogidos sera el objeto
     *  IMPORTANTE: para cada objeto habra otro hashmap asociado con la relacion del actor y las veces que el actor ha recogido dicho objeto
     *  IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     * @param actor sera o el jugador o la zarigueya
     * @param objeto sera o la comida o la gema
     */
    public static void objetoRecogido(String actor, String objeto){

    }

    /**
     * TODO: Se debera mostrar por consola toda la informacion del HashMap contadorObjetosRecogidos con el siguiente formato (el orden no importa) (14 puntos)
     *  Objetos recogidos durante la partida:
     *      - JUGADOR ha recogido COMIDA 0 veces
     *      - JUGADOR ha recogido GEMAS 2 veces
     *      - ZARIGUEYA ha recogido COMIDA 3 veces
     *      - ZARIGUEYA ha recogido GEMAS 1 vez
     *  IMPORTANTE: Si no tenemos informacion de algun elemento, se debera visualizar un 0 (cero)
     *  IMPORTANTE: Se debera emplear siempre el metodo entrySet para recorrer los mapas
     *  IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     *  IMPORTANTE: Se debera emplear StringBuilder para construir la cadena a mostrar
     */
    public static void mostrarObjetosRecogidos(){

    }

    /**
     * TODO: Se debera mostrar por consola el objeto que ha sido menos recogido en base al HashMap contadorObjetosRecogidos con el siguiente formato (12 puntos)
     *  El objeto menos recogido ha sido... ¡[COMIDA/GEMA] con un total de [XX] veces recogido!
     *  IMPORTANTE: Se podra emplear el metodo deseado para recorrer las entradas
     *  IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     *  IMPORTANTE: Se debera emplear StringBuilder para construir la cadena a mostrar
     */
    public static void mostrarElObjetoMenosRecogido(){

    }

    /**
     * TODO: Se debera incluir al HashMap historicoDisparos la cantidad de disparos certeros y fallidos que se efectuen durante el juego (10 puntos)
     *  IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     * @param exito representa si el disparo es certero (true) o fallido (false)
     */
    public static void capturarDisparo(boolean exito){

    }

    /**
     * TODO: Se debera descontar un disparo del tipo QUE SE INDIQUE del HashMap historicoDisparos (6 puntos)
     *  AVISO: Es muy importante implementar correctamente este metodo para que el metodo mostrarRatioPrecision funcione correctamente
     *  IMPORTANTE: Se debera controlar que no se pueda restar un tipo de disparo que no exista o que su contador sea 0 (no se pueden acertar o fallar -1 disparos)
     *  IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     * @param exito representa si el disparo es certero (true) o fallido (false)
     */
    public static void borrarDisparo(boolean exito){

    }

    /**
     * TODO: Se debera mostrar por consola la cantidad de disparos certeros y disparos fallados en base al contenido del HashMap historicoDisparos con el siguiente formato: (10 puntos)
     *  Has acertado un total de [XX] disparos y has fallado un total de [YY] disparos. En total has realizado [ZZ] disparos.
     *  Adicionalmente,
     *      Si se han acertado mas disparos que los que se han fallado --> ¡Lo haces muy bien!
     *      Si se han fallado mas disparos que los que se han acertado --> Eres un poco malo en esto...
     *      Si han acertado y fallado la misma cantidad de veces --> ¿Lo has hecho a posta?
     *  IMPORTANTE: Se debera emplear StringBuilder para construir la cadena a mostrar
     */
    public static void mostrarInformacionDisparos(){

    }
}
