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
            // le code de création de la base
            // voir ci-dessous
        }
    }
}