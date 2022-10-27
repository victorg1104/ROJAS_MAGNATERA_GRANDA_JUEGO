package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Copa extends Objeto{
	
	@Override
	public void crear(int posY, int tipo) {
		posX = 800;
		this.posY = posY;
		imagen = new Texture(Gdx.files.internal("images/pistonCup.png"));
		sonido = Gdx.audio.newSound(Gdx.files.internal("sounds/cuchau.mp3"));
		this.tipo = tipo;
		spr = new Sprite(imagen);
		spr.setCenter(posX, posY);
	}

	@Override
	public boolean accionar(Movible tarro) {
		tarro.sumarPuntos(10);
        sonido.play();
		return true;
	}

	@Override
	public void actualizarMovimiento() {
		posX -= 400 * Gdx.graphics.getDeltaTime();;
		spr.setCenter(posX, posY);
	}
}