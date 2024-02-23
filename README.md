# Tech Certification API

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

#### GET `/{certificationId}`

#### GET `/{technology}/rankings/{quantity}`
