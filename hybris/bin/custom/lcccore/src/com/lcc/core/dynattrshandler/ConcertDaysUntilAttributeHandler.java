package com.lcc.core.dynattrshandler;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;
import com.lcc.core.model.ConcertModel;
import java.time.Duration;
import java.time.Instant;
import org.springframework.stereotype.Component;

/**
 * Calculates the number of days until a concert date.
 *
 * This is a read-only computed attribute that depends on concertDate
 * and the current system time.
 */
public class ConcertDaysUntilAttributeHandler extends AbstractDynamicAttributeHandler<Long, ConcertModel> {

    /**
     * GET method: called when the attribute is read
     *
     * @param concertModel The Concert item being accessed
     * @return The number of days until the concert
     */
    @Override
    public Long get(final ConcertModel concertModel) {
        // Handle null case
        if (concertModel.getDate() == null) {
            return null;
        }

        // Get the concert date
        Instant concertInstant = concertModel.getDate().toInstant();
        Instant now = Instant.now();

        // If concert is in the past, return 0
        if (concertInstant.isBefore(now)) {
            return Long.valueOf(0);
        }

        // Calculate days between now and concert date
        Duration duration = Duration.between(now, concertInstant);
        long days = duration.toDays();

        return Long.valueOf((int) days);
    }

    /**
     * SET method: called when the attribute is written
     *
     * Usually dynamic attributes are read-only, but if write="true"
     * is set in items.xml, this method is called on set operations.
     *
     * For read-only attributes, you can throw an exception or do nothing.
     */
    @Override
    public void set(final ConcertModel concertModel, final Long value) {
        // Read-only attribute; do nothing or throw exception
        throw new UnsupportedOperationException("daysUntilConcert is read-only");
    }
}