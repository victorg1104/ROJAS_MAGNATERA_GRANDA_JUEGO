package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
	final GameRasho game;
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private Movible auto;
	private Obstaculos obs;
	private Texture fondo;
	private int tipo;

	   
	//boolean activo = true;

	public GameScreen(final GameRasho game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();

         fondo = new Texture(Gdx.files.internal("images/pista.png"));
	      // camera
	     camera = new OrthographicCamera();
	     camera.setToOrtho(false, 800, 480);
	     batch = new SpriteBatch();
	     
	     tipo = MathUtils.random(1,2);
	     
	     if (tipo == 1) {
	    	 auto = new Rasho();
	    	 auto.crearCarro();
	     }
	     else {
	    	 auto = new Hudson();
	    	 auto.crearCarro();
	     }
	      // creacion de la lluvia
	     obs = new Obstaculos();
	}
	
	public GameScreen(final GameRasho game, Music musicaFondo) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
         
        fondo = new Texture(Gdx.files.internal("images/pista.png")); 
	      // camera
	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, 800, 480);
	    batch = new SpriteBatch();

	     if (tipo == 1) {
	    	 auto = new Rasho();
	    	 auto.crearCarro();
	     }
	     else {
	    	 auto = new Hudson();
	    	 auto.crearCarro();
	     }
	      // creacion de la lluvia
	    obs = new Obstaculos(musicaFondo);
	}

	@Override
	public void render(float delta) {
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
		//actualizar matrices de la cámara
		camera.update();
		//actualizar 
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//dibujar textos
		font.draw(batch, String.valueOf(auto.getPuntos()), 740, 440);
		font.draw(batch, String.valueOf(auto.getVidas()), 260, 450);
		font.draw(batch, String.valueOf(game.getHigherScore()), 740, 475);
		
		if (!auto.estaHerido()) {
			// movimiento del tarro desde teclado
	        auto.actualizarPorTeclado();        
			// caida de la lluvia 
	       if (!obs.actualizarMovimiento(auto)) {
	    	  //actualizar HigherScore
	    	  if (game.getHigherScore()<auto.getPuntos())
	    		  game.setHigherScore(auto.getPuntos());  
	    	  //ir a la ventana de finde juego y destruir la actual
	    	  game.setScreen(new GameOverScreen(game));
	    	  dispose();
	       }
		}
		
		auto.dibujar(batch);
		obs.actualizarDibujo(batch);
		
		batch.end();
	}

	@Override
	public void show() {
	  // continuar con sonido de lluvia
	  obs.continuar();
	}


	@Override
	public void pause() {
		obs.pausar();
		game.setScreen(new PausaScreen(game, this)); 
	}

	@Override
	public void dispose() {
      auto.destruir();
      obs.destruir();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

}
