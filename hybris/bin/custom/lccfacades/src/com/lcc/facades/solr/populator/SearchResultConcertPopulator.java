package com.lcc.facades.solr.populator;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.converters.populator.SearchResultVariantProductPopulator;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;


public class SearchResultConcertPopulator extends SearchResultVariantProductPopulator
{
    private static final String BAND_FIELD = "band";

    @Override
    public void populate(final SearchResultValueData source, final ProductData target)
    {
        super.populate(source, target);
        target.setBandName(this.<String>getValue(source, BAND_FIELD));
    }
}
