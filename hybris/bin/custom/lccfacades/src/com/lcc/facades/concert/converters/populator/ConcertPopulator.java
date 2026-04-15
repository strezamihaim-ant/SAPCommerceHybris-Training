package com.lcc.facades.concert.converters.populator;

import com.lcc.core.model.ConcertModel;
import com.lcc.facades.concert.data.ConcertData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class ConcertPopulator implements Populator<ConcertModel, ConcertData> {

    @Override
    public void populate(final ConcertModel source, final ConcertData target) throws ConversionException {
        target.setCode(source.getCode());
        target.setVenue(source.getVenue());
        target.setDate(source.getDate());
        target.setConcertType(source.getConcertType());
    }
}
