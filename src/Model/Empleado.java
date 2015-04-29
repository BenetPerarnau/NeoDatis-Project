package Model;

import java.sql.Date;
/**
 * Modelo del Objecto Empleado que corresponde al que estará en la base de datos
 * @author Benet
 */
public class Empleado {	
	/**
	 * @param empNo número del empleado
	 */
	private int empNo;
	/**
	 * @param apellido del empleado
	 */
	private String apellido;
	/**
	 * @param nombre nombre del empleado
	 */
	private String nombre;
	/**
	 * @param oficio oficio del empleado
	 */
	private String oficio;
	/**
	 * @param Objecto Empleado que hace de director en un empleado normal
	 */
	private Empleado director;
	/**
	 * @param fecha en la qual se da de alta el empleado
	 */
	private Date fechaAlta;
	/**
	 * @param salario del Empleado
	 */
	private float salario;
	/**
	 * @param comision comisión que tendrá el empleado
	 */
	private float comision;
	/**
	 * @param departamento objeto Departamento 
	 */
	private Departamento departamento;
	
	/**
	 * Constructor vacio
	 */
	public Empleado(){}
	/**
	 * Constructor EMPLEADO NORMAL CON DIRECTOR
	 * @param empNo
	 * @param apellido
	 * @param nombre
	 * @param oficio
	 * @param director
	 * @param fechaAlta
	 * @param salario
	 * @param comision
	 * @param departamento
	 */
	public Empleado(int empNo, String apellido, String nombre,
				    String oficio, Empleado director, Date fechaAlta, 
				    float salario, float comision, Departamento departamento){	
		this.empNo=empNo;
		this.apellido=apellido;
		this.nombre=nombre;
		this.oficio=oficio;
		this.director=director;
		this.fechaAlta=fechaAlta;
		this.salario=salario;
		this.comision=comision;
		this.departamento=departamento;			
	}
	/**
	 * Constructor JEFE
	 * @param empNo
	 * @param apellido
	 * @param nombre
	 * @param oficio
	 * @param fechaAlta
	 * @param salario
	 * @param comision
	 * @param departamento
	 */
	public Empleado(int empNo, String apellido, String nombre,
		    String oficio, Date fechaAlta, 
		    float salario, float comision, Departamento departamento){	
			this.empNo=empNo;
			this.apellido=apellido;
			this.nombre=nombre;
			this.oficio=oficio;
			this.director=null;
			this.fechaAlta=fechaAlta;
			this.salario=salario;
			this.comision=comision;
			this.departamento=departamento;	
	}
	/**
	 *GETERS
	 */
	public int getEmpNo() {return empNo;}
	public String getApellido() {return apellido;}
	public String getNombre() {return nombre;}
	public String getOficio() {return oficio;}
	public Empleado getDirector() {return director;}
	public Date getFechaAlta() {return fechaAlta;}
	public float getSalario() {return salario;}
	public float getComision() {return comision;}
	public Departamento getDepartamento() {return departamento;}
	/**
	 * SETERS
	 */
	public void setEmpNo(int empNo) {this.empNo = empNo;}
	public void setApellido(String apellido) {this.apellido = apellido;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public void setOficio(String oficio) {this.oficio = oficio;}
	public void setDirector(Empleado director) {this.director = director;}
	public void setFechaAlta(Date fechaAlta) {this.fechaAlta = fechaAlta;}
	public void setSalario(float salario) {this.salario = salario;}
	public void setComision(float comision) {this.comision = comision;}
	public void setDepartamento(Departamento departamento) {this.departamento = departamento;}

	/**
	 * Por cada empleado visualiza el nombre y apellido de su director y el nombre de su departamento.
	 * @return cadena de texto con esos atributos
	 */
	public String toStringDirectorAndDep(){
		String aux="";
		if(director!=null){
		aux="[ Empleado => "+nombre+" "
				+ "Director: { Nombre: "+director.getNombre()+" Apellido: "+director.getApellido()+"} "
		        + "Departamento: { Nombre Departamento: "+departamento.getdNombre()+" }]";
		}else{
			aux="[ Empleado => "+nombre+" "
					+ "Director: {Él es el Director.} "
			        + "Departamento: { El jefe no pertenece a ningún departamento }]";
		}
		return aux;
	}
	/**
	 * String con todos los atributos
	 * @return cadena de texto con los valores de todos los atributos
	 */
	@Override
	public String toString() {
		return "Empleado [empNo=" + empNo + ", apellido=" + apellido
				+ ", nombre=" + nombre + ", oficio=" + oficio + ", director="
				+ director + ", fechaAlta=" + fechaAlta + ", salario="
				+ salario + ", comision=" + comision + ", departamento="
				+ departamento + "]";
	}
}