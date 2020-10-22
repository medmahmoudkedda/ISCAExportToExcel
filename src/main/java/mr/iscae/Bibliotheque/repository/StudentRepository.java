package mr.iscae.Bibliotheque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mr.iscae.Bibliotheque.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
	List<Student> findByName(String name);
}
