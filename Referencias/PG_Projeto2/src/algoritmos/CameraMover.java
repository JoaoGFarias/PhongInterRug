package algoritmos;

import data.Camera;
import data.Ponto;
import data.Vetor;

public class CameraMover {
	
	private static int fator = 5;

	public static void moveUp(Camera camera){
		Ponto c = camera.getPosicao();
		Vetor v = camera.getV();
		
		
		c.setX(c.getX() + v.getX()*fator);
		c.setY(c.getY() + v.getY()*fator);
		c.setZ(c.getZ() + v.getZ()*fator);
	}
	
	public static void moveDown(Camera camera){
		Ponto c = camera.getPosicao();
		Vetor v = camera.getV();
		
		
		c.setX(c.getX() - v.getX()*fator);
		c.setY(c.getY() - v.getY()*fator);
		c.setZ(c.getZ() - v.getZ()*fator);
	}
	
	public static void moveRight(Camera camera){
		Ponto c = camera.getPosicao();
		Vetor u = camera.getU();
		
		
		c.setX(c.getX() + u.getX()*fator);
		c.setY(c.getY() + u.getY()*fator);
		c.setZ(c.getZ() + u.getZ()*fator);
	}
	
	public static void moveLeft(Camera camera){
		Ponto c = camera.getPosicao();
		Vetor u = camera.getU();
		
		
		c.setX(c.getX() - u.getX()*fator);
		c.setY(c.getY() - u.getY()*fator);
		c.setZ(c.getZ() - u.getZ()*fator);
	}
	
	public static void moveCloser(Camera camera){
		Ponto c = camera.getPosicao();
		Vetor n = camera.getN();
		
		
		c.setX(c.getX() + n.getX()*fator);
		c.setY(c.getY() + n.getY()*fator);
		c.setZ(c.getZ() + n.getZ()*fator);
	}
	
	public static void moveFurther(Camera camera){
		Ponto c = camera.getPosicao();
		Vetor n = camera.getN();
		
		
		c.setX(c.getX() - n.getX()*fator);
		c.setY(c.getY() - n.getY()*fator);
		c.setZ(c.getZ() - n.getZ()*fator);
	}
	
	public static void turnRight(Camera camera){
		Vetor n = camera.getN();
		Vetor u = camera.getU();
		Vetor v = camera.getV();
		
		float xAux = n.getX(), zAux = n.getZ();
		
		n.setX(xAux*(float)Math.cos(Math.toRadians(15)) - zAux*(float)Math.sin(Math.toRadians(15)));
		n.setZ(xAux*(float)Math.sin(Math.toRadians(15)) + zAux*(float)Math.cos(Math.toRadians(15)));
		n.normalizar();
		
		
		xAux = u.getX();
		zAux = u.getZ();
		u.setX(xAux*(float)Math.cos(Math.toRadians(15)) - zAux*(float)Math.sin(Math.toRadians(15)));
		u.setZ(xAux*(float)Math.sin(Math.toRadians(15)) + zAux*(float)Math.cos(Math.toRadians(15)));
		u.normalizar();
		
		xAux = v.getX();
		zAux = v.getZ();
		v.setX(xAux*(float)Math.cos(Math.toRadians(15)) - zAux*(float)Math.sin(Math.toRadians(15)));
		v.setZ(xAux*(float)Math.sin(Math.toRadians(15)) + zAux*(float)Math.cos(Math.toRadians(15)));
		v.normalizar();
		
		Ponto c = camera.getPosicao();
		xAux = c.getX();
		zAux = c.getZ();
		c.setX(xAux*(float)Math.cos(Math.toRadians(15)) - zAux*(float)Math.sin(Math.toRadians(15)));
		c.setZ(xAux*(float)Math.sin(Math.toRadians(15)) + zAux*(float)Math.cos(Math.toRadians(15)));
		                                                      
	}     
	
	public static void turnLeft(Camera camera){
		Vetor n = camera.getN();
		Vetor u = camera.getU();
		Vetor v = camera.getV();
		
		float xAux = n.getX(), zAux = n.getZ();
		
		n.setX(xAux*(float)Math.cos(Math.toRadians(15)) + zAux*(float)Math.sin(Math.toRadians(15)));
		n.setZ(xAux*(float)-Math.sin(Math.toRadians(15)) + zAux*(float)Math.cos(Math.toRadians(15)));
		n.normalizar();
		
		
		xAux = u.getX();
		zAux = u.getZ();
		u.setX(xAux*(float)Math.cos(Math.toRadians(15)) + zAux*(float)Math.sin(Math.toRadians(15)));
		u.setZ(xAux*(float)-Math.sin(Math.toRadians(15)) + zAux*(float)Math.cos(Math.toRadians(15)));
		u.normalizar();
		
		xAux = v.getX();
		zAux = v.getZ();
		v.setX(xAux*(float)Math.cos(Math.toRadians(15)) + zAux*(float)Math.sin(Math.toRadians(15)));
		v.setZ(xAux*(float)-Math.sin(Math.toRadians(15)) + zAux*(float)Math.cos(Math.toRadians(15)));
		v.normalizar();
		
		Ponto c = camera.getPosicao();
		xAux = c.getX();
		zAux = c.getZ();
		c.setX(xAux*(float)Math.cos(Math.toRadians(15)) + zAux*(float)Math.sin(Math.toRadians(15)));
		c.setZ(xAux*(float)-Math.sin(Math.toRadians(15)) + zAux*(float)Math.cos(Math.toRadians(15)));
		                                                      
	}     
	
	
	public static void turnUp(Camera camera){
		Vetor n = camera.getN();
		Vetor u = camera.getU();
		Vetor v = camera.getV();
		
		//Matriz de rotacao * vetor		
		
		float yAux = n.getY(), zAux = n.getZ();
		
		n.setY(yAux*(float)Math.cos(Math.toRadians(15)) - zAux*(float)Math.sin(Math.toRadians(15)));
		n.setZ(yAux*(float)Math.sin(Math.toRadians(15)) + zAux*(float)Math.cos(Math.toRadians(15)));
		n.normalizar();
		
		
		yAux = u.getY();
		zAux = u.getZ();
		u.setY(yAux*(float)Math.cos(Math.toRadians(15)) - zAux*(float)Math.sin(Math.toRadians(15)));
		u.setZ(yAux*(float)Math.sin(Math.toRadians(15)) + zAux*(float)Math.cos(Math.toRadians(15)));
		u.normalizar();
		
		yAux = v.getY();
		zAux = v.getZ();
		v.setY(yAux*(float)Math.cos(Math.toRadians(15)) - zAux*(float)Math.sin(Math.toRadians(15)));
		v.setZ(yAux*(float)Math.sin(Math.toRadians(15)) + zAux*(float)Math.cos(Math.toRadians(15)));
		v.normalizar();
		
		Ponto c = camera.getPosicao();
		yAux = c.getY();
		zAux = c.getZ();
		c.setY(yAux*(float)Math.cos(Math.toRadians(15)) - zAux*(float)Math.sin(Math.toRadians(15)));
		c.setZ(yAux*(float)Math.sin(Math.toRadians(15)) + zAux*(float)Math.cos(Math.toRadians(15)));
	}
	
	public static void turnDown(Camera camera){
		Vetor n = camera.getN();
		Vetor u = camera.getU();
		Vetor v = camera.getV();
		
		//Matriz de rotacao * vetor		
		
		float yAux = n.getY(), zAux = n.getZ();
		
		n.setY(yAux*(float)Math.cos(Math.toRadians(15)) + zAux*(float)Math.sin(Math.toRadians(15)));
		n.setZ(yAux*(float)-Math.sin(Math.toRadians(15)) + zAux*(float)Math.cos(Math.toRadians(15)));
		n.normalizar();
		
		
		yAux = u.getY();
		zAux = u.getZ();
		u.setY(yAux*(float)Math.cos(Math.toRadians(15)) + zAux*(float)Math.sin(Math.toRadians(15)));
		u.setZ(yAux*(float)-Math.sin(Math.toRadians(15)) + zAux*(float)Math.cos(Math.toRadians(15)));
		u.normalizar();
		
		yAux = v.getY();
		zAux = v.getZ();
		v.setY(yAux*(float)Math.cos(Math.toRadians(15)) + zAux*(float)Math.sin(Math.toRadians(15)));
		v.setZ(yAux*(float)-Math.sin(Math.toRadians(15)) + zAux*(float)Math.cos(Math.toRadians(15)));
		v.normalizar();
		
		Ponto c = camera.getPosicao();
		yAux = c.getY();
		zAux = c.getZ();
		c.setY(yAux*(float)Math.cos(Math.toRadians(15)) + zAux*(float)Math.sin(Math.toRadians(15)));
		c.setZ(yAux*(float)-Math.sin(Math.toRadians(15)) + zAux*(float)Math.cos(Math.toRadians(15)));
	}
	
}
