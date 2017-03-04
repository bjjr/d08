package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.StatusRepository;
import domain.Status;

@Transactional
@Service
public class StatusService {
	
	@Autowired
	private StatusRepository statusRepository;
	
	
	
	public Status findOne(Integer id){
		return statusRepository.findOne(id);
	}
	
	public Collection<Status> findAll(){
		return statusRepository.findAll();
	}
	
	public Status findStatus(String statusString){
		return statusRepository.findStatus(statusString);
	}
}
