package com.lcc.core.service.impl;

import com.lcc.core.dao.BandDao;
import com.lcc.core.model.BandModel;
import com.lcc.core.service.BandService;

import java.util.List;

public class DefaultBandService implements BandService {

    private BandDao bandDao;

    @Override
    public List<BandModel> getBands() {
        return bandDao.findAllBands();
    }

    @Override
    public BandModel getBandForCode(final String code) {
        return bandDao.findBandByCode(code);
    }

    public void setBandDao(final BandDao bandDao) {
        this.bandDao = bandDao;
    }
}
