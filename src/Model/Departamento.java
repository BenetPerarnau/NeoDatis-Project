package Model;
/**
 * Modelo del Objecto Departamento que corresponde al que estará en la base de datos
 * @author Benet
 */
public class Departamento {
	/**
	 * @param id identificador único
	 */
	private String id;
	/**
	 * @param dNombre nombre del Departamento
	 */
	private String dNombre;
	/**
	 * @param depNo número del Departamento
	 */
	private int depNo;
	/**
	 * @param Lugar del departamento ?
	 */
	private String loc;
	/**
	 * Constructor vacio
	 */
	public Departamento(){}
	/**
	 * Constructor con todos los atributos de la clase 
	 * @param id
	 * @param dNombre
	 * @param depNo
	 * @param loc
	 */
	public Departamento(String id, String dNombre, int depNo, String loc){
		this.id=id;
		this.dNombre=dNombre;
		this.depNo=depNo;
		this.loc=loc;
	}
	/**
	 * GETERS
	 */
	public String getId(){return id;}
	public String getdNombre() {return dNombre;}
	public int getDepNo() {return depNo;}
	public String getLoc() {return loc;}
	/**
	 *SETERS
	 */
	public void setId(String id){this.id=id;}
	public void setdNombre(String dNombre) {this.dNombre = dNombre;}
	public void setDepNo(int depNo) {this.depNo = depNo;}
	public void setLoc(String loc) {this.loc = loc;}
	/**
	 * @return cadedna de texto con el valor de todos los atributos de la clase
	 */
	@Override
	public String toString() {
		return "Departamento [id=" + id + ", dNombre=" + dNombre + ", depNo="
				+ depNo + ", loc=" + loc + "]";
	}
	
	

}
