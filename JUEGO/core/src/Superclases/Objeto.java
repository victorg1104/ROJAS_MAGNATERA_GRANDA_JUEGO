package Superclases;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import Interfaces.BuilderObjetos;

public abstract class Objeto implements BuilderObjetos{
	protected int tipo;
	protected int posX;
	protected int posY;
	protected int daño;
	protected int premio;
	protected Texture imagen;
	protected Sprite spr;
	protected Sound sonido;
	
	public abstract void setAssets();
	public abstract boolean accionar(AutoProtagonista carro);
	
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
	
	public void actualizarMovimiento() {
		posX -= 400 * Gdx.graphics.getDeltaTime();;
		spr.setCenter(posX, posY);	
	}
	
	public void setDaño(int daño) {
		this.daño = daño;
	}
	
	public void setPremio(int premio) {
		this.premio = premio;
	}
	
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public void setPosicion(int posY) {
		posX = 800;
		this.posY = posY;
	}
	
	public void destruirObjeto() {
		sonido.stop();
		sonido.dispose();
		imagen.dispose();
	}
}
