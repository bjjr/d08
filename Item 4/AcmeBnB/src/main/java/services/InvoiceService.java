
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.InvoiceRepository;
import domain.CreditCard;
import domain.Invoice;
import domain.Tenant;

@Service
@Transactional
public class InvoiceService {

	@Autowired
	private InvoiceRepository	invoiceRepository;

	@Autowired
	private TenantService		tenantService;


	public Invoice create() {
		Invoice invoice = new Invoice();
		Tenant tenant;

		tenant = tenantService.findByPrincipal();
		Assert.notNull(tenant);

		invoice.setDetails("");
		invoice.setTenant(tenant);
		invoice.setTotalDue(.0);
		invoice.setVatNumber(0);
		invoice.setMomentIssued(new Date(System.currentTimeMillis() - 1000));
		invoice.setCreditCard(new CreditCard());

		return invoice;
	}

	public Invoice save(Invoice invoice) {
		Invoice res;
		Assert.notNull(invoice);

		res = invoiceRepository.save(invoice);
		Assert.notNull(res);

		return res;
	}

	public Invoice findOne(int id) {
		return invoiceRepository.findOne(id);
	}

	public Collection<Invoice> findAll() {
		return invoiceRepository.findAll();
	}

	public void flush() {
		invoiceRepository.flush();
	}

	public Double findMinInvoicesOfTenants() {
		return invoiceRepository.findMinInvoicesOfTenants();
	}

	public Double findAvgInvoicesOfTenants() {
		return invoiceRepository.findAvgInvoicesOfTenants();
	}

	public Double findMaxInvoicesOfTenants() {
		return invoiceRepository.findMaxInvoicesOfTenants();
	}
}
