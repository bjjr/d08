package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer>{

	@Query("select s from Status s where s.name = ?1")
	Status findStatus(String statusString);
}
