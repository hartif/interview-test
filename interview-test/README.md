# interview-test
Aplikasi login 

1. **Spin-up PostgreSQL database instance**

   You can use either of the below 2 options:
   - one way is to download from [here](https://www.postgresql.org/download) and install locally on the machine
   - another option is by running a postgres docker container:
     ```sh
     docker container run --name postgresdb -e POSTGRES_PASSWORD=admin -d -p 5432:5432 postgres
     ```
2. **Create database objects**
     ```
     docker container cp interviewtest_db.sql postgresdb:/
     docker container exec -it postgresdb bash
     ```
   - run the script using psql client:
     ```
     psql -U postgres --file interviewtest_db.sql
     ```
   ```
3. **Run the spring boot application**
   ```sh
   ./mvnw spring-boot:run
   ```
   this runs at port 8080 and hence all enpoints can be accessed starting from http://localhost:8080
