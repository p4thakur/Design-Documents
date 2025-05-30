package org.example.PhonePey.CRM.AgentIssue.Controller;

import org.example.PhonePey.CRM.AgentIssue.Enum.IssueType;
import org.example.PhonePey.CRM.AgentIssue.service.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        AgentService agentService = new AgentService();
        IssueDrivenAssignmentStrategy strategy = new ExpertiseBasedStrategy();
        AgentDrivenAssignmentStrategy strategy2 = new ExpertiseBasedStrategy();
        IssueService issueService = new IssueService(agentService, strategy,strategy2);

        agentService.addAgent("a@x.com", "Agent A", Arrays.asList(IssueType.PAYMENT_FAILURE));
        agentService.addAgent("b@x.com", "Agent B", Arrays.asList(IssueType.PENDING_REFUND));

        // Create issue immediately assigned
        issueService.createIssue("txn1", IssueType.PAYMENT_FAILURE, "Failed payment", "Money deducted", "user1@mail.com");

        // Create issue with no matching agent yet
        issueService.createIssue("txn2", IssueType.ACCOUNT_BLOCKED, "Blocked", "Cannot login", "user2@mail.com");


        // Now add agent for ACCOUNT_BLOCKED
        agentService.addAgent("c@x.com", "Agent C", Arrays.asList(IssueType.ACCOUNT_BLOCKED));

        // Resolve one
        issueService.resolveIssue("a@x.com");
    }
}

