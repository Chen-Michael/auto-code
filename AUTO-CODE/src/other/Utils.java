package other;

import java.util.regex.Pattern;

public class Utils {
	public static String formatFileName(String value){
		String[] cut = value.split("_");
		StringBuilder result = new StringBuilder();
		
		for (String str: cut){
			String str2 = str;
			result.append(str.toUpperCase().charAt(0) + str2.substring(1));
		}
		
		return result.toString();
	}
	
	public static String formatVariableName(String value){
		String[] cut = value.split("_");
		StringBuilder result = new StringBuilder();
		
		int i = 0;
		for (String str: cut){
			String str2 = str;
			if (i == 0){
				result.append(str.toLowerCase().charAt(0) + str2.substring(1));
			}else{
				result.append(str.toUpperCase().charAt(0) + str2.substring(1));
			}
		}
		
		return result.toString();
	}
}
