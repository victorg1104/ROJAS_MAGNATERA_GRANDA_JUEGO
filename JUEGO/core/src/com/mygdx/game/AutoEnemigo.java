package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import Superclases.AutoProtagonista;
import Superclases.Objeto;

/*Clase del objeto "auto enemigo" (auto rojo, verde, blanco en el juego)
 * obstáculo a superar, hace "daño" en caso de colisionar con él*/

public class AutoEnemigo extends Objeto{
	@Override
	public void accionar(AutoProtagonista carro) {
		sonido.play(0.2f); //Se reproduce sonido en caso de choque
		carro.dañarParpadear(daño); //Se le aplica daño correspondiente al auto prinicipal del juego
	}

	@Override
	public void setAssets() { //Se reciben los archivos correspondientes y se fijan los assets del objeto (sonido, texture y sprite)
		imagen = new Texture(Gdx.files.internal("images/francesco.png"));
		sonido = Gdx.audio.newSound(Gdx.files.internal("sounds/francesco.mp3"));
		spr = new Sprite(imagen);
		spr.setCenter(posX, posY);
	}
}