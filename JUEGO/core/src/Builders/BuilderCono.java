package Builders;

import com.mygdx.game.Cono;

import Interfaces.BuilderObjetos;

public class BuilderCono implements BuilderObjetos{
	private int tipo;
	private int posY;
	private int daño;
	private int premio;
	
	@Override
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@Override
	public void setPosicion(int posY) {
		this.posY = posY;
	}

	@Override
	public void setDaño(int daño) {
		this.daño = daño;
	}

	@Override
	public void setPremio(int premio) {
		this.premio = premio;
	}
	
	public Cono getCono() {
		return new Cono(tipo, posY, daño, premio);
	}

}
