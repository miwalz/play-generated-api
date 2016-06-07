package core.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

public class BasicModel {

	@Id
	private String id = new ObjectId().toString();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof BasicModel))
			return false;
		BasicModel that = (BasicModel) o;
		return !(id != null ? !id.equals(that.id) : that.id != null);
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
