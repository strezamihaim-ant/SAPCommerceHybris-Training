package com.lcc.facades.concert;

import com.lcc.facades.concert.data.ConcertData;

import java.util.List;

public interface ConcertFacade {

    List<ConcertData> getConcerts();

    ConcertData getConcertForCode(String code);
}
