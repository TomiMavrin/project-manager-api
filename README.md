# project-manager-api

API for Project Manager

## ER Diagram for DB

[Picture of Diagram](https://drive.google.com/file/d/10P2rTqO641YrlIBU-bWy-KOpoVZcptkJ/view?usp=sharing)

## DB

User(UID, name, email, password)
User_Board(user_UID, board_UID)
Board(UID, name, description)
Column(UID, name, category, board_UID)
Ticket(UID, title, subtitle, description, date_created, created_by, date_due)
Assigned(user_UID, ticket_UID)
