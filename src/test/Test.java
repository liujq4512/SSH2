package test;

import java.io.File;
import java.io.FileWriter;

public class Test {
	public static void main(String[] args) throws Exception {
		
		String subUrl = "/system/resource/tree";
		int i = subUrl.indexOf("tree");
		if(subUrl.indexOf("index")!=-1 || subUrl.indexOf("login")!=-1 || subUrl.indexOf("tree")!=-1){
			
		}else{
			System.out.println("not matched! i= "+ i);
			
		}
		
	}
	
	
	public static void test(){
		int a=5, m=4;
		int b=4, n=4;
		int c = ++b;
		int d=a--;
		int e = m++ + m;
		int f = n++ + ++n;
		System.out.println(c);
		System.out.println(d);
		System.out.println(e);
		System.out.println(f);
	}
	
	public static void createFiles(){
		File f = new File("c:/test02");
		if (!f.exists()) {
			f.mkdirs();
		}
		for (int i = 0; i < 1200; i++) {
			String path = "c:/test02/test" + i + ".txt";
			File d = new File(path);
			try{
				if (!d.exists()) {
					d.createNewFile();
				}
				FileWriter fw = new FileWriter(path);
				fw.write("has created file" + i);
				fw.flush();
				fw.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println("has created file" + i);
		}
	}
}
