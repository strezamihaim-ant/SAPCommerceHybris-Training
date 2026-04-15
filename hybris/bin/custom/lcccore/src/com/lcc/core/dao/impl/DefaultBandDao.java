package com.lcc.core.dao.impl;

import com.lcc.core.dao.BandDao;
import com.lcc.core.model.BandModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DefaultBandDao implements BandDao {

    private FlexibleSearchService flexibleSearchService;

    private static final String FIND_ALL_BANDS_QUERY =
            "SELECT {" + BandModel.PK + "} FROM {" + BandModel._TYPECODE + "}";

    private static final String FIND_BAND_BY_CODE_QUERY =
            "SELECT {" + BandModel.PK + "} FROM {" + BandModel._TYPECODE + "}" +
            " WHERE {" + BandModel.CODE + "} = ?code";

    @Override
    public List<BandModel> findAllBands() {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_ALL_BANDS_QUERY);
        final SearchResult<BandModel> result = flexibleSearchService.search(query);
        return result.getResult() != null ? result.getResult() : Collections.emptyList();
    }

    @Override
    public BandModel findBandByCode(final String code) {
        if (code == null) {
            return null;
        }

        final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_BAND_BY_CODE_QUERY, Map.of("code", code));

        try {
            return flexibleSearchService.searchUnique(query);
        } catch (ModelNotFoundException e) {
            return null;
        }
    }

    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
