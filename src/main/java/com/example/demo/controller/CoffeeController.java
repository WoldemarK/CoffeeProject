package com.example.demo.controller;

import com.example.demo.model.Coffee;
import com.example.demo.repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CoffeeController {

    private final CoffeeRepository repository;


    @GetMapping("/coffee")
    Iterable<Coffee> getCoffee() {
        return repository.findAll();
    }

    @GetMapping("/coffee/{id}")
    public Optional<Coffee> getCoffeeById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping("/coffee")
    public Coffee postCoffee(@RequestBody Coffee coffee) {
        return repository.save(coffee);
    }

    @PutMapping("/coffee/{id}")
    public ResponseEntity<Coffee> putCoffee(@PathVariable Long id,
                                            @RequestBody Coffee coffee) {
        return (!repository.existsById(id))
                ? new ResponseEntity<>(repository.save(coffee), HttpStatus.CREATED)
                : new ResponseEntity<>(repository.save(coffee), HttpStatus.OK);

    }


    @DeleteMapping("coffee/{id}")
    public void deleteCoffee(@PathVariable Long id) {
       repository.deleteById(id);
    }
}

