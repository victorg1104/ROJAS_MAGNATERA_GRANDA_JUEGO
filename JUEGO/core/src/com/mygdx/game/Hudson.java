package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import Superclases.AutoProtagonista;

public class Hudson extends AutoProtagonista{		
		@Override
	   	public void crearCarroLento() {
			vidas = 7;
			velx = 200;
			puntos = 0;
			herido = false;
			tiempoHeridoMax = 50;
	   		imagen = new Texture(Gdx.files.internal("images/hudson.png"));
			spr = new Sprite(imagen);
			spr.setCenter(64, 185);
	   	}
	   

		@Override
		public void crearCarroRapido() {
			// TODO Auto-generated method stub
			
		}
}
