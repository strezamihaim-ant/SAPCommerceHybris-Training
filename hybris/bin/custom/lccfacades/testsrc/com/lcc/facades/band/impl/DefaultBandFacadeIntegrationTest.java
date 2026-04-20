package com.lcc.facades.band.impl;

import static org.assertj.core.api.Assertions.assertThat;

import com.lcc.facades.band.BandFacade;
import com.lcc.facades.band.data.BandData;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTest;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

@IntegrationTest
public class DefaultBandFacadeIntegrationTest extends ServicelayerTest {

    private static final String BAND_CODE = "testBand001";
    private static final String BAND_NAME = "Test Band";

    @Resource
    private BandFacade bandFacade;

    @Before
    public void setUp() throws ImpExException {
        importCsv("/com/lcc/facades/band/impl/test-band-facade.impex", "UTF-8");
    }

    @Test
    public void shouldReturnAllBands() {
        // when
        final List<BandData> result = bandFacade.getBands();

        // then
        assertThat(result).isNotEmpty();
        assertThat(result).extracting(BandData::getCode).contains(BAND_CODE);
    }

    @Test
    public void shouldReturnBandForCode() {
        // when
        final BandData result = bandFacade.getBandForCode(BAND_CODE);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(BAND_CODE);
        assertThat(result.getName()).isEqualTo(BAND_NAME);
    }

    @Test
    public void shouldReturnNullForUnknownCode() {
        // when
        final BandData result = bandFacade.getBandForCode("nonExistentCode");

        // then
        assertThat(result).isNull();
    }
}
