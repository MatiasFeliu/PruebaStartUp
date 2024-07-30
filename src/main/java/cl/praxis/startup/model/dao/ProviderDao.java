package cl.praxis.startup.model.dao;

import cl.praxis.startup.model.models.ProviderDTO;

import java.util.List;

public interface ProviderDao {

    public List<ProviderDTO> selectAllProviders();

    public ProviderDTO insertProvider(ProviderDTO providerDTO);

    public ProviderDTO selectProvider(String providerName);
}
