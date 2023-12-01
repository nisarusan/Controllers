package nl.novi.persons.controller;


import nl.novi.persons.expection.RecordNotFoundException;
import nl.novi.persons.model.Television;
import nl.novi.persons.repository.TelevisionRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/television")
public class TelevisionController {


    //Auto wire and instance field
    @Autowired
    TelevisionRepository televisionRepository;


    @GetMapping
    public ResponseEntity<List<Television>> getAllTelevisions() {
        return ResponseEntity.ok(televisionRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<String> setTelevision(@RequestBody Television television) {
        televisionRepository.save(television);
        URI location = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + television.getId())
                        .toUriString()
        );
        return ResponseEntity.created(location).body(television.getName() + " is added");
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateTelevision(@PathVariable Long id, @RequestBody Television television) {
        if (televisionRepository.existsById(id)) {
            boolean updateTv = televisionRepository.existsById(id);

            // not sure if this is good, because setId is open?
            television.setId(id);
            televisionRepository.save(television);
            return ResponseEntity.ok("Television with ID " + id + " updated successfully");
        } else {
            throw new RecordNotFoundException("Not found");
        }
//        Optional<Television> existingTelevision = televisionRepository.findById(id);
//
//        if (televisionRepository.isPresent()) {
//            Television newTv = existingTelevision.get();
//            // Update the existing television's information
//            newTv.setType(television.getType());
//            newTv.setBrand(television.getBrand());
//            newTv.setName(television.getName());
//            // Update other fields as needed
//
//            // Save the updated television to the database
//            televisionRepository.save(television);

//        } else {
//            throw new RecordNotFoundException("Television with ID " + id + " not found");
//        }

    }
    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTv(@PathVariable Long id) {
        if (televisionRepository.existsById(id)) {
            televisionRepository.deleteById(id);
            return ResponseEntity.ok("Television with ID " + id + " deleted successfully");
        } else {
            throw new RecordNotFoundException("Television with ID " + id + " not found");
        }
    }


}
