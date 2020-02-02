package com.ts.codemetrics.controller.v1;

import com.ts.codemetrics.service.provider.pmprovider.Jira.JiraClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.PathParam;

@CrossOrigin
@RestController
@RequestMapping("jira/v1")
public class JiraController {

    @GetMapping("/{issueKey}")
    public ResponseEntity<String> getIssueDescription(@PathParam("issueKey") String issueKey){
        JiraClient jiraClient = new JiraClient("anjireddy.kata@nextsphere.com","hwHfGPPiHooyeQ6NxOHn2090", "https://nextsphere.atlassian.net/");
        issueKey="NMC-734";
        jiraClient.getIssue(issueKey);
        return ResponseEntity.ok("Success");
    }
}
