import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Lesson6 {

	public static void main(String[] args) {
		
		User user1 = new User("Joao", 5, false);
		User user2 = new User("Maria", 15, false);
		User user3 = new User("Pedro", 25, false);
		
		List<User> users = Arrays.asList(user1, user2, user3);
		
		//Method reference
		users.forEach(User::setModerator);

		//passing the lambda
		Consumer<User> setModerator2 = u -> u.setModerator();
		
		//Passando o Method
		Consumer<User> setModerator = User::setModerator;
		
		users.forEach(setModerator);
		
		//Sort - comparing - Method reference
		users.sort(Comparator.comparing(User::getName));
		
		//using import static - creating a more meaningful variable
		//Function<User, String> byName = User::getName;
		//users.sort(comparing(byName));
		
		//comparingInt
		users.sort(Comparator.comparingInt(User::getPoints));
		
		//theComparing - refining the comparator
		users.sort(Comparator.comparingInt(User::getPoints).thenComparing(User::getName));
		
		//nullsLast - null names will go to the end of sorting
		users.sort(Comparator.nullsLast(Comparator.comparing(User::getName)));
		
		//reverse ordering
		users.sort(Comparator.comparing(User::getPoints).reversed());
		
		//Referencing instance methods
		User joao = new User("Joao", 50, false);
		Runnable block = joao::setModerator;
		block.run();
		
		//Referencing Methods That Receive Arguments
		users.forEach(System.out::println);
		
		//Referencing constructors - Interface Supply and Function
		Function<String, User> userCreator = User::new;
		User jose = userCreator.apply("Jose");
		User pedro = userCreator.apply("Pedro");
		
	}
	
}
