package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Superclases.AutoProtagonista;
import Superclases.Objeto;

public class Cono extends Objeto{
	@Override
	public boolean accionar(AutoProtagonista carro) {
		carro.dañar(daño);
	   	sonido.play(0.2f);
		if (carro.getVidas() <= 0)
			return false; // si se queda sin vidas retorna falso /game over
		return true;
	}

	@Override
	public void setAssets() {
		imagen = new Texture(Gdx.files.internal("images/cono.png"));
		sonido = Gdx.audio.newSound(Gdx.files.internal("sounds/bonk.mp3"));
		spr = new Sprite(imagen);
		spr.setCenter(posX, posY);
	}
}
