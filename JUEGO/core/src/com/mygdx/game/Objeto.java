package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Objeto {
	protected int tipo;
	protected Texture imagen;
	protected Sprite spr;
	protected Sound sonido;
	protected int posX;
	protected int posY;
	
	public abstract void crear(int posY, int tipo);
	public abstract boolean accionar(Movible carro);	
	public abstract void actualizarMovimiento();
	
	public int getTipo() {
		return tipo;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public Rectangle getRectangle() {
		return spr.getBoundingRectangle();
	}
	
	public void dibujar(SpriteBatch batch) {
		spr.draw(batch);
	}
}
