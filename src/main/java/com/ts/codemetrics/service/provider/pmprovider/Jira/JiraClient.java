package com.ts.codemetrics.service.provider.pmprovider.Jira;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueType;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Optional;

//@Component
public class JiraClient {
    private String username;
    private String password;
    private String jiraUrl;
    private JiraRestClient restClient;

    public JiraClient(String username, String password, String jiraUrl) {
        this.username = username;
        this.password = password;
        this.jiraUrl = jiraUrl;
        this.restClient = getJiraRestClient();
    }

    public Optional<Issue> getIssue(String issueKey) {
        try {
            SearchResult issues = findIssue(issueKey);
            if (issues.getTotal() > 0) {
                Issue issue = restClient.getIssueClient().getIssue(issueKey).claim();
                return Optional.of(issue);
            } else {
                return Optional.empty();
            }
        }
        catch(Exception ex){
            System.out.println(ex.toString());
        }
        return Optional.empty();
    }

    private SearchResult findIssue(String id) {
        return restClient.getSearchClient().searchJql("issue = " + id).claim();
    }

    private JiraRestClient getJiraRestClient() {
        return new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(getJiraUri(), this.username, this.password);
    }

    private URI getJiraUri() {
        return URI.create(this.jiraUrl);
    }
}
