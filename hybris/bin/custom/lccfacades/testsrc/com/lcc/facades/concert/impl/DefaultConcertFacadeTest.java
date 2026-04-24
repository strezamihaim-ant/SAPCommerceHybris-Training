package com.lcc.facades.concert.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.lcc.core.model.ConcertModel;
import com.lcc.core.service.ConcertService;
import com.lcc.facades.concert.data.ConcertData;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultConcertFacadeTest {

    private static final String CATALOG_ID = "concertoursProductCatalog";
    private static final String CATALOG_VERSION = "Online";
    private static final String CONCERT_CODE = "20170101";

    @Mock
    private ConcertService concertService;
    @Mock
    private CatalogVersionService catalogVersionService;
    @Mock
    private Converter<ConcertModel, ConcertData> concertConverter;
    @Mock
    private CatalogVersionModel catalogVersion;
    @Mock
    private ConcertModel concertModel;

    @InjectMocks
    private DefaultConcertFacade facade;

    @Before
    public void setUp() {
        when(catalogVersionService.getCatalogVersion(CATALOG_ID, CATALOG_VERSION)).thenReturn(catalogVersion);
    }

    @Test
    public void shouldReturnConvertedConcerts() {
        // given
        final ConcertData concertData = new ConcertData();
        when(concertService.getConcertsByCatalogVersion(catalogVersion)).thenReturn(List.of(concertModel));
        when(concertConverter.convert(concertModel)).thenReturn(concertData);

        // when
        final List<ConcertData> result = facade.getConcerts();

        // then
        assertThat(result).hasSize(1);
        assertThat(result).containsExactly(concertData);
    }

    @Test
    public void shouldReturnEmptyListWhenNoConcerts() {
        // given
        when(concertService.getConcertsByCatalogVersion(catalogVersion)).thenReturn(Collections.emptyList());

        // when
        final List<ConcertData> result = facade.getConcerts();

        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void shouldReturnConvertedConcertForCode() {
        // given
        final ConcertData concertData = new ConcertData();
        when(concertService.getConcertByCodeAndCatalogVersion(CONCERT_CODE, catalogVersion)).thenReturn(concertModel);
        when(concertConverter.convert(concertModel)).thenReturn(concertData);

        // when
        final ConcertData result = facade.getConcertForCode(CONCERT_CODE);

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(concertData);
    }

    @Test
    public void shouldReturnNullWhenConcertNotFound() {
        // given
        when(concertService.getConcertByCodeAndCatalogVersion(CONCERT_CODE, catalogVersion)).thenReturn(null);

        // when
        final ConcertData result = facade.getConcertForCode(CONCERT_CODE);

        // then
        assertThat(result).isNull();
    }
}
