
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	
	@Autowired
	private CreditCardService creditCardService;


	public Invoice create() {
		Invoice invoice = new Invoice();
		Tenant tenant;

		tenant = tenantService.findByPrincipal();
		Assert.notNull(tenant);
		invoice.setTenant(tenant);
																
		invoice.setDetails("");
		invoice.setTotalDue(.0);
		
		
		invoice.setVatNumber(12371623);
		
		invoice.setMomentIssued(new Date(System.currentTimeMillis() - 1000));
		
		CreditCard tenantCreditCard = creditCardService.findConsumerCreditCard();
		Assert.notNull(tenantCreditCard, "You need to have a valid credit card");
		
		invoice.setCreditCard(tenantCreditCard);

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
	
	public Collection<Invoice> findTenantInvoices(){
		Tenant tenant = tenantService.findByPrincipal();
		
		Collection<Invoice> tenantInvoices = new ArrayList<>();
		
		for(Invoice i: this.findAll()){
			if(i.getTenant().equals(tenant)){
				tenantInvoices.add(i);
			}
		}
		
		return tenantInvoices;
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
