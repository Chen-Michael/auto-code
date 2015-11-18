package template.java;

import java.sql.Types;

import jdbc.ColumnInfo;
import jdbc.TableInfo;
import other.Utils;

public class JavaFuzzySearchDAO extends JavaDAO {

	public JavaFuzzySearchDAO() {
		
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
				setters.append("obj.set" + Utils.formatFileName(columnInfo.getColumnName()) + "(rs.getLong(\"" + columnInfo.getColumnName() + "\")); \r\n");
				break;
				
			case Types.REAL:
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
					column.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +".indexOf(\"%\") == 0){ \r\n");
						column.append("\t\t\t\t\t");
						column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` LIKE ?\"); \r\n");
					column.append("\t\t\t\t");
					column.append("} else{ \r\n");
						column.append("\t\t\t\t\t");
						column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` = ?\"); \r\n");
					column.append("\t\t\t\t");
					column.append("} \r\n");
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
					column.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +".indexOf(\"%\") == 0){ \r\n");
						column.append("\t\t\t\t\t");
						column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` LIKE ?\"); \r\n");
					column.append("\t\t\t\t");
					column.append("} else{ \r\n");
						column.append("\t\t\t\t\t");
						column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` = ?\"); \r\n");
					column.append("\t\t\t\t");
					column.append("} \r\n");
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
					column.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +".indexOf(\"%\") == 0){ \r\n");
						column.append("\t\t\t\t\t");
						column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` LIKE ?\"); \r\n");
					column.append("\t\t\t\t");
					column.append("} else{ \r\n");
						column.append("\t\t\t\t\t");
						column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` = ?\"); \r\n");
					column.append("\t\t\t\t");
					column.append("} \r\n");
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
					column.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +".indexOf(\"%\") == 0){ \r\n");
						column.append("\t\t\t\t\t");
						column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` LIKE ?\"); \r\n");
					column.append("\t\t\t\t");
					column.append("} else{ \r\n");
						column.append("\t\t\t\t\t");
						column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` = ?\"); \r\n");
					column.append("\t\t\t\t");
					column.append("} \r\n");
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
					column.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +".indexOf(\"%\") == 0){ \r\n");
						column.append("\t\t\t\t\t");
						column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` LIKE ?\"); \r\n");
					column.append("\t\t\t\t");
					column.append("} else{ \r\n");
						column.append("\t\t\t\t\t");
						column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` = ?\"); \r\n");
					column.append("\t\t\t\t");
					column.append("} \r\n");
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
					column.append("if (" + Utils.formatVariableName(columnInfo.getColumnName()) +".indexOf(\"%\") == 0){ \r\n");
						column.append("\t\t\t\t\t");
						column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` LIKE ?\"); \r\n");
					column.append("\t\t\t\t");
					column.append("} else{ \r\n");
						column.append("\t\t\t\t\t");
						column.append("sql.append(\" AND `" + columnInfo.getColumnName() + "` = ?\"); \r\n");
					column.append("\t\t\t\t");
					column.append("} \r\n");
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
}
