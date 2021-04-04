import java.io.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.*;
import java.util.*;
import org.javatuples.Pair;

import models.IdNameJob;
import models.sql.Retiree;
import org.intellij.lang.annotations.Language;

public class RegularQueries implements AutoCloseable{

    private final Connection connection;

    public RegularQueries(String pathToProperties) throws IOException, SQLException, FileNotFoundException {
        try(FileReader reader = new FileReader(pathToProperties)){
            Properties properties = new Properties();
            properties.load(reader);
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password")
            );
        }
    }

    public RegularQueries() throws IOException, SQLException {
        this("database.properties");
    }

    private ResultSet select(@Language("SQL") String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public List<Retiree> getByExperience() throws SQLException {
        List<Retiree> result = new ArrayList<>();
        @Language("SQL") String query = """
            select *
                from retiree
                    where retirement_experience > 5;""";
        try(ResultSet rs = select(query)){
            while (rs.next()){
                Retiree retiree = new Retiree(
                        rs.getLong("id"),
                        rs.getString("surname"),
                        rs.getString("name"),
                        rs.getString("patronymic"),
                        rs.getString("gender").charAt(0),
                        rs.getString("nationality"),
                        rs.getInt("birth_year"),
                        rs.getLong("phone"),
                        rs.getString("address"),
                        rs.getInt("retirement_experience"),
                        rs.getFloat("retirement")
                );
                result.add(retiree);
        }
        }

        return result;
    }

    public List<IdNameJob> getIdNameJob() throws SQLException {
        List<IdNameJob> result = new ArrayList<>();
        @Language("SQL") String query = """
            select retiree.id, surname, name, retirement_experience, job.job_position
                from retiree
                    left join job
                        on retiree.job_id = job.id;""";
        try(ResultSet rs = select(query)){
            while (rs.next()){
                IdNameJob struct = new IdNameJob(
                        rs.getLong("id"),
                        rs.getString("surname"),
                        rs.getString("name"),
                        rs.getString("job_position")
                );
                result.add(struct);
            }
        }
        return result;
    }

    public Long countByExperience() throws SQLException {
        @Language("SQL") String query = """
            select count(*)
                from retiree
                where retirement_experience=2;""";
        Long result = null;
        try(ResultSet rs = select(query)){
            if (rs.next())
                result = rs.getLong(1);
        }
        return result;
    }

    public BigDecimal sumRetirement() throws SQLException {
        @Language("SQL") String query = """
            select sum (retirement)
                from retiree
                where retirement_experience > 10;""";
        BigDecimal result = null;
        try(ResultSet rs = select(query)){
            if (rs.next()) {
                result = rs.getBigDecimal(1);
            }
        }
        return result;
    }

    public BigDecimal[] minMaxRetirement() throws SQLException {
        @Language("SQL") String query = """
            select min(retirement), max(retirement)
                from retiree;""";
        BigDecimal[] result = new BigDecimal[2];
        try(ResultSet rs = select(query)){
            if (rs.next()) {
                result[0] = rs.getBigDecimal(1);
                result[1] = rs.getBigDecimal(2);
            }
        }
        return result;
    }

    public List<Pair<Retiree,String>> getTeachers() throws SQLException {
        @Language("SQL") String query = """
        select retiree.*, job.job_position
        from retiree inner join job
            on retiree.job_id = job.id
                where job_position = 'teachers higher category';""";
        List<Pair<Retiree,String>> result = new ArrayList<>();
        try(ResultSet rs = select(query)){
            while (rs.next()){
                Retiree retiree = new Retiree(
                rs.getLong("id"),
                        rs.getString("surname"),
                        rs.getString("name"),
                        rs.getString("patronymic"),
                        rs.getString("gender").charAt(0),
                        rs.getString("nationality"),
                        rs.getInt("birth_year"),
                        rs.getLong("phone"),
                        rs.getString("address"),
                        rs.getInt("retirement_experience"),
                        rs.getFloat("retirement")
                );
                String job = rs.getString("job_position");
                Pair<Retiree,String> pair = new Pair<>(retiree,job);
                result.add(pair);
            }
        }
        return result;
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}