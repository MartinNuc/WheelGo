package cz.nuc.wheelgo.server.api.model;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mist
 * Date: 13.11.13
 * Time: 18:07
 */
public class ReportRepository {

    @Inject
    private EntityManager em;

    public Report findReportById(long id) {
        return em.find(Report.class, id);
    }

    public Problem findProblemById(long id) {
        return em.find(Problem.class, id);
    }

    public List<Problem> findProblemsAround(double latLowerBound, double latUpperBound, double longLowerBound, double longUpperBound)
    {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Problem> criteriaQuery = criteriaBuilder.createQuery(Problem.class);
        Root from = criteriaQuery.from(Problem.class);
        Predicate predicate1 = criteriaBuilder.ge(from.get("latitude"), latLowerBound);
        Predicate predicate2 = criteriaBuilder.le(from.get("latitude"), latUpperBound);
        Predicate predicate3 = criteriaBuilder.ge(from.get("longitude"), longLowerBound);
        Predicate predicate4 = criteriaBuilder.le(from.get("longitude"), longUpperBound);
        //Predicate predicate6 = criteriaBuilder.greaterThan(from.get(Problem_.expiration), new Date());
        criteriaQuery.where(criteriaBuilder.and(predicate1, predicate2, predicate3, predicate4));

        TypedQuery<Problem> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
