package org.example.PhonePey.CRM.AgentIssue.service;

import org.example.PhonePey.CRM.AgentIssue.model.Agent;
import org.example.PhonePey.CRM.AgentIssue.model.Issue;

import java.util.List;

public class ExpertiseBasedStrategy implements IssueDrivenAssignmentStrategy,AgentDrivenAssignmentStrategy {
    @Override
    public Agent assign(List<Agent> freeAgents, Issue issue) {
        for (Agent agent : freeAgents) {
            if (agent.canHandle(issue.getIssueType())) {
                return agent;
            }
        }
        return null;
    }

    @Override
    public void assignToAvailableAgent(Agent agent, List<Issue> unassignedIssues) {
        for (Issue issue : unassignedIssues) {
            if (agent.canHandle(issue.getIssueType())) {
                issue.assignAgent(agent);
                unassignedIssues.remove(issue);
                return;
            }
        }
    }
}

