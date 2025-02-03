package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class TasksApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(TasksApplication.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(TasksApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        List<String> list = Arrays.asList(strings);
        if (list.contains("install")) {

            // On supprime les tables si elles existent déjà
            jdbcTemplate.execute("DROP TABLE tasks IF EXISTS");
            jdbcTemplate.execute("DROP TABLE categories IF EXISTS");


            // On crée la table des catégories
            jdbcTemplate.execute(
                    "CREATE TABLE categories ("+
                            "category_id IDENTITY PRIMARY KEY," +
                            "name VARCHAR(20) DEFAULT '' "+
                            ");" );
            log.info("categories TABLE CREATED");

            jdbcTemplate.update("INSERT INTO categories(name) values('todo'); ");
            jdbcTemplate.update("INSERT INTO categories(name) values('wip');  ");
            jdbcTemplate.update("INSERT INTO categories(name) values('done'); ");
            log.info("categories TABLE POPULATED");


            // On crée la table des tâches
            jdbcTemplate.execute(
                    "CREATE TABLE tasks (" +
                            "   task_id       IDENTITY PRIMARY KEY," +
                            "   category      INTEGER NOT NULL," +
                            "   content       VARCHAR(500) NOT NULL," +
                            "   creation_date DATE DEFAULT CURRENT_DATE(), " +
                            "   end_date      DATE DEFAULT NULL, " +
                            "   FOREIGN KEY(category) REFERENCES categories(category_id)"+
                            ");");
            log.info("tasks TABLE CREATED");

            // un peu de data manipulation language ou DML
            jdbcTemplate.update("INSERT INTO tasks (category, content) values(3, 'finir le tp 1'); ");
            jdbcTemplate.update("INSERT INTO tasks (category, content) values(2, 'finir le tp 2'); ");
            jdbcTemplate.update("INSERT INTO tasks (category, content) values(1, 'finir le tp 3'); ");
            log.info("tasks TABLE POPULATED");
        }


    }
}