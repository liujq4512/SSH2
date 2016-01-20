package test;

public class Recursive {

	public static void main(String[] args){
		System.out.println(count(10));
	}
	
	public static int count(int m){
		if(m==0){
			return 0;
		}else{
			return m+count(m-1);
		}
		
	}
}
