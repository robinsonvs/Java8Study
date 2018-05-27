import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lesson7 {
	
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
		
		//makes the first 10 users moderators
		//comparing java 7 and 8
		
		//Implementation Java7
		Collections.sort(users, new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				return o1.getPoints() - o2.getPoints();
			}
		});
		Collections.reverse(users);
		List<User> top10 = users.subList(0, 10);
		for (User user : top10) {
			user.setModerator();
		}
		
		//Implementation Java8 - using Streams
		users.sort(Comparator.comparing(User::getPoints).reversed());
		users.subList(0, 10).forEach(User::setModerator);

		System.out.println("-----------------------------------");
		
		//Using Predicate
		Stream<User> stream1 = users.stream();
		stream1.filter(u -> u.getPoints() > 40);
		
		//he did not apply the filter to the user list
		//returns users with points over 40
		users.stream().filter(u -> u.getPoints() < 40);
		users.forEach(System.out::println);
		
		System.out.println("-----------------------------------");
		
		//here the filter has occurred correctly - Stream has no side effect
		//on the collection that originated it
		Stream<User> stream = users.stream().filter(u -> u.getPoints() < 40);
		stream.forEach(System.out::println);
		
		System.out.println("-----------------------------------");
		
		//Chain the invocations fluently
		users.stream().filter(u -> u.getPoints() > 40).forEach(System.out::println);
		
		
		//----------- Collectors Maps
		//Collectors Lambda
		List<Integer> points = users.stream().map(u -> u.getPoints()).collect(Collectors.toList());
		
		//Collectors Method Reference
		List<Integer> points2 = users.stream().map(User::getPoints).collect(Collectors.toList());
		
		//avoiding overhead boxing
		IntStream stream2 = users.stream().mapToInt(User::getPoints);
		
		//max - sorted - average : IntStream
		double averageScore = users.stream().mapToInt(User::getPoints).average().getAsDouble();
		
		//using OptionsDouble return
		OptionalDouble media = users.stream().mapToInt(User::getPoints).average();
		averageScore = media.orElse(0.0);
		
		//lean version
		averageScore = users.stream().mapToInt(User::getPoints).average().orElse(0.0);
		
		//throwing exception
		averageScore = users.stream().mapToInt(User::getPoints).average().orElseThrow(IllegalStateException::new);
		
		//other example
		Optional<String> maxName = users.stream().max(Comparator.comparing(User::getPoints)).map(User::getName);
		
	
	}

}
