package template.java;

import java.sql.Types;
import java.util.List;

import jdbc.ColumnInfo;
import jdbc.TableInfo;
import other.Utils;
import template.DAO;

public class JavaDAO implements DAO {

	@Override
	public String getClassImport(List<String> className) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("import java.sql.*;\r\n");
		sb.append("import java.util.*;\r\n");
		
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
	public String getInsertMethod(String pojoName, TableInfo tableInfo) {
		StringBuilder result   = new StringBuilder();
		StringBuilder column   = new StringBuilder();
		StringBuilder prepared = new StringBuilder();
		String sql = "";
		String autoIncrementName = "";
		boolean isAutoIncrement = false;
		int autoIncrementType = 0;
		
		int i = 1;
		int size = tableInfo.getColumns().size();
		
		for (ColumnInfo columnInfo: tableInfo.getColumns()){
			if (!columnInfo.isAutoIncrement()){
				column.append("?");
			} else if(columnInfo.isPrimaryKey()) {
				autoIncrementName = columnInfo.getColumnName();
				isAutoIncrement = true;
				autoIncrementType = columnInfo.getType();
				size--;
				continue;
			}
			
			if (i < size) column.append(",");
			
			switch (columnInfo.getType()){
			case Types.TINYINT:
				prepared.append("\t\t\t");
				prepared.append("preparedStatement.setByte(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				break;
				
			case Types.SMALLINT:
				prepared.append("\t\t\t");
				prepared.append("preparedStatement.setShort(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				break;
				
			case Types.INTEGER:
				prepared.append("\t\t\t");
				prepared.append("preparedStatement.setInt(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				break;
				
			case Types.BIGINT:
				prepared.append("\t\t\t");
				prepared.append("preparedStatement.setLong(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				break;
				
			case Types.FLOAT:
				prepared.append("\t\t\t");
				prepared.append("preparedStatement.setFloat(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				break;
				
			case Types.DOUBLE:
				prepared.append("\t\t\t");
				prepared.append("preparedStatement.setDouble(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				break;
				
			case Types.VARCHAR:
				prepared.append("\t\t\t");
				prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				break;
				
			case Types.CHAR:
				prepared.append("\t\t\t");
				prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				break;
				
			case Types.DATE:
				prepared.append("\t\t\t");
				prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				break;
				
			case Types.TIME:
				prepared.append("\t\t\t");
				prepared.append("preparedStatement.setString(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				break;
				
			case Types.TIMESTAMP:
				prepared.append("\t\t\t");
				prepared.append("preparedStatement.setLong(" + i +", pojo.get" + Utils.formatFileName(columnInfo.getColumnName()) + "()); \r\n");
				break;
			}
			
			i++;
		}
		
		sql = "INSERT INTO " + tableInfo.getTableName() + " VALUES(" + column.toString() + ");";
		
		result.append("public boolean insert(" + pojoName + " pojo, Connection conn){ \r\n");
		
			result.append("\t\t");
			result.append("String sql = \"" + sql + "\"; \r\n\r\n");
			result.append("\t\t");
			
			result.append("try{ \r\n");
				result.append("\t\t\t");
				
				if (isAutoIncrement){
					result.append("ResultSet generatedKeys = null; \r\n");
					result.append("\t\t\t");
					result.append("PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); \r\n");
				} else {
					result.append("PreparedStatement preparedStatement = conn.prepareStatement(sql); \r\n");
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeleteMethod(String pojoName, TableInfo tableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSearchMethod(String pojoName, TableInfo tableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getListMethod(String pojoName, TableInfo tableInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getClassFooter() {
		return "}";
	}

}
