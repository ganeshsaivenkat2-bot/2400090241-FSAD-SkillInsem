# 2400090241-FSAD-SkillInsem

This Maven project demonstrates using Hibernate Query Language (HQL) on a `Payment` entity. It contains:

- `com.klef.fsad.exam.Payment` entity with fields: ID, name, date, status, amount.
- `com.klef.fsad.exam.ClientDemo` with methods to insert a record and delete by ID using HQL named parameter.

## Requirements

- MySQL server running with a database named `fsadexam` (the configuration will create it if missing).
- Maven 3.x and JDK 1.8 or later.

## Build & Run

1. Update `src/main/resources/hibernate.cfg.xml` with your MySQL credentials.
2. Run `mvn clean compile` to build the project.
3. Execute the demo:
   ```sh
   mvn exec:java -Dexec.mainClass="com.klef.fsad.exam.ClientDemo"
   ```

The program inserts a sample `Payment` record and then deletes it using HQL.
