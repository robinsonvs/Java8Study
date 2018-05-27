import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Lesson4 {
	
	public static void main(String[] args) {

		//************* andThen
		User user1 = new User("Joao", 5, false);
		User user2 = new User("Maria", 15, false);
		User user3 = new User("Pedro", 25, false);
		
		List<User> immutableUsers = Arrays.asList(user1, user2, user3);
		
		Consumer<User> showMessage = u -> System.out.println("before print names");
		Consumer<User> printName = u -> System.out.println(u.getName());
		
		immutableUsers.forEach(showMessage.andThen(printName));
		//*************andThen

		//*************removeIf
		Predicate<User> predicate = new Predicate<User>() {
			public boolean test(User t) {
				return t.getPoints() > 10;
			}
		};

		List<User> mutableUsers = new ArrayList<>();
		mutableUsers.add(user1);
		mutableUsers.add(user2);
		mutableUsers.add(user2);
		
		mutableUsers.removeIf(predicate);
		mutableUsers.forEach(u -> System.out.println(u.getName() + " - " + u.getPoints()));
		//*************removeIf
	}

}
