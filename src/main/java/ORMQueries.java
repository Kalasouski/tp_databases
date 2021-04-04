import models.IdNameJob;
import models.sql.Retiree;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import models.hibernate.JobEntity;
import models.hibernate.RetireeEntity;
import org.javatuples.Pair;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.intellij.lang.annotations.Language;

public class ORMQueries {

    SessionFactory sessionFactory;

    public ORMQueries() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    private List select(@Language("HQL") String queryString) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.beginTransaction();
        Query query = session.createQuery(queryString);
        List result = query.getResultList();
        tr.commit();
        return result;
    }

    private Object selectSingle(@Language("HQL") String queryString) {
        return select(queryString).get(0);
    }

    public List<RetireeEntity> getByExperience() {
        @Language("HQL") String query = "from RetireeEntity where retirementExperience > 5";
        return select(query);
    }

    public List<IdNameJob> getIdNameJob(){
        @Language("HQL") String query = """
                select new models.IdNameJob(r.id, r.surname, r.name, j.jobPosition)
                    from RetireeEntity r
                        left join JobEntity j
                            on r.jobId =j.id
                """;
        return select(query);
    }

    public Long countByExperience(){
        @Language("HQL") String query = "select count(*) from RetireeEntity where retirementExperience = 2";
        return (Long)selectSingle(query);
    }

    public Double sumRetirement(){
        @Language("HQL") String query = "select sum(retirement) from RetireeEntity where retirementExperience > 10";
        return (Double) selectSingle(query);
    }

    public Double[] minMaxRetirement(){
        @Language("HQL") String query = """
                select min(retirement), max(retirement)
                    from RetireeEntity
                """;
        Object[] result = (Object[])selectSingle(query);
        return Arrays.stream(result).toArray(Double[]::new);
    }

    public List<Pair<Retiree,String>> getTeachers(){
        @Language("HQL") String query = """
                select new org.javatuples.Pair(r, j.jobPosition)
                    from RetireeEntity r
                        inner join JobEntity j
                            on r.jobId = j.id
                    where j.jobPosition = 'teachers higher category'               
                """;
        return select(query);
    }

}

class HibernateUtil {
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure(); //"hibernate.cfg.xml"
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().configure();
        sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
}