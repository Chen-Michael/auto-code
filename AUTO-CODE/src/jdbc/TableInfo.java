package jdbc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TableInfo implements Cloneable {
	private String tableName = "";
	private List<ColumnInfo> columns = new ArrayList<ColumnInfo>(); 

	public TableInfo(String tableName) {
		super();
		this.tableName = tableName;
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

	public void setColumns(List<ColumnInfo> columns) {
		this.columns = columns;
	}
	
	public Object clone() throws CloneNotSupportedException {
		TableInfo tableInfo = (TableInfo) super.clone();
		
		if (columns != null){
			List<ColumnInfo> copyColumns = new ArrayList<ColumnInfo>(); 
			copyColumns.addAll(columns);
			tableInfo.setColumns(copyColumns);
		}
	
		return tableInfo;
	}
}
