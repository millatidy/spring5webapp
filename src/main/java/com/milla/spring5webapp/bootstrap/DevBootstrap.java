package com.milla.spring5webapp.bootstrap;

import com.milla.spring5webapp.model.Author;
import com.milla.spring5webapp.model.Book;
import com.milla.spring5webapp.model.Publisher;
import com.milla.spring5webapp.repositories.AuthorRepository;
import com.milla.spring5webapp.repositories.BookRepository;
import com.milla.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    public void initData() {

        // Eric
        Author eric = new Author("Eric", "Evans");
        Publisher harperColins = new Publisher("Harper Colins", "23 Bho Street", "Ruwa", "Zimabwe");
        Book ddd = new Book("Domain Driven Desing", "1234", harperColins);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        publisherRepository.save(harperColins);
        bookRepository.save(ddd);


        // Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher wrks = new Publisher("Wrks", "32 Uni road", "Harare", "Zimbabwe");
        Book noEJB = new Book("J2EE Development without EJB", "2345", wrks);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        publisherRepository.save(wrks);
        bookRepository.save(noEJB);

    }

}
