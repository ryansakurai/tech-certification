# Tech Certification API

## ⚙️ Behavior

All endpoints consume and produce JSON.

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
    Meaning: The email is already in use.
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
    Meaning: The student does not exist.
```

#### PATCH `/{studentEmail}`

### /questions

#### POST

#### GET `/{technology}`

### /certifications

#### POST

#### GET `/{certificationId}`

#### GET `/{technology}/rankings/{quantity}`
