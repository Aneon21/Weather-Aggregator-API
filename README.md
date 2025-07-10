
# 🌦️ Weather Aggregator API

This Spring Boot REST API fetches weather data from the OpenWeatherMap API and caches responses in Redis.  

It’s designed for high performance, scalability, and clean error handling.

---

## 🚀 Features
- ✅ Fetch current weather by city name.  
- 🗄️ Redis caching to avoid redundant API calls.  
- 🛡️ Graceful error handling for both service and downstream errors.  
- ❤️ Health check endpoint.  

---

## 🛠️ Tech Stack
- Java 17+
- Spring Boot 3
- Redis (via Docker)
- Maven
- OpenWeatherMap API
- Docker (for Redis)

---

## 📦 Setup & Run

### 1️⃣ Prerequisites
- JDK 17+ installed
- Maven installed
- Docker Desktop running (for Redis)
- OpenWeatherMap API Key ([Get here](https://openweathermap.org/api))

---

### 2️⃣ Configure Application
Edit `src/main/resources/application.yaml`:  

```yaml
spring:
  cache:
    type: redis
  redis:
    host: localhost
    port: 6379

openweathermap:
  api-key: YOUR_API_KEY_HERE
  base-url: https://api.openweathermap.org/data/2.5
```

---

### 3️⃣ Run Redis in Docker
```bash
docker run -d -p 6379:6379 --name redis redis
```

---

### 4️⃣ Build & Run
```bash
mvn clean install
mvn spring-boot:run
```

---

## 📝 API Endpoints

### ✅ Get Weather by City
```
GET /api/weather?city=London
```

**Sample Success Response:**
```json
{
  "city": "London",
  "temp": 28.87,
  "cond": "Clear"
}
```

---

### ❤️ Health Check
```
GET /api/health
```

**Sample Response:**
```json
{
  "timestamp": "2025-07-10T18:38:57.1016435",
  "status": "UP"
}
```

---

## ⚠️ Error Responses

### 🌩️ Downstream API Error (e.g. city not found)
```json
{
  "timestamp": "2025-07-10T19:02:23.6385998",
  "message": "Unable to fetch weather data right now.",
  "downstream": {
    "status": 404,
    "error": "city not found"
  }
}
```

---

### 🚨 Internal Service Error
```json
{
  "timestamp": "2025-07-10T19:03:28.6856499",
  "error": "Unable to process this request right now",
  "details": "Something went wrong in our service. Please try again later."
}
```

---

## 🧑‍💻 Author
**Sayak Bose**  

- 📂 [GitHub](https://github.com/sayakbose20)  
- 💼 [LinkedIn](https://www.linkedin.com/in/sayakbose20)  
