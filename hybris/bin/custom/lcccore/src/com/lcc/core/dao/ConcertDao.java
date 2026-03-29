package com.lcc.core.dao;

import com.lcc.core.model.ConcertModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;

import java.util.List;


/**
 * DAO for concert list operations.
 */
public interface ConcertDao
{
    List<ConcertModel> findConcertsByCatalogVersion(CatalogVersionModel catalogVersion);

    ConcertModel findConcertByCodeAndCatalogVersion(String code, CatalogVersionModel catalogVersion);
}
