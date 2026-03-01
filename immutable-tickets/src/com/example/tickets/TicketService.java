package com.example.tickets;

import java.util.List;

/**
 * Service layer that creates and "updates" tickets.
 *
 * After refactor: no mutation. "Updates" create new ticket instances
 * via toBuilder() for immutability.
 */
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        return new IncidentTicket.Builder(id, reporterEmail, title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .addTag("NEW")
                .build();
    }

    public IncidentTicket escalateToCritical(IncidentTicket t) {
        // Create a new ticket with updated priority and additional tag
        return t.toBuilder()
                .priority("CRITICAL")
                .addTag("ESCALATED")
                .build();
    }

    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        // Create a new ticket with the assignee set
        return t.toBuilder()
                .assigneeEmail(assigneeEmail)
                .build();
    }
}
