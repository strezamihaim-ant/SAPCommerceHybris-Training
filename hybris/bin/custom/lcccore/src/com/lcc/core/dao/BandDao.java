package com.lcc.core.dao;

import com.lcc.core.model.BandModel;

import java.util.List;

public interface BandDao {

    List<BandModel> findAllBands();

    BandModel findBandByCode(String code);
}
