package fp.lab9;

/**
 *
 * @author jordy
 */
public class Soldado {
    
    private String nombre;
    
	private int fila;
	private int columna;
	private int vida;
        private boolean estado;
	public Soldado(String nom, int puntosvida, int aleatorioFila, int aleatorioColumn) {
		nombre=nom;
		vida=puntosvida;
		fila=aleatorioFila;
		columna=aleatorioColumn;
	}
        
	public Soldado() {
		nombre="anonimo";
	}
	
	public void setNombre(String nom) {
		nombre = nom;
	}

	public void setFila(int fil) {
		fila = fil;
	}

	public void setColumna(int col) {
		columna = col;
	}

	
	public void setVida(int vid) {
		vida = vid;
	}
        
        public void setEstado(boolean a) {
		estado = a;
	}
        
	public boolean getEstado() {
		return estado;
	}
        
	public String getNombre() {
		return nombre;
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

	public int getVida() {
		return vida;
	}
    @Override
	public String toString() {
		return  nombre+", Vida: "+vida+ ", Fila: "+fila+",   Columna: "+columna;
	}
    
}
