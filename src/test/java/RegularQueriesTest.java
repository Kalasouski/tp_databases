import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class RegularQueriesTest {
    private static Flyway flyway;
    private static RegularQueries regularQueries;

    @BeforeAll
    static void initialize() {
        try {
            Properties properties = new Properties();
            FileReader reader = new FileReader("database.properties");
            properties.load(reader);
            Flyway flyway = Flyway.configure().dataSource(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password")).load();
            flyway.migrate();
            regularQueries = new RegularQueries();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getByExperience() throws SQLException {
        var result = regularQueries.getByExperience();
        assertEquals(3,result.size());
    }

    @Test
    void getIdNameJob() throws SQLException {
        var result = regularQueries.getIdNameJob();
        assertEquals(5,result.size());
    }

    @Test
    void countByExperience() throws SQLException {
        var result = regularQueries.countByExperience();
        assertEquals(2,result);
    }

    @Test
    void sumRetirement() throws SQLException {
        var result = regularQueries.sumRetirement();
        assertEquals(BigDecimal.valueOf(3500),result);
    }

    @Test
    void minMaxRetirement() throws SQLException {
        var result = regularQueries.minMaxRetirement();
        assertEquals(BigDecimal.valueOf(500),result[0]);
        assertEquals(BigDecimal.valueOf(2000),result[1]);
    }

    @Test
    void getTeachers() throws SQLException {
        var result = regularQueries.getTeachers();
        assertEquals(2,result.size());
    }

    @AfterAll
    static void clean() throws SQLException {
        regularQueries.close();
        flyway.clean();
    }
}
