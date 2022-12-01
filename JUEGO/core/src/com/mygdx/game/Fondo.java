package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Fondo {
	private Texture imagenFondo;
	private BitmapFont fuenteFondo;
	private static Fondo fondo;
	
	private Fondo(String rutaFondo, BitmapFont fuenteFondo) {
		this.imagenFondo = new Texture(Gdx.files.internal(rutaFondo));
		this.fuenteFondo = fuenteFondo;
	}
	
	public static Fondo crearFondo(String rutaFondo, BitmapFont fuenteFondo) {
		if (Fondo.fondo == null){
			Fondo.fondo = new Fondo(rutaFondo, fuenteFondo);
		}
		return Fondo.fondo;
	}
	
	public Texture getImagenFondo() {
		return imagenFondo;
	}
	
	public BitmapFont getFuente() {
		return fuenteFondo;
	}
	
	public void destruir() {
		Fondo.fondo = null;
	}
}
