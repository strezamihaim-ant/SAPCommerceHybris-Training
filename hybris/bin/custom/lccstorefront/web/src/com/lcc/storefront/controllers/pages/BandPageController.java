package com.lcc.storefront.controllers.pages;

import com.lcc.facades.band.BandFacade;
import com.lcc.facades.band.data.BandData;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/bands")
public class BandPageController extends AbstractPageController
{
    private static final String BAND_LIST_CMS_PAGE = "bandListPage";

    @Resource(name = "bandFacade")
    private BandFacade bandFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String showBands(final HttpServletRequest request, final HttpServletResponse response, final Model model)
            throws CMSItemNotFoundException
    {
        final List<BandData> bands = bandFacade.getBands();
        model.addAttribute("bands", bands);

        final ContentPageModel cmsPage = getContentPageForLabelOrId(BAND_LIST_CMS_PAGE);
        storeCmsPageInModel(model, cmsPage);
        setUpMetaDataForContentPage(model, cmsPage);

        return getViewForPage(model);
    }
}
