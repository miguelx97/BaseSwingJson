package modelo;

public class CustomReturn {
		private Object object;
		private String error;
		
		public CustomReturn(Object object, String error) {
			super();
			this.object = object;
			this.error = error;
		}
		
		public CustomReturn() {}
				
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
