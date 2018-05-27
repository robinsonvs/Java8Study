
public class Lesson1 {

	interface Validator<T>{
		boolean valid(T t);
	}

	static Validator<String> validCEP = value -> value.matches("[0-9]{5}-[0-3]{3}");
	
	static //Object o = () -> {System.out.println("");};
	Runnable o = () ->  {System.out.println("");};
	
	public static void main(String[] args) {
		validCEP.valid("041ad01-300");
		
		System.out.println(o);
		System.out.println(o.getClass());
		
		//Can I access local variables from inside the lambda 
		int number = 5;
		new Thread(() -> System.out.println(number)).start();
		//number = 10; // implicitly final - don't compile 
	}


}
