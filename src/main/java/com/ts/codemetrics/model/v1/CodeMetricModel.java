package com.ts.codemetrics.model.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CodeMetricModel {
    private Long id;
    private String jobName;
    private String buildNumber;
    private String cqTaskId;
    private String commitId;
    private String cqStatusUrl;
    private String jobStatus;
    private String changedFiles;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date jobStartedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date jobEndedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date scanDate;
    private String userName;
    private String userEmailId;
}
