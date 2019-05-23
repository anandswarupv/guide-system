# guide-system
Metro transit Guide System backed by http://svc.metrotransit.org/

The guide-system will tell you how long it is until the next bus on “BUS ROUTE” leaving from “BUS STOP NAME” going “DIRECTION” using the api defined at http://svc.metrotransit.org/.

“BUS ROUTE” must be a substring of the bus route name which is only in one bus route. 

“BUS STOP NAME” must be a substring of the bus stop name which is only in one bus stop on that route

“DIRECTION” must be “north” “east” “west” or “south”

##### **Prerequisites**
1. Java version 8 and above
2. Internet connection 

##### **Steps to run the Application**
1. Build the project: `mvn clean install -Dmaven.test.skip=true`
2. Run using the jar created in the target folder

`java -jar target/guide-system-1.0-SNAPSHOT-jar-with-dependencies.jar "METRO Blue Line" "Target Field Station Platform 1" "south"`

*Output will be the time until the next bus or will be blank if the last bus for the day has already left.
