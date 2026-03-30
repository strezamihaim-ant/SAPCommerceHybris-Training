package com.lcc.core.service;

import com.lcc.core.model.ConcertModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;

import java.util.List;

public interface ConcertService {

    List<ConcertModel> getConcertsByCatalogVersion(CatalogVersionModel catalogVersion);

    ConcertModel getConcertByCodeAndCatalogVersion(String code, CatalogVersionModel catalogVersion);
}
