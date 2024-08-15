# Zootopia
Software para la gestión y mantenimiento de una reserva de animales.

## Server
- localhost:8080

- mvn spring-boot:run to run app

## Endpoints

- api-endpoint = /api/v1

Animals
- <p>GET localhost:8080/api/v1/animals/all</p>
- <p>GET localhost:8080/api/v1/animals/{id}</p>
- <p>GET localhost:8080/api/v1/animals/families</p>
- <p>POST localhost:8080/api/v1/animals/add</p>
- <p>PUT localhost:8080/api/v1/animals/update/{id}</p>
- <p>DELETE localhost:8080/api/api/v1/animals/delete/{id}</p>

Login
- <p>GET localhost:8080/api/v1/login</p>


## Database Normalization Diagram

![zootopia tablas normalizadas zootopia](https://github.com/user-attachments/assets/34f0cdd4-f049-4542-997a-58cfca699507)

## UML Diagram

![zootopia uml drawio](https://github.com/user-attachments/assets/2bdf960e-000b-485a-a891-f13a13091970)

## Tools
- Spring Boot

- Postman

- Docker

- MySql Workbench
