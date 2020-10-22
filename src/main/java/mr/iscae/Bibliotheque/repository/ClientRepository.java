package mr.iscae.Bibliotheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mr.iscae.Bibliotheque.entity.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{
	
}
