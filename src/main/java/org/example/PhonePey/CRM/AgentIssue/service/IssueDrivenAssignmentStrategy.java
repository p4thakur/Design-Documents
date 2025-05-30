package org.example.PhonePey.CRM.AgentIssue.service;

import org.example.PhonePey.CRM.AgentIssue.model.Agent;
import org.example.PhonePey.CRM.AgentIssue.model.Issue;

import java.util.List;

public interface IssueDrivenAssignmentStrategy {
    Agent assign(List<Agent> freeAgents, Issue issue);
}

