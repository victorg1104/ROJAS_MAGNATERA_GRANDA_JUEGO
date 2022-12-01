package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.DirectorObjetos;
import com.mygdx.game.Fondo;
import com.mygdx.game.GameRasho;
import com.mygdx.game.MusicaFondo;

import Superclases.AutoProtagonista;

public class SeleccionPersonajeScreen implements Screen{
	final GameRasho game;
    private OrthographicCamera camera;
	private SpriteBatch batch;
	private Fondo fondo;
	private MusicaFondo musicaFondo;
	private int tipo;
	

	public SeleccionPersonajeScreen(GameRasho game, MusicaFondo musicaFondo) {
		this.game = game;
        this.batch = game.getBatch();
        
        fondo = Fondo.crearFondo("images/FondoSeleccion.png", game.getFont()); //Crea fondo personalizado
        
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		this.musicaFondo = musicaFondo;
	}

	public SeleccionPersonajeScreen(GameRasho game) {
		this.game = game;
        this.batch = game.getBatch();
        
        fondo = Fondo.crearFondo("images/FondoSeleccion.png", game.getFont()); //Crea fondo personalizado
        
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		musicaFondo = new MusicaFondo();
		musicaFondo.setArchivoMusica("sounds/cancionCars.mp3");
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0f, 1);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(fondo.getImagenFondo(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		fondo.getFuente().getData().setScale(2, 2);
		fondo.getFuente().draw(batch, "1", 75, 290);
		fondo.getFuente().draw(batch, "2", 75, 180);
		musicaFondo.iniciarReproduccion();

		batch.end();

		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
			tipo = 1;
			dispose(); //En caso de que se toque la pantalla con el mouse o se presione alguna tecla, se da inicio al juego
			game.setScreen(new GameScreen(game, musicaFondo, tipo));
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
			tipo = 2;
			dispose(); //En caso de que se toque la pantalla con el mouse o se presione alguna tecla, se da inicio al juego
			game.setScreen(new GameScreen(game, musicaFondo, tipo));
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
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

	@Override
	public void dispose() {
		fondo.destruir();
		
	}
}
