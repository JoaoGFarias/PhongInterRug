package algoritmos;

import data.Ponto;

public class ZBuffer {
	private float[][] zBuffer;

	public ZBuffer(int resX, int resY) {
		this.zBuffer = new float[resX][resY];
		for (int i = 0; i < resX; i++) {
			for (int j = 0; j < resY; j++) {
				this.zBuffer[i][j] = Float.POSITIVE_INFINITY;
			}
		}
		
	}
	
	public Ponto verificaZBuffer(Ponto p, int x, int y){
		Ponto retorno = null;
		if(x >= this.zBuffer.length || y >= this.zBuffer[0].length || x < 0 || y < 0){
			return null;
		}
		if(p.getZ() < this.zBuffer[x][y]){
			this.zBuffer[x][y] = p.getZ();
			retorno = p;
		}		
		return retorno;
	}
	
	public void clear(){
		for (int i = 0; i < this.zBuffer.length; i++) {
			for (int j = 0; j < this.zBuffer[0].length; j++) {
				this.zBuffer[i][j] = Float.POSITIVE_INFINITY;
			}
		}
	}
}
