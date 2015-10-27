package template;

public interface POJO {
	public String getClassHeader   (String className);
	
	public String getByteVariable  (String variable );
	public String getShortVariable (String variable );
	public String getIntVariable   (String variable );
	public String getLongVariable  (String variable );
	public String getFloatVariable (String variable );
	public String getDoubleVariable(String variable );
	public String getStringVariable(String variable );
	
	public String getByteSetters   (String variable );
	public String getShortSetters  (String variable );
	public String getIntSetters    (String variable );
	public String getLongSetters   (String variable );
	public String getFloatSetters  (String variable );
	public String getDoubleSetters (String variable );
	public String getStringSetters (String variable );
	
	public String getByteGetters   (String variable );
	public String getShortGetters  (String variable );
	public String getIntGetters    (String variable );
	public String getLongGetters   (String variable );
	public String getFloatGetters  (String variable );
	public String getDoubleGetters (String variable );
	public String getStringGetters (String variable );
	
	public String getClassFooter   ();
}
