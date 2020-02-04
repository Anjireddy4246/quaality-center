package com.ts.codemetrics.service.provider.pmprovider.Jira.mapper;

import com.atlassian.jira.rest.client.api.domain.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ts.codemetrics.model.v1.ReleaseItemModel;
import com.ts.codemetrics.utils.Enums;

import java.util.Objects;

public class JiraResponseToModelMapper {
    public static ReleaseItemModel convertIssueResponseToModel(Issue issue) {
        ReleaseItemModel releaseItemModel = new ReleaseItemModel();
        if (Objects.nonNull(issue)) {
            releaseItemModel.setTitle(issue.getSummary());
            releaseItemModel.setDescription(issue.getDescription());
            Iterable<Version> versions = issue.getFixVersions();
            if (Objects.nonNull(versions) && versions.iterator().hasNext()) {
                String version = versions.iterator().next().getName();
                releaseItemModel.setFixedVersion(version);
                releaseItemModel.setReleaseNumber(version);
            }
            Iterable<Version> affectedVersions = issue.getAffectedVersions();
            if (Objects.nonNull(affectedVersions) && affectedVersions.iterator().hasNext()) {
                String version = affectedVersions.iterator().next().getName();
                releaseItemModel.setAffectedVersion(version);
                releaseItemModel.setReleaseNumber(version);
            }
            releaseItemModel.setItemCode(issue.getKey());
            Status status = issue.getStatus();
            releaseItemModel.setStatus(status.getName());
            IssueType issueType = issue.getIssueType();
            User assignee = issue.getAssignee();
            if(Objects.nonNull(assignee)){
                releaseItemModel.setAssignedTo(assignee.getEmailAddress());
            }
            User reportedBy = issue.getReporter();
            if(Objects.nonNull(reportedBy)){
                releaseItemModel.setReportedBy(reportedBy.getEmailAddress());
            }
            if(Objects.nonNull(issueType)){
                if(issueType.getName().equalsIgnoreCase("EPIC")){
                    releaseItemModel.setWorkItemType(Enums.WorkItemType.Epic);
                }
                else if(issueType.getName().equalsIgnoreCase("SUBTASK")){
                    releaseItemModel.setWorkItemType(Enums.WorkItemType.SubTask);
                }
                else if(issueType.getName().equalsIgnoreCase("STORY")){
                    releaseItemModel.setWorkItemType(Enums.WorkItemType.Story);
                }
                else if(issueType.getName().equalsIgnoreCase("BUG")){
                    releaseItemModel.setWorkItemType(Enums.WorkItemType.Bug);
                }
            }
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                releaseItemModel.setJsonResponse(objectMapper.writeValueAsString(issue));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return releaseItemModel;
    }


}
