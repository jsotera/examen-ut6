package es.mariaanasanz.proyecto6.ejercicios;

import javafx.scene.input.KeyCode;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author unknown unknown
 */
public class Estadisticas {

    // TODO: inicializar correctamente las variables (6 puntos)
    private static HashMap<KeyCode, Integer> contadorEventosTeclado = new HashMap<>();
    private static HashMap<String, HashMap<String, Integer>> contadorObjetosRecogidos = new HashMap<>();
    private static HashMap<Boolean, Integer> historicoDisparos = new HashMap<>();

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
        Integer contador = contadorEventosTeclado.get(code);
        if(contador!=null){
            contador = Integer.valueOf(contador.intValue()+1);
        } else {
            contador = Integer.valueOf(1);
        }
        contadorEventosTeclado.put(code, contador);
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
        StringBuilder sb = new StringBuilder("Teclas pulsadas durante la partida:");
        KeyCode[] codes = {KeyCode.LEFT, KeyCode.RIGHT, KeyCode.SHIFT, KeyCode.ESCAPE};
        int contadorNoOtros = 0;
        int contadorTotal = 0;
        for (KeyCode code : codes) {
            Integer contador = contadorEventosTeclado.get(code);
            if(contador==null){
                contador = Integer.valueOf(0);
            }
            contadorNoOtros = contadorNoOtros + contador.intValue();
            sb.append("\n\t- "+code.toString()+": "+contador.toString());
        }
        Collection<Integer> contadores = contadorEventosTeclado.values();
        for (Integer auxContador : contadores) {
            contadorTotal = contadorTotal + auxContador.intValue();
        }
        sb.append("\n\t- OTROS: "+(contadorTotal - contadorNoOtros));
        System.out.println(sb.toString());
    }

    /**
     * TODO: debera devolver el evento con menos ocurrencias del HashMap contadorEventosTeclado (10 puntos)
     *  IMPORTANTE: Se debera emplear el metodo entrySet() para recorrer las entradas
     *  IMPORTANTE: El resto de teclas pulsadas contaran como la misma tecla y si su contador es el menor, se debera devolver KeyCode.MINUS
     *  IMPORTANTE: Si el juego se cierra sin pulsar ninguna tecla, debera devolver KeyCode.ASTERISK
     * @return KeyCode menos frecuente
     */
    public static KeyCode teclaMenosPulsada(){
        Set<Entry<KeyCode, Integer>> entries = contadorEventosTeclado.entrySet();
        KeyCode[] codes = {KeyCode.LEFT, KeyCode.RIGHT, KeyCode.SHIFT, KeyCode.ESCAPE};
        KeyCode teclaMenosPulsada = KeyCode.ASTERISK;
        int minOcurrencias = 0;
        int contadorOtros = 0;
        for (Entry<KeyCode, Integer> entry : entries) {
            KeyCode code = entry.getKey();
            Integer contador = entry.getValue();
            if(Arrays.asList(codes).contains(code)){
                if(minOcurrencias==0 || minOcurrencias>contador.intValue()){
                    minOcurrencias = contador.intValue();
                    teclaMenosPulsada = code;
                }
            } else {
                contadorOtros = contadorOtros + contador.intValue();
            }
        }
        if(contadorOtros>0 && contadorOtros<minOcurrencias){
            teclaMenosPulsada = KeyCode.MINUS;
        }
        return teclaMenosPulsada;
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
        HashMap<String, Integer> objetosRecogidos = contadorObjetosRecogidos.get(objeto);
        if(objetosRecogidos==null){
            objetosRecogidos = new HashMap<>();
        }
        Integer cantidadRecogida = objetosRecogidos.get(actor);
        if(cantidadRecogida==null){
            cantidadRecogida = Integer.valueOf(0);
        }
        cantidadRecogida = Integer.valueOf(cantidadRecogida.intValue()+1);
        objetosRecogidos.put(actor, cantidadRecogida);
        contadorObjetosRecogidos.put(objeto, objetosRecogidos);
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
    public static void mostrarObjetosRecogidos() {
        String[] objetos = {"comida", "gema"};
        String[] actores = {"jugador", "zarigueya"};
        for (String objeto : objetos) {
            HashMap<String, Integer> objetosPorCadaActor = contadorObjetosRecogidos.get(objeto);
            if (objetosPorCadaActor == null) {
                objetosPorCadaActor = new HashMap<>();
            }
            for (String actor : actores) {
                Integer contador = objetosPorCadaActor.get(actor);
                if (contador == null) {
                    contador = Integer.valueOf(0);
                    objetosPorCadaActor.put(actor, contador);
                }
            }
            contadorObjetosRecogidos.put(objeto, objetosPorCadaActor);
        }
        StringBuilder sb = new StringBuilder("Objetos recogidos durante la partida:");
        Set <Entry<String, HashMap<String, Integer>>> objetoEntries = contadorObjetosRecogidos.entrySet();
        for (Entry<String, HashMap<String, Integer>> objetoEntry : objetoEntries) {
            Set<Entry<String, Integer>> actoresEntries = objetoEntry.getValue().entrySet();
            sb.append("\n\t- " + objetoEntry.getKey().toUpperCase() + ":");
            for (Entry<String, Integer> actoresEntry : actoresEntries) {
                Integer contador = actoresEntry.getValue();
                sb.append("\n\t\t- " + actoresEntry.getKey() + ": " + contador.toString());
                if (contador.intValue() == 1) {
                    sb.append(" vez");
                } else {
                    sb.append(" veces");
                }
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * TODO: Se debera mostrar por consola quien ha recogido menos objetos en base al HashMap contadorObjetosRecogidos con el siguiente formato (12 puntos)
     *  Quien ha recogido menos objetos ha sido... ¡[JUGADOR/ZARIGUEYA] con un total de [XX] objetos!
     *  IMPORTANTE: Se podra emplear el metodo deseado para recorrer las entradas
     *  IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     *  IMPORTANTE: Se debera emplear StringBuilder para construir la cadena a mostrar
     */
    public static void mostrarQuienHaRecogidoMenosObjetos(){
        StringBuilder sb = new StringBuilder("Quien ha recogido menos objetos ha sido... ");
        Set<String> keys = contadorObjetosRecogidos.keySet();
        int contadorJugador = 0;
        int contadorZarigueya = 0;
        for(String key : keys){
            Integer contador = contadorObjetosRecogidos.get(key).get("jugador");
            if(contador!=null){
                contadorJugador = contadorJugador + contador.intValue();
            }
            contador = contadorObjetosRecogidos.get(key).get("zarigueya");
            if(contador!=null){
                contadorZarigueya = contadorZarigueya + contador.intValue();
            }
        }
        if(contadorJugador<contadorZarigueya){
            sb.append("¡JUGADOR con un total de "+contadorJugador+" ");
            if(contadorJugador==1){
                sb.append("objeto!");
            } else {
                sb.append("objetos!");
            }
        } else {
            sb.append("¡ZARIGUEYA con un total de "+contadorZarigueya+" ");
            if(contadorZarigueya==1){
                sb.append("objeto!");
            } else {
                sb.append("objetos!");
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * TODO: Se debera incluir al HashMap historicoDisparos la cantidad de disparos certeros y fallidos que se efectuen durante el juego (10 puntos)
     *  IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     * @param exito representa si el disparo es certero (true) o fallido (false)
     */
    public static void capturarDisparo(boolean exito){
        Integer contador = historicoDisparos.get(Boolean.valueOf(exito));
        if(contador==null){
            contador = Integer.valueOf(0);
        }
        contador = Integer.valueOf(contador.intValue()+1);
        historicoDisparos.put(Boolean.valueOf(exito), contador);
    }

    /**
     * TODO: Se debera descontar un disparo del tipo QUE SE INDIQUE del HashMap historicoDisparos (6 puntos)
     *  AVISO: Es muy importante implementar correctamente este metodo para que el metodo mostrarRatioPrecision funcione correctamente
     *  IMPORTANTE: Se debera controlar que no se pueda restar un tipo de disparo que no exista o que su contador sea 0 (no se pueden acertar o fallar -1 disparos)
     *  IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     * @param exito representa si el disparo es certero (true) o fallido (false)
     */
    public static void borrarDisparo(boolean exito){
        Integer contador = historicoDisparos.get(Boolean.valueOf(exito));
        if(contador==null){
            return;
        }
        if(contador.intValue()>0) {
            contador = Integer.valueOf(contador.intValue() - 1);
            historicoDisparos.put(Boolean.valueOf(exito), contador);
        }
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
        int fallos = 0;
        Integer contador = historicoDisparos.get(Boolean.valueOf(false));
        if(contador!=null){
            fallos = contador.intValue();
        }
        Collection<Integer> coleccionContador = historicoDisparos.values();
        int totalDisparos = 0;
        for (Integer cantidad : coleccionContador) {
            totalDisparos = totalDisparos + cantidad.intValue();
        }
        int ratioFallos = 0;
        if(fallos>0){
            ratioFallos = (fallos*100)/totalDisparos;
        }
        StringBuilder sb = new StringBuilder("Tienes una posibilidad de fallar del "+ratioFallos+"%. ");
        if(ratioFallos<34){
            sb.append("¡Eres insuperable!");
        } else if (ratioFallos<67) {
            sb.append("No esta nada mal");
        } else {
            sb.append("Deberias entrenar un poco mas...");
        }
        System.out.println(sb.toString());
    }
}
