package clases;

import java.io.Serializable;

public class Empleado extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	private static String codEmpleado ="100";
	private String dep;
	private int codCat;
	
	public Empleado(String nom,String ape,String dni,String dep, int codCat) 
	{
		super(nom,ape,dni);
		this.dep = dep;
		this.codCat = codCat;
	}

	public String getCodEmpleado() {
		return codEmpleado;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public int getCodCat() {
		return codCat;
	}

	public void setCodCat(int codCat) {
		this.codCat = codCat;
	}

	@Override
	public String toString() {
		return super.toString()+"Empleado [codEmpleado=" + codEmpleado + ", dep=" + dep + ", codCat=" + codCat + "]";
	}
	
	
	
	
	
	
}
