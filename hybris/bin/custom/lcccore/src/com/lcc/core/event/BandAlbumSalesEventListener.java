package com.lcc.core.event;

import com.lcc.core.model.NewsModel;

import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Date;


public class BandAlbumSalesEventListener extends AbstractEventListener<BandAlbumSalesEvent>
{
	private ModelService modelService;

	@Override
	protected void onEvent(final BandAlbumSalesEvent event)
	{
		final NewsModel news = modelService.create(NewsModel.class);
		news.setDate(new Date());
		news.setHeadline("Album sales update for band: " + event.getName());
		news.setContent("Band " + event.getName() + " (code: " + event.getCode() + ") has reached " + event.getSales() + " album sales.");
		modelService.save(news);
	}

	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}
}
