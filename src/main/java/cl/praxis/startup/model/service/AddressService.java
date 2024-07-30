package cl.praxis.startup.model.service;

import cl.praxis.startup.model.models.AddressDTO;

import java.util.List;

public interface AddressService {

    public List<AddressDTO> selectAllAddress();

    public AddressDTO insertAddress(AddressDTO addressDTO);
}
