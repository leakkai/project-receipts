package ResponseHolder;

import java.io.Serializable;
import java.util.List;

public class ResponseHolder implements Serializable {

	private static final long serialVersionUID = -2095717733778571219L;
	
	private String status;
	
	private List<Object> object;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Object> getObject() {
		return object;
	}

	@SuppressWarnings("unchecked")
	public void setObject(List<?> object) {
		this.object = (List<Object>) object;
	}
}
