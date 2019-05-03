package modelo;

public class ReturnDatos {
		private Object object;
		private String error;
		
		public ReturnDatos(Object object, String error) {
			super();
			this.object = object;
			this.error = error;
		}
		
		public ReturnDatos() {}
				
		public Object getObject() {
			return object;
		}
		public void setObject(Object object) {
			this.object = object;
		}
		public String getError() {
			return error;
		}
		public void setError(String error) {
			this.error = error;
		}
		
		
		
}
