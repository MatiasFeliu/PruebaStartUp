package cl.praxis.startup.model.dao;

import cl.praxis.startup.model.models.AddressDTO;

import java.util.List;

public interface AddressDao {


    public List<AddressDTO> selectAllAddress();

    public AddressDTO insertAddress(AddressDTO addressDTO);


}
