package DAO;

public class Factory {
	
	
	public static Operaciones getInstance(int value){
		Operaciones result=null;
		switch(value){
		case 1:
			result=new Empleado_DAO();
			break;
		default:
			System.out.println("ERROR: Factory return null");
			break;
		}
		return result;
	}

}
