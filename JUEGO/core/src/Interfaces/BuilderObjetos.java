package Interfaces;

//Interfaz builder para cada objeto creado, define las operaciones necesarias para el seteo de un objeto dañino o de premio
public interface BuilderObjetos {
	public void setTipo(int tipo);
	public void setPosicion(int posY);
	public void setDaño(int daño);
	public void setPremio(int premio);
}
