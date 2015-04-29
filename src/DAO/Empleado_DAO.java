package DAO;

import java.util.ArrayList;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;
import Model.Conector;
import Model.Departamento;
import Model.Empleado;

public class Empleado_DAO implements Operaciones<Empleado> {
	
	
	private ODB conexion;
	private Conector cn;
	
	@Override
	public boolean insert(Object new_object_insert)throws Exception{
		try{
			Empleado auxe;
			Departamento auxd;			
			boolean result=false;
			cn=Conector.getInstance(Util.Constant.DATA_BASE_EMPLE);
			conexion=cn.getConexion();
			
			//Si no lo hago asi a la hora de hacer un select y buscar por el .class no lo encuentra pq inserta un Object general
			if(new_object_insert instanceof Empleado){
				auxe=(Empleado)new_object_insert;
				conexion.store(auxe);
			}else if(new_object_insert instanceof Departamento){
				auxd=(Departamento)new_object_insert;
				conexion.store(auxd);
			}
			
			result=true;
			return result;
		}finally{
			conexion.close();
			cn.closeConexion();
		}
		
	}

	@Override
	public ArrayList<Object> selectAll(Object object_class) throws Exception{
		try{
			cn=Conector.getInstance(Util.Constant.DATA_BASE_EMPLE);
			conexion=cn.getConexion();
			ArrayList<Object> data=null;
			Objects<Empleado> objE=null;
			Objects<Departamento> objD=null;
			if(object_class instanceof Empleado){
				objE=conexion.getObjects(Empleado.class);
			}else if(object_class instanceof Departamento){
				objD=conexion.getObjects(Departamento.class);
			}
			
			/**
			 * Al loro, como El objeto empleado tiene dentro de él un atributo de tipo Empledo lo separa e 2
			 */	
			if(object_class instanceof Empleado){
				
				byte aux=1;
				if(objE.hasNext()){
					data=new ArrayList<Object>();
				}
				while(objE.hasNext()){
					if(aux==1 || aux%2==0){
						data.add(objE.next());
					}else{
						objE.next();
					}
					aux++;
				}
			}else if(object_class instanceof Departamento){		
				if(objD.hasNext()){
					data=new ArrayList<Object>();
				}
				Departamento d;
				while(objD.hasNext()){
					d=(Departamento)objD.next();
					if(!Util.Utils.isRepeatDepartamentoInList(data, d)){
						data.add(d);
					}
					
				}
			}
			return data;
		}finally{
			conexion.close();
			cn.closeConexion();
		}
		
	}

	@Override
	public ArrayList<String> selectApeEmplesByDep(String id) throws Exception{
		try{
			cn=Conector.getInstance(Util.Constant.DATA_BASE_EMPLE);
			conexion=cn.getConexion();
			ArrayList<String> emps=new ArrayList<String>();
			
			Values valores=conexion.getValues(
					new ValuesCriteriaQuery(
							Empleado.class,Where.equal("departamento.id", id))
							.field("apellido"));
				
			while(valores.hasNext()){
				ObjectValues ob=valores.next();
				emps.add(ob.getByIndex(0).toString());
			}
			return emps;
		}finally{
			conexion.close();
			cn.closeConexion();
		}		
	}

	@Override
	public int getNumberEmpleByDep(String nameDep) throws Exception{
		try{
			cn=Conector.getInstance(Util.Constant.DATA_BASE_EMPLE);
			conexion=cn.getConexion();
			int result=-1;
			Values values= conexion.getValues(
					new ValuesCriteriaQuery(
								Empleado.class,
								Where.equal("departamento.dNombre", nameDep))
								.count("nombre"));
			ObjectValues aux=values.next();
			result=Integer.parseInt(aux.getByIndex(0)+"");
			return result;
		}finally{
			conexion.close();
			cn.closeConexion();
		}
		
	}

	@Override
	public ArrayList<String> selectApeEmpleByApeDirect(String ape_director) throws Exception{
		try{
			cn=Conector.getInstance(Util.Constant.DATA_BASE_EMPLE);
			conexion=cn.getConexion();
			ArrayList<String> apes=new ArrayList<String>();
			
			Values valores=conexion.getValues(
					new ValuesCriteriaQuery(
							Empleado.class,Where.equal("director.apellido", ape_director))
							.field("apellido"));
				
			while(valores.hasNext()){
				ObjectValues ob=valores.next();
				apes.add(ob.getByIndex(0).toString());
			}
			return apes;
		}finally{
			conexion.close();
			cn.closeConexion();
		}
	}
	/**
	 * Por cada departamento, el número de empleados
	 */
	@Override
	public Object[][] selectDepAndEmp()throws Exception {
		//1)Lo primero saber que departamentos hay y guardar una referencia de cada uno		
		//2)Contar los empleados que hay en los diferentes departamentos
		Object [][] resultado=null;
		try{
			//1
			ArrayList<Object> deps=selectAll(new Departamento());
			if(deps!=null){
				cn=Conector.getInstance(Util.Constant.DATA_BASE_EMPLE);
				conexion=cn.getConexion();
				resultado=new Object[deps.size()][2];
				int i=0;
				for(Object d:deps){
					Departamento aux=(Departamento)d;
					
					Values values= conexion.getValues(
							new ValuesCriteriaQuery(
										Empleado.class,
										Where.equal("departamento.dNombre", aux.getdNombre()))
										.count("nombre"));
					ObjectValues aux1=values.next();
					int count=Integer.parseInt(aux1.getByIndex(0)+"");
					resultado[i][0]=d;
					resultado[i][1]=count;
					i++;
				}
			}
			return resultado;
		}finally{
			if(!conexion.isClosed())conexion.close();
			if(cn!=null)cn.closeConexion();
		}
		
		
	}

}
