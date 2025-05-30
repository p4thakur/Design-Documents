package org.example.PhonePey.CRM.AgentIssue.service;

import org.example.PhonePey.CRM.AgentIssue.Enum.IssueType;
import org.example.PhonePey.CRM.AgentIssue.model.Agent;
import org.example.PhonePey.CRM.AgentIssue.model.Issue;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class IssueService implements AgentObserver {
//    private final Map<String, Issue> issues = new HashMap<>();
//    private final Map<String, List<Issue>> customerEmailToIssues = new HashMap<>();
//    private final List<Issue> unassignedIssues = new ArrayList<>();
    private final List<Issue> unassignedIssues = Collections.synchronizedList(new LinkedList<>());
    private final Map<String, Issue> issues = new ConcurrentHashMap<>();
    private final Map<String, List<Issue>> customerEmailToIssues = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);//creating my own ID

    private final AgentService agentService;
    private final IssueDrivenAssignmentStrategy issueStrategy;
    private final AgentDrivenAssignmentStrategy agentStrategy;

    public IssueService(AgentService agentService, IssueDrivenAssignmentStrategy issueStrategy,
                        AgentDrivenAssignmentStrategy agentStrategy) {
        this.agentService = agentService;
        this.issueStrategy = issueStrategy;
        this.agentStrategy = agentStrategy;
        this.agentService.registerObserver(this);
    }

    public Issue createIssue(String transactionId, IssueType issueType, String subject, String description, String email) {
        String issueId= "ISSUE-" + idCounter.getAndIncrement();
        Issue issue = new Issue(issueId,transactionId, issueType, subject, description, email);
        System.out.println("📩 Issue created: " + issue.getIssueId() + " for " + email + " [" + issueType + "]");

        Agent assigned = issueStrategy.assign(agentService.getFreeAgents(), issue);
        if (assigned != null) {
            assignIssueToAgent(issue, assigned);
        } else {
            unassignedIssues.add(issue);
            System.out.println("⏳ No agent available for issue: " + issue.getIssueId() + ". Added to waiting list.");
        }

        issues.put(issue.getIssueId(), issue);
        customerEmailToIssues.computeIfAbsent(email, k -> new ArrayList<>()).add(issue);
        return issue;
    }

    public List<Issue> getIssuesByCustomerEmail(String email) {
        return customerEmailToIssues.getOrDefault(email, new ArrayList<>());
    }

    public Issue getIssueById(String issueId) {
        return issues.get(issueId);
    }

    public void resolveIssue(String agentEmail) {
        Agent agent = agentService.getAgentByEmail(agentEmail);
        if (agent != null && agent.getCurrentIssue() != null) {
            Issue issue = agent.getCurrentIssue();
            issue.markResolved();
            System.out.println("✅ Issue resolved: " + issue.getIssueId() + " by agent " + agent.getEmail());
            agent.resolveCurrentIssue();
            agentService.markFree(agent); // This will notify unassigned issues via observer
        }
    }

    @Override
    public void onAgentAvailable(Agent agent) {
        Iterator<Issue> iterator = unassignedIssues.iterator();
        agentStrategy.assignToAvailableAgent(agent, unassignedIssues);
    }

    private void assignIssueToAgent(Issue issue, Agent agent) {
        issue.assignAgent(agent);
        agent.assignIssue(issue);
        agentService.markBusy(agent);
        System.out.println("🤝 Assigned issue " + issue.getIssueId() + " to agent " + agent.getEmail());
    }
}
