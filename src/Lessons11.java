import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Product {
	private String name;
	private Path file;
	private BigDecimal price;

	public Product(String name, Path file, BigDecimal price) {
		this.name = name;
		this.file = file;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public Path getFile() {
		return file;
	}

	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + "]";
	}
}

class Customer {
	private String name;

	public Customer(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + "]";
	}
}

class Payment {
	private List<Product> products;
	private LocalDateTime date;
	private Customer customer;

	public Payment(List<Product> products, LocalDateTime date, Customer customer) {
		super();
		this.products = products;
		this.date = date;
		this.customer = customer;
	}

	public List<Product> getProducts() {
		return products;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public Customer getCustomer() {
		return customer;
	}

	@Override
	public String toString() {
		return "Payment [products=" + products + ", date=" + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
				+ ", customer=" + customer + "]";
	}

}

public class Lessons11 {

	public static void main(String[] args) {

		Customer joao = new Customer("Joao");
		Customer pedro = new Customer("Pedro");
		Customer jose = new Customer("Jose");

		Product bach = new Product("Bach completo", Paths.get("/music/bach.mp3"), new BigDecimal(100));
		Product bandeira = new Product("Bandeira Brasil", Paths.get("/images/brasil.jpg"), new BigDecimal(50));
		Product beauty = new Product("Beleza Americana", Paths.get("beauty.mov"), new BigDecimal(150));
		Product vingadores = new Product("Os vingadores", Paths.get("/movies/vingadores.mov"), new BigDecimal(200));
		Product amelie = new Product("Amelia Poulin", Paths.get("/movies/amelie.mov"), new BigDecimal(100));
		
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterday = today.minusDays(1);
		LocalDateTime lastMonth = today.minusMonths(1);
		
		Payment payment1 = new Payment(asList(bach, vingadores), today, joao);
		Payment payment2 = new Payment(asList(bach, bandeira, amelie), yesterday, pedro);
		Payment payment3 = new Payment(asList(beauty, vingadores, amelie), today, joao);
		Payment payment4 = new Payment(asList(vingadores, bach, bandeira), lastMonth, jose);
		
		List<Payment> payments = asList(payment1, payment2, payment3, payment4);
		
		System.out.println("sorteando por data");
		payments.stream().sorted(Comparator.comparing(Payment::getDate)).forEach(System.out::println);
		System.out.println("-------------------------------------------------------------------");
		
		System.out.println("somando preco dos produtos");
		payment1.getProducts().stream()
			.map(Product::getPrice)
			.reduce(BigDecimal::add)
			.ifPresent(System.out::println);
		System.out.println("-------------------------------------------------------------------");
		
		System.out.println("somatorio de todos os pagamentos");
		BigDecimal total = payment1.getProducts().stream()
				.map(Product::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println(total);
		System.out.println("-------------------------------------------------------------------");
		
		Stream<BigDecimal> pricesStream = payments.stream()
				.map(p -> p.getProducts().stream()
						.map(Product::getPrice)
						.reduce(BigDecimal.ZERO, BigDecimal::add));
		pricesStream.forEach(System.out::println);
		System.out.println("-------------------------------------------------------------------");
		
		BigDecimal total2 = payments.stream()
				.map(p -> p.getProducts().stream()
						.map(Product::getPrice)
						.reduce(BigDecimal.ZERO, BigDecimal::add))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println(total2);
		System.out.println("-------------------------------------------------------------------");
		
		Stream<BigDecimal> priceOfEachProduct = payments.stream()
				.flatMap(p -> p.getProducts().stream().map(Product::getPrice));
		priceOfEachProduct.forEach(System.out::println);
		System.out.println("-------------------------------------------------------------------");
		
		BigDecimal totalFlat = payments.stream()
				.flatMap(p -> p.getProducts().stream().map(Product::getPrice))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println(totalFlat);
		System.out.println("-------------------------------------------------------------------");
		
		
		System.out.println("Produtos mais vendidos usando flatMap");
		Stream<Product> products = payments.stream()
				.map(Payment::getProducts)
				.flatMap(p -> p.stream());
		
		System.out.println("passando o lambda como method reference List::stream");
		Stream<Product> products2 = payments.stream()
				.map(Payment::getProducts)
				.flatMap(List::stream);
		
		System.out.println("juntando dois maps");
		Stream<Product> produts3 = payments.stream()
				.flatMap(p -> p.getProducts().stream());
		
		Map<Product, Long> topProducts = payments.stream()
				.flatMap(p -> p.getProducts().stream())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		topProducts.entrySet().stream().forEach(System.out::println);
		
		System.out.println("maior entrada do mapa");
		topProducts.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).ifPresent(System.out::println);
		
		System.out.println("obtendo a soma do valor por produto");
		Map<Product, BigDecimal> totalValuePerProduct = payments.stream()
				.flatMap(p -> p.getProducts().stream())
				.collect(Collectors.groupingBy(Function.identity(),
						 Collectors.reducing(BigDecimal.ZERO, Product::getPrice, BigDecimal::add)));
		
		totalValuePerProduct.entrySet().stream()
			.sorted(Comparator.comparing(Map.Entry::getValue))
			.forEach(System.out::println);

		
		
	}
}
