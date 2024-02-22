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
    Meaning: Student specified in path variable does not exist.
409 - Conflict:
    Body:
        error:
            code: string
            details: string
    Meaning: E-mail specified in request body is already in use.
```

### /questions

#### POST

#### GET `/{technology}`

### /certifications

#### POST

#### GET `/{certificationId}`

#### GET `/{technology}/rankings/{quantity}`
