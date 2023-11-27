package nl.novi.persons.controller;


import nl.novi.persons.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    //instance fields - retrieve list person objects
    List<Person> personList = new ArrayList<>();

    @GetMapping("/person")
    public ResponseEntity<List<Person>> getMapPerson() {
        return ResponseEntity.ok(personList);
    }

    @PostMapping("/person")
    public ResponseEntity<String> addPerson(@RequestBody Person person) {
        personList.add(person);
        return ResponseEntity.ok(person.getName() + " is succesfully added");
    }


    @PutMapping("/person/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
        for (int i = 0; i < personList.size(); i++) {
            Person person = personList.get(i);
            if (person.getId().equals(id)) {
                // Update the person's information, excluding ID from the request body
                updatedPerson.setId(person.getId());  // Ensure the ID is not changed
                personList.set(i, updatedPerson);
                return ResponseEntity.ok("Person updated successfully");
            }
        }
        return ResponseEntity.notFound().build();
    }
}
