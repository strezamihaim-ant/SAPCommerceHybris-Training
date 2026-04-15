package com.lcc.facades.band.impl;

import com.lcc.core.model.BandModel;
import com.lcc.core.service.BandService;
import com.lcc.facades.band.BandFacade;
import com.lcc.facades.band.data.BandData;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;

public class DefaultBandFacade implements BandFacade {

    private BandService bandService;
    private Converter<BandModel, BandData> bandConverter;

    @Override
    public List<BandData> getBands() {
        return bandConverter.convertAll(bandService.getBands());
    }

    @Override
    public BandData getBandForCode(final String code) {
        final BandModel band = bandService.getBandForCode(code);
        return band != null ? bandConverter.convert(band) : null;
    }

    public void setBandService(final BandService bandService) {
        this.bandService = bandService;
    }

    public void setBandConverter(final Converter<BandModel, BandData> bandConverter) {
        this.bandConverter = bandConverter;
    }
}
