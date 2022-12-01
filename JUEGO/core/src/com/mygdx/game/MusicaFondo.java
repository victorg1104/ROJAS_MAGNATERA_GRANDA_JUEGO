package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicaFondo {
	private Music musica;
	
	public void setArchivoMusica(String rutaArchivo) {
		musica = Gdx.audio.newMusic(Gdx.files.internal(rutaArchivo));
	}
	
	public void iniciarReproduccion() {
		musica.setLooping(true);
		musica.setVolume(0.05f);
		musica.play();
	}
	
	public void stop() {
		musica.stop();
	}
	
	public void destruir() {
		musica.dispose();
	}
}
