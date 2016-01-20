package test;

public class InterviewTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i=1;
		int j=10;
		do{
			if(i++ > --j) continue;
		}while(i<5);
		System.out.println(i + "  " +j);
	}

}
