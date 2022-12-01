package Superclases;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.DirectorObjetos;

import Interfaces.Movible;
import Screens.GameOverScreen;

public abstract class AutoProtagonista implements Movible{
	protected Sprite spr;
	protected Texture imagen;
	protected int grados;
	protected int vidas;
	protected int puntos;
	protected int velx;
	protected boolean herido;
	protected int tiempoHeridoMax;
	protected int tiempoHerido;
	   
	public abstract void crearCarroRapido();
	public abstract void crearCarroLento();

	
	public int getVidas() {
		return vidas;
	}

	public int getPuntos() {
		return puntos;
	}
	
	public Rectangle getArea() {
		return spr.getBoundingRectangle();
	}
	
	public void sumarPuntos(int pp) {
		puntos+=pp;
	}
	
	public void dañar(int daño) {
		  vidas -= daño;
		  herido = true;
		  tiempoHerido = tiempoHeridoMax;
		  grados = 10;
	}
	
	public void dibujar(SpriteBatch batch) {
		 if (!herido) {
		   if (grados != 360) 
			   spr.setRotation(0);		   
		   spr.draw(batch);
		 }
		 else {
		   spr.setRotation(grados);
		   spr.draw(batch);
		   grados *= 2;
		   tiempoHerido--;
		   if (tiempoHerido <= 0) herido = false;
		 }
	}
	
	public void destruir() {
	    imagen.dispose();
   }

   	public boolean estaHerido() {
   		return herido;
   	}
   	
   	public boolean actualizarMovimiento(DirectorObjetos obs) {
   		if (!estaHerido()) {
			// movimiento del tarro desde teclado
	        actualizarPorTeclado();        
			// caida de la lluvia 
	        if (!obs.actualizarMovimiento(this)) return false;
		}
   		return true;
   	}
	     
	@Override
	public void actualizarPorTeclado() { 
		   //movimiento desde teclado
		   if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) spr.setY(spr.getY() - velx * Gdx.graphics.getDeltaTime());
		   if(Gdx.input.isKeyPressed(Input.Keys.UP)) spr.setY(spr.getY() + velx * Gdx.graphics.getDeltaTime());
		   // que no se salga de los bordes izq y der
		   if(spr.getY() < 10) spr.setY(10);
		   if(spr.getY() > 365) spr.setY(365);
	}

}
