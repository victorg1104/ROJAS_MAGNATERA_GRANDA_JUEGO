package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import Builders.BuilderAutoEnemigo;
import Builders.BuilderCono;
import Builders.BuilderCopa;
import Interfaces.BuilderObjetos;
import Superclases.AutoProtagonista;
import Superclases.Objeto;

/*Clase directora, encargada de generar los distintos objetos que se presenten por pantalla*/

public class DirectorObjetos {
	private Array<Objeto> objetos; //Array que guarda los objetos creados
    private long lastDropTime; // Última vez que se generó un objeto
    private MusicaFondo musicaFondo; // Instancia de MusicaFondo, controla las operaciones del background musical
		
	//Constructor para los atributos de la clase recibiendo por parámetro la música de fondo a reproducir
	public DirectorObjetos(MusicaFondo musicaFondo) {
		this.musicaFondo = musicaFondo;

		objetos = new Array<Objeto>();
		//crearObjeto();
	     // start the playback of the background music immediately
	    musicaFondo.iniciarReproduccion();
	}

	//Función que crea objetos en posiciones aleatorias
	public void crearObjetos() {
		  int posiciones [] = {30,83,156,229,302,375}; /*Arreglo de posiciones posibles de spawn de objetos, 
		  												para dar mayor orden en pantalla*/
		  Objeto obj;

		  int aux = MathUtils.random(1,10); //Variable auxiliar para definir qué objeto generar
		  
		  //Probabilidades de generación de cada objeto según el valor obtenido por la variable auxiliar
	      if (aux < 9 && aux > 4) {
	    	  BuilderCono builder = new BuilderCono();
	    	  crearCono(builder, posiciones); //Se crea objeto respectivo
	    	  obj = builder.getCono();
	    	  objetos.add(obj); //Se añade al arreglo de objetos
	      }
	      else if(aux <= 4) {
	    	  BuilderAutoEnemigo builder = new BuilderAutoEnemigo();
	    	  crearAutoMalo(builder, posiciones); //Se crea objeto respectivo
	    	  obj = builder.getAutoEnemigo();
	    	  objetos.add(obj); //Se añade al arreglo de objetos
	      }
	      else {
	    	  BuilderCopa builder = new BuilderCopa();
	    	  crearCopa(builder, posiciones); //Se crea objeto respectivo
	    	  obj = builder.getCopa();
	    	  objetos.add(obj); //Se añade al arreglo de objetos
	      }
	      lastDropTime = TimeUtils.nanoTime(); //Se guarda instante en el cual se generó el último objeto para evitar sobrecarga en pantalla
	   }
	
	//Función que crea y setea un objeto de tipo Cono
	public void crearCono(BuilderObjetos builder, int [] posiciones) {
	  builder.setTipo(1);
	  builder.setPosicion(posiciones[MathUtils.random(5)]);
	  builder.setDaño(1);
	  builder.setPremio(0);
	}
	
	//Función que crea y setea un objeto de tipo AutoEnemigo
	public void crearAutoMalo(BuilderObjetos builder, int [] posiciones) {
	  builder.setTipo(2);
	  builder.setPosicion(posiciones[MathUtils.random(5)]);
	  builder.setDaño(2);
	  builder.setPremio(0);
	}
	
	//Función que crea y setea un objeto de tipo Copa
	public void crearCopa(BuilderObjetos builder, int [] posiciones) {
      builder.setTipo(3);
      builder.setPosicion(posiciones[MathUtils.random(5)]);
      builder.setPremio(10);
      builder.setDaño(0);
	}
   
	//Función que se encarga de actualizar el movimiento de cada objeto en pantalla
   public boolean actualizarMovimiento(AutoProtagonista carro) { 
	   // generar gotas de lluvia 
	   if (TimeUtils.nanoTime() - lastDropTime > 200000000) crearObjetos(); /*En caso de que hayan pasado 200000000 nanosegundos desde la última creación de algún objet
	   																		, crear nuevo objeto*/
	   for (int i=0; i < objetos.size; i++ ) {
		  Objeto obj = objetos.get(i);	//Se recibe cada objeto y se actualiza su movimiento por separado
	      obj.actualizarMovimiento();
	      //cae al suelo y se elimina
	      if(obj.getPosX() + 70 < 0)
	    	  objetos.removeIndex(i); //En caso de que salgan del límite de la pantalla, eliminar objeto
	      
	      if(obj.getRectangle().overlaps(carro.getArea())) { //Si el objeto choca con el auto
	    	obj.accionar(carro);
	    	objetos.removeIndex(i);
	    	if (carro.getVidas() <= 0) {
	    		obj.destruirObjeto();
	    		return false;
	    	}
	      }
	   }
	  return true; //Se retorna true para seguir con la ejecución del juego
   }
   
   //Función que dibuja cada objeto del array por pantalla
   public void actualizarDibujo(SpriteBatch batch) {	   
	  for (int i=0; i < objetos.size; i++ ) {
		  Objeto obj = objetos.get(i);
		  obj.dibujar(batch);

	   }
   }
   public void destruir() {
      musicaFondo.destruir(); //Finalizar reproducción de la música de fondo
   }
   public void pausar() {
	  musicaFondo.stop(); //Pausar la reproducción de la música de fondo
   }
   public void continuar() {
	  musicaFondo.iniciarReproduccion(); //Retomar reproducción de la música de fondo
   }
   
}
