package fi.haagahelia.course.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.Bookstore.domain.Book;
import fi.haagahelia.course.Bookstore.domain.BookRepository;
import fi.haagahelia.course.Bookstore.domain.Category;
import fi.haagahelia.course.Bookstore.domain.CategoryRepository;
import fi.haagahelia.course.Bookstore.domain.User;
import fi.haagahelia.course.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner studentDemo(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of books");
			crepository.save(new Category("Fantasy"));
			crepository.save(new Category("History"));
			crepository.save(new Category("Sci-Fi"));

			log.info("save a couple of books");
			repository.save(new Book("Les Miserables", "Victor Hugo", 1860, "234234", 15,
					crepository.findByName("History").get(0)));
			repository.save(new Book("War and Peace", "Leo Tolstoy", 1867, "6546", 20,
					crepository.findByName("History").get(0)));

			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$06$OZVm2CzF.vx1bb2XZcvSReBIUJOw77UIpEY8GFx9.HGL3gyhgcaSa", "user@mail.com", "USER");
			User user2 = new User("admin", "$2a$06$fClI3fmnqZPuWcB7Ac40bOwku33v124kdtl2wSwcUiguX.STIYpbG", "admin@mail.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
