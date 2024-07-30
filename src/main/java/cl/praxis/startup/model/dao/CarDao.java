package cl.praxis.startup.model.dao;

import cl.praxis.startup.model.models.CarDTO;

import java.util.List;

public interface CarDao {

    public List<CarDTO> selectAllCars();

    public CarDTO insertCar(CarDTO carDTO);
}
