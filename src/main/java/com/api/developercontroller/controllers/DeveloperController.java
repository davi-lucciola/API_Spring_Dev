package com.api.developercontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.developercontroller.models.Developer;
import com.api.developercontroller.repository.DeveloperRepository;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/api/developers")
public class DeveloperController {

    @Autowired
    DeveloperRepository developerRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Developer>> getAllDevelopers() {
        try {
            List<Developer> developers = new ArrayList<Developer>();

            developers.addAll(developerRepository.findAll());

            if (developers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(developers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Developer> getOneDeveloper(@PathVariable("id") int id) {
        Developer developer = developerRepository.findById(id);

        if (developer != null) {
            return new ResponseEntity<>(developer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createDeveloper(@RequestBody Developer developer) {
        Developer _developer = developerRepository.findById(developer.getId());

        if (_developer == null) {
            try {
                developerRepository.save(developer);
                return new ResponseEntity<>("O Desenvolvedor foi criado com sucesso!", HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDeveloper(@PathVariable("id") int id, @RequestBody Developer developer) {
        Developer _developer = developerRepository.findById(id);

        if (_developer != null) {
            _developer.setId(id);
            _developer.setNome(developer.getNome());
            _developer.setMainLanguage(developer.getMainLanguage());
            _developer.setFavoriteAnimal(developer.getFavoriteAnimal());

            developerRepository.update(_developer);
            return new ResponseEntity<>("O Desenvolvedor foi atualizado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    "Não foi possivel achar o Desenvolvedor com o id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeveloper (@PathVariable int id) {
        Developer toDeleteDeveloper = developerRepository.findById(id);

        if (toDeleteDeveloper != null) {
            developerRepository.deleteById(id);
            return new ResponseEntity<>("O desenvolvedor foi deletado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    "Não foi possivel achar o Desenvolvedor com o id=" + id, HttpStatus.NOT_FOUND);
        }
    }
}

