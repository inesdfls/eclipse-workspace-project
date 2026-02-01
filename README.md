# 🏥 Healthcare Care Authorization & Tracking Platform

## 1. Project Overview

This project implements a **healthcare care authorization and tracking platform** allowing patients to **submit, validate, authorize, and track medical care requests** (consultations, exams, treatments).

The system is designed around a **central REST orchestrator** responsible for managing the full business workflow and coordinating multiple **heterogeneous partner services**, each using a different API technology:

- REST  
- SOAP  
- gRPC  
- GraphQL  

Each service is autonomous and represents a healthcare stakeholder (identity verification, insurance coverage, medical risk evaluation, notifications).

---

## 2. Global Architecture

```

Patient
|
| REST (JSON)
v
Healthcare Orchestrator (REST - Tomcat)
|
|--> Identity Verification Service (SOAP)
|--> Insurance Coverage Service (gRPC)
|--> Medical Risk Evaluation Service (GraphQL)
|--> Notification Service (REST)

```

The orchestrator:
- applies business rules  
- coordinates service calls  
- tracks request state and history  

---

## 3. Services & Technologies

| Service                      | Technology        | Purpose                                   |
|------------------------------|-------------------|-------------------------------------------|
| Care Request Orchestrator    | REST (JAX-RS)     | Workflow coordination & tracking           |
| Identity Verification        | SOAP (JAX-WS)     | Patient identity validation                |
| Insurance Coverage           | gRPC              | Insurance eligibility check                |
| Medical Risk Evaluation      | GraphQL           | Medical risk scoring                       |
| Notification Service         | REST              | Patient notifications                     |

---

## 4. Services & Ports

| Service                      | URL / Port                                                                                           |
|------------------------------|-------------------------------------------------------------------------------------------------------|
| REST Orchestrator            | http://localhost:8080/HealthcareOrchestrator/api                                                      |
| SOAP Identity Service (WSDL) | http://localhost:8082/identity?wsdl                                                                    |
| gRPC Insurance Service       | localhost:9090                                                                                        |
| GraphQL Risk Service         | http://localhost:8083/graphql                                                                          |

---

## 5. How to Run the Application

### ⚠️ Mandatory Launch Order

Services **must be started in the following order**:

1. Identity SOAP Service  
2. Insurance gRPC Service  
3. Risk GraphQL Service  
4. Notification Service (Tomcat)  
5. Healthcare Orchestrator (Tomcat)  

---

### 5.1 SOAP Identity Service

- Project: `IdentitySOAPService`
- Main class: `IdentityWebService`
- Run as: **Java Application**

Expected console output:

```

SOAP Identity running: [http://localhost:8082/identity?wsdl](http://localhost:8082/identity?wsdl)

```

Verification:

```

[http://localhost:8082/identity?wsdl](http://localhost:8082/identity?wsdl)

```

---

### 5.2 gRPC Insurance Service

- Project: `InsuranceGRPCService`
- Run the gRPC server main class

Expected console output:

```

Server started, listening on 9090

```

---

### 5.3 GraphQL Risk Service

- Project: `RiskGraphQLService`
- Run application as Java Application

Expected console output:

```

GraphQL server started on port 8083

```

---

### 5.4 REST Orchestrator

- Project: `HealthcareOrchestrator`
- Deployment: Apache Tomcat
- Port: `8080`

Base API URL:

```

[http://localhost:8080/HealthcareOrchestrator/api](http://localhost:8080/HealthcareOrchestrator/api)

```

---

## 6. API Documentation & Testing Tools

All APIs are **fully testable and documented** using industry-standard tools:

- **Postman** → REST & GraphQL  
- **SoapUI** → SOAP  
- **grpcurl** → gRPC  

---

## 6.1 REST API – Care Requests

### Endpoints

| Method | Endpoint                        | Description                      |
|--------|----------------------------------|----------------------------------|
| POST   | `/care-requests`                | Submit a care request             |
| GET    | `/care-requests/{id}`           | Retrieve request status           |
| GET    | `/care-requests/{id}/history`   | Retrieve full request history     |

Base URL:

```

[http://localhost:8080/HealthcareOrchestrator/api](http://localhost:8080/HealthcareOrchestrator/api)

````

---

### Example – Submit Care Request

```http
POST /care-requests
Content-Type: application/json
````

```json
{
  "fullName": "Harry Styles",
  "nationalId": "OK12345",
  "insuranceId": "INS-001",
  "medicalAct": "MRI_SCAN",
  "cost": 500,
  "justification": "medical report + prescription"
}
```

Response includes:

* `requestId`
* `status`
* `history`

---

### Example – Track Request Status

```
GET /care-requests/{requestId}
```

---

### Example – Request History

```
GET /care-requests/{requestId}/history
```

---

## 6.2 SOAP Identity Verification Service

### WSDL

```
http://localhost:8082/identity?wsdl
```

### Business Rule

An identity is **valid only if the `nationalId` starts with `OK`**.

### Example SoapUI Request

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:tns="http://soap.identity/">
   <soapenv:Header/>
   <soapenv:Body>
      <tns:verifyIdentity>
         <nationalId>OK12345</nationalId>
      </tns:verifyIdentity>
   </soapenv:Body>
</soapenv:Envelope>
```

Response:

```xml
<return>true</return>
```

---

## 6.3 gRPC Insurance Coverage Service

### Tool Used

* **grpcurl**

### Proto File Location

```
InsuranceGRPCService/proto/insurance.proto
```

---

### List Available Services

```bash
grpcurl -plaintext \
  -import-path InsuranceGRPCService/proto \
  -proto insurance.proto \
  localhost:9090 list
```

Result:

```
InsuranceService
```

---

### Call `CheckCoverage`

```bash
grpcurl -plaintext \
  -import-path InsuranceGRPCService/proto \
  -proto insurance.proto \
  -d '{"insuranceId":"INS-001","medicalAct":"MRI_SCAN","cost": 500}' \
  localhost:9090 InsuranceService/CheckCoverage
```

Response:

```json
{
  "validPolicy": true,
  "covered": true,
  "message": "Covered ✅"
}
```

---

## 6.4 GraphQL Medical Risk Service

### Endpoint

```
POST http://localhost:8083/graphql
```

### Example Query (Postman)

```json
{
  "query": "query { scoreRisk(medicalAct:\"MRI_SCAN\", justification:\"medical report\", cost: 500) { riskLevel confidence } }"
}
```

Response:

```json
{
  "data": {
    "scoreRisk": {
      "riskLevel": "LOW",
      "confidence": 0.8
    }
  }
}
```

---

## 7. Demonstration Scenario (≈10 minutes)

1. Submit a care request via REST (Postman)
2. Verify identity via SOAP (SoapUI)
3. Validate insurance via gRPC (grpcurl)
4. Evaluate medical risk via GraphQL (Postman)
5. Track full request lifecycle via REST

---

## 8. Documentation & Proofs

| Folder          | Content                                 |
| --------------- | --------------------------------------- |
| `Docs/postman/` | Postman collections                     |
| `Docs/soapui/`  | SoapUI project                          |
| `Docs/screens/` | Screenshots (REST, SOAP, gRPC, GraphQL) |
| `Docs/`         | Présentation.pptx                       |

---

## 9. Conclusion

This project demonstrates:

* Multi-protocol service orchestration
* Proper use of REST, SOAP, gRPC, and GraphQL
* Clean separation of concerns
* Business rule enforcement
* Fully testable and documented APIs

---

## Crédits / Auteurs

Projet Web Services — EFREI Paris — Inge3 Bioinformatique

Auteurs : Inès DUFLOS, Marion FRESQUET, Mathilde MEZENTZEFF

