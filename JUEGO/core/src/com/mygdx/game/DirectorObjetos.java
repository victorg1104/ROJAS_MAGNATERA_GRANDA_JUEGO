package com.mygdx.game;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import Superclases.AutoProtagonista;
import Superclases.Objeto;

public class DirectorObjetos {
	private Array<Objeto> objetos;
    private long lastDropTime;
    private MusicaFondo musicaFondo;
	   
	public DirectorObjetos() {
		musicaFondo = new MusicaFondo();
		
		musicaFondo.setArchivoMusica("sounds/cancionCars.mp3");
		
		objetos = new Array<Objeto>();
		crearObjeto();
	     // start the playback of the background music immediately
	    musicaFondo.iniciarReproduccion();
	}
	
	public DirectorObjetos(MusicaFondo musicaFondo) {
		this.musicaFondo = musicaFondo;

		objetos = new Array<Objeto>();
		crearObjeto();
	     // start the playback of the background music immediately
	    musicaFondo.iniciarReproduccion();
	}

	private void crearObjeto() {
		  int posiciones [] = {20,73,146,219,292,365};

		  Objeto obj;

		  int aux = MathUtils.random(1,10);
		  
	      if (aux < 9 && aux > 4) {
	    	  obj = crearCono(posiciones);
	    	  objetos.add(obj);
	      }
	      else if(aux <= 4) {
	    	  obj = crearAutoMalo(posiciones);
	    	  objetos.add(obj);
	      }
	      else {
	    	  obj = crearCopa(posiciones);
	    	  objetos.add(obj);
	      }
	      lastDropTime = TimeUtils.nanoTime();
	   }
	
	public Objeto crearCono(int [] posiciones) {
	  Objeto obj = new Cono();
  	  obj.setTipo(1);
  	  obj.setPosicion(posiciones[MathUtils.random(5)]);
  	  obj.setAssets();
  	  obj.setDaño(1);

  	  return obj;
	}
	
	public Objeto crearAutoMalo(int [] posiciones) {
	  Objeto obj = new AutoEnemigo();
  	  obj.setTipo(2);
  	  obj.setPosicion(posiciones[MathUtils.random(5)]);
  	  obj.setAssets();
  	  obj.setDaño(2);
  
  	  return obj;
	}
	
	public Objeto crearCopa(int [] posiciones) {
	 Objeto obj = new Copa();
   	 obj.setTipo(3);
   	 obj.setPosicion(posiciones[MathUtils.random(5)]);
   	 obj.setAssets();
   	 obj.setPremio(10);

   	 return obj;
	}
	
   public boolean actualizarMovimiento(AutoProtagonista carro) { 
	   // generar gotas de lluvia 
	   if (TimeUtils.nanoTime() - lastDropTime > 200000000) crearObjeto();

	   for (int i=0; i < objetos.size; i++ ) {
		  Objeto obj = objetos.get(i);
	      obj.actualizarMovimiento();
	      //cae al suelo y se elimina
	      if(obj.getPosX() + 70 < 0) 
	    	  objetos.removeIndex(i); 
	      
	      if(obj.getRectangle().overlaps(carro.getArea())) { //la gota choca con el tarro
	    	if(obj.getTipo() == 1 || obj.getTipo() == 2) { // gota dañina
	    	  if(!obj.accionar(carro)) {
	    		  obj.destruirObjeto();
	    		  return false;
	    	  }
	    	  objetos.removeIndex(i);
	      	}else { // gota a recolectar
	      	  obj.accionar(carro);
	          objetos.removeIndex(i);
	      	}
	      }
	   }
	  //System.out.println(objetos.size);
	  return true; 
   }
   
   public void actualizarDibujo(SpriteBatch batch) {
	   
	  for (int i=0; i < objetos.size; i++ ) {
		  Objeto obj = objetos.get(i);
		  obj.dibujar(batch);

	   }
   }
   public void destruir() {
      musicaFondo.destruir();
   }
   public void pausar() {
	  musicaFondo.stop();
   }
   public void continuar() {
	  musicaFondo.iniciarReproduccion();
   }
   
}
