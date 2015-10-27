package jdbc;

public class ColumnInfo implements Cloneable {
	private boolean autoIncrement = false;
	private boolean primaryKey    = false;
	private boolean importedKey   = false;
	private boolean nullable      = false;
	private boolean index         = false;
	private String  columnName    = "";
	private String  defaultValue  = "";
	private int     size          = 0;
	private int     type          = 0;

	public ColumnInfo(String columnName) {
		super();
		this.columnName = columnName;
	}

	public boolean isAutoIncrement() {
		return autoIncrement;
	}

	public void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public boolean isImportedKey() {
		return importedKey;
	}

	public void setImportedKey(boolean importedKey) {
		this.importedKey = importedKey;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public boolean isIndex() {
		return index;
	}

	public void setIndex(boolean index) {
		this.index = index;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
