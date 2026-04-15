package com.lcc.facades.band.converters.populator;

import com.lcc.core.enums.MusicType;
import com.lcc.core.model.BandModel;
import com.lcc.facades.band.data.BandData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.List;
import java.util.stream.Collectors;

public class BandPopulator implements Populator<BandModel, BandData> {

    @Override
    public void populate(final BandModel source, final BandData target) throws ConversionException {
        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setHistory(source.getHistory());
        target.setAlbumSales(String.valueOf(source.getAlbumSales()));
        final List<String> genres = source.getMusicTypes()
                .stream()
                .map(MusicType::getCode)
                .collect(Collectors.toList());
        target.setGenres(genres);
        //target.setTours(source.getTours());
    }
}
