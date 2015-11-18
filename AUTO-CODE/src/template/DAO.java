package template;

import java.util.List;

import jdbc.TableInfo;

public interface DAO {
	public String getClassImport      (List<String> importClass);
	
	public String getClassHeader      (String className);
	public String getClassConstructor (String className);
	public String getVariable         (List<String> variables);
	
	public String getInsertMethod     (String pojoName, TableInfo tableInfo);
	public String getUpdateMethod     (String pojoName, TableInfo tableInfo);
	public String getDeleteMethod     (String pojoName, TableInfo tableInfo);
	public String getSearchMethod     (String pojoName, TableInfo tableInfo);
	public String getOuterSearchMethod(String pojoName, TableInfo tableInfo, String pojoSuffix, String daoSuffix);
	public String getListMethod       (String pojoName, TableInfo tableInfo);
	
	public String getCheckInsertPOJO     (String pojoName, TableInfo tableInfo);
	public String getCheckUpdatePOJO     (String pojoName, TableInfo tableInfo);
	public String getCheckDeletePOJO     (String pojoName, TableInfo tableInfo);
	public String getCheckSearchPOJO     (String pojoName, TableInfo tableInfo);
	
	public String getClassFooter      ();
}
