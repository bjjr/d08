
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.StatusRepository;
import domain.Status;

@Transactional
@Service
public class StatusService {

	@Autowired
	private StatusRepository	statusRepository;


	public Status create() {
		Status status = new Status();
		status.setName("PENDING");

		Status result = this.save(status);
		
		return result;
	}

	public Status save(Status status) {
		Assert.notNull(status, "StatusService.save: 'status' cannot be null");
		Assert.isTrue(status.getName().equals("PENDING") || status.getName().equals("ACCEPTED") || status.getName().equals("DENIED"), "StatusService.save: The status have to match any of the status");

		Status result;

		result = statusRepository.save(status);

		return result;
	}

	public Status findOne(Integer id) {
		return statusRepository.findOne(id);
	}

	public Collection<Status> findAll() {
		return statusRepository.findAll();
	}

	public void delete(Status status) {
		Assert.notNull(status, "StatusService.delete: status cannot be null");
		Assert.isTrue(status.getId() != 0, "StatusService.delete: id cannot be zero");

		statusRepository.delete(status);
	}
}
