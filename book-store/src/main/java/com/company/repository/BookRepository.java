package com.company.repository;

import com.company.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@EnableJpaRepositories
public interface BookRepository extends JpaRepository<Book, Integer> {

   List<Book> findBookByNameAndAndAuthor(String name, String surname);


}
