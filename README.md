# android-testrail-sdk
android-testrail-sdk is an Android library used to publish, edit, or update test infrastructure using TestRail APIs.

ReadMe is a work in progress but this is a start:

- Minimum API level is: 19 (Android 4.4 KitKat)

Usage:

TestRail testRail = new TestRail(URL, USER, PASSWORD);

URL = [https://test.corp.<company>.com/"] // Accepts both HTTP and HTTPS URLs
USER = [TestRail User]
PASSWORD = [TestRail Password/API Key]

The following methods are supported:

- getCase
- getCases
- addCase
- updateCase
- deleteCase
- getCaseTypes
- getConfigs
- addConfigGroup
- addConfig
- updateConfigGroup
- updateConfig
- deleteConfigGroup
- deleteConfig
- getPlan
- getPlans
- addPlan
- addPlanEntry
- updatePlan
- updatePlanEntry
- closePlan
- deletePlanEntry
- deletePlan
- getPriorities
- getResults
- getResultsForCase
- getResultsForRun
- addResult
- addResultForCase
- addResults
- addResultsForCases
- getRun
- getRuns
- addRun
- updateRun
- closeRun
- deleteRun
- getSuite
- getSuites
- addSuite
- updateSuite
- deleteSuite
- getTest
- getTests

The following objects are supported:

- Case
- CaseType
- Configuration
- ConfigurationGroup
- Entry
- Plan
- Priority
- Result
- Run
- Suite
- Test

... more to come!
