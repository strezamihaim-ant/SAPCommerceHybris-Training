package com.lcc.core.setup;

import com.lcc.core.constants.LcccoreConstants;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.servicelayer.impex.ImportConfig;
import de.hybris.platform.servicelayer.impex.ImportService;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@SystemSetup(extension = LcccoreConstants.EXTENSIONNAME)
public class ConcerttoursCustomSetup {
    private static final Logger LOG = Logger.getLogger(ConcerttoursCustomSetup.class);
    private static final String CONCERTTOURS_IMPEX = "/impex/concerttours.impex";

    private ImportService importService;

    @SystemSetup(type = SystemSetup.Type.PROJECT, process = SystemSetup.Process.ALL)
    public void createEssentialData() {
        LOG.info("=== Starting LCC Essential Data Setup ===");
        loadImpexFile();
        LOG.info("=== LCC Essential Data Setup Complete ===");
    }

    private void loadImpexFile() {
        try {
            final InputStream impexStream = this.getClass().getResourceAsStream(ConcerttoursCustomSetup.CONCERTTOURS_IMPEX);
            if (impexStream == null) {
                LOG.warn("ImpEx file not found: " + ConcerttoursCustomSetup.CONCERTTOURS_IMPEX);
                return;
            }

            final String impexContent = new String(impexStream.readAllBytes(), StandardCharsets.UTF_8);
            final ImportConfig config = new ImportConfig();

            config.setScript(impexContent);
            importService.importData(config);
            LOG.info("Successfully loaded ImpEx file: " + ConcerttoursCustomSetup.CONCERTTOURS_IMPEX);
        } catch (final IOException e) {
            LOG.error("Error importing ImpEx file: " + ConcerttoursCustomSetup.CONCERTTOURS_IMPEX, e);
            throw new RuntimeException("Failed to import LCC data", e);
        }
    }

    public void setImportService(final ImportService importService) {
        this.importService = importService;
    }
}