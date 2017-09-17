package hua.sso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
@CrossOrigin(allowCredentials="true",origins="http://localhost:4200")
public class Application {

	@Autowired
	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@RequestMapping("/sso/save")
	@ResponseBody
	public List<Customer> save() {
		// save a couple of customers
		repository.save(new Customer("Alice", "Smith"));
		repository.save(new Customer("Bob", "Smith"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();
		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : repository.findByLastName("Smith")) {
			System.out.println(customer);
		}

		return repository.findAll();
	}
	
	@RequestMapping("/sso/findAll")
	@ResponseBody
	public List<Customer> findAll() {
		List<Customer> list = repository.findAll();
		System.out.println("findAll" + list);
		return list;
	}
	
	@RequestMapping("/sso/findByFirstName")
	@ResponseBody
	public Customer findByFirstName(String firstName) {
		return repository.findByFirstName(firstName);
	}
	
	@RequestMapping("/sso/findByLastName")
	@ResponseBody
	public Customer findByLastName(String lastName) {
		return repository.findByFirstName(lastName);
	}
}
