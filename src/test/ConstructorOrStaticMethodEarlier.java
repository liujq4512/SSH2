package test;

//Static method is more earlier init than constructor
public class ConstructorOrStaticMethodEarlier {
	
	private static String initStr = "init String";
	
	public ConstructorOrStaticMethodEarlier(){
		System.out.println(initStr);
	}

	public static void main(String[] args) {
		ConstructorOrStaticMethodEarlier c = new ConstructorOrStaticMethodEarlier();
	}
}
