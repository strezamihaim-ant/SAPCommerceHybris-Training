package com.lcc.core.dynattrshandler;

import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import com.lcc.core.model.ConcertModel;
import java.time.Duration;
import java.time.Instant;

/**
 * Calculates the number of days until a concert date.
 *
 * This is a read-only computed attribute that depends on concertDate
 * and the current system time.
 *
 * Implements the DynamicAttributeHandler interface directly (not extending
 * AbstractDynamicAttributeHandler). This provides maximum flexibility and
 * explicit control over the handler implementation.
 *
 * Uses Long for greater range and precision in day calculations.
 */
public class ConcertDaysUntilAttributeHandler implements DynamicAttributeHandler<Long, ConcertModel> {

    /**
     * GET method: called when the attribute is read
     *
     * @param concertModel The Concert item being accessed
     * @return The number of days until the concert as a Long
     */
    @Override
    public Long get(final ConcertModel concertModel) {
        // Handle null case
        if (concertModel == null || concertModel.getDate() == null) {
            return null;
        }

        // Get the concert date as an Instant
        final Instant concertInstant = concertModel.getDate().toInstant();
        final Instant now = Instant.now();

        // If concert is in the past, return 0
        if (concertInstant.isBefore(now)) {
            return Long.valueOf(0L);
        }

        // Calculate days between now and concert date
        final Duration duration = Duration.between(now, concertInstant);
        final long days = duration.toDays();

        return Long.valueOf(days);
    }

    /**
     * SET method: called when the attribute is written
     *
     * This attribute is read-only, so this method throws an exception
     * if any attempt is made to set it.
     *
     * @param concertModel The Concert item
     * @param value The attempted new value
     * @throws UnsupportedOperationException always, as this is a read-only attribute
     */
    @Override
    public void set(final ConcertModel concertModel, final Long value) {
        throw new UnsupportedOperationException("daysUntilConcert is a read-only attribute");
    }
}