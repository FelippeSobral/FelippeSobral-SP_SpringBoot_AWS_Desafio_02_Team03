Markdown Live Preview
Reset
Copy

3.  **Start Docker**


Challenge02 API 🏫
Welcome to the Challenge02 project! This project provides a RESTful API for managing data on teachers, students, coordinators, and course registrations, featuring functionalities such as authentication, discipline management, and data validation.

Main Endpoints 🌐
Teachers 📚
GET /api/teacher: Lists all available teachers.
GET /api/teacher/{id}: Returns a specific teacher by ID.
POST /api/teacher: Creates a new teacher.
DELETE /api/teacher/{id}: Deletes a teacher by ID.
POST /api/teacher/login: Logs in a teacher and returns a JWT token.
Students 🎓
POST /api/student: Saves a new student.
POST /api/student/two: Test method for saving students.
GET /api/student/{id}: Retrieves data for a specific student by ID.
GET /api/student: Lists all available students.
POST /api/student/login: Logs in a student and returns a JWT token.
Coordinators 👨‍🏫
POST /api/coordinator: Creates a new coordinator, handling name uniqueness violation.
GET /api/coordinator: Lists all coordinators.
GET /api/coordinator/id/{id}: Returns a specific coordinator by ID, throws an exception if not found.
PUT /api/coordinator/{id}: Updates a specific coordinator, ensuring data integrity.
DELETE /api/coordinator/id/{id}: Removes a specific coordinator.
Course Registrations 📝
POST /api/registration: Registers a student in a specific course. (Fictitious example, adjust as per implementation)
GET /api/registration/student/{studentId}: Lists all course registrations of a student.
DELETE /api/registration/{registrationId}: Removes a student's registration for a specific course.
Security 🔒
JWT Filters: The API uses JWT for authentication. Tokens are generated and verified using the JwtTokenService.
Security Configuration: Access control is managed by SecurityConfiguration, defining which endpoints require authentication and which are public.
Database Configuration 🐳
his project uses a database running in a Docker container. Follow the steps below to set up and run the database using Docker.

Step-by-Step Guide to Run the Docker Database Container
Install Docker:

Ensure that Docker is installed on your machine. You can download it from Docker's official website.
Edit the application.properties:

Ensure your application’s application.properties file in Spring Boot has the same database credentials:
 spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
 spring.datasource.url=jdbc:mysql://localhost:3306/school_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Sao_Paulo
 spring.datasource.username=root
 spring.datasource.password=123
Start Docker Open the terminal/command prompt, navigate to the directory containing your Docker configuration file, and execute the following command:

docker-compose -f docker-compose.yml up -d
This command will download the required Docker images (if not already present) and start the database container in detached mode.

Verify the Setup You can verify the setup by checking running containers using:

docker ps
Stopping the Database To stop the running database container, run:

docker-compose down
Code Structure 🗂️
Controllers 📂
TeacherController: Manages operations related to teachers.
StudentController: Manages operations related to students.
CoordinatorController: Manages operations related to coordinators, including creation, update, listing, and deletion.
RegistrationServices: Manages student registration in courses, performing create, list, and delete operations.
Services ⚙️
TeacherService: Contains the business logic associated with teachers, including creation, authentication, and handling discipline data.
StudentService: Contains the business logic associated with students, including validations, authentication, and interactions with external services.
CoordinatorService: Handles the business logic for coordinators, including CRUD operations and exception handling.
RegistrationServices: Provides functionality to register students in courses and manage these registrations.
JwtTokenService: Service for generating and validating JWT tokens for authentication.
UserDetailsServiceImpl: Implements user loading for authentication, searching for teachers, students, and coordinators.
Security 🔐
UserAuthenticationFilter: Validates if requests have a valid JWT token for protected endpoints.
SecurityConfiguration: Configures Spring Security security, allowing or denying access to different endpoints.
Internal Resources
DTOs 📄
LoginRequest: Handles login data for authentication.
RecoveryJwtTokenDTO: Response structure for JWT tokens.
TeacherDTO, StudentDto, CoordinatorDTO: Data structures for creating or updating teachers, students, and coordinators.
RegistrationDTO: Data structure for handling course registrations.
Exceptions and Validations 🚨
EntityIdNotFoundException: Thrown when an entity is not found with the given ID.
NameUniqueViolationException: Thrown when attempting to create or update an entity with a name that already exists.
Implements other validations for mandatory fields and data mismatches.
Technologies Used 💻
Spring Boot: Main framework for API development.
Spring Security: Security and authentication in the application using JWT.
JPA/Hibernate: Data persistence.
BCryptPasswordEncoder: For password encoding.
Lombok: Simplifies Java development with annotations.
Docker: For managing the database container.
