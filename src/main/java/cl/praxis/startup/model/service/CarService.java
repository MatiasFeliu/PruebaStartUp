package cl.praxis.startup.model.service;

import cl.praxis.startup.model.models.CarDTO;

import java.util.List;

public interface CarService {
    public List<CarDTO> selectAllCars();

    public CarDTO insertCar(CarDTO carDTO);
}
