
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer> {

	@Query("select count(b)/(select count(t) from Tennant t) from Book b where b.status.name like 'ACCEPTED'")
	Double avgAcceptedPerTenant();

	@Query("select count(b)/(select count(t) from Tennant t) from Book b where b.status.name like 'DENIED'")
	Double avgDeniedPerTenant();

	@Query("select t,count(b) from Tenant t join t.books b where b.status.name = 'APPROVED' group by t")
	Collection<Tenant> tenantsMoreRequestsApproved();

	@Query("select t,count(b) from Tenant t join t.books b where b.status.name = 'DENIED' group by t")
	Collection<Tenant> tenantsMoreRequestsDenied();

	@Query("select t,count(b) from Tenant t join t.books b where b.status.name = 'PENDING' group by t")
	Collection<Tenant> tenantsMoreRequestsPending();

	@Query("select t from Tenant t where t.userAccount.id = ?1")
	Tenant findByUserAccount(int id);

}
