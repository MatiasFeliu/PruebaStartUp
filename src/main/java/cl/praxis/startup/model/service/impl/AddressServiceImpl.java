package cl.praxis.startup.model.service.impl;

import cl.praxis.startup.model.dao.AddressDao;
import cl.praxis.startup.model.models.AddressDTO;
import cl.praxis.startup.model.service.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {

    private final AddressDao addressDao;

    public AddressServiceImpl(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    public List<AddressDTO> selectAllAddress() {
        return addressDao.selectAllAddress();
    }

    @Override
    public AddressDTO insertAddress(AddressDTO addressDTO) {
        return addressDao.insertAddress(addressDTO);
    }
}
