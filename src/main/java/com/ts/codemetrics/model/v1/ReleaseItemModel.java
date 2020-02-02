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
    private String title;
    private String description;
    private String status;
    private String releaseNo;
    private String fixedVersion;
    private String affectedVersion;
    private String jsonResponse;
}
