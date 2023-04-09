package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    	System.out.println("Started in Bootstrap");
    	
    	Publisher publisher = new Publisher("Eric", "New Street", "St Petersburg", "FL", "12345");
        publisherRepository.save(publisher);
        
        System.out.println("Number of Publsiher: " + publisherRepository.count());
        
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        Book eee = new Book("Java Insel", "56545");
        eric.getBooks().add(eee);
        eee.getAuthors().add(eric);
        
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);
        eee.setPublisher(publisher);
        publisher.getBooks().add(eee);
       
        authorRepository.save(eric);
        bookRepository.save(ddd);
        bookRepository.save(eee);
        publisherRepository.save(publisher);

        
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);
        
        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);
        
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of Books: " + publisher.getBooks().size());
        
    }
}
