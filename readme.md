# CS166 Software Project 1
# Description
For this assignment, you will implement a single-activity Android application using a handful of basic controls. The goal of the assignment is to become familiar with the use of Android Studio, basic UI elements, and the Android documentation.
# View Layout
The view will have the following elements:
## Amount Borrowed
This will be a EditText into which the user will enter the amount to be borrowed as a floating-point value (e.g., “1000.00”).
## Interest Rate
This will be a SeekBar ranging from 0.0 to 20.0, indicating the annual percentage rate of the interest. This value should start at 10.0.
## Loan Term
This will be a RadioGroup with the choices 15, 20, and 30, representing the number of years of the loan.
## Taxes and Insurance
This will be a CheckBox that allows the user to select whether taxes and insurance are to be included in the monthly payment.
## Calculate
This will be a Button that, when pressed, will calculate the user’s monthly payments based on the values entered.
Monthly Payment
This will be a TextView that displays the monthly payment.
## Calculation
For interest rates other than 0%, the monthly payment can be calculated as:
M = (P * J/(1-(1+J)^(-N))) + T
where:
P = Principal (the amount borrowed)
J =Monthly interest in decimal form (annual interest rate / 1200) N = Number of months of the loan
T =Monthly taxes and insurance, if selected (0.1% of the amount borrowed) For interest rates of 0%, the monthly payment is simply:
M = (P/N) + T
# Advice
• The Java and Android SDK documentation is your friend. You will need it to determine how to:
- Create a String with the desired formatting
- Write a value to a label
- Read a value from a EditText o Convert an String to a float
- Read a value from a slider
- Determine which radio of a radio group is selected
- Determine if a CheckBox is checked
• Speeding up the emulator
-  You can use the VM you created with the android image during the lab.
- Remember that you can just keep the emulator open in the background, instead of closing it and reopening it whenever you want to run your app.
# Other Requirements
• The app shouldn't crash on user input errors, such as if no values are specified and the user clicks the Calculate button.
# Submission
On Android Studio, close the project (File -> Close Project). Zip the project folder (in the workspace folder) and submit to Canvas.

### Used Tech

* [Android Studio] - editor for android development
* [java] - the programming language
* [github repository] - git hub repo

## Reference


Written By Kaya Ota
Computer Science Depertment
San Jose State Univesity
Spring 2017


[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)
   [Android Studio]: <https://developer.android.com/studio/index.html>
   [java]: <http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html>
   [github repository]: <https://github.com/28kayak/MortgageCalculator>
   [jQuery]: <http://jquery.com>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>
