package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Kunal Indoliya.
 */
@Component
public class Bootstrap implements CommandLineRunner {
    private CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
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
}
