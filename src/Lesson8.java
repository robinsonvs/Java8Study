import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lesson8 {

	public static void main(String[] args) {
		
		User user1 = new User("Joao", 5, false);
		User user2 = new User("Maria", 15, false);
		User user3 = new User("Pedro", 25, false);
		User user4 = new User("Jose", 35, false);
		User user5 = new User("Ruan", 10, false);
		User user6 = new User("Max", 1, false);
		User user7 = new User("Paulo", 6, false);
		User user8 = new User("Joana", 9, false);
		User user9 = new User("Maria", 50, false);
		User user10 = new User("Amelia", 40, false);
		
		List<User> users = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10);
		
		
		//findAny/collect/forEach - terminal operation
		users.stream().filter(u -> u.getPoints() > 30).peek(System.out::println).findAny();
		System.out.println("----------------------------");
		
		//Ordering Streams
		users.stream().filter(u -> u.getPoints() > 40).sorted(Comparator.comparing(User::getName));
		
		//method invoked with Stream does not change who called it 
		users.sort(Comparator.comparing(User::getName));
		users.forEach(System.out::println);
		users.sort(Comparator.comparing(User::getName).reversed());
		
		System.out.println("----------------------------");
		
		//we need to use a collector
		users.stream().sorted(Comparator.comparing(User::getName)).collect(Collectors.toList());
		users.forEach(System.out::println);
		System.out.println("----------------------------");
		users = users.stream().sorted(Comparator.comparing(User::getName)).collect(Collectors.toList());
		users.forEach(System.out::println);		
		System.out.println("----------------------------");
		
		//
		int total = users.stream().mapToInt(User::getPoints).sum();
		
		//
		User over100 = users.stream().filter(u -> u.getPoints() > 30).collect(Collectors.toList()).get(0);
		//Lazy operations : findAny - terminal operation
		Optional<User> optionalUser = users.stream().filter(u -> u.getPoints() > 30).findAny();
		

		System.out.println("----------------------------");
		//peek - it is not a terminal operation, it does not print
		users.stream().filter(u -> u.getPoints() > 30).peek(System.out::println);
		
		//findAny/collect/forEach - terminal operation
		users.stream().filter(u -> u.getPoints() > 30).peek(System.out::println).findAny();
		System.out.println("----------------------------");
		
		
		//reductions
		int totalReduction = users.stream().mapToInt(User::getPoints).reduce(0, Integer::sum);
		
		int multiplication = users.stream().mapToInt(User::getPoints).reduce(1, (a,b) -> (a * b));
		
		int totalReduction2 = users.stream().reduce(0, (atual, u) -> atual + u.getPoints(), Integer::sum);
		
		//Iterables
		users.stream().iterator().forEachRemaining(System.out::println);
		
		//short circuit operations
		Random random = new Random(0);
		IntStream stream = IntStream.generate(() -> random.nextInt());
		List<Integer> list = stream.limit(100).boxed().collect(Collectors.toList());
	}
}
