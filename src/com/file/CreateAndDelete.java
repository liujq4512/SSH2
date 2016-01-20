package com.file;

import java.io.File;
import java.io.IOException;

public class CreateAndDelete {

	public static void main(String[] args) {
		File file = new File("D:/test.txt");
		if(!file.exists()){
			try {
				System.out.println("Create new file !");
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		file.delete();

	}

}
