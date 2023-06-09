package es.mariaanasanz.proyecto6.ejercicios;

import javafx.scene.input.KeyCode;

import java.util.HashMap;

/**
 * @author unknown unknown
 */
public class Estadisticas {

    // TODO: inicializar correctamente las variables (6 puntos)
    private static HashMap<KeyCode, Integer> contadorEventosTeclado;
    private static HashMap<String, HashMap<String, Integer>> contadorObjetosRecogidos;
    private static HashMap<Boolean, Integer> historicoDisparos;

    public static void mostrarEstadisticasSeguro(){
        try {
            System.out.println("****************************************");
            mostrarEventosTeclado();
            System.out.println("****************************************");
            KeyCode code = teclaMenosPulsada();
            if(code!=null) {
                System.out.println("La tecla que mas veces se ha pulsado ha sido: " + code.toString());
                System.out.println("****************************************");
            }
            mostrarObjetosRecogidos();
            System.out.println("****************************************");
            mostrarQuienHaRecogidoMenosObjetos();
            System.out.println("****************************************");
            mostrarRatioFracaso();
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
     * TODO: Captura todos los eventos que se produzcan durante la partida en el HashMap contadorEventosTeclado (8 puntos)
     *  Acabaremos poblando un HashMap que contenga la cantidad de veces que ha ocurrido cada evento de teclado
     *  Se deberan almacenar todos los tipos de eventos que lleguen, por muy distintos que sean:
     *      - KeyCode.RIGHT
     *      - KeyCode.LEFT
     *      - KeyCode.SHIFT
     *      - KeyCode.ESCAPE
     *      - KeyCode.CONTROL
     *      - KeyCode.A
     *      - KeyCode.S
     *      - ...
     * IMPORTANTE: Se deberan almacenar todos los eventos (teclas pulsadas) con su contador asociado
     * IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     *
     * @param code el cogido de evento capturado
     */
    public static void capturarEventoTeclado(KeyCode code) {

    }

    /**
     * TODO: Se debera mostrar por consola toda la informacion del HashMap contadorEventosTeclado CON EL SIGUIENTE ORDEN
     *  (SI, el orden que se muestra en el ejemplo es el que hay que seguir) (14 puntos)
     *  Teclas pulsadas durante la partida:
     *      - LEFT: 55 veces
     *      - RIGHT: 43 veces
     *      - SHIFT: 0 veces
     *      - ESCAPE: 1 vez
     *      - OTROS: 243 veces
     *  IMPORTANTE: Si no tenemos informacion de una tecla, sera como si se hubiese pulsado 0 veces
     *  IMPORTANTE: En OTROS se mostrarán el sumatorio del resto de teclas pulsadas
     *  IMPORTANTE: NO SE PODRAN EMPLEAR los metodos keySet() ni entrySet() para obtener informacion
     *  IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     *  IMPORTANTE: Se debera emplear StringBuilder para construir la cadena a mostrar
     */
    public static void mostrarEventosTeclado(){

    }

    /**
     * TODO: debera devolver el evento con menos ocurrencias del HashMap contadorEventosTeclado (10 puntos)
     *  IMPORTANTE: Se debera emplear el metodo entrySet() para recorrer las entradas
     *  IMPORTANTE: El resto de teclas pulsadas contaran como la misma tecla y si su contador es el menor, se debera devolver KeyCode.MINUS
     *  IMPORTANTE: Si el juego se cierra sin pulsar ninguna tecla, debera devolver KeyCode.ASTERISK
     * @return KeyCode menos frecuente
     */
    public static KeyCode teclaMenosPulsada(){
        return null;
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
     *      - COMIDA:
     *          - jugador: 0 veces
     *          - zarigueya: 3 veces
     *      - GEMAS:
     *          - jugador: 2 veces
     *          - zarigueya: 0 veces
     *  IMPORTANTE: Si no tenemos informacion de algun elemento, sera como si se hubiese pulsado 0 veces
     *  IMPORTANTE: Se debera emplear siempre el metodo entrySet para recorrer los mapas
     *  IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     *  IMPORTANTE: Se debera emplear StringBuilder para construir la cadena a mostrar
     */
    public static void mostrarObjetosRecogidos(){

    }

    /**
     * TODO: Se debera mostrar por consola quien ha recogido menos objetos en base al HashMap contadorObjetosRecogidos con el siguiente formato (12 puntos)
     *  Quien ha recogido menos objetos ha sido... ¡[JUGADOR/ZARIGUEYA] con un total de [XX] objetos!
     *  IMPORTANTE: Se podra emplear el metodo deseado para recorrer las entradas
     *  IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     *  IMPORTANTE: Se debera emplear StringBuilder para construir la cadena a mostrar
     */
    public static void mostrarQuienHaRecogidoMenosObjetos(){

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
     * TODO: Se debera mostrar por consola el porcentaje de fallos en base al contenido del HashMap historicoDisparos con el siguiente formato: (10 puntos)
     *  Tienes una posibilidad de fallar del [XX]%.
     *  Adicionalmente, en base a la proporcion de fallos, se mostrara un mensaje adicional:
     *      Si la proporcion de fallos es entre un 67 y un 100% --> Deberias entrenar un poco mas...
     *      Si la proporcion de fallos es entre un 34 y un 66% --> No esta nada mal
     *      Si la proporcion de fallos es entre un 0 y un 33% --> ¡Eres insuperable!
     *  IMPORTANTE: La proporcion de fallos se mide en base a los disparos fallados multiplicado por 100 entre el total de disparos
     *  IMPORTANTE: Se debera emplear StringBuilder para construir la cadena a mostrar
     */
    public static void mostrarRatioFracaso(){

    }
}
