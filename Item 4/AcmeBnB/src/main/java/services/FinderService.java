
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Finder;

@Service
@Transactional
public class FinderService {

	@Autowired
	private FinderRepository	finderRepository;


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
		Assert.notNull(finder);

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

}
