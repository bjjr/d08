
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Finder;
import domain.Property;
import domain.Tenant;

@Service
@Transactional
public class FinderService {

	@Autowired
	private FinderRepository	finderRepository;

	@Autowired
	private TenantService		tenantService;


	public Finder create() {
		Finder finder;

		finder = new Finder();
		finder.setDestinationCity("");
		finder.setKeyword("");
		finder.setMaxPrice(0.);
		finder.setMinPrice(0.);

		return finder;
	}

	public Finder save(Finder finder) {
		Finder result, originalFinder;
		Tenant tenant;

		originalFinder = finderRepository.findOne(finder.getId());
		Assert.notNull(originalFinder);

		tenant = tenantService.findByPrincipal();
		Assert.notNull(tenant);

		Assert.isTrue(tenant.getFinder().equals(originalFinder));

		result = finderRepository.save(finder);
		Assert.notNull(result);

		return result;
	}
	public Finder findOne(int id) {
		return finderRepository.findOne(id);
	}

	public Collection<Finder> findAll() {
		return finderRepository.findAll();
	}

	public void flush() {
		finderRepository.flush();
	}

	// Other business methods -------------------------------

	public Finder findByPrincipal() {
		return finderRepository.findByPrincipal(tenantService.findByPrincipal().getId());
	}

	public Collection<Property> resultsPerFinder(Finder finder) {
		return finderRepository.resultsPerFinder(finder.getKeyword(), finder.getMinPrice(), finder.getMaxPrice());
	}

	public Double avgResultsPerFinder() {
		return finderRepository.avgResultsPerFinder();
	}

	public Double maxResultsPerFinder() {
		return finderRepository.maxResultsPerFinder();
	}

	public Double minResultsPerFinder() {
		return finderRepository.minResultsPerFinder();
	}

}
