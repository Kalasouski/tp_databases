import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ORMQueriesTest {

    private static Flyway flyway;
    private static ORMQueries ormQueries;

    @BeforeAll
    static void initialize() {
        try {
            Properties properties = new Properties();
            FileReader reader = new FileReader("database.properties");
            properties.load(reader);
            flyway = Flyway.configure().dataSource(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password")).load();
            flyway.migrate();
            ormQueries = new ORMQueries();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    void getByExperience() {
        var result = ormQueries.getByExperience();
        assertEquals(3,result.size());
    }

    @Test
    void getIdNameJob() {
        var result = ormQueries.getIdNameJob();
        assertEquals(5,result.size());
    }

    @Test
    void countByExperience() {
        var result = ormQueries.countByExperience();
        assertEquals(2,result);
    }

    @Test
    void sumRetirement() {
        var result = ormQueries.sumRetirement();
        assertEquals(3500.0,result);
    }

    @Test
    void minMaxRetirement() {
        var result = ormQueries.minMaxRetirement();
        assertEquals(500.0,result[0]);
        assertEquals(2000.0,result[1]);
    }

    @Test
    void getTeachers() {
        var result = ormQueries.getTeachers();
        assertEquals(2,result.size());
    }

    @AfterAll
    static void clean() {
        flyway.clean();
    }
}
