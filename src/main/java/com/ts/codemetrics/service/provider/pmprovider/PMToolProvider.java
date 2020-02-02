package com.ts.codemetrics.service.provider.pmprovider;

import com.ts.codemetrics.model.v1.ExternalPMToolConfig;
import com.ts.codemetrics.model.v1.PMToolConfigModel;
import com.ts.codemetrics.model.v1.ReleaseItemModel;
import org.springframework.stereotype.Service;

public interface PMToolProvider {
    ReleaseItemModel getIssue(PMToolConfigModel pmToolConfigModel, String issueKey);
    String providerName();
}
