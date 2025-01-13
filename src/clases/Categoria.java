package clases;

import java.io.Serializable;

public class Categoria implements Serializable {
	private static final long serialVersionUID = 5971880007971321169L;
	private static int codCat=1;
	private String descripcion;
	private double sueldo;
	public Categoria(String descripcion, double sueldo) 
	{
		this.codCat=codCat++;
		this.descripcion = descripcion;
		this.sueldo = sueldo;
	}
	public static int getCodCat() {
		return codCat;
	}
	public static void setCodCat(int codCat) {
		Categoria.codCat = codCat;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getSueldo() {
		return sueldo;
	}
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
	@Override
	public String toString() {
		return "Categoria [+descripcion=" + descripcion + ", sueldo=" + sueldo + "]";
	}
}
