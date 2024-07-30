package cl.praxis.startup.model.service.impl;

import cl.praxis.startup.model.dao.ProviderDao;
import cl.praxis.startup.model.models.ProviderDTO;
import cl.praxis.startup.model.service.ProviderService;

import java.util.List;

public class ProviderServiceImpl implements ProviderService {

    private final ProviderDao providerDao;

    public ProviderServiceImpl(ProviderDao providerDao) {
        this.providerDao = providerDao;
    }

    @Override
    public List<ProviderDTO> selectAllProviders() {
        return providerDao.selectAllProviders();
    }

    @Override
    public ProviderDTO insertProvider(ProviderDTO providerDTO) {
        return providerDao.insertProvider(providerDTO);
    }

    public ProviderDTO selectProvider(String providerName) {
        return providerDao.selectProvider(providerName);
    }
}
