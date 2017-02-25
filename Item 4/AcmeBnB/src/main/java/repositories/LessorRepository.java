
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Lessor;

@Repository
public interface LessorRepository extends JpaRepository<Lessor, Integer> {

	@Query("select count(b)/(select count(l) from Lessor l) from Book b where b.status.name like 'ACCEPTED'")
	Double avgAcceptedPerLessor();

	@Query("select count(b)/(select count(l) from Lessor l) from Book b where b.status.name like 'DENIED'")
	Double avgDeniedPerLessor();

	@Query("select l,count(b) from Lessor l join l.properties p join p.books b where b.status.name = 'PENDING' group by l order by count(b) DESC")
	List<Object[]> lessorsOrderByNumPending();

	@Query("select l,count(b) from Lessor l join l.properties p join p.books b where b.status.name = 'APPROVED' group by l order by count(b) DESC")
	List<Object[]> lessorsOrderByNumApproved();

	@Query("select l,count(b) from Lessor l join l.properties p join p.books b where b.status.name = 'DENIED' group by l order by count(b) DESC")
	List<Object[]> lessorsOrderByNumDenied();

	@Query("select l from Lessor l where l.userAccount.id = ?1")
	Lessor findByUserAccountId(int userAccountId);
}
