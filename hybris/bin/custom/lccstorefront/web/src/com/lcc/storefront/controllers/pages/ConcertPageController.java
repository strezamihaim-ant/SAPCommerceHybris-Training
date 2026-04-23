package com.lcc.storefront.controllers.pages;

import com.lcc.facades.concert.ConcertFacade;
import com.lcc.facades.concert.data.ConcertData;
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
@RequestMapping("/concerts")
public class ConcertPageController extends AbstractPageController
{
    private static final String CONCERT_LIST_CMS_PAGE = "concertListPage";

    @Resource(name = "concertFacade")
    private ConcertFacade concertFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String showConcerts(final HttpServletRequest request, final HttpServletResponse response, final Model model)
            throws CMSItemNotFoundException
    {
        final List<ConcertData> concerts = concertFacade.getConcerts();
        model.addAttribute("concerts", concerts);

        final ContentPageModel cmsPage = getContentPageForLabelOrId(CONCERT_LIST_CMS_PAGE);
        storeCmsPageInModel(model, cmsPage);
        setUpMetaDataForContentPage(model, cmsPage);

        return getViewForPage(model);
    }
}
