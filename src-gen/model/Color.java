package genapi.model;

import org.mongodb.morphia.annotations.Entity;
import core.model.BasicModel;
import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;

@Entity("color")
public class Color extends BasicModel {
	
	private String name;
	
	public String getName() {
	    return this.name;
	}
	
	public void setName(String name) {
	    this.name = name;
	}
		
	@Reference
	private TestDatatype testDatatypes;
	
	public TestDatatype getTestDatatypes() {
	    return this.testDatatypes;
	}
	
	public void setTestDatatypes(TestDatatype testDatatypes) {
	    this.testDatatypes = testDatatypes;
	}
		
}
