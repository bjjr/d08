
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Finder;
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
		Finder result;
		Tenant tenant;

		Assert.notNull(finder);
		tenant = tenantService.findByPrincipal();
		Assert.notNull(tenant);

		// Only the logged Tenant can edit his Finder
		if (finder.getId() != 0) {
			Assert.isTrue(tenant.getFinder().getId() == finder.getId());
		}

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
