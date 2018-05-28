Project Specifics:

  Project developed using IntelliJ, using JavaFX to create and manage simple UI controllers.


Project Structure:

 src
    user   ( Package with classes for manage Applicant information )
       Address = Street, Number, City, ZipCode, State, Country
       ApplicantData = Information from the Application for the user ( Personal Information, Scores, felonies )
       ApplicationResult = Enum with the 3 status of the appication ( instant reject, instant accept, further review)
       Score = a class to keep the applicant score result and the total test value
       StateAgeAccept = Class to keep the relation to instant accept based on age range and state.
       States = Enum with the 50 US States.

   utilities  (Package that contani helpers) 
       ParseHelper:  class with methods to calculate age based on date of birth and Names format validaation

   ApplicationEvaluation:  Validate the Rules in 2 methods: qualifyForInstantAccept and qualifyForInstantReject 
                       using the Rules in RulesInstantAccept and RulesInstantReject.
 

   Controller: manage the UI elements and it actions
   DialogMessage:
   Main: Main class controller
   main.fxml - UI elements

   RulesInstantAccept - This contain the rules and values to Instant Accept, you can change the values
                         here and the rest of the code don't need to change to allow the new values
                        To add new rules just add them here and include the validation in ApplicationEvaluation in method qualifyForInstantAccept.

   RulesInstantReject - This contain the rules and values to Instant Reject, you can change the values
                         here and the rest of the code don't need to change to allow the new values
                        To add new rules just add them here and include the validation in ApplicationEvaluation in method qualifyForInstantReject.

   StudentsApplication: List of Student that submmited the application and the result and creationDate

 test
    ValidationsTest: some test to validate data without need of the UI.