# ☕ Brewlog Backend REST API

A modern, containerized **Spring Boot** REST API engine configured to handle full CRUD data logging architectures for custom coffee extractions. This service handles validation logic, custom query structures, and links to a production PostgreSQL database.

## 🚀 Architectural Tech Stack

* **Core Runtime Engine:** Java 17 / Spring Boot 3.x
* **Database & ORM Management:** Spring Data JPA / Hibernate 6.x
* **Connection Pooling Layer:** HikariCP Connection Pool
* **Production Database Context:** PostgreSQL Runtime Instance
* **Data Mapping Boilerplate Processor:** Project Lombok Annotation Processor
* **Containerization Engine:** Multi-stage Docker Architecture (Maven 3.9 / Eclipse Temurin JRE Alpine)
* **Cloud Infrastructure Provider:** Render Web Services

---

## 📂 Backend File Structure

```text
Brewlog-Backend/ (Repository Root Directory)
└── brewlog/     (Service Subfolder & Docker Root Context Root)
    ├── pom.xml                   # Maven project dependency manifest
    ├── Dockerfile                # Multi-stage automated cloud build configuration
    └── src/
        └── main/
            ├── java/com/brewlog/brewlog/
            │   ├── BrewlogApplication.java # API Main execution and custom global CORS configuration
            │   ├── controller/             # REST Endpoints layer (Spring Mapping Handlers)
            │   ├── entity/                 # Database Schema Definition Models (CamelCase Variables)
            │   ├── repository/             # Spring Data Repository persistence interfaces
            │   └── service/                # Business logic implementation core layers
            └── resources/
                └── application.properties  # Dynamic local/production configuration parameters
```

---

## ⚙️ Standard Production Environment Properties

To connect securely to your live PostgreSQL database on Render, ensure the following environmental keys are set up inside your cloud dashboard control console rather than being hardcoded into plain code scripts:

| Environment Property Key | Configuration Property Mapping Values / Formats |
| :--- | :--- |
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://[your-db-hostname-string]://[db_name]` |
| `SPRING_DATASOURCE_USERNAME` | `[your_unique_generated_database_user]` |
| `SPRING_DATASOURCE_PASSWORD` | `[your_secure_random_alphanumeric_password_string]` |
| `SPRING_PROFILES_ACTIVE` | `prod` |

---

## 🛠️ REST API Specification Interface Patterns

Every path is prefixed behind the global controller mapping route path: `/api/brews`.

### 1. Retrieve All Extraction Logs
* **Protocol:** `GET`
* **Path Endpoint URL:** `/api/brews`
* **Expected Return Payload Status:** `200 OK` (Returns an array list collection of extraction logs)

### 2. Commit a Custom Extraction
* **Protocol:** `POST`
* **Path Endpoint URL:** `/api/brews`
* **Content-Type Payload Structure:** `application/json`
* **JSON Schema Layout Structure Format:**
  ```json
  {
    "beans": "Ethiopian Yirgacheffe",
    "method": "V60 Pourover",
    "coffeeGrams": 15,
    "waterGrams": 250,
    "rating": 5,
    "testingNotes": "Bright bergamot flavor profile, subtle floral finish."
  }
  ```
* **Expected Return Payload Status:** `201 CREATED`

### 3. Modify an Extraction
* **Protocol:** `PUT`
* **Path Endpoint URL:** `/api/brews/{id}`
* **Expected Return Payload Status:** `200 OK`

### 4. Delete an Extraction
* **Protocol:** `DELETE`
* **Path Endpoint URL:** `/api/brews/{id}`
* **Expected Return Payload Status:** `204 NO CONTENT`

---

## 🐋 Cloud Multi-Stage Docker Layout Context Specifications

This application compiles itself via the **Docker** runtime inside your Render Dashboard environment settings. It leverages a multi-stage compilation profile to keep deployment images lean and fast:

```dockerfile
# --- Stage 1: Build execution space ---
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests -B

# --- Stage 2: Runtime image execution space ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

> ⚠️ **Render Deployment Rule:** When linking your repository to Render, navigate to **Advanced Settings** and change your **Root Directory Context** from a single dot (`.`) to exactly **`brewlog`** so Docker can successfully find your source variables and build your JAR file cleanly.
