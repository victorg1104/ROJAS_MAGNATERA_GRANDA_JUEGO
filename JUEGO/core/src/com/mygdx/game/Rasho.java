package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import Interfaces.Movible;
import Superclases.AutoProtagonista;

//Clase Rasho, uno de los personajes seleccionables para jugar, este se mueve más rápido, tiene menor resistencia y una imagen personalizada
public class Rasho extends AutoProtagonista implements Movible{		
		@Override
		public void setVidas() {
			vidas = 5;
		}

		@Override
		public void setSprite() {
			imagen = new Texture(Gdx.files.internal("images/rayo.png"));
			spr = new Sprite(imagen);
			spr.setCenter(64, 185);		
		}

		@Override
		public void actualizarPorTeclado() { //En caso de recibir señal desde teclado, mover abajo o arriba velocidad de 400 pixeles/s
			if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) spr.setY(spr.getY() - 400 * Gdx.graphics.getDeltaTime());
			if(Gdx.input.isKeyPressed(Input.Keys.UP)) spr.setY(spr.getY() + 400 * Gdx.graphics.getDeltaTime());
			// que no se salga de los bordes arriba y abajo
			if(spr.getY() < 10) spr.setY(10);
			if(spr.getY() > 365) spr.setY(365);
		}
}
