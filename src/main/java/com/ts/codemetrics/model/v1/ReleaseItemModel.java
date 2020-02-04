package com.ts.codemetrics.model.v1;

import com.ts.codemetrics.utils.Enums;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ReleaseItemModel extends BaseModel {
    private Long Id;
    private String itemCode;
    private Enums.WorkItemType workItemType;
    private String parentItemId;
    private Enums.Severity severity;
    private Long projectId;
    private String title;
    private String description;
    private String status;
    private String releaseNumber;
    private String fixedVersion;
    private String affectedVersion;
    private String jsonResponse;
    private String assignedTo;
    private String reportedBy;
    private boolean includeInReleaseNote = true;
}
