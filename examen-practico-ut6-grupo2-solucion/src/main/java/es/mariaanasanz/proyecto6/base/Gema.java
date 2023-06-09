package es.mariaanasanz.proyecto6.base;

import javafx.scene.image.Image;
/*
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
*/
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class Gema extends Objeto
{
    private double posicionInicialX, posicionInicialY;
    private static final String RUTA_SPRITE = "Recursos/GIF/items/gem.gif";
    private static final String RUTA_SONIDO_GEMA = "Recursos/Audio/gem.wav";
    private static final int VALOR_EN_PUNTOS = 500;
    public Gema(float anchoDeLaEscena, float altoDeLaEscena, int distanciaAlSuelo)
    {
        super(anchoDeLaEscena, altoDeLaEscena, distanciaAlSuelo);
        puntos = VALOR_EN_PUNTOS;
    }
    
    @Override
    public void posicionar(){
        setX(posicionInicialX);
        setY(posicionInicialY);
    }
    
    @Override
    public void inicializarMedios(){
        try {
            spriteIdle = new Image(Files.newInputStream(Path.of(RUTA_SPRITE)));
            /*
            sonido = new Media(new File(RUTA_SONIDO_GEMA).toURI().toString());
            reproductorSonido = new MediaPlayer(sonido);
            */
        } catch (Exception e) {
            System.out.println("ALGO NO HA IDO BIEN");
            e.printStackTrace();
        }
    }
    
    @Override
    public boolean recogidoPor(Jugador jugador){
        boolean valorDeRetorno = super.recogidoPor(jugador);
        return valorDeRetorno;
    }
    
    public void fijarPosicion(double posicionX, double posicionY){
        posicionInicialX = posicionX;
        posicionInicialY = posicionY;
        posicionar();
    }
}