package com.app;

import java.io.File;
import java.io.IOException;

public class FilesFirst {
	public static void main(String[] args) throws IOException {
		int count=0;
		File f=new File("C:\\Program Files");
			String[] s=f.list();
				for(String string:s) {
					File fo=new File(f,string);
					if(fo.isFile())							
							count++;
					System.out.println(string);					
				}
			System.out.println("total number of directory is "+count);
	}	
}
