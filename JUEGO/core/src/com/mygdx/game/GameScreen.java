package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
	final GameRasho game;
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private Carro auto;
	private Obstaculos obs;
	private Texture fondo;

	   
	//boolean activo = true;

	public GameScreen(final GameRasho game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();

         fondo = new Texture(Gdx.files.internal("images/pista.jpg"));
	      // camera
	     camera = new OrthographicCamera();
	     camera.setToOrtho(false, 800, 480);
	     batch = new SpriteBatch();
	      // creacion del carro
	     auto = new Carro();      
	      // creacion de la lluvia
	     obs = new Obstaculos();
	}
	
	public GameScreen(final GameRasho game, Music musicaFondo) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
         
        fondo = new Texture(Gdx.files.internal("images/pista.jpg")); 
	      // camera
	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, 800, 480);
	    batch = new SpriteBatch();
	      // creacion del tarro
	    auto = new Carro();
	    
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
		font.draw(batch, "Gotas totales: " + auto.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + auto.getVidas(), 670, 475);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 475);
		
		if (!auto.estaHerido()) {
			// movimiento del tarro desde teclado
	        auto.actualizarPosicion();        
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
		obs.actualizarDibujoLluvia(batch);
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	  // continuar con sonido de lluvia
	  obs.continuar();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		obs.pausar();
		game.setScreen(new PausaScreen(game, this)); 
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
      auto.destruir();
      obs.destruir();
	}

}
