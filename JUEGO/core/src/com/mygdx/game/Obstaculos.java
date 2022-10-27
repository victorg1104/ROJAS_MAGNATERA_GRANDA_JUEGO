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

public class Obstaculos {
	private Array<Objeto> objetos;
    private long lastDropTime;
    private Sound dropSound;
    private Music musicaFondo;
	   
	@SuppressWarnings("unchecked")
	public Obstaculos() {
		musicaFondo = Gdx.audio.newMusic(Gdx.files.internal("sounds/cancionCars.mp3"));
		dropSound = Gdx.audio.newSound(Gdx.files.internal("sounds/cuchau.mp3"));
		
		objetos = new Array<Objeto>();
		crearObjeto();
	     // start the playback of the background music immediately
	    musicaFondo.setLooping(true);
	    musicaFondo.setVolume(0.05f);
	    musicaFondo.play();
	}
	
	@SuppressWarnings("unchecked")
	public Obstaculos(Music musicaFondo) {
		this.musicaFondo = musicaFondo;
		dropSound = Gdx.audio.newSound(Gdx.files.internal("sounds/cuchau.mp3"));
		
		objetos = new Array<Objeto>();
		crearObjeto();
	     // start the playback of the background music immediately
	    musicaFondo.setLooping(true);
	    musicaFondo.setVolume(0.05f);
	    musicaFondo.play();
	}

	private void crearObjeto() {
		  int posiciones [] = {20,73,146,219,292,365};
	      //Rectangle raindrop = new Rectangle();
		  Objeto obj;
	      //raindrop.x = 800;
	      //raindrop.y = posiciones[MathUtils.random(5)];
	      //raindrop.width = 70;
	      //raindrop.height = 40;
	      //rainDropsPos.add(raindrop);
	      // ver el tipo de gota
		  int aux = MathUtils.random(1,10);
		  
	      if (aux < 9 && aux > 4) {
	    	  obj = new Cono();
	    	  obj.crear(posiciones[MathUtils.random(5)], 1);
	    	  objetos.add(obj);           //cono   	  
	      }
	      else if(aux <= 4) {
	    	  obj = new AutoEnemigo();
	    	  obj.crear(posiciones[MathUtils.random(5)], 1);
	    	  objetos.add(obj);
	      }
	      else {
	    	 obj = new Copa();
	    	 obj.crear(posiciones[MathUtils.random(5)], 2);
	    	 objetos.add(obj);            //copa
	      }
	      lastDropTime = TimeUtils.nanoTime();
	   }
	
   public void crearLineas() {
	   int posiciones [] = {30, 83, 156, 229, 302};
	   
	   Objeto linea = new Linea();
	   Objeto linea2 = new Linea();
	   Objeto linea3 = new Linea();
	   Objeto linea4 = new Linea();
	   Objeto linea5 = new Linea();
	   
	   linea.crear(30, 0);
	   linea2.crear(83, 0);
	   linea3.crear(156, 0);
	   linea4.crear(229, 0);
	   linea5.crear(302, 0);
   }
	
   public boolean actualizarMovimiento(Movible tarro) { 
	   // generar gotas de lluvia 
	   if (TimeUtils.nanoTime() - lastDropTime > 200000000) crearObjeto();
	   //if (TimeUtils.nanoTime() - lastDropTime > 200000000) crearLineas();
	   
	   // revisar si las gotas cayeron al suelo o chocaron con el tarro
	   for (int i=0; i < objetos.size; i++ ) {
		  Objeto obj = objetos.get(i);
	      obj.actualizarMovimiento();
	      //cae al suelo y se elimina
	      if(obj.getPosX() + 70 < 0) 
	    	  objetos.removeIndex(i); 
	      
	      if(obj.getRectangle().overlaps(tarro.getArea())) { //la gota choca con el tarro
	    	if(obj.getTipo() == 1) { // gota dañina
	    	  if(!obj.accionar(tarro)) {
	    		  obj.sonido.dispose();
	    		  return false;
	    	  }
	    	  objetos.removeIndex(i);
	      	}else { // gota a recolectar
	      	  obj.accionar(tarro);
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
		  //Rectangle raindrop = rainDropsPos.get(i);
		  /*if(objetos.get(i).getTipo() == 1) // gota dañina
			 obj.dibujar(batch);
	         batch.draw(gotaMala, raindrop.x, raindrop.y, 70, 40); 
		  else
			 batch.draw(copa, raindrop.x, raindrop.y, 70, 40); */
	   }
   }
   public void destruir() {
      dropSound.dispose();
      musicaFondo.dispose();
   }
   public void pausar() {
	  musicaFondo.stop();
   }
   public void continuar() {
	  musicaFondo.play();
   }
   
}
