package com.mygdx.game;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Obstaculos {
	private Array<Rectangle> rainDropsPos;
	private Array<Integer> rainDropsType;
    private long lastDropTime;
    private Texture copa;
    private Texture gotaMala;
    private Sound dropSound;
    private Music musicaFondo;
	   
	@SuppressWarnings("unchecked")
	public Obstaculos() {
		musicaFondo = Gdx.audio.newMusic(Gdx.files.internal("sounds/cancionCars.mp3"));
		dropSound = Gdx.audio.newSound(Gdx.files.internal("sounds/cuchau.mp3"));
		this.copa = new Texture(Gdx.files.internal("images/pistonCup.png"));
		this.gotaMala = new Texture(Gdx.files.internal("images/francesco.png"));
		
		rainDropsPos = new Array<Rectangle>();
		rainDropsType = new Array<Integer>();
		crearGotaDeLluvia();
	     // start the playback of the background music immediately
	    musicaFondo.setLooping(true);
	    musicaFondo.setVolume(0.05f);
	    musicaFondo.play();
	}
	
	@SuppressWarnings("unchecked")
	public Obstaculos(Music musicaFondo) {
		this.musicaFondo = musicaFondo;
		dropSound = Gdx.audio.newSound(Gdx.files.internal("sounds/cuchau.mp3"));
		this.copa = new Texture(Gdx.files.internal("images/pistonCup.png"));
		this.gotaMala = new Texture(Gdx.files.internal("images/francesco.png"));
		
		rainDropsPos = new Array<Rectangle>();
		rainDropsType = new Array<Integer>();
		crearGotaDeLluvia();
	     // start the playback of the background music immediately
	    musicaFondo.setLooping(true);
	    musicaFondo.setVolume(0.05f);
	    musicaFondo.play();
	}

	private void crearGotaDeLluvia() {
		  int posiciones [] = {75,150,225,300};
	      Rectangle raindrop = new Rectangle();
	      raindrop.x = 800;
	      raindrop.y = posiciones[MathUtils.random(3)];
	      raindrop.width = 70;
	      raindrop.height = 40;
	      rainDropsPos.add(raindrop);
	      // ver el tipo de gota
	      if (MathUtils.random(1,10) < 9)	    	  
	         rainDropsType.add(1);           //mala
	      else 
	    	 rainDropsType.add(2);
	      lastDropTime = TimeUtils.nanoTime();  //buena
	   }
	
   public boolean actualizarMovimiento(Carro tarro) { 
	   // generar gotas de lluvia 
	   if(TimeUtils.nanoTime() - lastDropTime > 300000000) crearGotaDeLluvia();
	  
	   
	   // revisar si las gotas cayeron al suelo o chocaron con el tarro
	   for (int i=0; i < rainDropsPos.size; i++ ) {
		  Rectangle raindrop = rainDropsPos.get(i);
	      raindrop.x -= 300 * Gdx.graphics.getDeltaTime();
	      //cae al suelo y se elimina
	      if(raindrop.x + 70 < 0) {
	    	  rainDropsPos.removeIndex(i); 
	    	  rainDropsType.removeIndex(i);
	      }
	      if(raindrop.overlaps(tarro.getArea())) { //la gota choca con el tarro
	    	if(rainDropsType.get(i)==1) { // gota dañina
	    	  tarro.dañar();
	    	  if (tarro.getVidas()<=0)
	    		 return false; // si se queda sin vidas retorna falso /game over
	    	  rainDropsPos.removeIndex(i);
	          rainDropsType.removeIndex(i);
	      	}else { // gota a recolectar
	    	  tarro.sumarPuntos(10);
	          dropSound.play();
	          rainDropsPos.removeIndex(i);
	          rainDropsType.removeIndex(i);
	      	}
	      }
	   } 
	  return true; 
   }
   
   public void actualizarDibujoLluvia(SpriteBatch batch) { 
	   
	  for (int i=0; i < rainDropsPos.size; i++ ) {
		  Rectangle raindrop = rainDropsPos.get(i);
		  if(rainDropsType.get(i)==1) // gota dañina
	         batch.draw(gotaMala, raindrop.x, raindrop.y, 70, 40); 
		  else
			 batch.draw(copa, raindrop.x, raindrop.y, 70, 40); 
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
