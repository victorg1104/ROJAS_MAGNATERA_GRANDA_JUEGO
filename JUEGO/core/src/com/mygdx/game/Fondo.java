package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

//Clase Singleton Fondo, para verificar que cada fondo sea creado una sola vez durante la ejecución del código
public class Fondo {
	private Texture imagenFondo;
	private BitmapFont fuenteFondo;
	private static Fondo fondo; //Instancia estática de la clase
	
	//Constructor PRIVADO de los atributos de la clase
	private Fondo(String rutaFondo, BitmapFont fuenteFondo) {
		this.imagenFondo = new Texture(Gdx.files.internal(rutaFondo));
		this.fuenteFondo = fuenteFondo;
	}
	
	//Función que crea Fondo, en caso de que no exista alguno ya creado anteriormente
	public static Fondo crearFondo(String rutaFondo, BitmapFont fuenteFondo) {
		if (Fondo.fondo == null){
			Fondo.fondo = new Fondo(rutaFondo, fuenteFondo); //en caso de no existir un fondo creado, se crea y se retorna
		}
		return Fondo.fondo;
	}
	
	//Función que retorna la Texture del fondo
	public Texture getImagenFondo() {
		return imagenFondo;
	}
	
	//Función que retorna la fuente del fondo
	public BitmapFont getFuente() {
		return fuenteFondo;
	}
	
	//Función que actualiza a null la instancia de la clase Singleton
	public void destruir() {
		Fondo.fondo = null;
	}
}
