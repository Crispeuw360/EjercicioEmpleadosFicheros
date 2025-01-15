package main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import clases.*;

import utilidades.*;

public class EmpleadosMain {
	
	public static int mostrarMenu() {
		System.out.println("1. Alta Empleado");
		System.out.println("2. Alta de categoría");
		System.out.println("3. Modificación del departamento de un empleado a partir de su código de empleado:");
		System.out.println("4. Listado de los departamentos con el número de empleados que hay en cada departamento:");
		System.out.println("5. Listado ordenado por categoría:");
		System.out.println("6. Salir");
		return Utilidades.leerInt();
	}
	
	public static void crearEmpleados(File fich)
	{
		
		
		
		Empleado empleado1 = new Empleado("Juan", "Pérez", "12345678A", Departamentos.INFORMATICA, 1);
		Empleado empleado2 = new Empleado("Ana", "López", "23456789B", Departamentos.OFICINA, 2);
		Empleado empleado3 = new Empleado("Luis", "Gómez", "34567890C", Departamentos.OFICINA, 3);
		Empleado empleado4 = new Empleado("Marta", "Ruiz", "45678901D", Departamentos.MARKETING, 4);
		Empleado empleado5 = new Empleado("Carlos", "Fernández", "56789012E", Departamentos.PRODUCCION, 5);
		
		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(new FileOutputStream(fich));
			oos.writeObject(empleado1);
			oos.writeObject(empleado2);
			oos.writeObject(empleado3);
			oos.writeObject(empleado4);
			oos.writeObject(empleado5);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void crearCategorias(File fich)
	{
		
		Categoria categoria1 = new Categoria("Junior", 1200);
		Categoria categoria2 = new Categoria("Semi-Senior", 1800);
		Categoria categoria3 = new Categoria("Senior", 2500.00);
		Categoria categoria4 = new Categoria("Team Leader", 3000.00);
		Categoria categoria5 = new Categoria("Manager", 4000.00);
		
		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(new FileOutputStream(fich));
			oos.writeObject(categoria1);
			oos.writeObject(categoria2);
			oos.writeObject(categoria3);
			oos.writeObject(categoria4);
			oos.writeObject(categoria5);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void altaEmpleado(File fich)
	{
		String deepIntro,nombre,apellido,dni,opcion;
		int codCat;
		Departamentos dep =null;
		
	

		if (fich.exists()) 
		{
			System.out.println("El fichero ya existe, se añadirán al final");
			MyObjectOutputStream moos;
			try {
				moos = new MyObjectOutputStream(new FileOutputStream(fich, true));				
				System.out.println("Introduce el nombre: ");
				nombre = Utilidades.introducirCadena();
				System.out.println("Introduce el apellido: ");
				apellido = Utilidades.introducirCadena();
				System.out.println("Introduce el dni: ");
				dni = Utilidades.introducirCadena();
				do {
					System.out.println("Introduce el dep: ");
					deepIntro = Utilidades.introducirCadena().toUpperCase();
					try {
						dep = Departamentos.valueOf(deepIntro);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} while (deepIntro==null);
				System.out.println("Introduce el codigo Categoria: ");
				codCat = Utilidades.leerInt();
				Empleado aux = new Empleado( nombre,apellido,dni,dep,codCat);
				System.out.println("Objeto creado");
				System.out.println(aux.toString());
				System.out.println("Quieres guardar el objeto? Si/No");
				opcion = Utilidades.introducirCadena("Si", "No");
				
				if (opcion.equalsIgnoreCase("Si")) 
				{
					moos.writeObject(aux);
					System.out.println("Se ha guardado Correctamente");
					moos.close();
				}else
				{
					System.out.println("No se ha guardado Saliendo del programa");
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void altaCategorias(File fich)
	{
		String descripcion,opcion;
		double sueldo;

		if (fich.exists()) 
		{
			System.out.println("El fichero ya existe, se añadirán al final");
			MyObjectOutputStream moos;
			try {
				moos = new MyObjectOutputStream(new FileOutputStream(fich, true));				
				System.out.println("Introduce una descripcion: ");
				descripcion = Utilidades.introducirCadena();
				System.out.println("Introduce el sueldo: ");
				sueldo = Utilidades.leerInt();
				Categoria aux = new Categoria(descripcion,sueldo);
				System.out.println("Objeto creado");
				System.out.println(aux.toString());
				System.out.println("Quieres guardar el objeto? Si/No");
				opcion = Utilidades.introducirCadena("Si", "No");
				
				if (opcion.equalsIgnoreCase("Si")) 
				{
					moos.writeObject(aux);
					System.out.println("Se ha guardado Correctamente");
					moos.close();
				}else
				{
					System.out.println("No se ha guardado Saliendo del programa");
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void modificarDepEmpleadoPorId(File fich)
	{
		boolean modificado = false;
		boolean finArchivo = false;
		
		String deepIntro,opcion;
		Departamentos dep;
		
		
		File fichAux = new File("fichAux.dat");
		
		String codEmpleado;
		System.out.println("Introduce el codigo de empleado");
		codEmpleado = Utilidades.introducirCadena();
		
		if (fich.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich));
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichAux));

				// Leer mientras no se alcance el fin del archivo
				while (!finArchivo) {
					try {
						Empleado aux = (Empleado) ois.readObject();
						if (aux.getCodEmpleado().equalsIgnoreCase(codEmpleado)) {
							System.out.println(aux.toString());
							System.out.println("Quieres cambiar el departamento");
							opcion = Utilidades.introducirCadena("Si", "No");
							if (opcion.equalsIgnoreCase("si")) 
							{
								do {
									System.out.println("Introduce el dep: ");
									deepIntro = Utilidades.introducirCadena().toUpperCase();
									try {
										dep = Departamentos.valueOf(deepIntro);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} while (deepIntro==null);
								System.out.println("departamento modificado");
								modificado = true;
							}else
							{
								System.out.println("no se ha modificado el empleado");
								modificado = false;
							}
						} else {
							System.out.println("No hay un animal con ese id");
						}
						oos.writeObject(aux);
					} catch (EOFException e) {
						// Fin del archivo alcanzado
						finArchivo = true;
					}
				}
				oos.close();
				ois.close();
				if (modificado) 
				{
					System.out.println("ArrayList modificado");
					if (fich.delete()) {
						fichAux.renameTo(fich);
					}
				}

			} catch (Exception e) {
				System.out.println("Fatal error");
			}
		} else {
			System.out.println("Fichero nuevo");
		}
	
	}
	
	public static void listarDepartamentos(File fich) 
	{
		int contOfic = 0, contMark = 0, contInfor = 0, contProduc = 0;
		boolean endFile = false;
		Departamentos depLeido;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich));
			do {
				try {
					Object object = ois.readObject();
					if (object instanceof Empleado) {
						depLeido = ((Empleado) object).getDep();
						switch (depLeido) 
						{
						
						case OFICINA:
							contOfic++;
							break;
						case MARKETING:
							contMark++;
							break;
						case INFORMATICA:
							contInfor++;
							break;
						case PRODUCCION:
							contProduc++;
							break;
						}
					}

				} catch (EOFException e) {
					endFile = true;
				}
			} while (!endFile);
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("*****ESTADISTICAS FINALES*****");
		System.out.println("Oficina:" + contOfic);
		System.out.println("Marketing:" + contMark);
		System.out.println("Informatica:" + contInfor);
		System.out.println("Produccion:" + contProduc);
	}
	
	public static void listarPorCategorias(File fich,File fich2)
	{
		ObjectInputStream ois = null;
		ArrayList <EmpleadoOrdenar> categoriaConteo = new ArrayList <EmpleadoOrdenar>();
		Categoria categoria;
		Empleado empleado;
		boolean continuar = true;

		try {
			ois = new ObjectInputStream(new FileInputStream(fich2));
			while (continuar) {
				try {
					categoria = (Categoria) ois.readObject();
					categoriaConteo.add(new EmpleadoOrdenar (categoria));
				} catch (EOFException eof) {
					continuar = false;
				} catch (ClassNotFoundException cnf) {
					cnf.printStackTrace();
				}
			}

		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}

		} catch (IOException e) {
				e.printStackTrace();

			}
		}

		continuar = true;

		try {
			ois = new ObjectInputStream(new FileInputStream(fich));
			while (continuar) {
				try {
					empleado = (Empleado) ois.readObject();
					categoriaConteo.get(empleado.getCodCat() - 1).sumarEmpleado();
				} catch (EOFException eof) {
					continuar = false;
				} catch (ClassNotFoundException cnf) {
					cnf.printStackTrace();
				}
			}
		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		if (categoriaConteo.isEmpty()) {
			System.out.println("No hay categorias");
		}	else {
			Collections.sort(categoriaConteo);
			for (EmpleadoOrdenar aux : categoriaConteo) {
				System.out.println(aux);
			}
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int opcion;
		File fichEmpleados = new File("empleados.dat");
		File fichCategorias = new File("Categorias.dat");
		
		crearEmpleados(fichEmpleados);
		crearCategorias(fichCategorias);
		
		do {
			opcion = mostrarMenu();
			switch (opcion) {
			case 1:
				altaEmpleado(fichEmpleados);
				break;
			case 2:
				altaCategorias(fichCategorias);
				break;
			case 3:
				modificarDepEmpleadoPorId(fichEmpleados);
				break;
			case 4:
				listarDepartamentos(fichEmpleados);
				break;
			case 5:
				listarPorCategorias(fichEmpleados,fichCategorias);
				break;
			}
		} while (opcion != 6);
	}

}
