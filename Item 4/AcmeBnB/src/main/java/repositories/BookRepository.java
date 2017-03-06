
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	@Query("select t.books from Tenant t where t.id = ?1")
	Collection<Book> findTenantBooks(int id);

	@Query("select sum(p.books.size)*1.0/(select count(b) from Book b) from Property p where p.audits.size >= 1")
	Double findAvgBooksProperty1Audit();

	@Query("select sum(p.books.size)*1.0/(select count(b) from Book b) from Property p where p.audits.size = 0")
	Double findAvgBooksPropertyNoAudit();
}
