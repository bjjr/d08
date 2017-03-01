
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Book;
import domain.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

	@Query("select b from Property p join p.books b where b.status.name = 'ACCEPTED' and b.checkOutDate > CURRENT_DATE and p.id = ?1")
	List<Book> pendingAcceptedBooks(int propertyId);
}
