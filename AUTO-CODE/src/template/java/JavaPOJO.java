package template.java;

import java.util.List;

import other.Utils;
import template.POJO;

public class JavaPOJO implements POJO {
	
	@Override
	public String getClassImport(List<String> className) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("import java.util.*;\r\n");
		for (String temp: className){
			sb.append("import " + temp + ";\r\n");
		}
		
		return sb.toString();
	}
	
	@Override
	public String getClassHeader(String className) {
		return "public class " + Utils.formatFileName(className) + "{";
	}

	@Override
	public String getClassConstructor(String className) {
		return "public " + Utils.formatFileName(className) + "(){}";
	}

	@Override
	public String getByteVariable(String variable) {
		return "private Byte " + Utils.formatVariableName(variable) + " = null;";
	}

	@Override
	public String getShortVariable(String variable) {
		return "private Short " + Utils.formatVariableName(variable) + " = null;";
	}

	@Override
	public String getIntVariable(String variable) {
		return "private Integer " + Utils.formatVariableName(variable) + " = null;";
	}

	@Override
	public String getLongVariable(String variable) {
		return "private Long " + Utils.formatVariableName(variable) + " = null;";
	}

	@Override
	public String getFloatVariable(String variable) {
		return "private Float " + Utils.formatVariableName(variable) + " = null;";
	}

	@Override
	public String getDoubleVariable(String variable) {
		return "private Double " + Utils.formatVariableName(variable) + " = null;";
	}

	@Override
	public String getStringVariable(String variable) {
		return "private String " + Utils.formatVariableName(variable) + " = null;";
	}

	@Override
	public String getModelVariable(String variable, String pojoSuffix) {
		String variable2 = Utils.formatFileName(variable);
		return "private List<" + variable2 + pojoSuffix + "> " + Utils.formatVariableName(variable) + " = new ArrayList<" + variable2 + pojoSuffix + ">();";
	}

	@Override
	public String getByteSetters(String variable) {
		String variable2 = Utils.formatVariableName(variable);
		return "public byte set" + Utils.formatFileName(variable) + "(Byte " + variable2 + "){ this." + variable2 + " = " + variable2 + ";}";
	}

	@Override
	public String getShortSetters(String variable) {
		String variable2 = Utils.formatVariableName(variable);
		return "public void set" + Utils.formatFileName(variable) + "(Short " + variable2 + "){ this." + variable2 + " = " + variable2 + ";}";
	}

	@Override
	public String getIntSetters(String variable) {
		String variable2 = Utils.formatVariableName(variable);
		return "public void set" + Utils.formatFileName(variable) + "(Integer " + variable2 + "){ this." + variable2 + " = " + variable2 + ";}";
	}

	@Override
	public String getLongSetters(String variable) {
		String variable2 = Utils.formatVariableName(variable);
		return "public void set" + Utils.formatFileName(variable) + "(Long " + variable2 + "){ this." + variable2 + " = " + variable2 + ";}";
	}

	@Override
	public String getFloatSetters(String variable) {
		String variable2 = Utils.formatVariableName(variable);
		return "public void set" + Utils.formatFileName(variable) + "(Float " + variable2 + "){ this." + variable2 + " = " + variable2 + ";}";
	}

	@Override
	public String getDoubleSetters(String variable) {
		String variable2 = Utils.formatVariableName(variable);
		return "public void set" + Utils.formatFileName(variable) + "(Double " + variable2 + "){ this." + variable2 + " = " + variable2 + ";}";
	}

	@Override
	public String getStringSetters(String variable) {
		String variable2 = Utils.formatVariableName(variable);
		return "public void set" + Utils.formatFileName(variable) + "(String " + variable2 + "){ this." + variable2 + " = " + variable2 + ";}";
	}

	@Override
	public String getModelSetters(String variable, String pojoSuffix) {
		variable = Utils.formatVariableName(variable);
		String variable2 = Utils.formatFileName(variable);
		return "public void set" + variable2 + "(List<" + variable2 + pojoSuffix + "> " + variable + "){ this." + variable + " = " + variable + ";}";
	}
	
	@Override
	public String getModelAdd(String variable, String pojoSuffix) {
		variable = Utils.formatVariableName(variable);
		String variable2 = Utils.formatFileName(variable);
		return "public void add" + variable2 + "(" + variable2 + pojoSuffix + " " + variable + "){ this." + variable + ".add(" + variable + ");}";
	}

	@Override
	public String getByteGetters(String variable) {
		return "public Byte get" + Utils.formatFileName(variable) + "(){ return " + Utils.formatVariableName(variable) + ";}";
	}

	@Override
	public String getShortGetters(String variable) {
		return "public Short get" + Utils.formatFileName(variable) + "(){ return " + Utils.formatVariableName(variable) + ";}";
	}

	@Override
	public String getIntGetters(String variable) {
		return "public Integer get" + Utils.formatFileName(variable) + "(){ return " + Utils.formatVariableName(variable) + ";}";
	}

	@Override
	public String getLongGetters(String variable) {
		return "public Long get" + Utils.formatFileName(variable) + "(){ return " + Utils.formatVariableName(variable) + ";}";
	}

	@Override
	public String getFloatGetters(String variable) {	
		return "public Float get" + Utils.formatFileName(variable) + "(){ return " + Utils.formatVariableName(variable) + ";}";
	}

	@Override
	public String getDoubleGetters(String variable) {	
		return "public Double get" + Utils.formatFileName(variable) + "(){ return " + Utils.formatVariableName(variable) + ";}";
	}

	@Override
	public String getStringGetters(String variable) {	
		return "public String get" + Utils.formatFileName(variable) + "(){ return " + Utils.formatVariableName(variable) + ";}";
	}

	@Override
	public String getModelGetters(String variable, String pojoSuffix) {
		String variable2 = Utils.formatFileName(variable);
		return "public List<" + variable2 + pojoSuffix + "> get" + variable2 + "(){ return " + Utils.formatVariableName(variable) + ";}";
	}

	@Override
	public String getClassFooter() {		
		return "}";
	}
	
}
