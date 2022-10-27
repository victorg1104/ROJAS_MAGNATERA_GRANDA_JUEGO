package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AutoEnemigo extends Objeto{

	@Override
	public void crear(int posY, int tipo) {
		posX = 800;
		this.posY = posY;
		imagen = new Texture(Gdx.files.internal("images/francesco.png"));
		sonido = Gdx.audio.newSound(Gdx.files.internal("sounds/francesco.mp3"));
		this.tipo = tipo;
		spr = new Sprite(imagen);
		spr.setCenter(posX, posY);
	}
	
	@Override
	public boolean accionar(Movible tarro) {
		tarro.dañar();
	   	sonido.play();
		if (tarro.getVidas() <= 0)
			return false; // si se queda sin vidas retorna falso /game over
		return true;
	}

	@Override
	public void actualizarMovimiento() {
		posX -= 400 * Gdx.graphics.getDeltaTime();;
		spr.setCenter(posX, posY);	
	}
}