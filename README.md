# qaconsultantsinterview


##Tools Required

	- Install Eclipse 
	- Install TestNG from Eclipse Marketplace


##Steps to configure the automation test environment

	1. Clone the repo
	2. Import the project as maven project
	3. Make sure selenium dependency is added in the pom.xml
	3. Right Click on the project, Click Maven --> Update Project
	4. Make sure chrome driver .exe file exists in driver directory


##Execute tests

	Run ComparingTables.java under src/test/java/tests directory as TestNG Test
		1. Right Click ComparingTables.java file
		2. Select Run As --> TestNG Test


##Solution Explanation

	1. Launch the browser with url
	2. Read the table 1 data and store as List<List<Strings>>
	3. Read the table 2 data and store as List<List<Strings>>
	4. Verify both tables has same number of ROWS
	5. Verify HEADERS of both table matches
	6. Verify the both the table has same data by comparing the two List<List<Strings>>
	    Loop through each list in List<List<Strings>> representing table 1 and
        Verify whether that list exits in List<List<Strings>>  representing table 2
		