package genapi.model;

import org.mongodb.morphia.annotations.Entity;
import core.model.BasicModel;
import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;

@Entity("testDatatype")
public class TestDatatype extends BasicModel {
	
	private String myString;
	
	public String getMyString() {
	    return this.myString;
	}
	
	public void setMyString(String myString) {
	    this.myString = myString;
	}
		
	private int myInt;
	
	public int getMyInt() {
	    return this.myInt;
	}
	
	public void setMyInt(int myInt) {
	    this.myInt = myInt;
	}
		
	private boolean myBool;
	
	public boolean getMyBool() {
	    return this.myBool;
	}
	
	public void setMyBool(boolean myBool) {
	    this.myBool = myBool;
	}
		
	private char myChar;
	
	public char getMyChar() {
	    return this.myChar;
	}
	
	public void setMyChar(char myChar) {
	    this.myChar = myChar;
	}
		
	private double myDouble;
	
	public double getMyDouble() {
	    return this.myDouble;
	}
	
	public void setMyDouble(double myDouble) {
	    this.myDouble = myDouble;
	}
		
}
