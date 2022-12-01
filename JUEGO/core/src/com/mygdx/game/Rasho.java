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

public class Rasho extends AutoProtagonista{		
	   @Override
	   	public void crearCarroRapido() {
		   	vidas = 5;
			velx = 400;
			puntos = 0;
			herido = false;
			tiempoHeridoMax = 50;
	   		imagen = new Texture(Gdx.files.internal("images/rayo.png"));
			spr = new Sprite(imagen);
			spr.setCenter(64, 185);
	   	}


		@Override
		public void crearCarroLento() {
			// TODO Auto-generated method stub
			
		}
}
