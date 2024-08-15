package dev.forkingaround.zootopia.controllers;

import dev.forkingaround.zootopia.dtos.AnimalRequest;
import dev.forkingaround.zootopia.models.Animal;
import dev.forkingaround.zootopia.services.AnimalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class AnimalControllerTest {

    @InjectMocks
    private AnimalController animalController;

    @Mock
    private AnimalService animalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAnimals() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal());
        when(animalService.getAllAnimals()).thenReturn(animals);

        ResponseEntity<List<Animal>> response = animalController.getAllAnimals();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(animals, response.getBody());
    }

    @Test
    void testGetAnimalByIdFound() {
        Animal animal = new Animal();
        when(animalService.getAnimalById(anyLong())).thenReturn(Optional.of(animal));

        
        ResponseEntity<Animal> response = animalController.getAnimalById(1L);

      
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(animal, response.getBody());
    }

    @Test
    void testGetAnimalByIdNotFound() {
    
        when(animalService.getAnimalById(anyLong())).thenReturn(Optional.empty());


        ResponseEntity<Animal> response = animalController.getAnimalById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    void testCreateAnimalSuccess() {
        AnimalRequest animalRequest = new AnimalRequest();
        Animal createdAnimal = new Animal();
        createdAnimal.setId(1L);
        when(animalService.createAnimal(any(AnimalRequest.class))).thenReturn(createdAnimal);

        ResponseEntity<String> response = animalController.createAnimal(animalRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Animal created with ID: 1", response.getBody());
    }

    @Test
    void testCreateAnimalBadRequest() {
        AnimalRequest animalRequest = new AnimalRequest();
        when(animalService.createAnimal(any(AnimalRequest.class))).thenThrow(new IllegalArgumentException("Invalid data"));

        ResponseEntity<String> response = animalController.createAnimal(animalRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid data: Invalid data", response.getBody());
    }

    @Test
    void testCreateAnimalInternalServerError() {
        AnimalRequest animalRequest = new AnimalRequest();
        when(animalService.createAnimal(any(AnimalRequest.class))).thenThrow(new RuntimeException("Internal error"));

        ResponseEntity<String> response = animalController.createAnimal(animalRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred: Internal error", response.getBody());
    }

    @Test
    void testUpdateAnimalSuccess() {
        AnimalRequest animalRequest = new AnimalRequest();
        doNothing().when(animalService).updateAnimal(anyLong(), any(AnimalRequest.class));

        ResponseEntity<String> response = animalController.updateAnimal(1L, animalRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Animal updated successfully", response.getBody());
    }

    @Test
    void testUpdateAnimalBadRequest() {
        AnimalRequest animalRequest = new AnimalRequest();
        doThrow(new IllegalArgumentException("Animal or type not found")).when(animalService).updateAnimal(anyLong(), any(AnimalRequest.class));

        ResponseEntity<String> response = animalController.updateAnimal(1L, animalRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Animal or type not found: Animal or type not found", response.getBody());
    }

    @Test
    void testUpdateAnimalInternalServerError() {
        AnimalRequest animalRequest = new AnimalRequest();
        doThrow(new RuntimeException("Internal error")).when(animalService).updateAnimal(anyLong(), any(AnimalRequest.class));

        ResponseEntity<String> response = animalController.updateAnimal(1L, animalRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred while updating the animal: Internal error", response.getBody());
    }

    @Test
    void testDeleteAnimalSuccess() {
        doNothing().when(animalService).deleteAnimal(anyLong());

        ResponseEntity<String> response = animalController.deleteAnimal(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteAnimalNotFound() {
        doThrow(new IllegalArgumentException("Animal not found")).when(animalService).deleteAnimal(anyLong());

        ResponseEntity<String> response = animalController.deleteAnimal(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Animal not found: Animal not found", response.getBody());
    }

    @Test
    void testDeleteAnimalInternalServerError() {
        doThrow(new RuntimeException("Internal error")).when(animalService).deleteAnimal(anyLong());

        ResponseEntity<String> response = animalController.deleteAnimal(1L);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred while deleting the animal: Internal error", response.getBody());
    }
}
