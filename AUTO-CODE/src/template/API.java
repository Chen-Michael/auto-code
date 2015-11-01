package template;

import java.util.List;

import jdbc.TableInfo;

public interface API {
	public String getClassImport      (List<String> className);
	
	public String getClassHeader      (String className);
	public String getClassConstructor (String className);
	
	public String getDoGetMethod      (String pojoName, String daoName, TableInfo tableInfo);
	public String getDoPutMethod      (String pojoName, String daoName, TableInfo tableInfo);
	public String getDoPostMethod     (String pojoName, String daoName, TableInfo tableInfo);
	public String getDoDeleteMethod   (String pojoName, String daoName, TableInfo tableInfo);
	
	public String getPOJO             (String pojoName, TableInfo tableInfo);
	public String checkInsertPOJO     (String pojoName, TableInfo tableInfo);
	public String checkUpdatePOJO     (String pojoName, TableInfo tableInfo);
	public String checkDeletePOJO     (String pojoName, TableInfo tableInfo);
	public String checkSearchPOJO     (String pojoName, TableInfo tableInfo);
	
	public String getClassFooter      ();
}
