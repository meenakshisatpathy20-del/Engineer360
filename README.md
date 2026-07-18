# Engineer360

A centralized engineering management backend for managing projects, tasks, technical notes, developer profiles, GitHub repositories, engineering events, and coding contests.

## Problem Statement

- Engineering activities are distributed across multiple platforms.
- Projects and source code are managed separately.
- Tasks and technical notes are disconnected from projects.
- Coding profiles and contests exist across different platforms.
- Interviews, deadlines, and contests require separate tracking.
- Engineer360 brings these activities into one centralized engineering workspace.

## Core Features

- Secure user registration and login
- JWT-based stateless authentication
- BCrypt password hashing
- User-specific data isolation
- Project management
- GitHub repository linking with projects
- Project-specific task management
- Project-specific technical notes
- Developer profile management
- GitHub, LeetCode, Codeforces, CodeChef, and AtCoder profile support
- Engineering calendar
- Interview and deadline tracking
- Live Codeforces contest synchronization
- External REST API integration
- PostgreSQL persistence
- Docker containerization
- CI/CD pipeline
- AWS deployment

## Tech Stack

Backend: Java 21, Spring Boot, Spring Web

Security: Spring Security, JWT, BCrypt

Database: PostgreSQL

Persistence: Spring Data JPA, Hibernate

External API: Codeforces API

API Client: Spring RestClient

Build Tool: Maven

API Testing: Postman

Version Control: Git, GitHub

DevOps: Docker, GitHub Actions, AWS

## Backend Architecture

Client / Postman  
↓  
Spring Security Filter Chain  
↓  
JWT Authentication Filter  
↓  
Controller Layer  
↓  
Service Layer  
↓  
Repository Layer  
↓  
Spring Data JPA + Hibernate  
↓  
PostgreSQL

## Spring Boot Concepts Implemented

- Layered Architecture
- REST API Development
- Dependency Injection
- Inversion of Control
- Controller-Service-Repository Pattern
- Spring Security
- JWT Authentication
- Stateless Session Management
- Custom OncePerRequestFilter
- Spring SecurityContext
- BCrypt Password Encoding
- Spring Data JPA
- Hibernate ORM
- JPA Entity Relationships
- DTOs
- External REST API Integration
- Provider Pattern
- Environment-based Configuration

## Authentication Flow

Register User  
↓  
BCrypt Password Hashing  
↓  
Store User in PostgreSQL  
↓  
Login  
↓  
Validate Credentials  
↓  
Generate JWT  
↓  
Return JWT  
↓  
Send JWT as Bearer Token  
↓  
JwtAuthenticationFilter validates JWT  
↓  
Authenticated user stored in SecurityContext  
↓  
Protected APIs become accessible

## Data Relationships

User  
↓  
Projects  
↓  
Tasks and Notes

User  
↓  
Developer Profile  
↓  
GitHub / LeetCode / Codeforces / CodeChef / AtCoder

User  
↓  
Calendar Events  
↓  
Interviews / Deadlines / Contests / Custom Events

## Postman API Testing

Base URL:

http://localhost:8080

Protected endpoints require:

Authorization → Bearer Token → JWT received from login

### Register User

Method: POST

URL: http://localhost:8080/auth/register

Purpose:
- Create a new user
- Hash password using BCrypt
- Store user in PostgreSQL

Request Body:

{
  "name": "Pakhi",
  "email": "pakhi3@gmail.com",
  "password": "123456"
}

### Login User

Method: POST

URL: http://localhost:8080/auth/login

Purpose:
- Validate credentials
- Generate JWT
- Return authentication token

Request Body:

{
  "email": "pakhi3@gmail.com",
  "password": "123456"
}

Response:

{
  "token": "generated-jwt-token"
}

### Create Project

Method: POST

URL: http://localhost:8080/projects

Authorization: Bearer Token

Purpose:
- Create a project for the authenticated user
- Link a GitHub repository with the project

Request Body:

{
  "title": "Engineer360",
  "description": "Engineering management platform for students",
  "status": "IN_PROGRESS",
  "githubUrl": "https://github.com/your-username/Engineer360"
}

### Get Projects

Method: GET

URL: http://localhost:8080/projects

Authorization: Bearer Token

Purpose:
- Retrieve projects owned by the authenticated user

### Create Task

Method: POST

URL: http://localhost:8080/projects/{projectId}/tasks

Example:

http://localhost:8080/projects/3/tasks

Authorization: Bearer Token

Purpose:
- Create a task inside a specific project

### Get Project Tasks

Method: GET

URL: http://localhost:8080/projects/{projectId}/tasks

Authorization: Bearer Token

### Create Project Note

Method: POST

URL: http://localhost:8080/projects/{projectId}/notes

Example:

http://localhost:8080/projects/3/notes

Authorization: Bearer Token

Purpose:
- Store technical notes inside a project

### Create Calendar Event

Method: POST

URL: http://localhost:8080/events

Authorization: Bearer Token

Supported Event Types:
- Interview
- Deadline
- Contest
- Custom Event

Request Body:

{
  "title": "Technical Interview",
  "description": "Backend engineering interview",
  "eventType": "INTERVIEW",
  "startDateTime": "2026-07-25T10:00:00",
  "endDateTime": "2026-07-25T11:00:00"
}

### Get All Calendar Events

Method: GET

URL: http://localhost:8080/events

Authorization: Bearer Token

Purpose:
- Retrieve the complete engineering calendar
- View manual events and synchronized contests

### Get Calendar Event

Method: GET

URL: http://localhost:8080/events/{id}

Authorization: Bearer Token

### Update Calendar Event

Method: PUT

URL: http://localhost:8080/events/{id}

Authorization: Bearer Token

### Delete Calendar Event

Method: DELETE

URL: http://localhost:8080/events/{id}

Authorization: Bearer Token

### Synchronize Upcoming Contests

Method: POST

URL: http://localhost:8080/events/sync-contests

Authorization: Bearer Token

Request Body: Not Required

Purpose:
- Fetch upcoming Codeforces contests
- Convert contests into calendar events
- Store synchronized contests in PostgreSQL
- Display contests in the user's engineering calendar

## Contest Synchronization Flow

Authenticated User  
↓  
Developer Profile  
↓  
ContestSyncService  
↓  
ContestProvider  
↓  
CodeforcesContestProvider  
↓  
Codeforces API  
↓  
Upcoming Contests  
↓  
Calendar Events  
↓  
PostgreSQL

## Security Implementation

- JWT-based authentication
- Stateless backend sessions
- BCrypt password hashing
- Custom JWT authentication filter
- Protected REST endpoints
- Authentication stored in Spring SecurityContext
- User identity extracted from JWT
- User-specific resource ownership
- Password excluded from API responses
- Secrets managed using environment variables

## Database and Persistence

Database: PostgreSQL

ORM: Hibernate

Persistence Layer: Spring Data JPA

Main Entities:
- User
- Project
- Task
- Note
- DeveloperProfile
- CalendarEvent

Persistence Flow:

Java Entity  
↓  
JPA Repository  
↓  
Hibernate  
↓  
PostgreSQL

## External API Integration

Current Integration:
- Codeforces API

Implementation:
- Spring RestClient
- ContestProvider abstraction
- CodeforcesContestProvider implementation
- ContestSyncService for synchronization

Purpose:
- Fetch live upcoming contests
- Convert external contest data into internal calendar events
- Keep external API logic separate from core business logic

## Docker

Purpose:
- Package the application with its runtime environment
- Maintain consistent execution across environments
- Simplify cloud deployment

Flow:

Spring Boot Application  
↓  
Maven Build  
↓  
Executable JAR  
↓  
Docker Image  
↓  
Docker Container

## CI/CD

Tool: GitHub Actions

Pipeline:

Code Push  
↓  
GitHub Repository  
↓  
GitHub Actions Trigger  
↓  
Checkout Source Code  
↓  
Set Up Java 21  
↓  
Maven Build  
↓  
Build Validation  
↓  
Docker Image Build  
↓  
AWS Deployment

Workflow Location:

.github/workflows/

## AWS Deployment

Deployment Flow:

GitHub  
↓  
GitHub Actions  
↓  
Docker Image  
↓  
AWS  
↓  
Running Engineer360 Backend

Configuration:
- Database credentials through environment variables
- JWT secrets through environment variables
- AWS credentials through CI/CD secrets
- Sensitive credentials excluded from source control

## Project Structure

src/main/java/com/engineer360

auth → Registration and login

calendar → Engineering calendar management

config → Spring and security configuration

contest → External contest integrations

note → Project-specific technical notes

profile → Developer and coding profiles

project → Project management

security → JWT generation and authentication

task → Project-specific task management

user → User management

## Key Engineering Decisions

- JWT for stateless REST authentication
- BCrypt for secure password storage
- PostgreSQL for relational persistence
- Spring Data JPA for repository abstraction
- Hibernate for object-relational mapping
- Layered architecture for separation of concerns
- Authenticated user ownership for data isolation
- Provider Pattern for extensible contest integrations
- Docker for portable deployment
- GitHub Actions for CI/CD automation
- AWS for cloud hosting

