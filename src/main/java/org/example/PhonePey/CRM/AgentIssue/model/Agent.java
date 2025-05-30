package org.example.PhonePey.CRM.AgentIssue.model;

import org.example.PhonePey.CRM.AgentIssue.Enum.IssueType;

import java.util.*;

public class Agent {
    private final String email;
    private final String name;
    private final Set<IssueType> expertise;
    private final List<Issue> history = new ArrayList<>();
    private Issue currentIssue;

    public Agent(String email, String name, List<IssueType> expertise) {
        this.email = email;
        this.name = name;
        this.expertise = new HashSet<>(expertise);
    }

    public boolean canHandle(IssueType issueType) {
        return expertise.contains(issueType);
    }

    public synchronized  void assignIssue(Issue issue) {
        this.currentIssue = issue;
    }

    public synchronized void resolveCurrentIssue() {
        if (currentIssue != null) {
            history.add(currentIssue);
            currentIssue = null;
        }
    }

    public Issue getCurrentIssue() {
        return currentIssue;
    }

    public String getEmail() { return email; }

    public List<Issue> getHistory() { return history; }
}

