package Superclases;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import Interfaces.BuilderObjetos;

//Clase Objeto, define una estructura para todos los objetos, además implementa la interfaz BuilderObjetos
public abstract class Objeto{
	protected int tipo;
	protected int posX;
	protected int posY;
	protected int daño;
	protected int premio;
	protected Texture imagen;
	protected Sprite spr;
	protected Sound sonido;
	
	public Objeto(int tipo, int posY, int daño, int premio) {
		this.tipo = tipo;
		posX = 800;
		this.posY = posY;
		this.daño = daño;
		this.premio = premio;
		setAssets();
	}
	
	//Métodos abstractos, dependen de cada subclase
	public abstract void setAssets(); //Carga los assets predefinidos
	public abstract void accionar(AutoProtagonista carro); //Realiza una acción predeterminada en caso de colisión
	
	public int getTipo() {
		return tipo;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public Rectangle getRectangle() {
		return spr.getBoundingRectangle(); //Retorna el rectangle que utiliza el sprite
	}
	
	public void dibujar(SpriteBatch batch) {
		spr.draw(batch);
	}
	
	public void actualizarMovimiento() {
		posX -= 400 * Gdx.graphics.getDeltaTime();;
		spr.setCenter(posX, posY);	
	}
	
	public void destruirObjeto() {
		sonido.stop();
		sonido.dispose();
		imagen.dispose();
	}
}
