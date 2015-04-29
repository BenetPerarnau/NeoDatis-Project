package Util;

import java.util.ArrayList;
import java.util.Date;
import Model.Departamento;

/**
 * Clase con métodos estáticos auxiliares, para operaciones durante la ejecución del programa.
 * @author Benet
 *
 */
public class Utils {
	/**
	 * @return obtiene la fecha actual en formato java no sql y retorna la misma fecha pero en formato Date sql
	 */
	public static java.sql.Date getFechaActual(){
		Date fecha = new Date();
		java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
		return fechaSQL;
	}
	/**
	 * @param array  Colección donde buscar si se repite el @param new_entry
	 * @param new_entry Parametro candidato a entrar en el @param array
	 * @return si @param new_entry ya esta en @param array @return = true , sino @return = false
	 */
	public static boolean isRepeatDepartamentoInList(ArrayList<Object> array, Departamento new_entry){
		if(array.size()>0){
			for(int i=0; i<array.size(); i++){				
				if(new_entry.getId().equalsIgnoreCase((((Departamento)array.get(i)).getId()))){
					return true;
				}				
			}
		}else{
			return false;
		}
		return false;
	}	
}
