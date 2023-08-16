#Feature: A set of scenarios for testing the "example" module
#    Scenario: Testing how a case where a user adds a product to the cart
#    Given an example scenario
#    When all step definitions are implemented
#    Then the scenario passes


Feature: Teacher Delete a choice while Student answer it
  Scenario: Teacher delete a choice
  Given Teacher LogIn
  And Teacher has a course
  And Course has a choice
  And Teacher delete choice
  Then Message displayed choice deleted successfully
#
#  Scenario: Student answer a choice
#  Given Student log in successfully
#  And Student register to the course
#  And student enter the choice
#  Then Message display Student answer the choice
#
#
#  Scenario: Student inside a choice while Teacher delete it.
#  Given Student and Teacher log in successfully
#  And Student and Teacher inside course page
#  And Course SQ has a choice
#  And student enter the choice
#  And Teacher delete choice
#  Then Message display Error
