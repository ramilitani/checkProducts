# checkProducts

Utility tool that receives an interval and filter all order placed during that interval.

### Prerequisites
```
Docker
Docker-Compose
Java 8
Maven
```
### Running the application
 - Go to the root path of the application to run the docker-compose. This will start the MySql database.
    ```
    docker-compose -f docker-compose.yml up -d
    ```
   
 - In the root path of the application run the below maven command to build the project
    ```
    mvn clean install
    ```
 
 - Go to the target folder and run the following command to run the application.
    ```
    java -jar checkproducts.jar "<INITIAL_DATE>" "<FINAL_DATE>" "<CUSTOM_INTERVAL>"
    
    PS: java -jar checkproducts.jar "2019-01-01 00:00:00" "2020-05-02 00:00:00" "1-3,4-5,6-12,>12"
   
    ```
   
   - The INITIAL_DATE and FINAL_DATE need to be in this format: YYYY-MM-DD HH:MM:SS
   - The CUSTOM_INTERVAL is optional and when informed it should be in this format: "1-3,4-5,6-12,>12"

### Application execution warnings
  
  - The result is displayed both on the console and in the result.log file.
  - When starting the docker-compose a sql script is loaded to create the database schema and 
  insert dummy data into the database. If you do not want to insert this dummy data, you can remove it in the data/init.sql file. 
