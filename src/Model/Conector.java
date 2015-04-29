package Model;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
/**
 * Clase Conector para establecer la conexión con la base de datos Neodatis
 * @author Benet
 */
public class Conector {
	/**
	 * @param conexion instáncia de la clase
	 */
	private static Conector conexion;
	/**
	 * @param odb instáncia de la clase ODB
	 */
	private static ODB odb;
	/**
	 * @param nameDataBase cadena de texto con el nombre de la base de datos a la que estamos apuntando al llamar a la clase
	 */
	private String nameDataBase;
	/**
	 * Constructor de la case privado
	 * @param nameDataBase cadena de texto con el nombre de la base de datos
	 */
	private Conector(String nameDataBase){
		this.nameDataBase=nameDataBase;
		odb=ODBFactory.open(nameDataBase);
		
	}	
	/**
	 * Modelo Singleton
	 * @param nameDataBase recibe el nombre de la base de datos a la que queremos acceder
	 * @return retorna una instancia de esta clase
	 */
	public static Conector getInstance(String nameDataBase){
		//System.out.println("getInstance "+nameDataBase);
		if(conexion==null){			
			conexion=new Conector(nameDataBase);
		}else if(!conexion.nameDataBase.equalsIgnoreCase(nameDataBase)){
			conexion=new Conector(nameDataBase);
		}
		return conexion;
	}
	/**
	 * @return retorna un Objeto ODB con la conexion a la base de datos
	 */
	public ODB getConexion(){
		return odb;
	}
	/**
	 * Métoo para cerrar la instancia de la clase
	 */
	public static void closeConexion(){
		conexion=null;
	}
	/**
	 * @return retorna el nombre de la base de datos a la que apunta la instancia 
	 */
	public String getNameDataBase(){
		return nameDataBase;
	}
}
