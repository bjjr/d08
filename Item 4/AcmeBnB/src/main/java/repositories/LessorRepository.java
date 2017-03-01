
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

	//	FORMA 1: Ratio de todas las solicitudes (Aceptadas, Denegadas y Pendientes) con respecto a las Aceptadas.
	@Query("select l from Lessor l order by ((select count(b1) from Lessor l1 join l1.properties p1 join p1.books b1)/(select count(b2) from Lessor l2 join l2.properties p join p.books b2 where b2.status.name = 'ACCEPTED')) asc")
	Lessor lessorMaxRatio();
	@Query("select l from Lessor l order by ((select count(b1) from Lessor l1 join l1.properties p1 join p1.books b1)/(select count(b2) from Lessor l2 join l2.properties p join p.books b2 where b2.status.name = 'ACCEPTED')) desc")
	Lessor lessorMinRatio();

	//	FORMA 2: Ratio de todas las solicitudes Pendientes con respecto a las Aceptadas.
	//	select l from Lessor l order by ((select count(b1) from Lessor l1 join l1.properties p1 join p1.books b1 where b1.status.name = 'PENDING')/(select count(b2) from Lessor l2 join l2.properties p join p.books b2 where b2.status.name = 'ACCEPTED')) asc;
	//	select l from Lessor l order by ((select count(b1) from Lessor l1 join l1.properties p1 join p1.books b1 where b1.status.name = 'PENDING')/(select count(b2) from Lessor l2 join l2.properties p join p.books b2 where b2.status.name = 'ACCEPTED')) desc;

	@Query("select l from Lessor l where l.userAccount.id = ?1")
	Lessor findByUserAccountId(int userAccountId);
}
