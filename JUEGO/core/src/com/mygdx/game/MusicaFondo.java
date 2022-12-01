package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

//Clase MusicaFondo, controla las operaciones respectivas a el background musical de cada screen
public class MusicaFondo {
	private Music musica;
	
	public void setArchivoMusica(String rutaArchivo) {
		musica = Gdx.audio.newMusic(Gdx.files.internal(rutaArchivo)); //Definir archivo
	}
	
	//Función que inicia la reproducción con los valores por default deseados
	public void iniciarReproduccion() {
		musica.setLooping(true);
		musica.setVolume(0.05f);
		musica.play();
	}
	
	//Colocar pausa a la música
	public void stop() {
		musica.stop();
	}
	
	//Finalizar la reproducción de la música
	public void destruir() {
		musica.dispose();
	}
}
