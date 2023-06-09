package es.mariaanasanz.proyecto6.ejercicios;

import javafx.scene.input.KeyCode;

import java.util.*;

/**
 * @author unknown unknown
 */
public class Estadisticas {

    // TODO: inicializar correctamente las variables (6 puntos)
    private static TreeMap<String, Integer> contadorEventosTeclado = new TreeMap<>();
    private static HashMap<String, HashMap<String, Integer>> contadorObjetosRecogidos = new HashMap<>();
    private static HashMap<Boolean, Integer> historicoDisparos = new HashMap<>();

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
     * TODO: Captura todos los eventos que se produzcan durante la partida en el TreeMap contadorEventosTeclado (8 puntos)
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
        Integer contador = contadorEventosTeclado.get(code.toString());
        if(contador!=null){
            contador = Integer.valueOf(contador.intValue()+1);
        } else {
            contador = Integer.valueOf(1);
        }
        contadorEventosTeclado.put(code.toString(), contador);
    }

    /**
     * TODO: Se debera mostrar por consola toda la informacion del TreeMap contadorEventosTeclado en orden alfabetico descendente
     *  (el orden que se muestra en el ejemplo es el ejemplo que hay que seguir) (14 puntos)
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
        StringBuilder sb = new StringBuilder("Teclas pulsadas durante la partida:");
        List<String> codigos = List.of(KeyCode.LEFT.toString(), KeyCode.RIGHT.toString(),
                KeyCode.SHIFT.toString(), KeyCode.ESCAPE.toString());
        int contadorOtros = 0;
        ArrayList<String> keys = new ArrayList<>(contadorEventosTeclado.keySet());
        for (int i = keys.size()-1; i >= 0; i--) {
            if(codigos.contains(keys.get(i))) {
                sb.append("\n\t- " + keys.get(i) + ": " + contadorEventosTeclado.get(keys.get(i)).toString());
                if (contadorEventosTeclado.get(keys.get(i)).intValue() == 1) {
                    sb.append(" vez");
                } else {
                    sb.append(" veces");
                }
            } else {
                contadorOtros = contadorOtros + contadorEventosTeclado.get(keys.get(i)).intValue();
            }
        }
        if(contadorOtros>0){
            sb.append("\n\t- DEMAS: " + contadorOtros);
            if (contadorOtros == 1) {
                sb.append(" vez");
            } else {
                sb.append(" veces");
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * TODO: debera devolver verdadero si se han pulsado mas teclas correctas y false si han sido mas las incorrectas (10 puntos)
     *  IMPORTANTE: Las teclas correctas son LEFT, RIGTH, SHIFT y ESCAPE
     *  IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     *  IMPORTANTE: Si el juego se cierra sin pulsar ninguna tecla, debera devolver true
     * @return true o false
     */
    public static boolean teclasPulsadasCorrectamente(){
        List<String> codigos = List.of(KeyCode.LEFT.toString(), KeyCode.RIGHT.toString(),
                KeyCode.SHIFT.toString(), KeyCode.ESCAPE.toString());
        int contadorOtros = 0;
        int contadorBuenas = 0;
        ArrayList<String> keys = new ArrayList<>(contadorEventosTeclado.keySet());
        for (int i = keys.size()-1; i >= 0; i--) {
            if(codigos.contains(keys.get(i))) {
                contadorBuenas = contadorBuenas + contadorEventosTeclado.get(keys.get(i)).intValue();
            } else {
                contadorOtros = contadorOtros + contadorEventosTeclado.get(keys.get(i)).intValue();
            }
        }
        if(contadorOtros>contadorBuenas){
            return false;
        } else {
            return true;
        }
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
        Set <Map.Entry<String, HashMap<String, Integer>>> objetoEntries = contadorObjetosRecogidos.entrySet();
        for (Map.Entry<String, HashMap<String, Integer>> objetoEntry : objetoEntries) {
            Set<Map.Entry<String, Integer>> actoresEntries = objetoEntry.getValue().entrySet();
            for (Map.Entry<String, Integer> actoresEntry : actoresEntries) {
                Integer contador = actoresEntry.getValue();
                sb.append("\n\t- " + actoresEntry.getKey().toUpperCase() + " ha recogido " + objetoEntry.getKey().toUpperCase() + " " + contador.toString());
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
     * TODO: Se debera mostrar por consola el objeto que ha sido menos recogido en base al HashMap contadorObjetosRecogidos con el siguiente formato (12 puntos)
     *  El objeto menos recogido ha sido... ¡[COMIDA/GEMA] con un total de [XX] veces recogido!
     *  IMPORTANTE: Se podra emplear el metodo deseado para recorrer las entradas
     *  IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     *  IMPORTANTE: Se debera emplear StringBuilder para construir la cadena a mostrar
     */
    public static void mostrarElObjetoMenosRecogido(){
        StringBuilder sb = new StringBuilder("El objeto menos recogido ha sido... ");
        Set<String> keys = contadorObjetosRecogidos.keySet();
        int contadorComida = 0;
        int contadorGemas = 0;
        HashMap<String, Integer> mapaComida = contadorObjetosRecogidos.get("comida");
        if(mapaComida!=null){
            Iterator<Integer> iterator = mapaComida.values().iterator();
            while(iterator.hasNext()){
                contadorComida = contadorComida + iterator.next().intValue();
            }
        }
        HashMap<String, Integer> mapaGemas = contadorObjetosRecogidos.get("gema");
        if(mapaGemas!=null){
            Iterator<Integer> iterator = mapaGemas.values().iterator();
            while(iterator.hasNext()){
                contadorGemas = contadorGemas + iterator.next().intValue();
            }
        }
        if(contadorComida<contadorGemas){
            sb.append("¡COMIDA con un total de "+contadorComida+" ");
            if(contadorComida==1){
                sb.append("vez recogido!");
            } else {
                sb.append("veces recogido!");
            }
        } else {
            sb.append("¡GEMA con un total de "+contadorGemas+" ");
            if(contadorGemas==1){
                sb.append("vez recogido!");
            } else {
                sb.append("veces recogido!");
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
     * TODO: Se debera mostrar por consola la cantidad de disparos certeros y disparos fallados en base al contenido del HashMap historicoDisparos con el siguiente formato: (10 puntos)
     *  Has acertado un total de [XX] disparos y has fallado un total de [YY] disparos. En total has realizado [ZZ] disparos.
     *  Adicionalmente,
     *      Si se han acertado mas disparos que los que se han fallado --> ¡Lo haces muy bien!
     *      Si se han fallado mas disparos que los que se han acertado --> Eres un poco malo en esto...
     *      Si han acertado y fallado la misma cantidad de veces --> ¿Lo has hecho a posta?
     *  IMPORTANTE: Se debera emplear StringBuilder para construir la cadena a mostrar
     */
    public static void mostrarInformacionDisparos(){
        int fallos = 0;
        int aciertos = 0;
        Integer contadorF = historicoDisparos.get(Boolean.valueOf(false));
        if(contadorF!=null){
            fallos = contadorF.intValue();
        }
        Integer contadorA = historicoDisparos.get(Boolean.valueOf(false));
        if(contadorA!=null){
            aciertos = contadorA.intValue();
        }
        int totalDisparos = fallos+aciertos;

        StringBuilder sb = new StringBuilder("Has acertado un total de "+aciertos+" disparos y has fallado un total de "+fallos+" disparos." +
                " En total has realizado "+totalDisparos+" disparos. ");
        if(aciertos>fallos){
            sb.append("¡Lo haces muy bien!");
        } else if (aciertos<fallos) {
            sb.append("Eres un poco malo en esto...");
        } else {
            sb.append("¿Lo has hecho a posta?");
        }
        System.out.println(sb.toString());
    }
}
