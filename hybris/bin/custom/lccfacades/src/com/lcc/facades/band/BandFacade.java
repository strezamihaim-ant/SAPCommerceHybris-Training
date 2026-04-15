package com.lcc.facades.band;

import com.lcc.facades.band.data.BandData;

import java.util.List;

public interface BandFacade {


    List<BandData> getBands();

    BandData getBandForCode(String code);
}
