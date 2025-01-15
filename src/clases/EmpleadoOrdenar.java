package clases;

public class EmpleadoOrdenar implements Comparable<EmpleadoOrdenar>{
	private String desc;
	private double sueldo;
	private int codCategoria;
	private int nEmpleados;
	
	public EmpleadoOrdenar(Categoria categoria) {
		this.desc = categoria.getDescripcion();
		this.sueldo = categoria.getSueldo();
		this.codCategoria = categoria.getCodCat();
		this.nEmpleados = 0;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public int getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(int codCategoria) {
		this.codCategoria = codCategoria;
	}

	public int getnEmpleados() {
		return nEmpleados;
	}

	public void setnEmpleados(int nEmpleados) {
		this.nEmpleados = nEmpleados;
	}

	@Override
	public String toString() {
		return "EmpleadoOrdenar [desc=" + desc + ", sueldo=" + sueldo + ", codCategoria=" + codCategoria
				+ ", nEmpleados=" + nEmpleados + "]";
	}
	
	public void sumarEmpleado() {
		this.setnEmpleados(nEmpleados + 1);
	}

	@Override
	public int compareTo(EmpleadoOrdenar o) {
		// TODO Auto-generated method stub
		
		return this.desc.compareTo(o.desc);
	}
	
}
