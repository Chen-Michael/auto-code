package jdbc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TableInfo implements Cloneable {
	private String schemaName = "";
	private String tableName  = "";
	private int importedCount = 0;
	private int exportedCount = 0;
	private List<ColumnInfo> columns         = new ArrayList<ColumnInfo>(); 
	private List<ColumnInfo> exportedColumns = new ArrayList<ColumnInfo>(); 

	public TableInfo(String tableName) {
		super();
		this.tableName = tableName;
	}
	
	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<ColumnInfo> getColumns() {
		return columns;
	}
	
	public int getImportedCount() {
		return importedCount;
	}

	public void setImportedCount(int importedCount) {
		this.importedCount = importedCount;
	}

	public int getExportedCount() {
		return exportedCount;
	}

	public void setColumns(List<ColumnInfo> columns) {
		this.columns = columns;
	}

	public List<ColumnInfo> getExportedColumns() {
		return exportedColumns;
	}

	public void setExportedColumns(List<ColumnInfo> exportedColumns) {
		this.exportedColumns = exportedColumns;
		this.exportedCount   = exportedColumns.size();
	}
	
	public Object clone() throws CloneNotSupportedException {
		TableInfo tableInfo = (TableInfo) super.clone();
		
		if (columns != null){
			List<ColumnInfo> copyColumns = new ArrayList<ColumnInfo>(); 
			copyColumns.addAll(columns);
			tableInfo.setColumns(copyColumns);
		}
		
		if (exportedColumns != null){
			List<ColumnInfo> copyColumns2 = new ArrayList<ColumnInfo>(); 
			copyColumns2.addAll(exportedColumns);
			tableInfo.setExportedColumns(copyColumns2);
		}
	
		return tableInfo;
	}
}
