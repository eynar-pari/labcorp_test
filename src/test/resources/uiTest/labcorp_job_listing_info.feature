Feature: Search and List Job

  @ui
  Scenario: As a user I want to search a job so that the job information is displayed

    Given I open LAB_CORP_URL
    When I search the "QA Test Automation Developer"
    And click on result "Senior Software QA Analyst"
    Then verify the job position information
      | Job Title    | Senior Software QA Analyst |
      | Job Location | Burlington, North Carolina |
      | Job Id       | 21-85987                   |
    And verify the responsibilities
      | 1 | Identifying, planning and executing testing activities within development sprints to ensure high quality software and compliance according to regulatory statutes, policies and procedures. |
      | 2 | Test web and desktop application software, including PowerBuilder, Java, C#, and ASP.Net, along with web services.                                                                          |
      | 3 | Create and maintain test plans, cases and summaries.                                                                                                                                        |

    And I apply to the job
    Then verify the job information
      | Job Title    | Senior Software QA Analyst |
      | Job Location | Burlington, NC             |
      | Job Id       | 21-85987                   |
    And verify the job responsibilities
      | 1 | Identifying, planning and executing testing activities within development sprints to ensure high quality software and compliance according to regulatory statutes, policies and procedures. |
      | 2 | Test web and desktop application software, including PowerBuilder, Java, C#, and ASP.Net, along with web services.                                                                          |
      | 3 | Create and maintain test plans, cases and summaries.                                                                                                                                        |
    And return to the job search
