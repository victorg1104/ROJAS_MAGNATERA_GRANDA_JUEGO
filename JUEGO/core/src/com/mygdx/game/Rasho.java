package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public class Rasho implements Movible{
	   private Sprite spr;
	   private Texture imagenRayo;
	   private int grados;
	   private int vidas = 3;
	   private int puntos = 0;
	   private int velx = 400;
	   private boolean herido = false;
	   private int tiempoHeridoMax = 50;
	   private int tiempoHerido;
	   
	   
	   @Override
	   	public void crearCarro() {
	   		imagenRayo = new Texture(Gdx.files.internal("images/rayo.png"));
			spr = new Sprite(imagenRayo);
			spr.setCenter(64, 185);
	   	}
	   
	   
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
		
	   public void dañar() {
		  vidas--;
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
	     
	   	public void actualizarPorTeclado() { 
		   // movimiento desde mouse/touch
		   /*if(Gdx.input.isTouched()) {
			      Vector3 touchPos = new Vector3();
			      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			      camera.unproject(touchPos);
			      bucket.x = touchPos.x - 64 / 2;
			}*/
		   //movimiento desde teclado
		   if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) spr.setY(spr.getY() - velx * Gdx.graphics.getDeltaTime());
		   if(Gdx.input.isKeyPressed(Input.Keys.UP)) spr.setY(spr.getY() + velx * Gdx.graphics.getDeltaTime());
		   // que no se salga de los bordes izq y der
		   if(spr.getY() < 10) spr.setY(10);
		   if(spr.getY() > 365) spr.setY(365);
	   }
	    

	   	public void destruir() {
		    imagenRayo.dispose();
	   }
	
	   	public boolean estaHerido() {
	   		return herido;
	   	}
}
