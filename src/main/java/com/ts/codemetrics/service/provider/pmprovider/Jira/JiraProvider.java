package com.ts.codemetrics.service.provider.pmprovider.Jira;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.ts.codemetrics.model.v1.PMToolConfigModel;
import com.ts.codemetrics.model.v1.ReleaseItemModel;
import com.ts.codemetrics.service.provider.pmprovider.Jira.mapper.JiraResponseToModelMapper;
import com.ts.codemetrics.service.provider.pmprovider.PMToolProvider;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JiraProvider implements PMToolProvider {
    @Override
    public ReleaseItemModel getIssue(PMToolConfigModel pmToolConfigModel, String issueKey) {
        ReleaseItemModel releaseItemModel = new ReleaseItemModel();
        JiraClient jiraClient = new JiraClient(pmToolConfigModel.getLoginId(),
                pmToolConfigModel.getPassword(), pmToolConfigModel.getUrl());
        Optional<Issue> issue = jiraClient.getIssue(issueKey);
        if (issue.isPresent()) {
            releaseItemModel = JiraResponseToModelMapper.convertIssueResponseToModel(issue.get());
        } else {
            releaseItemModel.setSuccess(Boolean.FALSE);
        }
        return releaseItemModel;
    }

    @Override
    public String providerName() {
        return "JIRA";
    }
}
