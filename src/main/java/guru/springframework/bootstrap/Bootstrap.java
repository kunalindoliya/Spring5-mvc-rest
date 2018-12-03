package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Kunal Indoliya.
 */
@Component
public class Bootstrap implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
    }

    private void loadCategories() {
        Category fruits=new Category();
        fruits.setName("Fruits");
        Category dried=new Category();
        dried.setName("Dried");
        Category nuts=new Category();
        nuts.setName("Nuts");
        Category fresh=new Category();
        fresh.setName("Fresh");
        Category exotic=new Category();
        exotic.setName("Exotic");
        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(nuts);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);

        System.out.println("Data Loaded: "+categoryRepository.count());
    }
    private void loadCustomers(){
        Customer c1=new Customer();
        c1.setId(1L);
        c1.setFirstname("Michael");
        c1.setLastname("John");
        Customer c2=new Customer();
        c2.setId(2L);
        c2.setFirstname("Lisa");
        c2.setLastname("Halloween");
        Customer c3=new Customer();
        c3.setId(3L);
        c3.setFirstname("John");
        c3.setLastname("Ribs");
        customerRepository.save(c1);
        customerRepository.save(c2);
        customerRepository.save(c3);

        System.out.println("Customer loaded:"+customerRepository.count());
    }
}
