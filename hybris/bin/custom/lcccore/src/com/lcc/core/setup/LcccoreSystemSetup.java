/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.lcc.core.setup;

import static com.lcc.core.constants.LcccoreConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.lcc.core.constants.LcccoreConstants;
import com.lcc.core.service.LcccoreService;


@SystemSetup(extension = LcccoreConstants.EXTENSIONNAME)
public class LcccoreSystemSetup
{
	private final LcccoreService lcccoreService;

	public LcccoreSystemSetup(final LcccoreService lcccoreService)
	{
		this.lcccoreService = lcccoreService;
	}

	@SystemSetup(process = SystemSetup.Process.ALL, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		lcccoreService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return LcccoreSystemSetup.class.getResourceAsStream("/lcccore/sap-hybris-platform.png");
	}
}
