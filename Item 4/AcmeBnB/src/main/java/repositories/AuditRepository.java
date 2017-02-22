
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Audit;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Integer> {

	@Query("select min(p.audits.size) from Property p")
	Double findMinAuditsOfProperties();

	@Query("select avg(p.audits.size) from Property p")
	Double findAvgAuditsOfProperties();

	@Query("select max(p.audits.size) from Property p")
	Double findMaxAuditsOfProperties();

}
