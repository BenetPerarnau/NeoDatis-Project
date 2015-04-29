package Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import DAO.Empleado_DAO;
import Model.Departamento;
import Model.Empleado;
/**
 * @author Benet
 * 
 * Crea las clases Empleado y Departamento. Define los métodos getter y setter para cada atributo.
 * Define los constructores necesarios. Ten en cuenta que todos los empleados deben tener un director.
 * El empleado con mayor rango (el presidente) tiene como director a él mismo.
 * 1. Crea una base de datos Neodatis para guardar los datos de empleados y departamentos, e inserta datos.
 * 2. Visualiza por línea de comandos todos los departamentos y empleados de la base de datos. 
 * Por cada empleado visualiza el nombre y apellido de su director y el nombre de su departamento.
 * 3. Realiza las siguientes consultas:
 * 		3.1 Apellidos de los empleados del departamento con id=?
 *  	3.2 Número de empleados del departamento de nombre=?
 *		3.3 Apellido de los empleados cuyo apellido de director es=?
 * 		3.4 Por cada departamento, el número de empleados
 */
public class Test {
	/**
	 * Variable necesaria en varios métodos de la clase test
	 * @param daoEmpleado del Objecto Empleado_DAO para las consultas en la base de datos
	 */
	private static Empleado_DAO daoEmpleado;
	/**
	 * Incio del programa
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		byte op=-1;
		do{
			showMenuP();
			try {
				op=Byte.parseByte(stdin.readLine());
			} catch (NumberFormatException | IOException e) {}			
			switch(op){
			case 1:
				/**
				 * Crea una base de datos Neodatis para guardar los datos de empleados y departamentos, e inserta datos.
				 */				
				try{
					insertValues();
					System.out.println("Inserts OK.");
				}catch(Exception e){
					System.out.println("ERROR inserts.");
				}
				break;
			case 2:
				/**
				 * Visualiza por línea de comandos todos los departamentos y empleados de la base de datos.
				 * Por cada empleado visualiza el nombre y apellido de su director y el nombre de su departamento.
				 */
				try {
					showAllDepartamentos();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				try {
					showAllEmpleados();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case 3:
				/**
				 * Realiza las siguientes consultas:
				 */
				byte op2=-1;
				do{
					showMenuC();
					try {
						op2=Byte.parseByte(stdin.readLine());
					} catch (NumberFormatException | IOException e) {
						//pq si no es la primera vez que se llama y no se ponse un número no vaya a la op anterior y vaya a default.
						op2=-1;
					}
					switch(op2){
					case 1:
						/**
						 * Apellidos de los empleados del departamento con id=?
						 */
						try {
							getEmpleadosSegunDep(stdin);
						}catch (Exception e) {
							//e.printStackTrace();
						}						
						break;
					case 2:
						/**
						 * Número de empleados del departamento de nombre=?
						 */						
						try {
							getCountEmpleadosByDepart(stdin);
						} catch (Exception e) {
							//e.printStackTrace();
						}					
						break;
					case 3:
						/**
						 * Apellido de los empleados cuyo apellido de director es=?
						 */
						try {
							getEmplesByApeDirector(stdin);
						} catch (Exception e) {
							//e.printStackTrace();
						}
						break;
					case 4:
						/**
						 * Por cada departamento, el número de empleados
						 */
						try {
							getNumberEmpleByDepart(stdin);
						} catch (Exception e) {
							//e.printStackTrace();
						}
						break;
					case 5:
						System.out.println("Atrás");
						break;
						default:
							System.out.println("Valor no válido.");
							break;
					}				
				}while(op2!=5);
				break;
			case 4:
				System.out.println("Adiós");
				break;
			default:
				System.out.println("Valor no válido.");
				break;
			}
		}while(op!=4);
				
	}
////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////1//////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Crea una base de datos Neodatis para guardar los datos de empleados y departamentos, e inserta datos
	 * @throws Exception
	 */
	private static void insertValues() throws Exception{
		Departamento[] deps= generateDepartamentos();
		Empleado[] emps=generateEmpleados(deps);							
		daoEmpleado=new Empleado_DAO();
		
		for(int i=0; i<deps.length;i++){
			daoEmpleado.insert(deps[i]);
			System.out.println("Insert Dep ok => "+deps[i].toString());
		}
		for(int i=0; i<emps.length; i++){
			daoEmpleado.insert(emps[i]);
			System.out.println("Insert Emp ok => "+emps[i].toString());
		}
	}
	/**
	 * Generar registros para la tabla Departamentos
	 * @return array de de Objetos Departamento
	 */
	private static Departamento[] generateDepartamentos(){
		Departamento[] data=new Departamento[2];
		for(int i=0; i<2; i++){
			data[i]=new Departamento("000"+i,"NameDepartamento "+i,i,"Barcelona");
		}	
		return data;
	}
	/**
	 * Generar Empleados para la tabla Empleados
	 * @param departamentos departamentos disponibles para setear en cada Empleado
	 * @return array de objectos Empleado
	 */
	private static Empleado[] generateEmpleados(Departamento[] departamentos){		
		int aux=0;
		Empleado direct=new Empleado(0,"Perarnau","Benet","NS",Util.Utils.getFechaActual(),1000,50,null);
		Empleado[] data=new Empleado[10];
		data[0]=direct;
		for(int i=1; i<10; i++){
			data[i]=new Empleado(i,"ApellidoEmple "+i,"NombreEmple "+i,"OficioEmple "+i,direct,Util.Utils.getFechaActual(),500,10,departamentos[aux]);			
			if(aux==0){aux=1;}else{aux=0;}
		}
		return data;
	}
	
////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////2//////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Visualiza por línea de comandos todos los departamentos y empleados de la base de datos.
	 * Por cada empleado visualiza el nombre y apellido de su director y el nombre de su departamento.
	 */
	
	/**
	 * Printará todos los registros en la tabla Departamentos
	 * @throws Exception
	 */
	private static void showAllDepartamentos() throws Exception{
		daoEmpleado=new Empleado_DAO();
		ArrayList<Object> deps=daoEmpleado.selectAll(new Departamento());
		if(deps!=null){
			for(Object a:deps){
				System.out.println(((Departamento)a).toString());
			}
		}else{
			System.out.println("La tabla Departamentos no tiene registros.");
		}
	}
	/**
	 * Printará todos los registros en la tabla Empleados
	 * @throws Exception
	 */
	private static void showAllEmpleados() throws Exception{
		daoEmpleado=new Empleado_DAO();
		ArrayList<Object> emps=daoEmpleado.selectAll(new Empleado());
		if(emps!=null){
			for(Object a:emps){
				System.out.println(((Empleado)a).toStringDirectorAndDep());
			}
		}else{
			System.out.println("La tabla Empleados no tiene registros.");
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////3.1////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Apellidos de los empleados del departamento con id=?
	 * Printará el resultado
	 */
	private static void getEmpleadosSegunDep(BufferedReader stdin) throws IOException, Exception{
		System.out.print("ID => ");
			String id = stdin.readLine();
			daoEmpleado=new Empleado_DAO();
			ArrayList<String> apes=daoEmpleado.selectApeEmplesByDep(id);
			if(apes.size()>0){
				System.out.println("Apellidos:");
				for(String e:apes){
					System.out.println(e);
				}
			}else{
				System.out.println("No existe ningún Empleado en el Departamento con id= "+id);
			}
	}
	
////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////3.2////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Número de empleados del departamento de nombre=?
	 * Printará el resultado
	 */	
	public static void getCountEmpleadosByDepart(BufferedReader stdin) throws IOException, Exception{
		
		System.out.print("Nombre del Departamento => ");
		String nameDep=stdin.readLine();
		
		daoEmpleado=new Empleado_DAO();
		int num_emp=daoEmpleado.getNumberEmpleByDep(nameDep);
		
		if(num_emp!=-1 && num_emp!=0){
			System.out.println("Número de empleados en el departamento "+nameDep+" : "+num_emp );
		}else{
			System.out.println("No hay Empleados en el Departamento con nombre "+nameDep);
		}		
	}
	
////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////3.3////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Apellido de los empleados cuyo apellido de director es=?
	 * Printará el resultado
	 */
	public static void getEmplesByApeDirector(BufferedReader stdin)throws Exception{
		System.out.print("Apellido del Director => ");
		String apeDire=stdin.readLine();
		daoEmpleado=new Empleado_DAO();
		ArrayList<String> apes=daoEmpleado.selectApeEmpleByApeDirect(apeDire);
		
		if(apes.size()>0){
			for(String a:apes){
				System.out.println(a);
			}
		}else{
			System.out.println("No existen empleados cuyo apellido de director es "+apeDire+".");
		}
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////3.4////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Por cada departamento, el número de empleados.
	 * Printará el resultado
	 */
	public static void getNumberEmpleByDepart(BufferedReader stdin)throws Exception{
		
		daoEmpleado=new Empleado_DAO();
		Object [][] data=daoEmpleado.selectDepAndEmp();
		if(data!=null){
			for(int i=0; i<data.length; i++){
				System.out.println("Departamento : "+((Departamento)(data[i][0])).getdNombre());
				System.out.println("Nº Empleados : "+data[i][1]);
			}
		}else{
			System.out.println("No hay registros en la base de datos.");
		}
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////		MENUS		////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Menú Principal
	 */
	private static void showMenuP(){
		
		System.out.println("1. Insertar Datos");
		System.out.println("2. Visualiza por línea de comandos todos los departamentos y empleados de la base de datos. Por cada empleado visualiza el nombre y apellido de su director y el nombre de su departamento.");
		System.out.println("3. Consultas");
		System.out.println("4. Salir");
		System.out.print("OP => ");
	}
	/**
	 * Menu secundario de la 3 opción del menú principal
	 */
	private static void showMenuC(){

		System.out.println("1. Apelldios de los empleados del departamento con id=?");
		System.out.println("2. Número de empleados del departamento de nombre=?");
		System.out.println("3. Apellido de los empleados cuyo apellido de director es=?");
		System.out.println("4. Por cada departamento, el número de empleados.");
		System.out.println("5. Atrás");
		System.out.print("OP => ");
	}
	
}
