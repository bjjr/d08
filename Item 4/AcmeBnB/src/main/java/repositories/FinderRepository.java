
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Finder;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer> {

	@Query("select t.finder from Tenant t where t.id = ?1")
	Finder findByPrincipal(int id);

	@Query("select avg(b.property) from Tenant t left join t.books b where t.finder.keyword like b.property.name or t.finder.keyword like b.property.description or t.finder.keyword like b.property.address")
	Double avgResultsPerFinder();

	@Query("select min(b.property) from Tenant t left join t.books b where t.finder.keyword like b.property.name or t.finder.keyword like b.property.description or t.finder.keyword like b.property.address")
	Double minResultsPerFinder();

	@Query("select max(b.property) from Tenant t left join t.books b where t.finder.keyword like b.property.name or t.finder.keyword like b.property.description or t.finder.keyword like b.property.address")
	Double maxResultsPerFinder();

}
