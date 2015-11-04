package template;

import java.sql.Types;
import java.util.List;

import jdbc.ColumnInfo;
import jdbc.TableInfo;

public class Template {
	public static String getPOJO(POJO pojo, String pojoName, TableInfo tableInfo, List<String> importClass){
		StringBuilder result = new StringBuilder();
		
		result.append(pojo.getClassImport(importClass) + "\r\n");
		result.append(pojo.getClassHeader(pojoName) + "\r\n");
		result.append("\t" + pojo.getClassConstructor(pojoName) + "\r\n");
		
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
				
			case Types.LONGVARCHAR:
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
				result.append("\t" + pojo.getStringVariable(columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getStringSetters (columnInfo.getColumnName()) + "\r\n");
				result.append("\t" + pojo.getStringGetters (columnInfo.getColumnName()) + "\r\n\r\n");
				break;
			}
		}
		
		result.append(pojo.getClassFooter());
		
		return result.toString();
	}
	
	public static String getDAO(DAO dao, String pojoName, String daoName, TableInfo tableInfo, List<String> importClass){
		StringBuilder result = new StringBuilder();
		
		result.append(dao.getClassImport(importClass) + "\r\n");
		result.append(dao.getClassHeader(daoName) + "\r\n");
		result.append("\t" + dao.getClassConstructor(daoName) + "\r\n");
		result.append("\t" + dao.getInsertMethod(pojoName, tableInfo) + "\r\n");
		result.append("\t" + dao.getUpdateMethod(pojoName, tableInfo) + "\r\n");
		result.append("\t" + dao.getDeleteMethod(pojoName, tableInfo) + "\r\n");
		result.append("\t" + dao.getSearchMethod(pojoName, tableInfo) + "\r\n");
		result.append("\t" + dao.checkInsertPOJO  (pojoName, tableInfo) + "\r\n");
		result.append("\t" + dao.checkUpdatePOJO  (pojoName, tableInfo) + "\r\n");
		result.append("\t" + dao.checkDeletePOJO  (pojoName, tableInfo) + "\r\n");
		result.append("\t" + dao.checkSearchPOJO  (pojoName, tableInfo) + "\r\n");
		result.append(dao.getClassFooter());
		
		return result.toString();
	}
	
	public static String getAPI(API api, String pojoName, String daoName, String apiName, TableInfo tableInfo, List<String> importClass){
		StringBuilder result = new StringBuilder();
		
		result.append(api.getClassImport(importClass) + "\r\n");
		result.append(api.getClassHeader(apiName) + "\r\n");
		result.append("\t" + api.getClassConstructor(apiName) + "\r\n");
		result.append("\t" + api.getDoGetMethod   (pojoName, daoName, tableInfo) + "\r\n");
		result.append("\t" + api.getDoPutMethod   (pojoName, daoName, tableInfo) + "\r\n");
		result.append("\t" + api.getDoPostMethod  (pojoName, daoName, tableInfo) + "\r\n");
		result.append("\t" + api.getDoDeleteMethod(pojoName, daoName, tableInfo) + "\r\n");
		result.append("\t" + api.getPOJO          (pojoName, tableInfo) + "\r\n");
		result.append(api.getClassFooter());
		
		return result.toString();
	}
}
