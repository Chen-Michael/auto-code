package template;

import java.sql.Types;

import jdbc.ColumnInfo;
import jdbc.TableInfo;
import other.Utils;

public class Template {
	public static String getPOJO(POJO pojo, String suffix, TableInfo tableInfo){
		StringBuilder result = new StringBuilder();
		
		result.append(pojo.getClassHeader(tableInfo.getTableName() + suffix) + "\r\n");
		
		for (ColumnInfo columnInfo: tableInfo.getColumns()){
			switch (columnInfo.getType()){
			case Types.TINYINT:
				result.append("\t" + pojo.getByteVariable(columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getByteSetters (columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getByteGetters (columnInfo.getColumnName()) + "\r\n\r\n");
				break;
				
			case Types.SMALLINT:
				result.append("\t" + pojo.getShortVariable(columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getShortSetters (columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getShortGetters (columnInfo.getColumnName()) + "\r\n\r\n");
				break;
				
			case Types.INTEGER:
				result.append("\t" + pojo.getIntVariable(columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getIntSetters (columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getIntGetters (columnInfo.getColumnName()) + "\r\n\r\n");
				break;
				
			case Types.BIGINT:
				result.append("\t" + pojo.getLongVariable(columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getLongSetters (columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getLongGetters (columnInfo.getColumnName()) + "\r\n\r\n");
				break;
				
			case Types.FLOAT:
				result.append("\t" + pojo.getFloatVariable(columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getFloatSetters (columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getFloatGetters (columnInfo.getColumnName()) + "\r\n\r\n");
				break;
				
			case Types.DOUBLE:
				result.append("\t" + pojo.getDoubleVariable(columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getDoubleSetters (columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getDoubleGetters (columnInfo.getColumnName()) + "\r\n\r\n");
				break;
				
			case Types.VARCHAR:
				result.append("\t" + pojo.getStringVariable(columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getStringSetters (columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getStringGetters (columnInfo.getColumnName()) + "\r\n\r\n");
				break;
				
			case Types.CHAR:
				result.append("\t" + pojo.getStringVariable(columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getStringSetters (columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getStringGetters (columnInfo.getColumnName()) + "\r\n\r\n");
				break;
				
			case Types.DATE:
				result.append("\t" + pojo.getStringVariable(columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getStringSetters (columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getStringGetters (columnInfo.getColumnName()) + "\r\n\r\n");
				break;
				
			case Types.TIME:
				result.append("\t" + pojo.getStringVariable(columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getStringSetters (columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getStringGetters (columnInfo.getColumnName()) + "\r\n\r\n");
				break;
				
			case Types.TIMESTAMP:
				result.append("\t" + pojo.getLongVariable(columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getLongSetters (columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getLongGetters (columnInfo.getColumnName()) + "\r\n\r\n");
				break;
			}
		}
		
		result.append(pojo.getClassFooter());
		
		return result.toString();
	}
	
	public static String getDAO(){
		StringBuilder result = new StringBuilder();
		
		return result.toString();
	}
	
	public static String getAPI(){
		StringBuilder result = new StringBuilder();
		
		return result.toString();
	}
}
