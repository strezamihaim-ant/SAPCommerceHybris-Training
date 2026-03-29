package com.lcc.core.service.impl;

import com.lcc.core.dao.ConcertDao;
import com.lcc.core.model.ConcertModel;
import com.lcc.core.service.ConcertService;
import de.hybris.platform.catalog.model.CatalogVersionModel;

import java.util.Collections;
import java.util.List;

public class DefaultConcertService implements ConcertService {

    private ConcertDao concertDao;

    @Override
    public List<ConcertModel> findConcertsByCatalogVersion(final CatalogVersionModel catalogVersion) {
        final List<ConcertModel> concerts = concertDao.findConcertsByCatalogVersion(catalogVersion);

        if (concerts == null || concerts.isEmpty())
        {
            return Collections.emptyList();
        }

        return concerts;
    }

    @Override
    public ConcertModel findConcertByCodeAndCatalogVersion(final String code, final CatalogVersionModel catalogVersion) {
        return concertDao.findConcertByCodeAndCatalogVersion(code, catalogVersion);
    }

    public void setConcertDao(final ConcertDao concertDao) {
        this.concertDao = concertDao;
    }
}
