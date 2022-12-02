package Superclases;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.DirectorObjetos;
import com.mygdx.game.Hudson;
import com.mygdx.game.Rasho;

import Interfaces.Movible;
import Screens.GameOverScreen;

//Clase abstracta que define un Template para las subclases Hudson y Rasho
public abstract class AutoProtagonista{
	protected Sprite spr;
	protected Texture imagen;
	protected int grados;
	protected int vidas;
	protected int puntos;
	protected int tiempoHeridoMax;
	protected int tipo;
	protected boolean herido;
	protected boolean girar;
	protected boolean parpadear;
	protected float tiempoHerido;
	protected float opacidad;
	private Movible movible;
	
	public abstract void setVidas();
	public abstract void setSprite();
		
	public void crearCarro() { //Template Method
		//Funciones abstractas, dependientes de cada subclase
		setVidas();
		setSprite();
		//Funciones comunes, pertenecientes a todas las subclases
		setPuntos();
		setHerido();
		setGirar();
		setParpadear();
		setTiempoHeridoMax();
	}

	public void setTipo(int tipo){
		this.tipo = tipo;
	}
	
	public void setMovible() {
		if (tipo == 1) movible = (Rasho) this;
		else movible = (Hudson) this;
	}
	
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
   		// movimiento del auto desde teclado, utiliza una instancia de la interfaz movible
	     movible.actualizarPorTeclado();        
		// verificación de game over
	     if (!obs.actualizarMovimiento(this)) return false;
		
   		return true; //Se retorna true en caso de que el juego no se haya terminado
   	}
}
