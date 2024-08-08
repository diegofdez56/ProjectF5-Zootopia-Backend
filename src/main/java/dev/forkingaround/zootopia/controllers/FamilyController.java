package dev.forkingaround.zootopia.controllers;

import dev.forkingaround.zootopia.models.Family;
import dev.forkingaround.zootopia.repositories.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/families")
public class FamilyController {

    @Autowired
    private FamilyRepository familyRepository;

    @GetMapping
    public ResponseEntity<List<Family>> getAllFamilies() {
        List<Family> families = familyRepository.findAll();
        return ResponseEntity.ok(families);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Family> getFamilyById(@PathVariable Long id) throws Exception {
        Optional<Family> family = familyRepository.findById(id);
        if (family.isPresent()) {
            return ResponseEntity.ok(family.get());
        } else {
            throw new Exception("Family not found with ID " + id);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Family> createFamily(@RequestBody Family newFamily) {
        Family createdFamily = familyRepository.save(newFamily);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFamily);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Family> updateFamily(@PathVariable Long id, @RequestBody Family updatedFamily) throws Exception {
        if (!familyRepository.existsById(id)) {
            throw new Exception("Family not found with ID " + id);
        }
        updatedFamily.setId(id);
        Family family = familyRepository.save(updatedFamily);
        return ResponseEntity.ok(family);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFamily(@PathVariable Long id) throws Exception {
        if (!familyRepository.existsById(id)) {
            throw new Exception("Family not found with ID " + id);
        }
        familyRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

