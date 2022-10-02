# RMS_API_Tests
##**Part 1 - Automated BDD tests** 

The five test case scenarios within the getAllMedia feature file exercise the endpoint https://testapi.io/api/ottplatform/media and verify performance and contents of response Json data.

The tests can be run from CucumberTestRunner class

##**Part 2 - Additional Functional tests to be run manually**
Note: For these tests it is assumed that title_list -> primary in the response Json data holds the track artist

###**Test Case 1**

Given I retrieve the data using the endpoint above  
When I search for songs by "Nirvana"  
Then only the song "Smells Like Teen Spirit" will be retrieved  

###**Test Case 2**

Given I retrieve the data using the endpoint above  
When I choose to order the items by Artist  
Then the artist "Candi Staton" will be first in the list  
And the artist "Yeh Yeh" will be last in the list  

###**Test Case 3**

Given I am logged into my Spotify account  
And I retrieve the data using the endpoint above  
When I search for songs by "Candi Staton"  
And I pick the song called "Young Hearts Run Free"  
And I access the Spotify uri contained in the data item
Then the song called "Young Hearts Run Free" by "Candi Staton" will be loaded in Spotify player
