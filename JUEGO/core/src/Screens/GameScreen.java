package Screens;

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
import com.mygdx.game.GameRasho;
import com.mygdx.game.Hudson;
import com.mygdx.game.MusicaFondo;
import com.mygdx.game.DirectorObjetos;
import com.mygdx.game.Fondo;
import com.mygdx.game.Rasho;

import Superclases.AutoProtagonista;

public class GameScreen implements Screen {
	final GameRasho game;
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private AutoProtagonista auto;
	private DirectorObjetos obs;
	private Fondo fondo;

	//Constructor para los atributos de clase, recibe por parámetro la instancia GameRasho y musicaFondo, en caso que el juego comience desde la pantalla de inicio 
	public GameScreen(final GameRasho game, MusicaFondo musicaFondo, int tipo) {
		this.game = game;
        this.batch = game.getBatch();

        fondo = Fondo.crearFondo("images/pista.png", game.getFont());

	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, 800, 480);
	    batch = new SpriteBatch();
	    
	  //Creación de personajes principales
	    if (tipo == 1) {
	    	auto = new Rasho();
	    	auto.crearCarro();
	    	auto.setTipo(tipo);
	    	auto.setMovible();
	    }
	    else {
	    	auto = new Hudson();
	    	auto.crearCarro();
	    	auto.setTipo(tipo);
	    	auto.setMovible();
	    }
	 // Creación del director de creación de objetos
	    obs = new DirectorObjetos(musicaFondo);
	    obs.crearObjetos();
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
		batch.draw(fondo.getImagenFondo(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//dibujar textos
		fondo.getFuente().draw(batch, String.valueOf(auto.getPuntos()), 740, 440);
		fondo.getFuente().draw(batch, String.valueOf(auto.getVidas()), 260, 450);
		fondo.getFuente().draw(batch, String.valueOf(game.getHigherScore()), 740, 475);
		
		if (!auto.estaHerido()) {
			// movimiento de objetos
	       if (!auto.actualizarMovimiento(obs)) {
	    	  //actualizar HigherScore en caso de Game Over
	    	  if (game.getHigherScore()<auto.getPuntos())
	    		  game.setHigherScore(auto.getPuntos());  
	    	  //ir a la ventana de Game Over y destruir la actual
	    	  dispose();
	    	  game.setScreen(new GameOverScreen(game));
	       }
		}
		
		auto.dibujar(batch);
		obs.actualizarDibujo(batch);
		
		batch.end();
	}

	@Override
	public void show() {
	  // continuar con música background
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
      fondo.destruir();
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