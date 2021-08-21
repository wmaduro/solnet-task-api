
# Solnet Task API Test

This project intends to provide endpoints to a task management API.

## Technical Stack
-----------
- Java 8
- Maven
- Spring Boot
- Spring Jpa
- Spring Web
- Lombok
- Derby
- SpringFox Swagger 2

### How to test Locally
-----------
#### Requirements
You have to have the below tools intalled.
- **GIT**
- **JDK 8**

#### Step by Step (LINUX)

- #### Clone from GIT
    ```sh
    git clone https://github.com/wmaduro/solnet-task-api.git
    ```
- #### Jump into the project folder 
    ```sh
    cd solnet-task-api
    ```    
- #### Change permission:
    ```sh
    chmod +x mvnw
    ```  
- #### Compile using built-in maven  
    ```sh
    ./mvnw clean package
    ```
- #### Run The API
    ```sh
    java -jar target/solnet-task-api-0.0.1-SNAPSHOT.jar
    ```          
- #### Run the url 
    http://localhost:8080/task/swagger-ui.html

### How to test in the Cloud
- Access through https://solnet-task-api.herokuapp.com/task/swagger-ui.html

