
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Finder;
import domain.Property;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer> {

	@Query("select t.finder from Tenant t where t.id = ?1")
	Finder findByPrincipal(int id);

	@Query("select p from Lessor l join l.properties p where p.rate >= ?2 and p.rate <= ?3 and (p.name like concat('%', ?1, '%') or p.description like concat('%', ?1, '%') or p.address like concat('%', ?1, '%'))")
	Collection<Property> resultsPerFinder(String keyword, double minPrice, double maxPrice);

	@Query("select avg(b.property) from Tenant t left join t.books b where t.finder.keyword like b.property.name or t.finder.keyword like b.property.description or t.finder.keyword like b.property.address")
	Double avgResultsPerFinder();

	@Query("select min(b.property) from Tenant t left join t.books b where t.finder.keyword like b.property.name or t.finder.keyword like b.property.description or t.finder.keyword like b.property.address")
	Double minResultsPerFinder();

	@Query("select max(b.property) from Tenant t left join t.books b where t.finder.keyword like b.property.name or t.finder.keyword like b.property.description or t.finder.keyword like b.property.address")
	Double maxResultsPerFinder();

}
