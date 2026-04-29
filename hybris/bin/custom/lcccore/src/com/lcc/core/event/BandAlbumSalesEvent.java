package com.lcc.core.event;

import de.hybris.platform.servicelayer.event.ClusterAwareEvent;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;


public class BandAlbumSalesEvent extends AbstractEvent implements ClusterAwareEvent
{
	private final String code;
	private final String name;
	private final Long sales;

	public BandAlbumSalesEvent(final String code, final String name, final Long sales)
	{
		this.code = code;
		this.name = name;
		this.sales = sales;
	}

	@Override
	public boolean publish(final int sourceNodeId, final int targetNodeId)
	{
		return sourceNodeId == targetNodeId;
	}

	public String getCode()
	{
		return code;
	}

	public String getName()
	{
		return name;
	}

	public Long getSales()
	{
		return sales;
	}
}
