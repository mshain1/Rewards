# Rewards

Task:
A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total.

Running instructions:
Clone this branch, run local build (mvn clean install) and run the project.


Reward values are configurable in application.properties
Dataset: transactions.json

Resposne sample:
*******************
Summary of rewards for customer: 1
Month:JANUARY Rewards:140
Month:FEBRUARY Rewards:90
Month:MARCH Rewards:10
Total rewards for all months: 240
*******************
Summary of rewards for customer: 2
Month:JANUARY Rewards:160
Month:FEBRUARY Rewards:49
Month:MARCH Rewards:380
Total rewards for all months: 589
