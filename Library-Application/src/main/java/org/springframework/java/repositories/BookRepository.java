package org.springframework.java.repositories;

import org.hibernate.boot.spi.JpaOrmXmlPersistenceUnitDefaultAware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.java.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>  {

	Book findByBookname(String bookname);
}
