package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Fondo;
import com.mygdx.game.GameRasho;

public class GameOverScreen implements Screen {
	private final GameRasho game;
	private SpriteBatch batch;	   
	private OrthographicCamera camera;
	private Fondo fondo;

	public GameOverScreen(final GameRasho game) {
		this.game = game;
        this.batch = game.getBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		fondo = Fondo.crearFondo("images/imagenGameOver.png", game.getFont());
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(fondo.getImagenFondo(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		fondo.getFuente().draw(batch, "Presiona enter para jugar de nuevo", 140, 50);
		
		
		//musicaFondo.play();
		
		batch.end();

		if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			dispose();
			game.setScreen(new GameScreen(game));
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
