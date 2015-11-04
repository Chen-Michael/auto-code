package template.java;

import java.sql.Types;
import java.util.List;

import jdbc.ColumnInfo;
import jdbc.TableInfo;
import other.Utils;
import template.API;

public class JavaAPI implements API {

	@Override
	public String getClassImport(List<String> className) {
		StringBuilder result = new StringBuilder();
		
		result.append("import java.io.*;\r\n");
		result.append("import java.sql.*;\r\n");
		result.append("import java.util.*;\r\n");
		result.append("import javax.servlet.http.*;\r\n");
		result.append("import javax.servlet.ServletException;\r\n");
		result.append("import javax.servlet.annotation.WebServlet;\r\n");
		
		for (String temp: className){
			result.append(temp + ";\r\n");
		}
		
		return result.toString();
	}

	@Override
	public String getClassHeader(String className) {
		StringBuilder result = new StringBuilder();
		className = Utils.formatFileName(className);
		
		result.append("@WebServlet(\"/" + className + "\") \r\n");
		result.append("public class " + className + " extends HttpServlet { \r\n");
		result.append("\t");
		result.append("private static final long serialVersionUID = 1L;");
		
		return result.toString();
	}

	@Override
	public String getClassConstructor(String className) {
		return "public " + Utils.formatFileName(className) + "(){ super(); }";
	}

	@Override
	public String getDoGetMethod(String pojoName, String daoName, TableInfo tableInfo) {
		StringBuilder result = new StringBuilder();
		
		result.append("protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { \r\n");
		result.append("\t\t");
		result.append("HttpSession session = request.getSession(); \r\n");
		result.append("\t\t");
		result.append("Connection  conn    = null; \r\n");
		result.append("\t\t");
		result.append(pojoName + " pojo = getPOJO(request); \r\n");
		result.append("\t\t");
		result.append(daoName  + " dao = new " + daoName + "(); \r\n");
		result.append("\t\t");
		result.append("String result = \"\"; \r\n");
		result.append("\t\t");
		result.append("result = dao.checkSearchPOJO(pojo);  \r\n");
		result.append("\t\t");
		result.append("if (\"\".equals(result)){ \r\n");
		result.append("\t\t\t");
		result.append("request.setAttribute(\"List\", dao.search(pojo, conn)); \r\n");
		result.append("\t\t");
		result.append("} else{ \r\n");
		result.append("\t\t\t");
		result.append("request.setAttribute(\"result\", result); \r\n");
		result.append("\t\t");
		result.append("} \r\n");
		result.append("\t\t");
		result.append("request.getRequestDispatcher(\"WEB-INF/" + tableInfo.getTableName() + ".jsp\").forward(request, response); \r\n");
		result.append("\t");
		result.append("}");
		
		return result.toString();
	}

	@Override
	public String getDoPutMethod(String pojoName, String daoName, TableInfo tableInfo) {
		StringBuilder result = new StringBuilder();
		
		result.append("protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { \r\n");
		result.append("\t");
		result.append("}");
		
		return result.toString();
	}

	@Override
	public String getDoPostMethod(String pojoName, String daoName, TableInfo tableInfo) {
		StringBuilder result = new StringBuilder();
		
		result.append("protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { \r\n");
		result.append("\t\t");
		result.append("HttpSession session = request.getSession(); \r\n");
		result.append("\t\t");
		result.append("Connection  conn    = null; \r\n");
		result.append("\t\t");
		result.append(pojoName + " pojo = getPOJO(request); \r\n");
		result.append("\t\t");
		result.append(daoName  + " dao = new " + daoName + "(); \r\n");
		result.append("\t\t");
		result.append("String action = request.getParameter(\"action\"); \r\n");
		result.append("\t\t");
		result.append("String result = \"\"; \r\n");
		result.append("\t\t");
		result.append("switch (action){ \r\n");
			result.append("\t\t");
			result.append("case \"insert\" :  \r\n");
				result.append("\t\t\t");
				result.append("result = dao.checkInsertPOJO(pojo);  \r\n");
				result.append("\t\t\t");
				result.append("if (!\"\".equals(result)) break;  \r\n");
				result.append("\t\t\t");
				result.append("result = (dao.insert(pojo, conn))? \"Scuess\": \"Failure\";  \r\n");
				result.append("\t\t\t");
				result.append("break;  \r\n");
			result.append("\t\t");
			result.append("case \"update\" :  \r\n");
				result.append("\t\t\t");
				result.append("result = dao.checkUpdatePOJO(pojo);  \r\n");
				result.append("\t\t\t");
				result.append("if (!\"\".equals(result)) break;  \r\n");
				result.append("\t\t\t");
				result.append("result = (dao.update(pojo, conn))? \"Scuess\": \"Failure\";  \r\n");
				result.append("\t\t\t");
				result.append("break;  \r\n");
			result.append("\t\t");
			result.append("case \"delete\" :  \r\n");
				result.append("\t\t\t");
				result.append("result = dao.checkDeletePOJO(pojo);  \r\n");
				result.append("\t\t\t");
				result.append("if (!\"\".equals(result)) break;  \r\n");
				result.append("\t\t\t");
				result.append("result = (dao.delete(pojo, conn))? \"Scuess\": \"Failure\";  \r\n");
				result.append("\t\t\t");
				result.append("break;  \r\n");
		result.append("\t\t");
		result.append("} \r\n");
		result.append("\t\t");
		result.append("request.setAttribute(\"result\", result); \r\n");
		result.append("\t\t");
		result.append("request.getRequestDispatcher(\"WEB-INF/" + tableInfo.getTableName() + ".jsp\").forward(request, response); \r\n");
		result.append("\t");
		result.append("}");
		
		return result.toString();
	}

	@Override
	public String getDoDeleteMethod(String pojoName, String daoName, TableInfo tableInfo) {
		StringBuilder result = new StringBuilder();
		
		result.append("protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { \r\n");
		result.append("\t");
		result.append("}");
		
		return result.toString();
	}

	@Override
	public String getPOJO(String pojoName, TableInfo tableInfo) {
		StringBuilder result = new StringBuilder();
		
		result.append("public " + pojoName + " getPOJO(HttpServletRequest request){ \r\n");
		result.append("\t\t");
		result.append(pojoName + " pojo = new " + pojoName + "(); \r\n");
		
		for (ColumnInfo columnInfo: tableInfo.getColumns()){
			result.append("\t\t");
			switch (columnInfo.getType()){
			case Types.TINYINT:
				result.append("try{ \r\n");
				
					result.append("\t\t\t");
					result.append("pojo.set" + Utils.formatFileName(columnInfo.getColumnName()) +
							 "(Byte.parseByte(request.getParameter(\"" + Utils.formatVariableName(columnInfo.getColumnName()) + "\"))); \r\n");
				
				result.append("\t\t");
				result.append("} catch(Exception e){ \r\n");
				
					result.append("\t\t\t");
					result.append("pojo.set" + Utils.formatFileName(columnInfo.getColumnName()) + "((Byte) null); \r\n");
				
				result.append("\t\t");
				result.append("} \r\n");
				break;

			case Types.SMALLINT:
				result.append("try{ \r\n");
				
					result.append("\t\t\t");
					result.append("pojo.set" + Utils.formatFileName(columnInfo.getColumnName()) +
							 "(Short.parseShort(request.getParameter(\"" + Utils.formatVariableName(columnInfo.getColumnName()) + "\"))); \r\n");
				
				result.append("\t\t");
				result.append("} catch(Exception e){ \r\n");
				
					result.append("\t\t\t");
					result.append("pojo.set" + Utils.formatFileName(columnInfo.getColumnName()) + "((Short) null); \r\n");
				
				result.append("\t\t");
				result.append("} \r\n");
				break;
				
			case Types.INTEGER:
				result.append("try{ \r\n");
			
					result.append("\t\t\t");
					result.append("pojo.set" + Utils.formatFileName(columnInfo.getColumnName()) +
							 "(Integer.parseInt(request.getParameter(\"" + Utils.formatVariableName(columnInfo.getColumnName()) + "\"))); \r\n");
				
				result.append("\t\t");
				result.append("} catch(Exception e){ \r\n");
				
					result.append("\t\t\t");
					result.append("pojo.set" + Utils.formatFileName(columnInfo.getColumnName()) + "((Integer) null); \r\n");
				
				result.append("\t\t");
				result.append("} \r\n");
				break;
				
			case Types.BIGINT:
				result.append("try{ \r\n");
				
					result.append("\t\t\t");
					result.append("pojo.set" + Utils.formatFileName(columnInfo.getColumnName()) +
							 "(Long.parseLong(request.getParameter(\"" + Utils.formatVariableName(columnInfo.getColumnName()) + "\"))); \r\n");
				
				result.append("\t\t");
				result.append("} catch(Exception e){ \r\n");
				
					result.append("\t\t\t");
					result.append("pojo.set" + Utils.formatFileName(columnInfo.getColumnName()) + "((Long) null); \r\n");
				
				result.append("\t\t");
				result.append("} \r\n");
				break;
				
			case Types.FLOAT:
				result.append("try{ \r\n");
				
					result.append("\t\t\t");
					result.append("pojo.set" + Utils.formatFileName(columnInfo.getColumnName()) +
							 "(Float.parseFloat(request.getParameter(\"" + Utils.formatVariableName(columnInfo.getColumnName()) + "\"))); \r\n");
				
				result.append("\t\t");
				result.append("} catch(Exception e){ \r\n");
				
					result.append("\t\t\t");
					result.append("pojo.set" + Utils.formatFileName(columnInfo.getColumnName()) + "((Float) null); \r\n");
				
				result.append("\t\t");
				result.append("} \r\n");
				break;
				
			case Types.DOUBLE:
				result.append("try{ \r\n");
				
					result.append("\t\t\t");
					result.append("pojo.set" + Utils.formatFileName(columnInfo.getColumnName()) +
							 "(Double.parseDouble(request.getParameter(\"" + Utils.formatVariableName(columnInfo.getColumnName()) + "\"))); \r\n");
				
				result.append("\t\t");
				result.append("} catch(Exception e){ \r\n");
				
					result.append("\t\t\t");
					result.append("pojo.set" + Utils.formatFileName(columnInfo.getColumnName()) + "((Double) null); \r\n");
				
				result.append("\t\t");
				result.append("} \r\n");
				break;
				
			case Types.VARCHAR:
				result.append("pojo.set" + Utils.formatFileName(columnInfo.getColumnName()) +
						 "(request.getParameter(\"" + Utils.formatVariableName(columnInfo.getColumnName()) + "\")); \r\n");
				break;
				
			case Types.CHAR:
				result.append("pojo.set" + Utils.formatFileName(columnInfo.getColumnName()) +
						 "(request.getParameter(\"" + Utils.formatVariableName(columnInfo.getColumnName()) + "\")); \r\n");
				break;
				
			case Types.DATE:
				result.append("pojo.set" + Utils.formatFileName(columnInfo.getColumnName()) +
						 "(request.getParameter(\"" + Utils.formatVariableName(columnInfo.getColumnName()) + "\")); \r\n");
				break;
				
			case Types.TIME:
				result.append("pojo.set" + Utils.formatFileName(columnInfo.getColumnName()) +
						 "(request.getParameter(\"" + Utils.formatVariableName(columnInfo.getColumnName()) + "\")); \r\n");
				break;
				
			case Types.TIMESTAMP:
				result.append("pojo.set" + Utils.formatFileName(columnInfo.getColumnName()) +
						 "(request.getParameter(\"" + Utils.formatVariableName(columnInfo.getColumnName()) + "\")); \r\n");
				break;
			}
		}
		
		result.append("\t\t");
		result.append("return pojo; \r\n");
		
		result.append("\t");
		result.append("}");
		
		return result.toString();
	}

	@Override
	public String getClassFooter() {
		return "}";
	}

}
