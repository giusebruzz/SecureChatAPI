SecureChatAPI è una REST API sviluppata con Spring Boot che fornisce un sistema di messaggistica privata tra utenti autenticati.
Il progetto implementa autenticazione JWT, gestione delle conversazioni, invio e lettura dei messaggi, con persistenza su database MySQL.

Tecnologie utilizzate:
- Java 21
- Spring Boot 
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA (Hibernate)
- MySQL 8
- Maven
- Swagger / OpenAPI

Autenticazione:
L’accesso alle API è protetto tramite JWT.
Flusso:
1. Registrazione utente
2. Login
3. Ricezione token JWT
4. Utilizzo del token negli endpoint protetti tramite header:

Endpoints principali:
Auth:
POST /auth/register
POST /auth/login

Conversazioni:
GET /conversations/{conversationId}/messages

Messaggi:
POST /messages/send

Struttura database:
- User
- Conversation
- Message
