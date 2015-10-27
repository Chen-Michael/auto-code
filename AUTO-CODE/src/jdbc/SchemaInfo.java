package jdbc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SchemaInfo implements Cloneable {
	private String scheamName = "";
	private List<TableInfo> tables;

	public SchemaInfo(String scheamName) {
		super();
		this.scheamName = scheamName;
	}

	public String getScheamName() {
		return scheamName;
	}

	public void setScheamName(String scheamName) {
		this.scheamName = scheamName;
	}

	public List<TableInfo> getTables() {
		return tables;
	}

	public void setTables(List<TableInfo> tables) {
		this.tables = tables;
	}
	
	public Object clone() throws CloneNotSupportedException {
		SchemaInfo schemaInfo = (SchemaInfo) super.clone();
		
		if (tables != null){
			List<TableInfo> copyTables = new ArrayList<TableInfo>(); 
			copyTables.addAll(tables);
			schemaInfo.setTables(copyTables);
		}
	
		return schemaInfo;
	}
}
