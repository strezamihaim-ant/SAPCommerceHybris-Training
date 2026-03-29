package com.lcc.core.service;

import com.lcc.core.model.ConcertModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;

import java.util.List;

public interface ConcertService {

    List<ConcertModel> findConcertsByCatalogVersion(CatalogVersionModel catalogVersion);

    ConcertModel findConcertByCodeAndCatalogVersion(String code, CatalogVersionModel catalogVersion);
}
