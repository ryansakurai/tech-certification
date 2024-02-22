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
```

#### GET `/{studentEmail}`

#### PATCH `/{studentEmail}`

### /questions

#### POST

#### GET `/{technology}`

### /certifications

#### POST

#### GET `/{certificationId}`

#### GET `/{technology}/rankings/{quantity}`
