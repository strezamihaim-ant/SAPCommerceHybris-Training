package com.lcc.core.dao.impl;

import com.lcc.core.dao.ConcertDao;
import com.lcc.core.model.ConcertModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultConcertDao implements ConcertDao {

    private FlexibleSearchService flexibleSearchService;

    private static final String GET_LIST_OF_CONCERTS_BY_CATALOG_VERSION_QUERY =
            "SELECT {" + ConcertModel.PK + "}" +
                    " FROM {" + ConcertModel._TYPECODE + "}" +
                    " WHERE {" + ConcertModel.CATALOGVERSION + "} = ?catalogVersion";

    private static final String GET_CONCERT_BY_CODE_AND_CATALOG_VERSION_QUERY =
            "SELECT {" + ConcertModel.PK + "}" +
                    " FROM {" + ConcertModel._TYPECODE + "}" +
                    " WHERE {" + ConcertModel.CATALOGVERSION + "} = ?catalogVersion" +
                    " AND {" + ConcertModel.CODE + "} = ?code";

    @Override
    public List<ConcertModel> findConcertsByCatalogVersion(final CatalogVersionModel catalogVersion)
    {
        if (catalogVersion == null)
        {
            return Collections.emptyList();
        }

        final Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("catalogVersion", catalogVersion);

        final FlexibleSearchQuery query = new FlexibleSearchQuery(GET_LIST_OF_CONCERTS_BY_CATALOG_VERSION_QUERY, queryParams);
        query.setResultClassList((List.of(ConcertModel.class)));

        final SearchResult<ConcertModel> result = flexibleSearchService.search(query);

        if(result.getResult() != null) {
            return result.getResult();
        }

        return Collections.emptyList();
    }

    @Override
    public ConcertModel findConcertByCodeAndCatalogVersion(final String code, final CatalogVersionModel catalogVersion) {
        if (code == null || catalogVersion == null)
        {
            return null;
        }

        final Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("code", code);
        queryParams.put("catalogVersion", catalogVersion);

        final FlexibleSearchQuery query = new FlexibleSearchQuery(GET_CONCERT_BY_CODE_AND_CATALOG_VERSION_QUERY, queryParams);

        try
        {
            return flexibleSearchService.searchUnique(query);
        }
        catch (ModelNotFoundException modelNotFoundException)
        {
            // Add when logging is configured
            //LOG.debug("Concert with code [{}] and catalog version [{}] not found", code, catalogVersion.getVersion(), modelNotFoundException);
        }
        return null;
    }

    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
