package org.example.PhonePey.CRM.AgentIssue.service;

import org.example.PhonePey.CRM.AgentIssue.model.Agent;

public interface AgentObserver {
    void onAgentAvailable(Agent agent);
}

