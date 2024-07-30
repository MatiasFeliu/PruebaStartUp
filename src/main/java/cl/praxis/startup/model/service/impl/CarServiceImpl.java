package cl.praxis.startup.model.service.impl;

import cl.praxis.startup.model.dao.CarDao;
import cl.praxis.startup.model.models.CarDTO;
import cl.praxis.startup.model.service.CarService;

import java.util.List;

public class CarServiceImpl implements CarService {

    private final CarDao carDao;

    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public List<CarDTO> selectAllCars() {
        return carDao.selectAllCars();
    }

    @Override
    public CarDTO insertCar(CarDTO carDTO) {
        return carDao.insertCar(carDTO);
    }
}
