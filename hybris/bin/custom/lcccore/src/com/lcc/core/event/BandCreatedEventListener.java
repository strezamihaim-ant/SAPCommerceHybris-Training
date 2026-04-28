package com.lcc.core.event;

import com.lcc.core.model.BandModel;
import com.lcc.core.model.NewsModel;

import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.event.events.AfterItemCreationEvent;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Date;

import org.apache.log4j.Logger;


public class BandCreatedEventListener extends AbstractEventListener<AfterItemCreationEvent>
{
	private static final Logger LOG = Logger.getLogger(BandCreatedEventListener.class);

	private ModelService modelService;

	@Override
	protected void onEvent(final AfterItemCreationEvent event)
	{
		try
		{
			final PK pk = (PK) event.getSource();
			final ItemModel item = modelService.get(pk);
			if (BandModel._TYPECODE.equals(item.getItemtype()))
			{
				final BandModel band = (BandModel) item;
				final NewsModel news = modelService.create(NewsModel.class);
				news.setDate(new Date());
				news.setHeadline("New band signed: " + band.getName());
				news.setContent("The band " + band.getName() + " has been signed.");
				modelService.save(news);
			}
		}
		catch (final Exception e)
		{
			LOG.error("Failed handling Band creation event", e);
		}
	}

	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}
}
