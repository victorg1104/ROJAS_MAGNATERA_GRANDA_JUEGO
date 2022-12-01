package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Superclases.AutoProtagonista;
import Superclases.Objeto;

public class Copa extends Objeto{
	@Override
	public boolean accionar(AutoProtagonista carro) {
		carro.sumarPuntos(10);
        sonido.play(0.2f);
		return true;
	}

	@Override
	public void setAssets() {
		imagen = new Texture(Gdx.files.internal("images/pistonCup.png"));
		sonido = Gdx.audio.newSound(Gdx.files.internal("sounds/cuchau.mp3"));
		spr = new Sprite(imagen);
		spr.setCenter(posX, posY);
	}

}