package com.lcc.core.jobs;

import com.lcc.core.model.ConcertModel;
import com.lcc.core.service.ConcertService;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.util.mail.MailUtils;
import org.apache.commons.mail.Email;

import java.util.List;
import java.util.Locale;

public class ConcertUpcomingNotificationJob extends AbstractJobPerformable<CronJobModel> {

    private ConcertService concertService;
    private CatalogVersionService catalogVersionService;
    private I18NService i18NService;

    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("electronicsProductCatalog", "Online");
        List<ConcertModel> concerts = (concertService.getConcertsByCatalogVersion(catalogVersionModel));

        if (concerts == null || concerts.isEmpty()) {
            return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        }

        for (ConcertModel concert : concerts) {
            if (concert.getBaseProduct().getCode() == null) {
                return new PerformResult(CronJobResult.ERROR, CronJobStatus.FINISHED);
            }

            try {
                Long daysLeft = concert.getDaysLeft();
                if (daysLeft != null && daysLeft > 0) {
                    String mailGroup = "testgroup@yopmail.com";
                    sendCountdownEmail(mailGroup, concert, daysLeft);
                }
            } catch (Exception e) {
                // LOG.error("Failed to send email for concert: " + concert.getCode(), e);
            }
        }

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);

    }

    private void sendCountdownEmail(String recipient, ConcertModel concert, Long daysLeft) throws Exception {
        final Locale currentLocale = i18NService.getCurrentLocale();

        Email email;
        email = MailUtils.getPreConfiguredEmail();
        email.addTo(recipient);
        email.setSubject("Concert Countdown: " + concert.getName(currentLocale));
        email.setMsg("Countdown started! There are " + daysLeft + " days left!!!");

        email.send();
    }

    public void setConcertService(ConcertService concertService) {
        this.concertService = concertService;
    }

    public void setCatalogVersionService(CatalogVersionService catalogVersionService) {
        this.catalogVersionService = catalogVersionService;
    }

    public void setI18NService(I18NService i18NService) {
        this.i18NService = i18NService;
    }
}

