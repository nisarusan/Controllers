package nl.novi.persons.repository;


import nl.novi.persons.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    public List<Person> findByDobAfter(LocalDate dob);

}
