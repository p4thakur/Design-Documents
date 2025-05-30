package org.example.PhonePey.CRM.AgentIssue.service;

import org.example.PhonePey.CRM.AgentIssue.Enum.IssueType;
import org.example.PhonePey.CRM.AgentIssue.model.Agent;
import org.example.PhonePey.CRM.AgentIssue.model.Issue;

import java.util.*;

public class AgentService {
    private final Map<String, Agent> agents = new HashMap<>();
    private final Set<Agent> freeAgents = new HashSet<>();
    private AgentObserver observer;

    public void registerObserver(AgentObserver observer) {
        this.observer = observer;
    }

    public void addAgent(String email, String name, List<IssueType> expertise) {
        Agent agent = new Agent(email, name, expertise);
        agents.put(email, agent);
        freeAgents.add(agent);
        System.out.println("✅ Agent created: " + name + " (" + email + ")");
    }

    public Agent getAgentByEmail(String email) {
        return agents.get(email);
    }

    public List<Agent> getFreeAgents() {
        return new ArrayList<>(freeAgents);
    }

    public void markBusy(Agent agent) {
        freeAgents.remove(agent);
    }

    public void markFree(Agent agent) {
        freeAgents.add(agent);
        if (observer != null) {
            observer.onAgentAvailable(agent);
        }
    }

    public List<Issue> getAgentHistory(String email) {
        Agent agent = agents.get(email);
        return agent != null ? agent.getHistory() : Collections.emptyList();
    }
}

