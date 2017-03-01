
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AuditRepository;
import domain.Audit;
import domain.Auditor;

@Service
@Transactional
public class AuditService {

	// Managed repository -----------------------------------

	@Autowired
	private AuditRepository	auditRepository;

	// Supporting services ----------------------------------

	@Autowired
	private AuditorService	auditorService;


	// Constructors -----------------------------------------

	public AuditService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public Audit create() {
		Audit result;

		result = new Audit();

		return result;
	}

	public Audit findOne(int auditId) {
		Audit result;

		result = auditRepository.findOne(auditId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Audit> findAll() {
		Collection<Audit> result;

		result = auditRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Audit save(Audit audit) {
		Assert.notNull(audit);

		Audit result;
		Auditor auditor;
		Date momentWritten;

		if (audit.getId() == 0) {
			momentWritten = new Date(System.currentTimeMillis() - 1000);
			audit.setMomentWritten(momentWritten);

			auditor = auditorService.findByPrincipal();
			auditor.getAudits().add(audit);
			auditorService.save(auditor);
		} else if (audit.getId() != 0 && audit.getDraft() == true) {
			momentWritten = new Date(System.currentTimeMillis() - 1000);
			audit.setMomentWritten(momentWritten);
		}

		audit.setDraft(false);
		result = auditRepository.save(audit);

		return result;
	}

	// Other business methods -------------------------------

	public Audit saveAsDraft(Audit audit) {
		Assert.notNull(audit);

		Audit result;
		Auditor auditor;

		if (audit.getId() == 0) {
			auditor = auditorService.findByPrincipal();
			auditor.getAudits().add(audit);
			auditorService.save(auditor);
		}

		audit.setDraft(true);
		result = auditRepository.save(audit);

		return result;
	}

	public Double findMinAuditsOfProperties() {
		Double result;

		result = auditRepository.findMinAuditsOfProperties();
		Assert.notNull(result);

		return result;
	}

	public Double findAvgAuditsOfProperties() {
		Double result;

		result = auditRepository.findAvgAuditsOfProperties();
		Assert.notNull(result);

		return result;
	}

	public Double findMaxAuditsOfProperties() {
		Double result;

		result = auditRepository.findMaxAuditsOfProperties();
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		auditRepository.flush();
	}

}
