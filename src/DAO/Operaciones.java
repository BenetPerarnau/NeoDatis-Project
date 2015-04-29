package DAO;

import java.util.ArrayList;

public interface Operaciones<My_Object> {
	//Insertar valores
	public boolean insert(Object new_object_insert)throws Exception;
	//select all tabla
	public ArrayList<Object> selectAll(Object object)throws Exception ;
	// Apellidos de los empleados del departamento con id=?
	public ArrayList<String> selectApeEmplesByDep(String id)throws Exception;
	// Número de empleados del departamento de nombre=?
	public int getNumberEmpleByDep(String nameDep)throws Exception;
	// Apellido de los empleados cuyo apellido de director es=?
	public ArrayList<String> selectApeEmpleByApeDirect(String ape_director)throws Exception;
	// Por cada departamento, el número de empleados
	public Object[][] selectDepAndEmp()throws Exception;
}
