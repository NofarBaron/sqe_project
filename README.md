# Assignment 3: Software Quality Engineering
This is a repository for assignment 3 of the Software Quality Engineering course at the [Ben-Gurion University](https://in.bgu.ac.il/), Israel.

## Assignment Description
In this assignment, we tested an open-source software called [moodle](https://moodle.org/).

שמירת התרגום
Model is free software that is used as an online system for computer-integrated teaching or learning.

## Installation
$$*TODO* Write instructions on how to install the software and prepare the testing environment$$

## What we tested
We tested the quiz module that allows for creating and taking quizzes. We chose to test the following user stories: 

*User story:* A teacher adds a new quiz to the course with two yes/no questions

*Preconditions:* There is a course with a teacher

*Expected outcome:* The quiz is added to the course.

*User story:* A students attempts a quiz and answers correctly.

*Preconditions:* There is a course with a quiz with two yes/no questions and the quiz grade is calculated automatically and the grade is visible to the students upon submission.

*Expected outcome:* The student receives 100.
$$

## How we tested
We used two different testing methods:
1. [Cucumber](https://cucumber.io/), a BDD testing framework.
2. [Provengo](https://provengo.tech/), a story-based testing framework.

Each of the testing methods is elaborated in its own directory. 

## Results
Update all README.md files (except for d-e, see Section 1). Specifically, replace all $$*TODO*…$$ according to the instructions inside the $$.

