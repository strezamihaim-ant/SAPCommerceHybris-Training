package com.lcc.core.interceptors;

import com.lcc.core.model.ConcertModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import java.util.Date;


/**
 * Interceptor to validate that the concert date is in the future.
 */
public class ConcertDateValidateInterceptor implements ValidateInterceptor<ConcertModel>
{
	@Override
	public void onValidate(final ConcertModel concert, final InterceptorContext ctx) throws InterceptorException
	{
		final Date concertDate = concert.getDate();
		if (concertDate != null && concertDate.before(new Date()))
		{
			throw new InterceptorException("Concert date must be in the future.");
		}
	}
}
