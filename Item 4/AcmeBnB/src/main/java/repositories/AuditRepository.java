
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Attachment;
import domain.Audit;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Integer> {

	@Query("select min(p.audits.size) from Property p")
	Double findMinAuditsOfProperties();

	@Query("select avg(p.audits.size) from Property p")
	Double findAvgAuditsOfProperties();

	@Query("select max(p.audits.size) from Property p")
	Double findMaxAuditsOfProperties();

	@Query("select a from Attachment a where a.audit.id = ?1")
	Collection<Attachment> findAttachmentsByAudit(int auditId);

	@Query("select a from Audit a where a.property.id = ?1 and a.draft = false")
	Collection<Audit> findAuditsPublishedByProperty(int propertyId);

}
