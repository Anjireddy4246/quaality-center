package com.ts.codemetrics.service.cqprovider.sonarqube;

public class SonarMetricDefinition {
    /**
     * It is the complexity calculated based on the number of paths through the code.
     * Whenever the control flow of a function splits, the complexity counter gets incremented by one.
     * Each function has a minimum complexity of 1. This calculation varies slightly by language because
     * keywords and functionalities do.
     */
    public static final String COMPLEXITY = "complexity";
    /**
     * How hard it is to understand the code's control flow.
     */
    public static final String COGNITIVE_COMPLEXITY = "cognitive_complexity";

    /**
     * Number of new issues.
     */
    public static final String NEW_VIOLATIONS = "new_violations";

    /**
     * Number of new blocker issues.
     */
    public static final String NEW_BLOCKER_VIOLATIONS = "new_blocker_violations";

    /**
     * Number of new critical issues.
     */
    public static final String NEW_CRITICAL_VIOLATIONS = "new_critical_violations";

    /**
     * Number of new major issues.
     */
    public static final String NEW_MAJOR_VIOLATIONS = "new_major_violations";

    /**
     * Number of new minor issues.
     */
    public static final String NEW_MINOR_VIOLATIONS = "new_minor_violations";

    /**
     * Number of new info issues.
     */
    public static final String NEW_INFO_VIOLATIONS = "new_info_violations";

    /**
     * Number of issues
     */
    public static final String VIOLATIONS = "violations";

    /**
     * Number of open issues
     */
    public static final String OPEN_ISSUES = "open_issues";

    /**
     * Number of code smells
     */
    public static final String CODE_SMELLS = "code_smells";

    /**
     * Number of new code smells
     */
    public static final String NEW_CODE_SMELLS = "new_code_smells";

    /**
     * MAINTAINABILITY RATING
     * Rating given to your project related to the value of your Technical Debt Ratio. The default Maintainability Rating grid is:
     *
     * A=0-0.05, B=0.06-0.1, C=0.11-0.20, D=0.21-0.5, E=0.51-1
     *
     * The Maintainability Rating scale can be alternately stated by saying that if the outstanding remediation cost is:
     *
     * <=5% of the time that has already gone into the application, the rating is A
     * between 6 to 10% the rating is a B
     * between 11 to 20% the rating is a C
     * between 21 to 50% the rating is a D
     * anything over 50% is an E
     */
    public static final String MAINTAINABILITY_RATING = "sqale_rating";

    /**
     * Effort to fix all maintainability issues. The measure is stored in minutes in the DB.
     * An 8-hour day is assumed when values are shown in days.
     */
    public static final String TECHNICAL_DEBT = "sqale_index";

    /**
     * Technical Debt of new code
     */
    public static final String TECHNICAL_DEBT_NEW_CODE = "new_technical_debt";

    /**
     *Ratio between the cost to develop the software and the cost to fix it. The Technical Debt Ratio formula is:
     *
     * 	Remediation cost / Development cost
     * Which can be restated as:
     *
     * 	Remediation cost / (Cost to develop 1 line of code * Number of lines of code)
     * The value of the cost to develop a line of code is 0.06 days.
     */

    public static final String TECHNICAL_DEBT_RATIO = "sqale_debt_ratio";

    public static final String NEW_TECHNICAL_DEBT_RATIO = "new_sqale_debt_ratio";
    /**
     * State of the Quality Gate associated to your Project. Possible values are : ERROR, WARN, OK
     */
    public static final String QUALITY_GATE_STATUS = "alert_status";

    /**
     * For all the conditions of your Quality Gate, you know which condition is failing and which is not.
     */
    public static final String QUALITY_GATES_DETAILS = "quality_gate_details";

    /**
     * Number of bugs.
     */
    public static final String BUGS = "bugs";

    /**
     * Number of new bugs.
     */
    public static final String NEW_BUGS = "new_bugs";

    /**
     * A = 0 Bug
     * B = at least 1 Minor Bug
     * C = at least 1 Major Bug
     * D = at least 1 Critical Bug
     * E = at least 1 Blocker Bug
     */
    public static final String RELIABILITY_RATING = "reliability_rating";

    /**
     * Effort to fix all bug issues. The measure is stored in minutes in the DB.
     * An 8-hour day is assumed when values are shown in days.
     */
    public static final String RELIABILITY_REMEDIATION_EFFORT ="reliability_remediation_effort";

    /**
     * Number of vulnerabilities.
     */
    public static final String VULNERABILITIES ="vulnerabilities";

    /**
     * Number of new vulnerabilities.
     */
    public static final String NEW_VULNERABILITIES ="new_vulnerabilities";

    /**
     * A = 0 Vulnerability
     * B = at least 1 Minor Vulnerability
     * C = at least 1 Major Vulnerability
     * D = at least 1 Critical Vulnerability
     * E = at least 1 Blocker Vulnerability
     */
    public static final String SECURITY_RATING ="security_rating";

    /**
     * Effort to fix all vulnerability issues. The measure is stored in minutes in the DB.
     * An 8-hour day is assumed when values are shown in days.
     */
    public static final String SECURITY_REMEDIATION_EFFORT ="security_remediation_effort";

    /**
     * Effort to fix new vulnerability issues. The measure is stored in minutes in the DB.
     * An 8-hour day is assumed when values are shown in days.
     */
    public static final String NEW_SECURITY_REMEDIATION_EFFORT ="new_security_remediation_effort";

    /**
     * Number of physical lines that contain at least one character
     * which is neither a whitespace nor a tabulation nor part of a comment.
     */
    public static final String NCLOC ="ncloc";

    public static final String BRANCH_COVERAGE ="branch_coverage";

    public static final String NEW_BRANCH_COVERAGE ="new_branch_coverage";

    /**
     * List of covered conditions.
     */
    public static final String BRANCH_COVERAGE_HITS_DATA ="branch_coverage_hits_data";

    /**
     * Number of conditions by line.
     */
    public static final String CONDITIONS_BY_LINE ="conditions_by_line";

    /**
     * Number of covered conditions by line.
     */
    public static final String COVERED_CONDITIONS_BY_LINE ="covered_conditions_by_line";

    /**
     * It is a mix of Line coverage and Condition coverage.
     */
    public static final String COVERAGE ="coverage";

    /**
     * Identical to Coverage but restricted to new / updated source code.
     */
    public static final String NEW_COVERAGE ="new_coverage";

    /**
     *
     */
    public static final String LINE_COVERAGE ="line_coverage";

    /**
     * Identical to Line coverage but restricted to new / updated source code.
     */
    public static final String NEW_LINE_COVERAGE ="new_line_coverage";

    /**
     * List of covered lines.
     */
    public static final String COVERAGE_LINE_HITS ="coverage_line_hits_data";

    /**
     * Number of lines of code which could be covered by unit tests
     * (for example, blank lines or full comments lines are not considered as lines to cover).
     */
    public static final String LINES_TO_COVER ="lines_to_cover";

    /**
     * Identical to Lines to cover but restricted to new / updated source code.
     */
    public static final String NEW_LINES_TO_COVER ="new_lines_to_cover";

    /**
     * Number of skipped unit tests.
     */
    public static final String SKIPPED_TESTS ="skipped_tests";

    /**
     * Number of conditions which are not covered by unit tests.
     */
    public static final String UNCOVERED_CONDITIONS ="uncovered_conditions";

    /**
     * Identical to Uncovered conditions but restricted to new / updated source code.
     */
    public static final String NEW_UNCOVERED_CONDITIONS ="new_uncovered_conditions";

    /**
     * Number of lines of code which are not covered by unit tests.
     */
    public static final String UNCOVERED_LINES ="uncovered_lines";

    /**
     * Identical to Uncovered lines but restricted to new / updated source code.
     */
    public static final String NEW_UNCOVERED_LINES ="new_uncovered_lines";

    /**
     * Number of unit tests.
     */
    public static final String TESTS ="tests";

    /**
     * 	Time required to execute all the unit tests.
     */
    public static final String TEST_EXECUTION_TIME ="test_execution_time";

    /**
     * Number of unit tests that have failed with an unexpected exception.
     */
    public static final String TEST_FAILURES ="test_failures";

    /**
     * Number of unit tests that have failed.
     */
    public static final String TEST_ERRORS ="test_errors";

    /**
     * Test success density = (Unit tests - (Unit test errors + Unit test failures)) / Unit tests * 100
     */
    public static final String TEST_SUCCESS_DENSITY ="test_success_density";


    public static final String[] sonarMetrics = new String[]{"complexity", "cognitive_complexity", "new_violations",
            "new_blocker_violations", "new_critical_violations", "new_major_violations", "new_minor_violations", "new_info_violations",
            "violations", "open_issues", "code_smells", "new_code_smells", "sqale_rating", "sqale_index", "new_technical_debt", "sqale_debt_ratio",
            "new_sqale_debt_ratio", "alert_status", "quality_gate_details", "bugs", "new_bugs", "reliability_rating", "reliability_remediation_effort",
            "vulnerabilities", "new_vulnerabilities", "security_rating", "security_remediation_effort", "new_security_remediation_effort",
            "ncloc", "branch_coverage", "new_branch_coverage", "coverage", "new_coverage", "line_coverage", "new_line_coverage", "lines_to_cover", "new_lines_to_cover",
            "skipped_tests", "uncovered_conditions", "new_uncovered_conditions", "uncovered_lines", "new_uncovered_lines", "tests",
            "test_execution_time", "test_failures", "test_errors", "test_success_density"};




}
