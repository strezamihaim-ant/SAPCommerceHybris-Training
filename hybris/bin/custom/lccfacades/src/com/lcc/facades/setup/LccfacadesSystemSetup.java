/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.lcc.facades.setup;

import static com.lcc.facades.constants.LccfacadesConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.lcc.facades.constants.LccfacadesConstants;
import com.lcc.facades.service.LccfacadesService;


@SystemSetup(extension = LccfacadesConstants.EXTENSIONNAME)
public class LccfacadesSystemSetup
{
	private final LccfacadesService lccfacadesService;

	public LccfacadesSystemSetup(final LccfacadesService lccfacadesService)
	{
		this.lccfacadesService = lccfacadesService;
	}

	@SystemSetup(process = SystemSetup.Process.ALL, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		lccfacadesService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return LccfacadesSystemSetup.class.getResourceAsStream("/lccfacades/sap-hybris-platform.png");
	}
}
