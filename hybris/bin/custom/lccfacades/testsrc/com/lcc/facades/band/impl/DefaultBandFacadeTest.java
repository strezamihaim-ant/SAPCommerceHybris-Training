package com.lcc.facades.band.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.lcc.core.model.BandModel;
import com.lcc.core.service.BandService;
import com.lcc.facades.band.data.BandData;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultBandFacadeTest {

    private static final String BAND_CODE = "A001";

    @Mock
    private BandService bandService;
    @Mock
    private Converter<BandModel, BandData> bandConverter;
    @Mock
    private BandModel bandModel;

    @InjectMocks
    private DefaultBandFacade facade;

    @Test
    public void shouldReturnConvertedBands() {
        // given
        final BandData bandData = new BandData();
        when(bandService.getBands()).thenReturn(List.of(bandModel));
        when(bandConverter.convertAll(List.of(bandModel))).thenReturn(List.of(bandData));

        // when
        final List<BandData> result = facade.getBands();

        // then
        assertThat(result).hasSize(1);
        assertThat(result).containsExactly(bandData);
    }

    @Test
    public void shouldReturnEmptyListWhenNoBands() {
        // given
        when(bandService.getBands()).thenReturn(Collections.emptyList());
        when(bandConverter.convertAll(Collections.emptyList())).thenReturn(Collections.emptyList());

        // when
        final List<BandData> result = facade.getBands();

        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void shouldReturnConvertedBandForCode() {
        // given
        final BandData bandData = new BandData();
        when(bandService.getBandForCode(BAND_CODE)).thenReturn(bandModel);
        when(bandConverter.convert(bandModel)).thenReturn(bandData);

        // when
        final BandData result = facade.getBandForCode(BAND_CODE);

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(bandData);
    }

    @Test
    public void shouldReturnNullWhenBandNotFound() {
        // given
        when(bandService.getBandForCode(BAND_CODE)).thenReturn(null);

        // when
        final BandData result = facade.getBandForCode(BAND_CODE);

        // then
        assertThat(result).isNull();
    }
}
