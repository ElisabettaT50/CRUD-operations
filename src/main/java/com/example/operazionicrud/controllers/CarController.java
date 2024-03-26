package com.example.operazionicrud.controllers;

import com.example.operazionicrud.entities.CarEntity;
import com.example.operazionicrud.services.CarSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    CarSevice carSevice;

    @GetMapping("/getallcars")
    public ResponseEntity<List<CarEntity>> getAllCars() {
        return ResponseEntity.ok().body(carSevice.getAllCars());
    }

    @PostMapping("/createcar")
    public ResponseEntity<CarEntity> addCar(@RequestBody CarEntity car) {
        return ResponseEntity.ok().body(carSevice.addCar(car));
    }

    @GetMapping("/getcarbyid/{id}")
    public ResponseEntity<CarEntity> getCarList(@PathVariable Long id) {
        Optional<CarEntity> optionalCar = carSevice.findCarByIdIfExist(id);
        if(optionalCar.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            return ResponseEntity.ok().body(optionalCar.get());
        }
    }

    @PostMapping("/updatecarbyid/{id}")
    public ResponseEntity<CarEntity> updateCarById(@PathVariable Long id,
                                                   @RequestBody CarEntity newCar) {
        Optional<CarEntity> updatedCar = carSevice.updateCarTypeByIdIfExist(id, newCar);
        if(updatedCar.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(updatedCar.get());
        }
    }

    @DeleteMapping("/deletecar")
    public ResponseEntity<CarEntity> daleteCar(@RequestBody CarEntity car) {
        Optional<CarEntity> carToBeDeleted = carSevice.deleteCar(car);
        if (carToBeDeleted.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().body(carToBeDeleted.get());
        }
    }

    @DeleteMapping("/deleteallcars")
    public ResponseEntity<List<CarEntity>> deleteAllCars() {
        return ResponseEntity.ok().body(carSevice.deleteAllCars());
    }
}
