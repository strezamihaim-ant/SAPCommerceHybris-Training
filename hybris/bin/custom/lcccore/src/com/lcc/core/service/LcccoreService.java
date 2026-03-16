/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.lcc.core.service;

public interface LcccoreService
{
	String getHybrisLogoUrl(String logoCode);

	void createLogo(String logoCode);
}
