package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public interface Movible{
	public void actualizarPorTeclado();
	public void crearCarro();
	public int getVidas();	
	public int getPuntos();
	public Rectangle getArea();
	public void sumarPuntos(int pp);
	public void dañar();
	public void dibujar(SpriteBatch batch);  
	public void destruir();
	public boolean estaHerido();
}
