package genapi.model;

import org.mongodb.morphia.annotations.Entity;
import core.model.BasicModel;
import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;

@Entity("pet")
public class Pet extends BasicModel {
	
	private String name;
	
	public String getName() {
	    return this.name;
	}
	
	public void setName(String name) {
	    this.name = name;
	}
		
	@Reference
	private Color color;
	
	public Color getColor() {
	    return this.color;
	}
	
	public void setColor(Color color) {
	    this.color = color;
	}
		
	@Reference
	private List<Color> colors = new ArrayList<Color>();
	
	public List<Color> getColors() {
		return this.colors;
	}
	
	public void setColors(List<Color> colors) {
		this.colors = colors;
	}
		
	@Embedded
	private List<Color> colorsEmb = new ArrayList<Color>();
	
	public List<Color> getColorsEmb() {
		return this.colorsEmb;
	}
	
	public void setColorsEmb(List<Color> colorsEmb) {
		this.colorsEmb = colorsEmb;
	}
		
	private List<String> strings = new ArrayList<String>();
	
	public List<String> getStrings() {
		return this.strings;
	}
	
	public void setStrings(List<String> strings) {
		this.strings = strings;
	}
		
	private List<String> stringsEmb = new ArrayList<String>();
	
	public List<String> getStringsEmb() {
		return this.stringsEmb;
	}
	
	public void setStringsEmb(List<String> stringsEmb) {
		this.stringsEmb = stringsEmb;
	}
		
}
