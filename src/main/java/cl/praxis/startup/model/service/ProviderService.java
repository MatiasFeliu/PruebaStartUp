package cl.praxis.startup.model.service;

import cl.praxis.startup.model.models.ProviderDTO;

import java.util.List;

public interface ProviderService {
    public List<ProviderDTO> selectAllProviders();

    public ProviderDTO insertProvider(ProviderDTO providerDTO);

    public ProviderDTO selectProvider(String providerName);
}
