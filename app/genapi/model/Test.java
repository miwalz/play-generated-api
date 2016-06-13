package genapi.model;

import org.mongodb.morphia.annotations.Entity;
import core.model.BasicModel;
import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;

@Entity("test")
public class Test extends BasicModel {
	
	private int count;
	
	public int getCount() {
	    return this.count;
	}
	
	public void setCount(int count) {
	    this.count = count;
	}
		
}
