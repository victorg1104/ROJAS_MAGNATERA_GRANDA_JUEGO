package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Fondo;
import com.mygdx.game.GameRasho;
import com.mygdx.game.MusicaFondo;

//Clase MainMenu, implementa la interfaz Screen y corresponde a la pantalla que se muestra al iniciar la ejecución del juego
public class MainMenuScreen implements Screen {

	final GameRasho game;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Fondo fondo;
	private MusicaFondo musicaFondo;

	//Constructor de atributos de la clase, reciber por parámetros la instancia de GameRasho
	public MainMenuScreen(final GameRasho game) {
		this.game = game;
        this.batch = game.getBatch();
        
        fondo = Fondo.crearFondo("images/imagenMenu.png", game.getFont()); //Crea fondo personalizado
        
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		musicaFondo = new MusicaFondo();
		
		musicaFondo.setArchivoMusica("sounds/cancionCars.mp3"); //Asigna música de background
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0f, 1);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(fondo.getImagenFondo(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		fondo.getFuente().getData().setScale(2, 2);
		fondo.getFuente().draw(batch, "Presiona cualquier tecla para comenzar!", 140, 50);
		
		musicaFondo.iniciarReproduccion();

		batch.end();

		if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
			dispose(); //En caso de que se toque la pantalla con el mouse o se presione alguna tecla, se da inicio al juego
			game.setScreen(new SeleccionPersonajeScreen(game, musicaFondo));
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
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