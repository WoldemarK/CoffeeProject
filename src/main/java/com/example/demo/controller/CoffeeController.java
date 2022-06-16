package com.example.demo.controller;

import com.example.demo.model.Coffee;
import com.example.demo.repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CoffeeController {
    private final CoffeeRepository repository;

    @Value("${greeting-name: Mirage}")
    private String name;

    @Value("${greeting-coffee: ${greeting-name}}")
    private String coffee;

    @GetMapping("/cof")
    String getName() {
        return name;
    }

    @GetMapping("/coff")
    String getNameAndCoffee() {
        return coffee;
    }

    @GetMapping("/coffee")
    Iterable<Coffee> getCoffee() {
        return repository.findAll();
    }

    @GetMapping("/coffee/{id}")
    public ResponseEntity<Optional<Coffee>> getCoffeeById(@PathVariable Long id) {
        Optional<Coffee> cof = repository.findById(id);
        return cof.isPresent()
                ? new ResponseEntity<>(cof, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/coffee")
    public ResponseEntity<HttpStatus> postCoffee(@RequestBody Coffee coffee) {
        repository.save(coffee);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    @PutMapping("/coffee/{id}")
    public ResponseEntity<Coffee> putCoffee(@PathVariable Long id,
                                            @RequestBody Coffee coffee) {
        return (!repository.existsById(id))
                ? new ResponseEntity<>(repository.save(coffee), HttpStatus.CREATED)
                : new ResponseEntity<>(repository.save(coffee), HttpStatus.OK);

    }


    @DeleteMapping("coffee/{id}")
    public ResponseEntity<?> deleteCoffee(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}

