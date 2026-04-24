package com.lcc.core.solr.provider.impl;

import com.lcc.core.model.BandModel;
import com.lcc.core.model.ConcertModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ProductBandNameValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider
{
    private FieldNameProvider fieldNameProvider;
    private ModelService modelService;

    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
            final Object model) throws FieldValueProviderException
    {
        if (!(model instanceof ConcertModel))
        {
            return Collections.emptyList();
        }

        final ConcertModel concert = (ConcertModel) model;
        final BandModel band = modelService.getAttributeValue(concert, "band");

        if (band == null)
        {
            return Collections.emptyList();
        }

        final String bandName = modelService.getAttributeValue(band, BandModel.NAME);

        if (StringUtils.isBlank(bandName))
        {
            return Collections.emptyList();
        }

        final List<FieldValue> fieldValues = new ArrayList<>();
        final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
        for (final String fieldName : fieldNames)
        {
            fieldValues.add(new FieldValue(fieldName, bandName));
        }

        return fieldValues;
    }

    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
    {
        this.fieldNameProvider = fieldNameProvider;
    }

    @Required
    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }
}
