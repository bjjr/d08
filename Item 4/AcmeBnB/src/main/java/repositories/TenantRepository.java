
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer> {

	@Query("select count(b)/(select count(t) from Tenant t) from Book b where b.status.name like 'ACCEPTED'")
	Double avgAcceptedPerTenant();

	@Query("select count(b)/(select count(t) from Tenant t) from Book b where b.status.name like 'DENIED'")
	Double avgDeniedPerTenant();

	@Query("select t from Tenant t join t.books b where b.status.name = 'APPROVED' group by t")
	Collection<Tenant> tenantsMoreRequestsApproved();

	@Query("select t from Tenant t join t.books b where b.status.name = 'DENIED' group by t")
	Collection<Tenant> tenantsMoreRequestsDenied();

	@Query("select t from Tenant t join t.books b where b.status.name = 'PENDING' group by t")
	Collection<Tenant> tenantsMoreRequestsPending();

	@Query("select t from Tenant t order by ((select count(b1) from Tenant t1 join t1.books b1)/(select count(b2) from Tenant t2 join t2.books b2 where b2.status.name = 'ACCEPTED')) asc")
	Collection<Tenant> tenantMaxRatio();

	@Query("select t from Tenant t order by ((select count(b1) from Tenant t1 join t1.books b1)/(select count(b2) from Tenant t2 join t2.books b2 where b2.status.name = 'ACCEPTED')) desc")
	Collection<Tenant> tenantMinRatio();

	@Query("select t from Tenant t where t.userAccount.id = ?1")
	Tenant findByUserAccount(int id);

}
