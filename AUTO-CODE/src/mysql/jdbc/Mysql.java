package mysql.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mysql {
	private static Connection conn = null;
	
	public static synchronized boolean connect(String host, String account, String password){
		if (conn == null){
			try {
				if (host.indexOf("jdbc:mysql://") < 0) host = "jdbc:mysql://" + host;
				
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection (host, account, password);
			} catch (ClassNotFoundException e) {
				System.out.println(e.toString());
				return false;
		    } catch (SQLException e2) {
		    	System.out.println(e2.toString());
				return false;
		    } finally {
		    	
		    }
		}
		
	    return true;
	}
	
	public static synchronized Connection getConn(String host, String account, String password){
		if (conn == null) connect(host, account, password);
		
	    return conn;
	}
	
	public static synchronized List<String> getSchemaList(){
		List<String> result = new ArrayList<String>();
		
		try {
			ResultSet rs = conn.getMetaData().getCatalogs();
			while (rs.next()) {
				result.add(rs.getString("TABLE_CAT"));
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	public static synchronized List<String> getTableList(String schemaName){
		List<String> result = new ArrayList<String>();
		
		try {
			ResultSet rs = conn.getMetaData().getTables(schemaName, null, "%", null);
			while (rs.next()) {
				result.add(rs.getString("TABLE_NAME"));
			}	
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	public static synchronized List<Map<String, String>> getColumnList(String schemaName, String tableName){
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		
		try {
			ResultSet rs = conn.getMetaData().getColumns(schemaName, null, tableName, null);
			while (rs.next()) {
				HashMap<String, String> h = new HashMap<String, String>();
				h.put("Name", rs.getString("COLUMN_NAME"));
				h.put("Type", rs.getString("TYPE_NAME"));

				result.add(h);
			}	
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	public static synchronized List<String> getPrimaryList(String schemaName, String tableName){
		List<String> result = new ArrayList<String>();
		
		try {
			ResultSet rs = conn.getMetaData().getPrimaryKeys(schemaName, null, tableName);
			while (rs.next()) {
				result.add(rs.getString("COLUMN_NAME"));
			}	
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	public static synchronized List<String> getImportedList(String schemaName, String tableName){
		List<String> result = new ArrayList<String>();
		
		try {
			ResultSet rs = conn.getMetaData().getImportedKeys(schemaName, null, tableName);
			while (rs.next()) {
				result.add(rs.getString("FKCOLUMN_NAME"));
			}	
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
}
