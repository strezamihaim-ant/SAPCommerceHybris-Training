package com.lcc.facades.concert.impl;

import static org.assertj.core.api.Assertions.assertThat;

import com.lcc.facades.concert.ConcertFacade;
import com.lcc.facades.concert.data.ConcertData;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTest;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

@IntegrationTest
public class DefaultConcertFacadeIntegrationTest extends ServicelayerTest {

    private static final String CONCERT_CODE = "testConcert001";
    private static final String CONCERT_VENUE = "Test Venue";

    @Resource
    private ConcertFacade concertFacade;

    @Before
    public void setUp() throws ImpExException {
        importCsv("/com/lcc/facades/concert/impl/test-concert-facade.impex", "UTF-8");
    }

    @Test
    public void shouldReturnAllConcerts() {
        // when
        final List<ConcertData> result = concertFacade.getConcerts();

        // then
        assertThat(result).isNotEmpty();
        assertThat(result).extracting(ConcertData::getCode).contains(CONCERT_CODE);
    }

    @Test
    public void shouldReturnConcertForCode() {
        // when
        final ConcertData result = concertFacade.getConcertForCode(CONCERT_CODE);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(CONCERT_CODE);
        assertThat(result.getVenue()).isEqualTo(CONCERT_VENUE);
    }

    @Test
    public void shouldReturnNullForUnknownCode() {
        // when
        final ConcertData result = concertFacade.getConcertForCode("nonExistentCode");

        // then
        assertThat(result).isNull();
    }
}
