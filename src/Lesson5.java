import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class Lesson5 {
	
	public static void main(String[] args) {

		User user1 = new User("Joao", 5, false);
		User user2 = new User("Maria", 15, false);
		User user3 = new User("Pedro", 25, false);
		
		List<User> users = Arrays.asList(user1, user2, user3);	
		
		Comparator<User> comparator = new Comparator<User>() {
			public int compare(User o1, User o2) {
				return o1.getName().compareTo(o2.getName());
			}
		};
		
		Collections.sort(users, comparator);
		
		//Using Comparator as Funcional Interfacea
		Comparator<User> comparator2 = (u1, u2) -> u1.getName().compareTo(u2.getName());
		Collections.sort(users, comparator2);

		//dont't create the local variable
		Collections.sort(users, (u1, u2) -> u1.getName().compareTo(u2.getName()));
		
		//using default sort method declared in List interface
		users.sort((u1, u2) -> u1.getName().compareTo(u2.getName()));
		
		//using comparing - comparator factory Comparator
		Comparator<User> comparator3 = Comparator.comparing(u -> u.getName());
		users.sort(comparator3);
		
		//without the local variable
		users.sort(Comparator.comparing(u -> u.getName()));
		
		//using static import
		//users.sort(comparing(u -> u.getName()));
		
		//----------------------------------------------------------------------------
		//ordering by natural order
		List<String> words = Arrays.asList("Earth","Tree","Rain");
		Collections.sort(words);
		
		//not compile in Java 8
		//there is no sort method in List that does not receive parameters
		//words.sort();
		
		words.sort(Comparator.naturalOrder());
		System.out.println(words);
		
		words.sort(Comparator.reverseOrder());
		System.out.println(words);
		//----------------------------------------------------------------------------		
		
		
		//Detailing the comparing
		//Implementation
	    /*public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
	            Function<? super T, ? extends U> keyExtractor)
	    {
	        Objects.requireNonNull(keyExtractor);
	        return (Comparator<T> & Serializable)
	            (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
	    }*/	
		//using Interface Function
		Function<User, String> extractName = u -> u.getName();
		Comparator<User> comparator4 = Comparator.comparing(extractName);
		users.sort(comparator4);

		//unnecessary autoboxing
		//we exchange Funtion for ToIntFunction
		//Function<User, Integer> extractPoints = u -> u.getPoints();
		ToIntFunction<User> extractPoints = u -> u.getPoints();
		Comparator<User> comparator5 = Comparator.comparingInt(extractPoints);
		users.sort(comparator5);		
		
		//using lean version, passing the lambda to factory comparators
		users.sort(Comparator.comparingInt(u -> u.getPoints()));
		//----------------------------------------------------------------------------
		
	}
}

