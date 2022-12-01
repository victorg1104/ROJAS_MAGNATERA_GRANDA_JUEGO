package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Superclases.AutoProtagonista;
import Superclases.Objeto;

/*Clase "cono", obstáculo a superar, aplica "daño" al personaje principal en caso de colisión*/

public class Cono extends Objeto{
	@Override
	public void accionar(AutoProtagonista carro) {
		sonido.play(0.2f); //Se reproduce sonido en caso de choque
		carro.dañarGirar(daño); //Se le aplica daño correspondiente al auto principal en caso de colisión
	}

	@Override
	public void setAssets() { //Se reciben los archivos correspondientes y se fijan los assets del objeto (sonido, texture y sprite)
		imagen = new Texture(Gdx.files.internal("images/cono.png"));
		sonido = Gdx.audio.newSound(Gdx.files.internal("sounds/bonk.mp3"));
		spr = new Sprite(imagen);
		spr.setCenter(posX, posY);
	}
}
