Feature: test 

Background: There should be no status 
	Given user deletes existing status 
	
@run 
Scenario: Test twitter status update 
	Given there already exists a tweet with a status "Feeling good." 
	And user wants update status with "Blessed be my India" 
	And wants to reply to status with text "Feeling good" 
	And wants to attach a link 
	And content "is not" sensitive 
	And geographical location is of "Pune" 
	When user posts the status 
	Then status should have expected text 
	And status should be replied to former status 
	And status should have expected attachment 
	And content should not be sensitive 
	And should have location "Pune" 
		
