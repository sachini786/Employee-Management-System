Feature: Employee Management

  Scenario Outline: Successfully create a new employee when all details are valid
    Given the employee management service is initialized
    When I create an employee with the data <firstName>,<lastName>,<email>,<department>,<position>
    Then the employee should be successfully created
    Examples:
      | firstName | lastName | email                      | department | position   |
      | John      | Joy      | john.joy@example.com       | HR         | Manager    |
      | Alice     | Smith    | alice.smith@example.com    | IT         | Developer  |
      | Bob       | White    | bob.white@example.com      | Finance    | Analyst    |
      | Carol     | Green    | carol.green@example.com    | Marketing  | Specialist |
