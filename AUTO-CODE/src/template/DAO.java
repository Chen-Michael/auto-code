package template;

import java.util.List;

import jdbc.TableInfo;

public interface DAO {
	public String getClassImport            (List<String> className);
	
	public String getClassHeader            (String className);
	public String getClassConstructor       (String className);
	
	public String getInsertMethod           (String pojoName, TableInfo tableInfo);
	public String getUpdateMethod           (String pojoName, TableInfo tableInfo);
	public String getDeleteMethod           (String pojoName, TableInfo tableInfo);
	public String getSearchMethod           (String pojoName, TableInfo tableInfo);
	public String getRelationalSearchMethod (String pojoName, TableInfo tableInfo, String pojoSuffix, String daoSuffix);
	public String getListMethod             (String pojoName, TableInfo tableInfo);
	
	public String checkInsertPOJO           (String pojoName, TableInfo tableInfo);
	public String checkUpdatePOJO           (String pojoName, TableInfo tableInfo);
	public String checkDeletePOJO           (String pojoName, TableInfo tableInfo);
	public String checkSearchPOJO           (String pojoName, TableInfo tableInfo);
	
	public String getClassFooter            ();
}
