package com.ts.codemetrics.utils;

public class Enums {
    public enum ProjectManagementTool {
        JIRA(1),
        TFS(2),
        GITHUB(3),
        GITLAB(4);
        private Integer projectManagementTool;
        private ProjectManagementTool(Integer projectManagementTool) {
            this.projectManagementTool = projectManagementTool;
        }
        public Integer value() {
            return this.projectManagementTool;
        }
    }

    public enum WorkItemType{
        Other(0),
        Epic(1),
        Bug(2),
        Story(3),
        SubTask(4);

        private Integer workItemType;
        private WorkItemType(Integer workItemType){
            this.workItemType = workItemType;
        }

        public Integer value(){
            return this.workItemType;
        }
    }

    public enum Severity{
        Other(0),
        Critical(1),
        High(2),
        Medium(3),
        Low(4);

        private Integer severity;
        private Severity(Integer severity){
            this.severity = severity;
        }

        public Integer value(){
            return this.severity;
        }
    }

    public enum Criticality{
        Other(0),
        High(1),
        Medium(2),
        Low(3);

        private Integer criticality;
        private Criticality(Integer criticality){
            this.criticality = criticality;
        }

        private Integer value(){
            return this.criticality;
        }
    }
}
