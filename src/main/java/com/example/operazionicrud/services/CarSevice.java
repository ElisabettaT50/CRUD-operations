package com.example.operazionicrud.services;

import com.example.operazionicrud.entities.CarEntity;
import com.example.operazionicrud.repositories.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarSevice {

    @Autowired
    private CarRepo carRepo;

    public CarEntity addCar(CarEntity car) {
        return carRepo.save(car);
    }

    public List<CarEntity> getAllCars() {
        return carRepo.findAll();
    }

    public Optional<CarEntity> findCarByIdIfExist(Long id) {
        if (carRepo.existsById(id)) {
            return carRepo.findById(id);
        } else {
            return Optional.empty();
        }
    }

    public Optional<CarEntity> updateCarTypeByIdIfExist(Long id, CarEntity car) {
        Optional<CarEntity> carWithTypeToBeUpd = carRepo.findById(id);
        if (carWithTypeToBeUpd.isPresent()) {
            carWithTypeToBeUpd.get().setType(car.getType());
            carRepo.save(carWithTypeToBeUpd.get());
            return carWithTypeToBeUpd;
        } else {
            return Optional.empty();
        }
    }

    public Optional<CarEntity> deleteCar(CarEntity car) {
        if (carRepo.existsById(car.getId())) {
            carRepo.delete(car);
            return Optional.of(car);
        } else {
            return Optional.empty();
        }
    }

    public List<CarEntity> deleteAllCars() {
        List<CarEntity> carToBeDeletedList = carRepo.findAll();
        carRepo.deleteAll(carToBeDeletedList);
        return carToBeDeletedList;
    }
}
