package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import Interfaces.Movible;
import Superclases.AutoProtagonista;

//Clase Hudson, uno de los personajes seleccionables en el juego, el cual se mueve más lento, tiene mayor resistencia y una imagen personalizada
public class Hudson extends AutoProtagonista implements Movible{
		@Override
		public void setVidas() {
			vidas = 7;
		}

		@Override
		public void setSprite() {
			imagen = new Texture(Gdx.files.internal("images/hudson.png"));
			spr = new Sprite(imagen);
			spr.setCenter(64, 185);			
		}

		@Override
		public void actualizarPorTeclado() { //En caso de recibir señal desde teclado, mover abajo o arriba velocidad de 200 pixeles/s
			if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) spr.setY(spr.getY() - 200 * Gdx.graphics.getDeltaTime());
			if(Gdx.input.isKeyPressed(Input.Keys.UP)) spr.setY(spr.getY() + 200 * Gdx.graphics.getDeltaTime());
			// que no se salga de los bordes arriba y abajo
			if(spr.getY() < 10) spr.setY(10);
			if(spr.getY() > 365) spr.setY(365);
		}
}
