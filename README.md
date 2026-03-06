# URL Shortener Service

## Project Overview

This project is a simple URL shortener built using **Java and Spring Boot**.
It allows users to submit a long URL and get a short URL. When the short URL is opened, it redirects to the original link.

The application stores URL mappings **in memory**.

---

## Features

* Shorten long URLs using REST API
* Redirect short URL to original URL
* Return the same short URL if the same link is shortened again
* Metrics API to show **top 3 domains shortened the most**

---

## Technologies Used

* Java
* Spring Boot
* Maven
* ConcurrentHashMap (in-memory storage)
* Base62 encoding for short URL generation

---

## Project Structure

controller – REST API endpoints
service – Business logic
repository – In-memory storage
model – Data models
util – Utility classes

---

## API Endpoints

### Shorten URL

POST `/api/shorten`

Example request:

```json
{
"url": "https://www.youtube.com/watch?v=abc"
}
```

Example response:

```json
{
"shortUrl": "http://localhost:8080/api/qj"
}
```

---

### Redirect URL

GET `/api/{code}`

Example:

GET `/api/qj`

This will redirect the user to the original URL.

---

### Metrics API

GET `/api/metrics`

Example response:

```json
{
"udemy.com": 3,
"youtube.com": 2,
"Wikipedia.com": 1
}
```

---

## How to Run

1. Clone the repository

2. Navigate to the project folder

3. Build the project

```
mvn clean install
```

4. Run the application

```
mvn spring-boot:run
```

The application will start at:

http://localhost:8080

---

## Notes

* URL data is stored in memory
* Data will be lost when the application restarts
* Base62 encoding is used to generate short URLs

---


## Run with Docker

Build image: ``` docker build -t url-shortener . ```

Run container: ``` docker run -p 8080:8080 url-shortener ```

---

Author: Manojkumar Shinde
