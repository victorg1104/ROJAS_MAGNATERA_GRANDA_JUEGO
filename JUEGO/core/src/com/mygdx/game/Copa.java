package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Superclases.AutoProtagonista;
import Superclases.Objeto;

/*Clase copa, objeto a recolectar durante el juego, en caso de colisión, suma puntaje y premia al jugador*/

public class Copa extends Objeto{
	@Override
	public void accionar(AutoProtagonista carro) {
		sonido.play(0.2f); //Reproduce sonido en caso de premio
		carro.sumarPuntos(10); //En caso de colisión, suma puntos al score del personaje principal
	}

	@Override
	public void setAssets() { //Se reciben los archivos correspondientes y se fijan los assets del objeto (sonido, texture y sprite)
		imagen = new Texture(Gdx.files.internal("images/pistonCup.png"));
		sonido = Gdx.audio.newSound(Gdx.files.internal("sounds/cuchau.mp3"));
		spr = new Sprite(imagen);
		spr.setCenter(posX, posY);
	}

}