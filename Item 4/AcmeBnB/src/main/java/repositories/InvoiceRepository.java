
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

	@Query("select min(t.invoices.size) from Tenant t")
	Double findMinInvoicesOfTenants();

	@Query("select avg(t.invoices.size) from Tenant t")
	Double findAvgInvoicesOfTenants();

	@Query("select max(t.invoices.size) from Tenant t")
	Double findMaxInvoicesOfTenants();

	@Query("select sum(i.totalDue) from Invoice i")
	Double totalMoney();

}
