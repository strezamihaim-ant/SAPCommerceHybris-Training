package com.lcc.facades.tour.converters.populator;

import com.lcc.core.model.ConcertModel;
import com.lcc.facades.concert.data.ConcertData;
import com.lcc.facades.tour.data.TourData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

public class TourPopulator implements Populator<ProductModel, TourData> {

    private Converter<ConcertModel, ConcertData> concertConverter;

    @Override
    public void populate(final ProductModel source, final TourData target) throws ConversionException {
        target.setCode(source.getCode());
        target.setTourName(source.getName());

        final List<ConcertModel> concerts = source.getVariants()
                .stream()
                .filter(ConcertModel.class::isInstance)
                .map(ConcertModel.class::cast)
                .collect(Collectors.toList());

        target.setNumberOfConcerts(concerts.size());
        target.setConcerts(concertConverter.convertAll(concerts));
    }

    public void setConcertConverter(final Converter<ConcertModel, ConcertData> concertConverter) {
        this.concertConverter = concertConverter;
    }
}
