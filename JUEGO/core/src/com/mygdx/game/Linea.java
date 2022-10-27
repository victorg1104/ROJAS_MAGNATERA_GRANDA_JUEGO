package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Linea extends Objeto{

	@Override
	public void crear(int posY, int tipo) {
		posX = 800;
		this.posY = posY;
		imagen = new Texture(Gdx.files.internal("images/LINEA.png"));
		this.tipo = tipo;
		spr = new Sprite(imagen);
		spr.setCenter(posX, posY);	
	}

	@Override
	public boolean accionar(Movible carro) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void actualizarMovimiento() {
		// TODO Auto-generated method stub
		
	}
	
}
