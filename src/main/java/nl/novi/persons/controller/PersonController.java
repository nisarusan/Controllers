package nl.novi.persons.controller;


import nl.novi.persons.model.Person;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    //instance fields - retrieve list person objects
    List<Person> personList = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Person>> getMapPerson() {
        return ResponseEntity.ok(personList);
    }

    //getMapping with search
    @GetMapping("/search")
    public ResponseEntity<List<Person>> getPersonSearch(@RequestParam String substring) {
        List<Person> searchResult = new ArrayList<>();
        for (Person person : personList) {
            if (person.getName().contains(substring)) {
                searchResult.add(person);
            }
        }
        return ResponseEntity.ok(searchResult);
    }

    @PostMapping
    public ResponseEntity<String> addPerson(@RequestBody Person person) {
        personList.add(person);
        URI location = URI.create("/person/" + person.getId());
//        Long newPersonId = person.getId();  // Replace with the actual ID logic
        // Construct the URI for the newly created resource
//        return ResponseEntity.ok(person.getName() + " is succesfully added");
        return ResponseEntity.created(location).body(person.getName() + " is succesfully added");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
        for (int i = 0; i < personList.size(); i++) {
            Person person = personList.get(i);
            if (person.getId().equals(id)) {
                // Update the person's information, excluding ID from the request body
                updatedPerson.setId(person.getId());  // Ensure the ID is not changed
                personList.set(i, updatedPerson);
//                return new ResponseEntity<>(person, HttpStatus.OK);
                return ResponseEntity.ok(person.getName() + " is updated successfully with: " + updatedPerson.getName());
            }
        }
        // traditional way of creating a bad request resource by making a new object instance of the response entity
//        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        // this is the the new way with Spring Boot;
        return ResponseEntity.badRequest().build();
    }

    //Delete CRUD
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        for (Person person : personList) {
            if (person.getId().equals(id)) {
                personList.remove(person);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(person.getName() + " with id " + id + " succesfully got deleted");
//                return ResponseEntity.noContent(person.getName() + " with id " + id + " succesfully got deleted");
            }
        }
        return ResponseEntity.notFound().build();
    }

}

