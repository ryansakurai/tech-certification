<h1 align="center">  💻  Tech Certification API  💻  </h1>

A REST API that allows students to answer questions and get their certifications developed using:
- Java
- Spring Boot
    - Spring MVC
    - Spring Data JPA
    - Hibernate
- Lombok
- PostgreSQL
- Docker

## 🚀 Running

To run the application, you'll have to have JRE, Maven and Docker installed in your machine. To start the database container on port 5432, run the following command:

```bash
docker-compose up
```

Then, to start the application on port 8085, run the following command:

```bash
mvn spring-boot:run
```

To alter any of the ports, go to `/docker-compose.yml` and `/src/main/resources/application.properties`.

## ⚙️ Behavior

All endpoints consume and produce JSON and the following documentation uses mostly [YAML](https://en.wikipedia.org/wiki/YAML)

### /students

#### POST

- Request Body

```yaml
email: string
name: string
```

- Response

```yaml
201 - Created:
    Headers:
        Location: URI (string)
409 - Conflict:
    Body:
        error:
            code: string
            details: string
    Meaning: Email is already in use.
```

#### GET `/{studentEmail}`

- Path Variable

```yaml
studentEmail: string
```

- Response

```yaml
200 - OK:
    Body:
        email: string
        fullName: string
        certifications:
            - id: UUID (string)
            technology: string
404 - Not Found:
    Meaning: Student does not exist.
```

#### PATCH `/{studentEmail}`

- Path Variable

```yaml
studentEmail: string
```

- Request Body

```yaml
email: string
```

- Response

```yaml
200 - OK:
    Headers:
        Location: URI (string)
404 - Not Found:
    Meaning: Student referenced in path variable does not exist.
409 - Conflict:
    Body:
        error:
            code: string
            details: string
    Meaning: E-mail specified in request body is already in use.
```

### /questions

#### POST

- Request Body

```yaml
technology: string
description: string
alternatives:
    - description: string
      correct: boolean
```

- Response

```yaml
201 - Created:
    Headers:
        Location: URI (string)
```

#### GET `/{technology}`

- Path Variable

```yaml
technology: string
```

- Response

```yaml
200 - OK:
    Body:
        - id: UUID (string)
          technology: string
          description: string
          alternatives:
              - id: UUID (string)
                description: string
404 - Not Found:
    Meaning: No questions found for the specified technology.
```

### /certifications

#### POST

- Request Body

```yaml
email: string
technology: string
answers:
    - questionId: UUID (string)
      alternativeId: UUID (string)
```

- Response

```yaml
201 - Created:
    Headers:
        Location: URI (string)
404 - Not Found:
    Meaning: Either one of the resources referenced in request body (student, question and alternative) does not exist or technology, question and alternative are conflictant.
```

#### GET `/{certificationId}`

- Path Variable

```yaml
certificationId: string
```

- Response

```yaml
200 - OK:
    Body:
        id: UUID (string)
        technology: string
        grade: float
        timeOfEmition: datetime (string)
        answers:
            - question:
                  id: UUID (string)
                  description: string
              chosenAlternative:
                  id: UUID (string)
                  description: string
              correct: boolean
404 - Not Found:
    Body:
        error:
            code: string
            details: string
    Meaning: Certification does not exist.
```

#### GET `/{technology}/rankings/{quantity}`

- Path Variables

```yaml
technology: string
quantity: integer
```

- Response

```yaml
200 - OK:
    Body:
        - id: UUID (string)
          grade: float
          student:
              email: string
              name: string
404 - Not Found:
    Body:
        error:
            code: string
            details: string
    Meaning: No certifications found for the specified technology.
```
