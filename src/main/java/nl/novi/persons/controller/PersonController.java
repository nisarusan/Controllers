package nl.novi.persons.controller;


import nl.novi.persons.model.Person;
import nl.novi.persons.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {


    @Autowired
    private PersonRepository personRepository;

    //instance fields - retrieve list person objects
//    List<Person> personList = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Person>> getMapPerson() {
       return ResponseEntity.ok(personRepository.findAll());
//        return ResponseEntity.ok(personList);
    }

    //getMapping with search
    @GetMapping("/search")
    public ResponseEntity<List<Person>> getPersonSearch(@RequestParam String substring) {
        List<Person> searchResult = new ArrayList<>();
        for (Person person : personRepository.findAll()) {
            if (person.getName().contains(substring)) {
                searchResult.add(person);
            }
        }
        return ResponseEntity.ok(searchResult);
    }

    @PostMapping
    public ResponseEntity<String> addPerson(@RequestBody Person person) {
        personRepository.save(person);
        URI location = URI.create("/person/" + person.getId());
//        Long newPersonId = person.getId();  // Replace with the actual ID logic
        // Construct the URI for the newly created resource
//        return ResponseEntity.ok(person.getName() + " is succesfully added");
        return ResponseEntity.created(location).body(person.getName() + " is succesfully added");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
        Optional<Person> optionalPerson = personRepository.findById(id);

        if (optionalPerson.isPresent()) {
            Person existingPerson = optionalPerson.get();

            // Update the existing person's information, excluding ID from the request body
            existingPerson.setName(updatedPerson.getName());
            // Update other fields as needed

            // Save the updated person to the database
            personRepository.save(existingPerson);

            return ResponseEntity.ok(existingPerson.getName() + " is updated successfully with: " + updatedPerson.getName());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Delete CRUD
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        for (Person person : personRepository.findAll()) {
            if (person.getId().equals(id)) {
                personRepository.delete(person);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(person.getName() + " with id " + id + " succesfully got deleted");
//                return ResponseEntity.noContent(person.getName() + " with id " + id + " succesfully got deleted");
            }
        }
        return ResponseEntity.notFound().build();
    }
}

