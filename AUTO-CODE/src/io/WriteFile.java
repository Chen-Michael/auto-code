package io;

import java.io.FileWriter;

public class WriteFile {
	public static void write(String path, String value){
		try {
			FileWriter fw = new FileWriter(path);
			fw.write(value);
			fw.flush();
			fw.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
