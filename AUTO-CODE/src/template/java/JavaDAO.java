package template.java;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import jdbc.ColumnInfo;
import jdbc.TableInfo;
import other.Utils;
import template.DAO;

public class JavaDAO implements DAO {

	@Override
	public String getClassImport(List<String> className) {
		StringBuilder result = new StringBuilder();
		
		result.append("import java.sql.*;\r\n");
		result.append("import java.util.*;\r\n");
		
		for (String temp: className){
			result.append(temp + ";\r\n");
		}
		
		return result.toString();
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
	public String getInsertMethod(String pojoName, TableInfo tableInfo) {
		StringBuilder result    = new StringBuilder();
		StringBuilder column    = new StringBuilder();
		StringBuilder column2   = new StringBuilder();
		StringBuilder prepared  = new StringBuilder();
		String sql = "";
		String autoIncrementName = "";
		boolean isAutoIncrement = false;
		int autoIncrementType = 0;
		
		int i = 1;
		int size = tableInfo.getColumns().size();
		
		for (ColumnInfo columnInfo: tableInfo.getColumns()){
			if (!columnInfo.isAutoIncrement()){
				column.append("`" + columnInfo.getColumnName() + "`");
				column2.append("?");
			} else if(columnInfo.isPrimaryKey()) {
				autoIncrementName = columnInfo.getColumnName();
				isAutoIncrement = true;
				autoIncrementType = columnInfo.getType();
				size--;
				continue;
			}
			
			if (i < size){
				column.append(",");
				column2.append(",");
			}
			
			switch (columnInfo.getType()){
			case Types.TINYINT:
				prepared.append("\t\t\t");
				prepared.append("Byte " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.TINYINT); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setByte(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;

			case Types.SMALLINT:
				prepared.append("\t\t\t");
				prepared.append("Short " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.SMALLINT); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setShort(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.INTEGER:
				prepared.append("\t\t\t");
				prepared.append("Integer " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.INTEGER); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setInt(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.BIGINT:
				prepared.append("\t\t\t");
				prepared.append("Long " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.BIGINT); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setLong(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.FLOAT:
				prepared.append("\t\t\t");
				prepared.append("Float " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.FLOAT); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setFloat(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.DOUBLE:
				prepared.append("\t\t\t");
				prepared.append("Double " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.DOUBLE); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setDouble(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.VARCHAR:
				prepared.append("\t\t\t");
				prepared.append("String " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.VARCHAR); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.LONGVARCHAR:
				prepared.append("\t\t\t");
				prepared.append("String " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.VARCHAR); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.CHAR:
				prepared.append("\t\t\t");
				prepared.append("String " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.CHAR); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.DATE:
				prepared.append("\t\t\t");
				prepared.append("String " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.DATE); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.TIME:
				prepared.append("\t\t\t");
				prepared.append("String " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.TIME); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.TIMESTAMP:
				prepared.append("\t\t\t");
				prepared.append("String " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.VARCHAR); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
			}
			
			i++;
		}
		
		sql = "INSERT INTO " + tableInfo.getTableName() + "(" + column.toString() + ") VALUES(" + column2.toString() + ");";
		
		result.append("public boolean insert(" + pojoName + " pojo, Connection conn){ \r\n");
		
			result.append("\t\t");
			result.append("String sql = \"" + sql + "\"; \r\n\r\n");
			result.append("\t\t");
			
			result.append("try{ \r\n");
				result.append("\t\t\t");
				
				if (isAutoIncrement){
					result.append("ResultSet generatedKeys = null; \r\n");
					result.append("\t\t\t");
					result.append("PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); \r\n\r\n");
				} else {
					result.append("PreparedStatement preparedStatement = conn.prepareStatement(sql); \r\n\r\n");
				}
				
				result.append(prepared.toString() + "\r\n");
				
				result.append("\t\t\t");
				result.append("if (preparedStatement.executeUpdate() > 0){ \r\n");
				
					result.append("\t\t\t\t");
					if (isAutoIncrement){
						result.append("generatedKeys = preparedStatement.getGeneratedKeys(); \r\n");
						result.append("\t\t\t\t");
						result.append("generatedKeys.next(); \r\n");
						result.append("\t\t\t\t");
						
						switch (autoIncrementType){
						case Types.TINYINT:
							result.append("pojo.set" + Utils.formatFileName(autoIncrementName) + "(generatedKeys.getByte(1)); \r\n");
							break;
							
						case Types.SMALLINT:
							result.append("pojo.set" + Utils.formatFileName(autoIncrementName) + "(generatedKeys.getShort(1)); \r\n");							
							break;
							
						case Types.INTEGER:
							result.append("pojo.set" + Utils.formatFileName(autoIncrementName) + "(generatedKeys.getInt(1)); \r\n");
							break;
							
						case Types.BIGINT:
							result.append("pojo.set" + Utils.formatFileName(autoIncrementName) + "(generatedKeys.getLong(1)); \r\n");
							break;
						}	
						
						result.append("\t\t\t\t");
					}
					
					result.append("return true; \r\n");
				
				result.append("\t\t\t");
				result.append("} else { \r\n");
					
					result.append("\t\t\t\t");
					result.append("return false; \r\n");
					
				result.append("\t\t\t");
				result.append("}\r\n");
				
			result.append("\t\t");
			result.append("} catch (Exception e) { \r\n");
			
				result.append("\t\t\t");
				result.append("System.out.println(e.getMessage()); \r\n");
				result.append("\t\t\t");
				result.append("return false; \r\n");
				
			result.append("\t\t");
			result.append("} \r\n");
		
		result.append("\t");
		result.append("}");
		
		return result.toString();
	}

	@Override
	public String getUpdateMethod(String pojoName, TableInfo tableInfo) {
		StringBuilder result    = new StringBuilder();
		StringBuilder column    = new StringBuilder();
		StringBuilder column2   = new StringBuilder();
		StringBuilder prepared  = new StringBuilder();
		String sql = "";
		List<String> primaryKeyName  = new ArrayList<String>();
		List<Integer> primaryKeyType = new ArrayList<Integer>();
		
		int i = 1;
		
		for (ColumnInfo columnInfo: tableInfo.getColumns()){
			if (columnInfo.isPrimaryKey()){
				primaryKeyName.add(columnInfo.getColumnName());
				primaryKeyType.add(columnInfo.getType());
				continue;
			}
			
			if (i > 1) column.append(", ");
			column.append("`" + columnInfo.getColumnName() + "` = ?");
			
			switch (columnInfo.getType()){
			case Types.TINYINT:
				prepared.append("\t\t\t");
				prepared.append("Byte " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.TINYINT); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setByte(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;

			case Types.SMALLINT:
				prepared.append("\t\t\t");
				prepared.append("Short " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.SMALLINT); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setShort(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.INTEGER:
				prepared.append("\t\t\t");
				prepared.append("Integer " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.INTEGER); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setInt(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.BIGINT:
				prepared.append("\t\t\t");
				prepared.append("Long " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.BIGINT); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setLong(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.FLOAT:
				prepared.append("\t\t\t");
				prepared.append("Float " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.FLOAT); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setFloat(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.DOUBLE:
				prepared.append("\t\t\t");
				prepared.append("Double " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.DOUBLE); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setDouble(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.VARCHAR:
				prepared.append("\t\t\t");
				prepared.append("String " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.VARCHAR); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.LONGVARCHAR:
				prepared.append("\t\t\t");
				prepared.append("String " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.VARCHAR); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.CHAR:
				prepared.append("\t\t\t");
				prepared.append("String " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.CHAR); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.DATE:
				prepared.append("\t\t\t");
				prepared.append("String " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.DATE); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.TIME:
				prepared.append("\t\t\t");
				prepared.append("String " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.TIME); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.TIMESTAMP:
				prepared.append("\t\t\t");
				prepared.append("String " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" == null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.VARCHAR); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} else { \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
			}
			
			i++;
		}		
		
		if (primaryKeyName.size() > 0){
			for (int j = 0, len = primaryKeyType.size(); j < len; j++){
				if (column2.length() > 0) column2.append(" AND ");
				column2.append("`" + primaryKeyName.get(j) + "` = ?");
				
				switch (primaryKeyType.get(j)){
				case Types.TINYINT:
					prepared.append("\t\t\t");
					prepared.append("Byte " + Utils.formatVariableName(primaryKeyName.get(j)) + " = pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "(); \r\n");
					prepared.append("\t\t\t");
					prepared.append("if (" + Utils.formatVariableName(primaryKeyName.get(j)) +" == null){ \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.TINYINT); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} else { \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setByte(" + i +", pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "()); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} \r\n");
					break;

				case Types.SMALLINT:
					prepared.append("\t\t\t");
					prepared.append("Short " + Utils.formatVariableName(primaryKeyName.get(j)) + " = pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "(); \r\n");
					prepared.append("\t\t\t");
					prepared.append("if (" + Utils.formatVariableName(primaryKeyName.get(j)) +" == null){ \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.SMALLINT); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} else { \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setShort(" + i +", pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "()); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} \r\n");
					break;
					
				case Types.INTEGER:
					prepared.append("\t\t\t");
					prepared.append("Integer " + Utils.formatVariableName(primaryKeyName.get(j)) + " = pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "(); \r\n");
					prepared.append("\t\t\t");
					prepared.append("if (" + Utils.formatVariableName(primaryKeyName.get(j)) +" == null){ \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.INTEGER); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} else { \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setInt(" + i +", pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "()); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} \r\n");
					break;
					
				case Types.BIGINT:
					prepared.append("\t\t\t");
					prepared.append("Long " + Utils.formatVariableName(primaryKeyName.get(j)) + " = pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "(); \r\n");
					prepared.append("\t\t\t");
					prepared.append("if (" + Utils.formatVariableName(primaryKeyName.get(j)) +" == null){ \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.BIGINT); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} else { \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setLong(" + i +", pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "()); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} \r\n");
					break;
					
				case Types.FLOAT:
					prepared.append("\t\t\t");
					prepared.append("Float " + Utils.formatVariableName(primaryKeyName.get(j)) + " = pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "(); \r\n");
					prepared.append("\t\t\t");
					prepared.append("if (" + Utils.formatVariableName(primaryKeyName.get(j)) +" == null){ \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.FLOAT); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} else { \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setFloat(" + i +", pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "()); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} \r\n");
					break;
					
				case Types.DOUBLE:
					prepared.append("\t\t\t");
					prepared.append("Double " + Utils.formatVariableName(primaryKeyName.get(j)) + " = pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "(); \r\n");
					prepared.append("\t\t\t");
					prepared.append("if (" + Utils.formatVariableName(primaryKeyName.get(j)) +" == null){ \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.DOUBLE); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} else { \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setDouble(" + i +", pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "()); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} \r\n");
					break;
					
				case Types.VARCHAR:
					prepared.append("\t\t\t");
					prepared.append("String " + Utils.formatVariableName(primaryKeyName.get(j)) + " = pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "(); \r\n");
					prepared.append("\t\t\t");
					prepared.append("if (" + Utils.formatVariableName(primaryKeyName.get(j)) +" == null){ \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.VARCHAR); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} else { \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "()); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} \r\n");
					break;
					
				case Types.LONGVARCHAR:
					prepared.append("\t\t\t");
					prepared.append("String " + Utils.formatVariableName(primaryKeyName.get(j)) + " = pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "(); \r\n");
					prepared.append("\t\t\t");
					prepared.append("if (" + Utils.formatVariableName(primaryKeyName.get(j)) +" == null){ \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.VARCHAR); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} else { \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "()); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} \r\n");
					break;
					
				case Types.CHAR:
					prepared.append("\t\t\t");
					prepared.append("String " + Utils.formatVariableName(primaryKeyName.get(j)) + " = pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "(); \r\n");
					prepared.append("\t\t\t");
					prepared.append("if (" + Utils.formatVariableName(primaryKeyName.get(j)) +" == null){ \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.CHAR); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} else { \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "()); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} \r\n");
					break;
					
				case Types.DATE:
					prepared.append("\t\t\t");
					prepared.append("String " + Utils.formatVariableName(primaryKeyName.get(j)) + " = pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "(); \r\n");
					prepared.append("\t\t\t");
					prepared.append("if (" + Utils.formatVariableName(primaryKeyName.get(j)) +" == null){ \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.DATE); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} else { \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "()); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} \r\n");
					break;
					
				case Types.TIME:
					prepared.append("\t\t\t");
					prepared.append("String " + Utils.formatVariableName(primaryKeyName.get(j)) + " = pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "(); \r\n");
					prepared.append("\t\t\t");
					prepared.append("if (" + Utils.formatVariableName(primaryKeyName.get(j)) +" == null){ \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.TIME); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} else { \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "()); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} \r\n");
					break;
					
				case Types.TIMESTAMP:
					prepared.append("\t\t\t");
					prepared.append("String " + Utils.formatVariableName(primaryKeyName.get(j)) + " = pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "(); \r\n");
					prepared.append("\t\t\t");
					prepared.append("if (" + Utils.formatVariableName(primaryKeyName.get(j)) +" == null){ \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setNull(" + i +", java.sql.Types.VARCHAR); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} else { \r\n");
					prepared.append("\t\t\t\t");
					prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(primaryKeyName.get(j)) + "()); \r\n");
					prepared.append("\t\t\t");
					prepared.append("} \r\n");
					break;
				}
				
				i++;
			}
			
			sql = "UPDATE " + tableInfo.getTableName() + " SET " + column.toString() + " WHERE " + column2.toString() + ";";
		} else {
			sql = "UPDATE " + tableInfo.getTableName() + " SET " + column.toString() + ";";
		}		
		
		result.append("public boolean update(" + pojoName + " pojo, Connection conn){ \r\n");
		
			result.append("\t\t");
			result.append("String sql = \"" + sql + "\"; \r\n\r\n");
			result.append("\t\t");
			
			result.append("try{ \r\n");
				result.append("\t\t\t");
				
				result.append("PreparedStatement preparedStatement = conn.prepareStatement(sql); \r\n\r\n");
				
				result.append(prepared.toString() + "\r\n");
				
				result.append("\t\t\t");
				result.append("if (preparedStatement.executeUpdate() > 0){ \r\n");
				
					result.append("\t\t\t\t");
					result.append("return true; \r\n");
				
				result.append("\t\t\t");
				result.append("} else { \r\n");
					
					result.append("\t\t\t\t");
					result.append("return false; \r\n");
					
				result.append("\t\t\t");
				result.append("}\r\n");
				
			result.append("\t\t");
			result.append("} catch (Exception e) { \r\n");
			
				result.append("\t\t\t");
				result.append("System.out.println(e.getMessage()); \r\n");
				result.append("\t\t\t");
				result.append("return false; \r\n");
				
			result.append("\t\t");
			result.append("} \r\n");
		
		result.append("\t");
		result.append("}");
		
		return result.toString();
	}

	@Override
	public String getDeleteMethod(String pojoName, TableInfo tableInfo) {
		StringBuilder result   = new StringBuilder();
		StringBuilder column   = new StringBuilder();
		StringBuilder prepared = new StringBuilder();
		String sql = "";
		
		int i = 1;
		
		for (ColumnInfo columnInfo: tableInfo.getColumns()){
			if (!columnInfo.isPrimaryKey() && !columnInfo.isImportedKey()) continue;

			if (column.length() > 0) column.append(" AND ");
			column.append("`" + columnInfo.getColumnName() + "` = ?");
			
			switch (columnInfo.getType()){
			case Types.TINYINT:
				prepared.append("\t\t\t");
				prepared.append("if (pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "() != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setByte(" + i++ +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;

			case Types.SMALLINT:
				prepared.append("\t\t\t");
				prepared.append("if (pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "() != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setShort(" + i++ +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.INTEGER:				
				prepared.append("\t\t\t");
				prepared.append("if (pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "() != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setInt(" + i++ +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.BIGINT:
				prepared.append("\t\t\t");
				prepared.append("if (pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "() != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setLong(" + i++ +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.FLOAT:
				prepared.append("\t\t\t");
				prepared.append("if (pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "() != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setFloat(" + i++ +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.DOUBLE:
				prepared.append("\t\t\t");
				prepared.append("if (pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "() != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setDouble(" + i++ +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.VARCHAR:
				prepared.append("\t\t\t");
				prepared.append("if (pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "() != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(" + i++ +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.LONGVARCHAR:
				prepared.append("\t\t\t");
				prepared.append("if (pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "() != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(" + i++ +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.CHAR:
				prepared.append("\t\t\t");
				prepared.append("if (pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "() != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(" + i++ +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.DATE:
				prepared.append("\t\t\t");
				prepared.append("if (pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "() != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(" + i++ +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.TIME:
				prepared.append("\t\t\t");
				prepared.append("if (pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "() != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(" + i++ +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
				
			case Types.TIMESTAMP:
				prepared.append("\t\t\t");
				prepared.append("if (pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "() != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(" + i++ +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				break;
			}
		}
		
		if (column.length() > 0){
			sql = "DELETE FROM " + tableInfo.getTableName() + " WHERE " + column.toString() + ";";
		} else {
			sql = "DELETE FROM " + tableInfo.getTableName() + ";";
		}

		result.append("public boolean delete(" + pojoName + " pojo, Connection conn){ \r\n");
		
			result.append("\t\t");
			result.append("String sql = \"" + sql + "\"; \r\n\r\n");
			result.append("\t\t");
			
			result.append("try{ \r\n");
				result.append("\t\t\t");
				
				result.append("PreparedStatement preparedStatement = conn.prepareStatement(sql); \r\n\r\n");
				
				result.append(prepared.toString() + "\r\n");
				
				result.append("\t\t\t");
				result.append("if (preparedStatement.executeUpdate() > 0){ \r\n");
				
					result.append("\t\t\t\t");
					result.append("return true; \r\n");
				
				result.append("\t\t\t");
				result.append("} else { \r\n");
					
					result.append("\t\t\t\t");
					result.append("return false; \r\n");
					
				result.append("\t\t\t");
				result.append("}\r\n");
				
			result.append("\t\t");
			result.append("} catch (Exception e) { \r\n");
			
				result.append("\t\t\t");
				result.append("System.out.println(e.getMessage()); \r\n");
				result.append("\t\t\t");
				result.append("return false; \r\n");
				
			result.append("\t\t");
			result.append("} \r\n");
		
		result.append("\t");
		result.append("}");
		
		return result.toString();
	}

	@Override
	public String getSearchMethod(String pojoName, TableInfo tableInfo) {
		StringBuilder result   = new StringBuilder();
		StringBuilder column   = new StringBuilder();
		StringBuilder prepared = new StringBuilder();
		StringBuilder setters  = new StringBuilder();
		String sql = "";
		
		int i = 1;
		
		for (ColumnInfo columnInfo: tableInfo.getColumns()){			
			switch (columnInfo.getType()){
			case Types.TINYINT:
				column.append("\t\t\t");
				column.append("Byte " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				column.append("\t\t\t");
				column.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				column.append("\t\t\t\t");
				column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` = ?\"); \r\n");
				column.append("\t\t\t");
				column.append("} \r\n");
				
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setByte(i++, pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				
				setters.append("\t\t\t\t");
				setters.append("obj.set" + Utils.formatFileName(columnInfo.getColumnName()) + "(rs.getByte(\"" + columnInfo.getColumnName() + "\")); \r\n");
				break;

			case Types.SMALLINT:
				column.append("\t\t\t");
				column.append("Short " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				column.append("\t\t\t");
				column.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				column.append("\t\t\t\t");
				column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` = ?\"); \r\n");
				column.append("\t\t\t");
				column.append("} \r\n");
				
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setShort(i++, pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				
				setters.append("\t\t\t\t");
				setters.append("obj.set" + Utils.formatFileName(columnInfo.getColumnName()) + "(rs.getShort(\"" + columnInfo.getColumnName() + "\")); \r\n");
				break;
				
			case Types.INTEGER:
				column.append("\t\t\t");
				column.append("Integer " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				column.append("\t\t\t");
				column.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				column.append("\t\t\t\t");
				column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` = ?\"); \r\n");
				column.append("\t\t\t");
				column.append("} \r\n");
				
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setInt(i++, pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				
				setters.append("\t\t\t\t");
				setters.append("obj.set" + Utils.formatFileName(columnInfo.getColumnName()) + "(rs.getInt(\"" + columnInfo.getColumnName() + "\")); \r\n");
				break;
				
			case Types.BIGINT:
				column.append("\t\t\t");
				column.append("Long " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				column.append("\t\t\t");
				column.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				column.append("\t\t\t\t");
				column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` = ?\"); \r\n");
				column.append("\t\t\t");
				column.append("} \r\n");
				
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setLong(i++, pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				
				setters.append("\t\t\t\t");
				setters.append("obj.set" + Utils.formatFileName(columnInfo.getColumnName()) + "(\"rs.getLong(" + columnInfo.getColumnName() + "\")); \r\n");
				break;
				
			case Types.FLOAT:
				column.append("\t\t\t");
				column.append("Float " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				column.append("\t\t\t");
				column.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				column.append("\t\t\t\t");
				column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` = ?\"); \r\n");
				column.append("\t\t\t");
				column.append("} \r\n");
				
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setFloat(i++, pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				
				setters.append("\t\t\t\t");
				setters.append("obj.set" + Utils.formatFileName(columnInfo.getColumnName()) + "(rs.getFloat(\"" + columnInfo.getColumnName() + "\")); \r\n");
				break;
				
			case Types.DOUBLE:
				column.append("\t\t\t");
				column.append("Double " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				column.append("\t\t\t");
				column.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				column.append("\t\t\t\t");
				column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` = ?\"); \r\n");
				column.append("\t\t\t");
				column.append("} \r\n");
				
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setDouble(i++, pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				
				setters.append("\t\t\t\t");
				setters.append("obj.set" + Utils.formatFileName(columnInfo.getColumnName()) + "(rs.getDouble(\"" + columnInfo.getColumnName() + "\")); \r\n");
				break;
				
			case Types.VARCHAR:
				column.append("\t\t\t");
				column.append("String " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				column.append("\t\t\t");
				column.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				column.append("\t\t\t\t");
				column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` = ?\"); \r\n");
				column.append("\t\t\t");
				column.append("} \r\n");
				
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(i++, pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				
				setters.append("\t\t\t\t");
				setters.append("obj.set" + Utils.formatFileName(columnInfo.getColumnName()) + "(rs.getString(\"" + columnInfo.getColumnName() + "\")); \r\n");
				break;
				
			case Types.LONGVARCHAR:
				column.append("\t\t\t");
				column.append("String " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				column.append("\t\t\t");
				column.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				column.append("\t\t\t\t");
				column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` = ?\"); \r\n");
				column.append("\t\t\t");
				column.append("} \r\n");
				
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(i++, pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				
				setters.append("\t\t\t\t");
				setters.append("obj.set" + Utils.formatFileName(columnInfo.getColumnName()) + "(rs.getString(\"" + columnInfo.getColumnName() + "\")); \r\n");
				break;
				
			case Types.CHAR:
				column.append("\t\t\t");
				column.append("String " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				column.append("\t\t\t");
				column.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				column.append("\t\t\t\t");
				column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` = ?\"); \r\n");
				column.append("\t\t\t");
				column.append("} \r\n");
				
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(i++, pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				
				setters.append("\t\t\t\t");
				setters.append("obj.set" + Utils.formatFileName(columnInfo.getColumnName()) + "(rs.getString(\"" + columnInfo.getColumnName() + "\")); \r\n");
				break;
				
			case Types.DATE:
				column.append("\t\t\t");
				column.append("String " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				column.append("\t\t\t");
				column.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				column.append("\t\t\t\t");
				column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` = ?\"); \r\n");
				column.append("\t\t\t");
				column.append("} \r\n");
				
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(i++, pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				
				setters.append("\t\t\t\t");
				setters.append("obj.set" + Utils.formatFileName(columnInfo.getColumnName()) + "(rs.getString(\"" + columnInfo.getColumnName() + "\")); \r\n");
				break;
				
			case Types.TIME:
				column.append("\t\t\t");
				column.append("String " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				column.append("\t\t\t");
				column.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				column.append("\t\t\t\t");
				column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` = ?\"); \r\n");
				column.append("\t\t\t");
				column.append("} \r\n");
				
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(i++, pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				
				setters.append("\t\t\t\t");
				setters.append("obj.set" + Utils.formatFileName(columnInfo.getColumnName()) + "(rs.getString(\"" + columnInfo.getColumnName() + "\")); \r\n");
				break;
				
			case Types.TIMESTAMP:
				column.append("\t\t\t");
				column.append("String " + Utils.formatVariableName(columnInfo.getColumnName()) + " = pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "(); \r\n");
				column.append("\t\t\t");
				column.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				column.append("\t\t\t\t");
				column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` = ?\"); \r\n");
				column.append("\t\t\t");
				column.append("} \r\n");
				
				prepared.append("\t\t\t");
				prepared.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +" != null){ \r\n");
				prepared.append("\t\t\t\t");
				prepared.append("preparedStatement.setString(i++, pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				prepared.append("\t\t\t");
				prepared.append("} \r\n");
				
				setters.append("\t\t\t\t");
				setters.append("obj.set" + Utils.formatFileName(columnInfo.getColumnName()) + "(rs.getString(\"" + columnInfo.getColumnName() + "\")); \r\n");
				break;
			}
			
			i++;
		}		
		
		sql = "SELECT * FROM " + tableInfo.getTableName() + " SET " + column.toString() + ";";	
		
		result.append("public List<" + pojoName + "> search(" + pojoName + " pojo, Connection conn){ \r\n");
			
			result.append("\t\t");
			result.append("int i = 1; \r\n");
			result.append("\t\t");
			result.append("StringBuilder sql = new StringBuilder(); \r\n");
			result.append("\t\t");
			result.append("List<" + pojoName + "> result = new ArrayList<" + pojoName + ">(); \r\n");
			result.append("\t\t");
			
			result.append("try{ \r\n");
				
				result.append("\t\t\t");
				result.append("sql.append(\"SELECT * FROM " + tableInfo.getTableName() + " WHERE 1=1 \");\r\n");
				result.append(column.toString() + "\r\n");
				
				result.append("\t\t\t");
				result.append("PreparedStatement preparedStatement = conn.prepareStatement(sql.toString()); \r\n\r\n");
				
				result.append(prepared.toString() + "\r\n");
				
				result.append("\t\t\t");
				result.append("ResultSet rs = preparedStatement.executeQuery(); \r\n");
				
				result.append("\t\t\t");
				result.append("while(rs.next()){ \r\n");
				
					result.append("\t\t\t\t");
					result.append(pojoName + " obj = new " + pojoName + "(); \r\n");
					
					result.append(setters.toString());
					
					result.append("\t\t\t\t");
					result.append("result.add(obj); \r\n");
							
				result.append("\t\t\t");;
				result.append("}\r\n");
				
			result.append("\t\t");
			result.append("} catch (Exception e) { \r\n");
			
				result.append("\t\t\t");
				result.append("System.out.println(e.getMessage()); \r\n");
				
			result.append("\t\t");
			result.append("} \r\n");
			
			result.append("\t\t");
			result.append("return result; \r\n");
		
		result.append("\t");
		result.append("}");
		
		return result.toString();
	}

	@Override
	public String getRelationalSearchMethod(String pojoName, TableInfo tableInfo, String pojoSuffix, String daoSuffix) {
		StringBuilder result   = new StringBuilder();
		StringBuilder column   = new StringBuilder();
		StringBuilder prepared = new StringBuilder();
		StringBuilder setters  = new StringBuilder();
		
		result.append("public List<" + pojoName + "> relationalSearch(" + pojoName + " pojo, Connection conn){ \r\n");
			
			result.append("\t\t");
			result.append("boolean firstSearch = false; \r\n");
			result.append("\t\t");
			result.append("List<" + pojoName + "> result = new ArrayList<" + pojoName + ">(); \r\n\r\n");
			
			if (tableInfo.getExportedCount() == 0){
				result.append("\t\t");
				result.append("result = search(pojo, conn); \r\n");
			} else{
				for (ColumnInfo columnInfo: tableInfo.getColumns()){
					result.append("\t\t");
					result.append("if (pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "() != null) firstSearch = true; \r\n");
				}
				result.append("\r\n");
				result.append("\t\t");
				result.append("if (firstSearch){ \r\n");
				
					result.append("\t\t\t");
					result.append("result = search(pojo, conn); \r\n");
					result.append("\t\t\t");
					result.append("for (" + pojoName + " pojo2: result){ \r\n");
						
						int i = 1;
						for (ColumnInfo columnInfo: tableInfo.getExportedColumns()){
							String name = Utils.formatFileName(columnInfo.getTableName());
							
							result.append("\t\t\t\t");
							result.append(name + daoSuffix + " dao" + i + " = new " + name + daoSuffix + "(); \r\n");
							result.append("\t\t\t\t");
							result.append("for (" + name + pojoSuffix + " pojo3 :pojo.get" + name + "()){ \r\n");
								
								result.append("\t\t\t\t\t");
								result.append("pojo3.set" + Utils.formatFileName(columnInfo.getColumnName()) + "(pojo2.get" + Utils.formatFileName(columnInfo.getExportedColumnName()) + "()); \r\n");
								result.append("\t\t\t\t\t");
								result.append("pojo2.set" + name + "(dao" + (i++) + ".relationalSearch(pojo3, conn)); \r\n");
							
							result.append("\t\t\t\t");
							result.append("} \r\n");
						}
					
					result.append("\t\t\t");
					result.append("} \r\n");
					
				result.append("\t\t");
				result.append("} \r\n");
			}

			result.append("\t\t");
			result.append("return result; \r\n");
		
		result.append("\t");
		result.append("}");
		
		return result.toString();
	}

	@Override
	public String getListMethod(String pojoName, TableInfo tableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkInsertPOJO(String pojoName, TableInfo tableInfo) {
		StringBuilder result = new StringBuilder();
		
		result.append("public String checkInsertPOJO(" + pojoName + " pojo){ \r\n");
		result.append("\t\t");
		result.append("String result = \"\"; \r\n");
		
		for (ColumnInfo columnInfo: tableInfo.getColumns()){
			if (columnInfo.isNullable() == false && columnInfo.isAutoIncrement() == false){
				result.append("\t\t");
				result.append("if (pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "() == null) "
						+ "result = \"The " + Utils.formatVariableName(columnInfo.getColumnName()) + " Is Null\"; \r\n");
			}
		}
		
		result.append("\t\t");
		result.append("return result; \r\n");
		
		result.append("\t");
		result.append("}");
		
		return result.toString();
	}

	@Override
	public String checkUpdatePOJO(String pojoName, TableInfo tableInfo) {
		StringBuilder result = new StringBuilder();
		
		result.append("public String checkUpdatePOJO(" + pojoName + " pojo){ \r\n");
		result.append("\t\t");
		result.append("String result = \"\"; \r\n");
		
		for (ColumnInfo columnInfo: tableInfo.getColumns()){
			if (columnInfo.isNullable() == false){
				result.append("\t\t");
				result.append("if (pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "() == null) "
						+ "result = \"The " + Utils.formatVariableName(columnInfo.getColumnName()) + " Is Null\"; \r\n");
			}
		}
		
		result.append("\t\t");
		result.append("return result; \r\n");
		
		result.append("\t");
		result.append("}");
		
		return result.toString();
	}

	@Override
	public String checkDeletePOJO(String pojoName, TableInfo tableInfo) {
		StringBuilder result = new StringBuilder();
		
		result.append("public String checkDeletePOJO(" + pojoName + " pojo){ \r\n");
		result.append("\t\t");
		result.append("String  result = \"\"; \r\n");
		result.append("\t\t");
		result.append("boolean hasImported = false; \r\n");
		
		for (ColumnInfo columnInfo: tableInfo.getColumns()){
			if (columnInfo.isPrimaryKey() == true){
				result.append("\t\t");
				result.append("if (hasImported != true && pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "() == null) "
						+ "result = \"The " + Utils.formatVariableName(columnInfo.getColumnName()) + " Is Null\"; \r\n");
			}
			
			if (columnInfo.isImportedKey() == true){
				result.append("\t\t");
				result.append("if (pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "() != null){ \r\n");
				result.append("\t\t\t");
				result.append("result = \"\"; \r\n");
				result.append("\t\t\t");
				result.append("hasImported = true; \r\n");
				result.append("\t\t");
				result.append("}");
			}
		}
		
		result.append("\t\t");
		result.append("return result; \r\n");
		
		result.append("\t");
		result.append("}");
		
		return result.toString();
	}

	@Override
	public String checkSearchPOJO(String pojoName, TableInfo tableInfo) {
		StringBuilder result = new StringBuilder();
		
		result.append("public String checkSearchPOJO(" + pojoName + " pojo){ \r\n");
		result.append("\t\t");
		result.append("String result = \"\"; \r\n");
		
		result.append("\t\t");
		result.append("return result; \r\n");
		
		result.append("\t");
		result.append("}");
		
		return result.toString();
	}

	@Override
	public String getClassFooter() {
		return "}";
	}

}
