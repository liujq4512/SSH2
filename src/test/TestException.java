package test;

public class TestException {
	public static void main(String args[]){
		try{
			throw new MyException2();
		}catch(MyException2 e1){
			System.out.println("e2");
		}catch(MyException1 e2){ //catch 子类异常必须放在父类异常之前
			System.out.println("e1");
		}
	}
}

@SuppressWarnings("serial")
class MyException1 extends Exception{
	
}
class MyException2 extends MyException1{
	
}
