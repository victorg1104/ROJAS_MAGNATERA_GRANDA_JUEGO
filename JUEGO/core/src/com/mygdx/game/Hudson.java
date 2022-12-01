package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import Superclases.AutoProtagonista;

//Clase Hudson, uno de los personajes seleccionables en el juego, el cual se mueve más lento, tiene mayor resistencia y una imagen personalizada
public class Hudson extends AutoProtagonista{
		@Override
		public void setVidas() {
			vidas = 7;
		}


		@Override
		public void setVelx() {
			velx = 200;	
		}


		@Override
		public void setSprite() {
			imagen = new Texture(Gdx.files.internal("images/hudson.png"));
			spr = new Sprite(imagen);
			spr.setCenter(64, 185);			
		}
}
