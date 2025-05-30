package org.example.PhonePey.CRM.AgentIssue.model;

import org.example.PhonePey.CRM.AgentIssue.Enum.IssueType;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Issue {
    private final String issueId;
    private final AtomicInteger idCounter = new AtomicInteger(1);
    private final String transactionId;
    private final IssueType issueType;
    private final String subject;
    private final String description;
    private final String email;
    private boolean resolved;
    private Agent assignedAgent;

    public Issue(String issueId, String transactionId, IssueType issueType, String subject, String description, String email) {
        this.issueId = issueId;
        this.transactionId = transactionId;
        this.issueType = issueType;
        this.subject = subject;
        this.description = description;
        this.email = email;
        this.resolved = false;
    }

    public String getIssueId() { return issueId; }
    public IssueType getIssueType() { return issueType; }
    public String getEmail() { return email; }
    public boolean isResolved() { return resolved; }
    public Agent getAssignedAgent() { return assignedAgent; }

    public void assignAgent(Agent agent) {
        this.assignedAgent = agent;
    }

    public void markResolved() {
        this.resolved = true;
    }

    @Override
    public String toString() {
        return "Issue{" + issueId + ", " + subject + ", " + issueType + ", Resolved=" + resolved + "}";
    }
}
