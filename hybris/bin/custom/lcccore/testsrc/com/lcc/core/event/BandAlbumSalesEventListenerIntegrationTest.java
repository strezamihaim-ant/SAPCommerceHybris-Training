package com.lcc.core.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.lcc.core.model.NewsModel;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Collections;

import javax.annotation.Resource;

import org.junit.Test;


@IntegrationTest
public class BandAlbumSalesEventListenerIntegrationTest extends ServicelayerBaseTest
{
	@Resource
	private EventService eventService;

	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Test
	public void shouldCreateNewsWhenBandAlbumSalesEventIsPublished() throws InterruptedException
	{
		// given
		final String bandCode = "queen";
		final String bandName = "Queen";
		final Long sales = 100000000L;

		// when
		eventService.publishEvent(new BandAlbumSalesEvent(bandCode, bandName, sales));
		Thread.sleep(2000);

		// then
		final SearchResult<NewsModel> result = flexibleSearchService.search(
				"SELECT {pk} FROM {News} WHERE {headline} = ?headline",
				Collections.singletonMap("headline", "Album sales update for band: " + bandName));

		assertEquals(1, result.getCount());

		final NewsModel news = result.getResult().get(0);
		assertNotNull(news.getDate());
		assertEquals("Album sales update for band: " + bandName, news.getHeadline());
		assertEquals("Band " + bandName + " (code: " + bandCode + ") has reached " + sales + " album sales.", news.getContent());
	}
}
