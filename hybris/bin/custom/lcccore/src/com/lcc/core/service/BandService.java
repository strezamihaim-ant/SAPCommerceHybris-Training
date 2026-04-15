package com.lcc.core.service;

import com.lcc.core.model.BandModel;

import java.util.List;

public interface BandService {

    List<BandModel> getBands();

    BandModel getBandForCode(String code);
}
