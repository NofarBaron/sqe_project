# Testing Choice-Module In Moodle using Cucumber
This directory contains the cucumber files for testing the module of the "Detect bugs while edit choice" application.

## Running the tests
Run ```mvn test``` to run all the tests.

## What we tested
We tested the Choice module that allows you to ask a question and set up buttons which learners can click to make a selection from a number of possible responses. They can choose one or more options.

We chose to test the following user stories: 

***User story:*** A teacher delete a choice while Student answer it. to the course with two yes/no questions

*Preconditions:* There is a course with a teacher, the course has a choice. 

*Expected outcome:* The choice is deleted from the course.

***User story:*** A students answer a choice.

*Preconditions:* There is a course with a choice, and the student is register to this chourse.

*Expected outcome:* Message displayed- can`t answer the choice, choice already deleted.



## Feature files
The behaviors that we tested are in the feature files that inside the [resources/hellocucumber](resources/hellocucumber) directory. See the files for a detailed description of the tests.

$$*TODO*: Make sure that the text inside the feature files is informative and self-explanatory. This includes the "Feature: ...", "Scenario: ...", and "Given/When/Then ..." lines. See the "example.feature" file for an example.$$

## Step files
The step files in the [src/test/java/hellocucumber](src/test/java/hellocucumber) directory contain the code that defines how each sentence in the feature files is translated to Selenium actions. See the files for a detailed description of the implementation.

$$*TODO* Make sure that each step is documented and properly writen (meaningful variable names, no magic number, etc.). See the [StepDefinitions.java](src/test/java/hellocucumber/StepDefinitions.java) file for an example.$$

