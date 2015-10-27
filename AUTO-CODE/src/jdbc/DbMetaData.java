package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbMetaData {
	private Connection conn = null;
	
	public DbMetaData(DbConnectionInfo info) throws Exception {
		String hosting   = info.getHosting();
		String account   = info.getAccount();
		String password  = info.getPassword();
		String className = info.getClassName();
		
		Class.forName(className);
		conn = DriverManager.getConnection(hosting, account, password);
	}
	
	public List<SchemaInfo> getAllSchemaData(){
		List<SchemaInfo> schemas = getSchemaList();
		
		for (SchemaInfo schema: schemas){
			List<TableInfo> tables = getTableList(schema);
			
			for (TableInfo table: tables){
				List<ColumnInfo> columns = getColumnList  (schema, table);
				List<String>     primary = getPrimaryList (schema, table);
				List<String>     imported= getImportedList(schema, table);
				
				for (ColumnInfo column: columns){
					if (primary. indexOf(column.getColumnName()) > -1) column.setPrimaryKey (true);
					if (imported.indexOf(column.getColumnName()) > -1) column.setImportedKey(true);
				}
				
				table.setColumns(columns);
			}
			
			schema.setTables(tables);
		}
		
		return schemas;
	}
	
	public List<SchemaInfo> getSchemaList(){
		List<SchemaInfo> result = new ArrayList<SchemaInfo>();
		
		try {
			ResultSet rs = conn.getMetaData().getCatalogs();
			while (rs.next()) {
				result.add(new SchemaInfo(rs.getString("TABLE_CAT")));
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	public List<TableInfo> getTableList(SchemaInfo schemaInfo){
		List<TableInfo> result = new ArrayList<TableInfo>();
		
		try {
			ResultSet rs = conn.getMetaData().getTables(schemaInfo.getScheamName(), null, "%", null);
			while (rs.next()) {
				result.add(new TableInfo(rs.getString("TABLE_NAME")));
			}	
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	public List<ColumnInfo> getColumnList(SchemaInfo schemaInfo, TableInfo tableInfo){
		List<ColumnInfo> result = new ArrayList<ColumnInfo>();
		
		try {
			ResultSet rs = conn.getMetaData().getColumns(schemaInfo.getScheamName(), null, tableInfo.getTableName(), null);
			while (rs.next()) {
				ColumnInfo columnInfo = new ColumnInfo(rs.getString("COLUMN_NAME"));
				columnInfo.setType(rs.getInt("DATA_TYPE"));
				columnInfo.setSize(rs.getInt("COLUMN_SIZE"));
				columnInfo.setDefaultValue(rs.getString("COLUMN_DEF"));
				
				if ("YES".equals(rs.getString("IS_NULLABLE"))){
					columnInfo.setNullable(true);
				}else {
					columnInfo.setNullable(false);
				}
				
				if ("YES".equals(rs.getString("IS_AUTOINCREMENT"))){
					columnInfo.setAutoIncrement(true);
				}else {
					columnInfo.setAutoIncrement(false);
				}
				result.add(columnInfo);
			}	
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	public List<String> getPrimaryList(SchemaInfo schemaInfo, TableInfo tableInfo){
		List<String> result = new ArrayList<String>();
		
		try {
			ResultSet rs = conn.getMetaData().getPrimaryKeys(schemaInfo.getScheamName(), null, tableInfo.getTableName());
			while (rs.next()) {
				result.add(rs.getString("COLUMN_NAME"));
			}	
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	public List<String> getImportedList(SchemaInfo schemaInfo, TableInfo tableInfo){
		List<String> result = new ArrayList<String>();
		
		try {
			ResultSet rs = conn.getMetaData().getImportedKeys(schemaInfo.getScheamName(), null, tableInfo.getTableName());
			while (rs.next()) {
				result.add(rs.getString("FKCOLUMN_NAME"));
			}	
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
}
