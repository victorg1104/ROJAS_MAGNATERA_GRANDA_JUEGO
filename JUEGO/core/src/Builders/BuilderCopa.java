package Builders;

import com.mygdx.game.Copa;

import Interfaces.BuilderObjetos;

//Builder de objetos de tipo Copa, recibe cada atributo por separado, crea y retorna un objeto de la clase Copa
public class BuilderCopa implements BuilderObjetos{
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
	
	public Copa getCopa() {
		return new Copa(tipo, posY, daño, premio);
	}

}
