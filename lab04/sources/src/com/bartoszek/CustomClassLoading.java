package com.bartoszek;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomClassLoading {
	
	public static class MyClassLoader extends ClassLoader{
		public MyClassLoader() {
			super(MyClassLoader.class.getClassLoader());
		}
		
		public Class<?>loadFromDisk(String path)throws IOException{
			byte[]b=Files.readAllBytes(Paths.get(path));
			return defineClass(null, b, 0,b.length);
		}
	}
}
