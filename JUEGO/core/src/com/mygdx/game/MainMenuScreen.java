package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


public class MainMenuScreen implements Screen {

	final GameRasho game;
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;
	private Texture fondo;
	private Music musicaFondo;

	public MainMenuScreen(final GameRasho game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        fondo = new Texture(Gdx.files.internal("images/imagenMenu.png"));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		musicaFondo = Gdx.audio.newMusic(Gdx.files.internal("sounds/cancionCars.mp3"));
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0f, 1);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		font.getData().setScale(2, 2);
		font.draw(batch, "Presiona cualquier tecla para comenzar!", 140, 50);
		
		musicaFondo.setLooping(true);
		musicaFondo.setVolume(0.05f);
		musicaFondo.play();

		batch.end();

		if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
			game.setScreen(new GameScreen(game, musicaFondo));
			dispose();
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

}
