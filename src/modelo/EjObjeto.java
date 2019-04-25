package modelo;

public class EjObjeto {
	private int id;
	private int ejInt;
	private String ejString;
	
	public EjObjeto() {}

	public EjObjeto(int id, int ejInt, String ejString) {
		super();
		this.id = id;
		this.ejInt = ejInt;
		this.ejString = ejString;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEjInt() {
		return ejInt;
	}

	public void setEjInt(int ejInt) {
		this.ejInt = ejInt;
	}

	public String getEjString() {
		return ejString;
	}

	public void setEjString(String ejString) {
		this.ejString = ejString;
	}

	@Override
	public String toString() {
		return "EjObjeto [id=" + id + ", ejInt=" + ejInt + ", ejString=" + ejString + "]";
	}
	
	
}
