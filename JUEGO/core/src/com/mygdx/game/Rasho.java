package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import Superclases.AutoProtagonista;

//Clase Rasho, uno de los personajes seleccionables para jugar, este se mueve más rápido, tiene menor resistencia y una imagen personalizada
public class Rasho extends AutoProtagonista{		
		@Override
		public void setVidas() {
			vidas = 5;
		}


		@Override
		public void setVelx() {
			velx = 400;
		}


		@Override
		public void setSprite() {
			imagen = new Texture(Gdx.files.internal("images/rayo.png"));
			spr = new Sprite(imagen);
			spr.setCenter(64, 185);		
		}
}
