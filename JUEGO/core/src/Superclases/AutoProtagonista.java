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

//Clase abstracta que implementa la interfaz movible, es a su vez la clase que define un Template para las subclases Hudson y Rasho
public abstract class AutoProtagonista implements Movible{
	protected Sprite spr;
	protected Texture imagen;
	protected int grados;
	protected float opacidad;
	protected int vidas;
	protected int puntos;
	protected int velx;
	protected boolean herido;
	protected boolean girar;
	protected boolean parpadear;
	protected int tiempoHeridoMax;
	protected float tiempoHerido;
	
	public void crearCarro() { //Template Method
		//Funciones abstractas, dependientes de cada subclase
		setVidas();
		setVelx();
		setSprite();
		//Funciones comunes, pertenecientes a todas las subclases
		setPuntos();
		setHerido();
		setGirar();
		setParpadear();
		setTiempoHeridoMax();
	}
	/*public abstract void crearCarroRapido(); //Setea los atributos para un auto rápido
	public abstract void crearCarroLento(); //Setea los atributos para un auto lento*/
	public abstract void setVidas();
	public abstract void setVelx();
	public abstract void setSprite();
	
	public void setPuntos() {
		puntos = 0;
	}
	
	public void setHerido() {
		herido = false;
	}
	
	public void setGirar() {
		girar = false;
	}
	
	public void setParpadear() {
		parpadear = false;
	}
	
	public void setTiempoHeridoMax() {
		tiempoHeridoMax = 50;
	}
	
	public int getVidas() {
		return vidas;
	}

	public int getPuntos() {
		return puntos;
	}
	
	public Rectangle getArea() { //Retorna el rectángulo que utiliza el sprite, para poder detectar colisiones
		return spr.getBoundingRectangle();
	}
	
	public void sumarPuntos(int pp) {
		puntos+=pp;
	}
	
	//Función que setea los valores para animación de giro
	public void dañarGirar(int daño) {
		  vidas -= daño;
		  herido = true;
		  girar = true;
		  tiempoHerido = tiempoHeridoMax;
		  grados = 10;
	}
	
	//Función que setea los valores para animación de parpadeo
	public void dañarParpadear(int daño) {
		vidas -= daño;
		herido = true;
		parpadear = true;
		tiempoHerido = tiempoHeridoMax;
		opacidad = 1;
	}
	
	public void dibujar(SpriteBatch batch) {
		 if (!herido) {
			//En caso de que algún valor quede alterado luego de alguna animación, setearlo a los valores default
		   if (grados != 360 || spr.getColor().a < 1) { 
			   spr.setRotation(0); 
			   spr.setAlpha(1);
		   }
		   spr.draw(batch);
		 }
		 else if(herido && girar) {
		   animacionGiro(batch); //Girar en caso de chocar con el cono
		 }
		 else if(herido && parpadear) {
		   animacionParpadeo(batch); //Parpadear en caso de chocar con otro auto
		 }
	}
	
	//Animación de parpadeo del auto principal
	public void animacionParpadeo(SpriteBatch batch) {
		if(spr.getColor().a > 0)
			   spr.setAlpha(opacidad -= 0.8);
		   else spr.setAlpha(opacidad += 0.05);
		   spr.draw(batch);
		   tiempoHerido --;
		   if (tiempoHerido <= 0) {
			   herido = false;
			   girar = false;
			   parpadear = false;
		   }
	}
	
	//Animación de giro del auto principal
	public void animacionGiro(SpriteBatch batch) {
		spr.setRotation(grados);
		spr.draw(batch);
		grados *= 2;
		tiempoHerido--;
		if (tiempoHerido <= 0) {
			   herido = false;
			   girar = false;
			   parpadear = false;
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
			// movimiento del auto desde teclado
	        actualizarPorTeclado();        
			// verificación de choque entre objetos
	        if (!obs.actualizarMovimiento(this)) return false;
		}
   		return true; //Se retorna true en caso de que el juego no se haya terminado
   	}
	     
	@Override
	public void actualizarPorTeclado() { 
		   //movimiento desde teclado
		   if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) spr.setY(spr.getY() - velx * Gdx.graphics.getDeltaTime());
		   if(Gdx.input.isKeyPressed(Input.Keys.UP)) spr.setY(spr.getY() + velx * Gdx.graphics.getDeltaTime());
		   // que no se salga de los bordes arriba y abajo
		   if(spr.getY() < 10) spr.setY(10);
		   if(spr.getY() > 365) spr.setY(365);
	}

}
