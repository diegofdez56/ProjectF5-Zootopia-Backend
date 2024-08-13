package dev.forkingaround.zootopia.controllers;

import dev.forkingaround.zootopia.dtos.AnimalRequest;
import dev.forkingaround.zootopia.models.Animal;
import dev.forkingaround.zootopia.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${api-endpoint}/animals")
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping("/all")
    public ResponseEntity<List<Animal>> getAllAnimals() {
        List<Animal> animals = animalService.getAllAnimals();
        return ResponseEntity.ok(animals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        Optional<Animal> animal = animalService.getAnimalById(id);
        return animal.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping("/add")
    public ResponseEntity<String> createAnimal(@RequestBody AnimalRequest animalRequest) {
        try {
            Animal createdAnimal = animalService.createAnimal(animalRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Animal created with ID: " + createdAnimal.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAnimal(@PathVariable Long id, @RequestBody AnimalRequest animalRequest) {
        try {
            animalService.updateAnimal(id, animalRequest);
            return ResponseEntity.ok("Animal updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Animal or type not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the animal: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAnimal(@PathVariable Long id) {
        try {
            animalService.deleteAnimal(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the animal: " + e.getMessage());
        }
    }
}
