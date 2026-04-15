package com.lcc.facades.concert.impl;

import com.lcc.core.model.ConcertModel;
import com.lcc.core.service.ConcertService;
import com.lcc.facades.concert.ConcertFacade;
import com.lcc.facades.concert.data.ConcertData;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultConcertFacade implements ConcertFacade {

    private static final String CATALOG_ID = "concertoursProductCatalog";
    private static final String CATALOG_VERSION = "Online";

    private ConcertService concertService;
    private CatalogVersionService catalogVersionService;
    private Converter<ConcertModel, ConcertData> concertConverter;

    @Override
    public List<ConcertData> getConcerts() {
        final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion(CATALOG_ID, CATALOG_VERSION);
        return concertService.getConcertsByCatalogVersion(catalogVersion)
                .stream()
                .map(concertConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public ConcertData getConcertForCode(final String code) {
        final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion(CATALOG_ID, CATALOG_VERSION);
        final ConcertModel concert = concertService.getConcertByCodeAndCatalogVersion(code, catalogVersion);
        return concert != null ? concertConverter.convert(concert) : null;
    }

    public void setConcertService(final ConcertService concertService) {
        this.concertService = concertService;
    }

    public void setCatalogVersionService(final CatalogVersionService catalogVersionService) {
        this.catalogVersionService = catalogVersionService;
    }

    public void setConcertConverter(final Converter<ConcertModel, ConcertData> concertConverter) {
        this.concertConverter = concertConverter;
    }
}
