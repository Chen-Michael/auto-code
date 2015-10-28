package template.java;

import java.util.List;

import other.Utils;
import template.POJO;

public class JavaPOJO implements POJO {
	
	@Override
	public String getClassImport(List<String> className) {
		StringBuilder sb = new StringBuilder();
		
		for (String temp: className){
			sb.append(temp + ";\r\n");
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
		return "private byte " + Utils.formatVariableName(variable) + " = null;";
	}

	@Override
	public String getShortVariable(String variable) {
		return "private short " + Utils.formatVariableName(variable) + " = null;";
	}

	@Override
	public String getIntVariable(String variable) {
		return "private int " + Utils.formatVariableName(variable) + " = null;";
	}

	@Override
	public String getLongVariable(String variable) {
		return "private long " + Utils.formatVariableName(variable) + " = null;";
	}

	@Override
	public String getFloatVariable(String variable) {
		return "private float " + Utils.formatVariableName(variable) + " = null;";
	}

	@Override
	public String getDoubleVariable(String variable) {
		return "private double " + Utils.formatVariableName(variable) + " = null;";
	}

	@Override
	public String getStringVariable(String variable) {
		return "private String " + Utils.formatVariableName(variable) + " = null;";
	}

	@Override
	public String getByteSetters(String variable) {
		String variable2 = Utils.formatVariableName(variable);
		return "public byte set" + Utils.formatFileName(variable) + "(byte " + variable2 + "){ this." + variable2 + " = " + variable2 + ";}";
	}

	@Override
	public String getShortSetters(String variable) {
		String variable2 = Utils.formatVariableName(variable);
		return "public void set" + Utils.formatFileName(variable) + "(short " + variable2 + "){ this." + variable2 + " = " + variable2 + ";}";
	}

	@Override
	public String getIntSetters(String variable) {
		String variable2 = Utils.formatVariableName(variable);
		return "public void set" + Utils.formatFileName(variable) + "(int " + variable2 + "){ this." + variable2 + " = " + variable2 + ";}";
	}

	@Override
	public String getLongSetters(String variable) {
		String variable2 = Utils.formatVariableName(variable);
		return "public void set" + Utils.formatFileName(variable) + "(long " + variable2 + "){ this." + variable2 + " = " + variable2 + ";}";
	}

	@Override
	public String getFloatSetters(String variable) {
		String variable2 = Utils.formatVariableName(variable);
		return "public void set" + Utils.formatFileName(variable) + "(float " + variable2 + "){ this." + variable2 + " = " + variable2 + ";}";
	}

	@Override
	public String getDoubleSetters(String variable) {
		String variable2 = Utils.formatVariableName(variable);
		return "public void set" + Utils.formatFileName(variable) + "(double " + variable2 + "){ this." + variable2 + " = " + variable2 + ";}";
	}

	@Override
	public String getStringSetters(String variable) {
		String variable2 = Utils.formatVariableName(variable);
		return "public void set" + Utils.formatFileName(variable) + "(String " + variable2 + "){ this." + variable2 + " = " + variable2 + ";}";
	}

	@Override
	public String getByteGetters(String variable) {
		return "public byte get" + Utils.formatFileName(variable) + "(){ return " + Utils.formatVariableName(variable) + ";}";
	}

	@Override
	public String getShortGetters(String variable) {
		return "public short get" + Utils.formatFileName(variable) + "(){ return " + Utils.formatVariableName(variable) + ";}";
	}

	@Override
	public String getIntGetters(String variable) {
		return "public int get" + Utils.formatFileName(variable) + "(){ return " + Utils.formatVariableName(variable) + ";}";
	}

	@Override
	public String getLongGetters(String variable) {
		return "public long get" + Utils.formatFileName(variable) + "(){ return " + Utils.formatVariableName(variable) + ";}";
	}

	@Override
	public String getFloatGetters(String variable) {	
		return "public float get" + Utils.formatFileName(variable) + "(){ return " + Utils.formatVariableName(variable) + ";}";
	}

	@Override
	public String getDoubleGetters(String variable) {	
		return "public double get" + Utils.formatFileName(variable) + "(){ return " + Utils.formatVariableName(variable) + ";}";
	}

	@Override
	public String getStringGetters(String variable) {	
		return "public String get" + Utils.formatFileName(variable) + "(){ return " + Utils.formatVariableName(variable) + ";}";
	}

	@Override
	public String getClassFooter() {		
		return "}";
	}
	
}
