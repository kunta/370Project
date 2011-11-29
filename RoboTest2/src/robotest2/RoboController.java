/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robotest2;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.*;

/**
 *
 * @author Kevin
 */
public class RoboController extends Thread {

    public void RoboController() {
        start();
    }

    /**
     *
     * @author Kevin
     */
    public void ImportCourseCatalog() {
        EntityManager eManager = Persistence.createEntityManagerFactory("cmpt370group06PU").createEntityManager();

        try {
            eManager.getTransaction().begin();
            Course.CourseCatalog = eManager.createNamedQuery("Course.findAll").getResultList();
            eManager.getTransaction().commit();
        } catch (NoResultException e) {
            eManager.getTransaction().rollback();
        }

        eManager.close();

    }

    /**
     *
     * @author Kevin
     */
    public void AddCourseToDB(Course newCourse) {
        EntityManager eManager = Persistence.createEntityManagerFactory("cmpt370group06PU").createEntityManager();

        try {
            eManager.getTransaction().begin();
            eManager.persist(newCourse);
            eManager.getTransaction().commit();

        } catch (NoResultException e) {
            eManager.getTransaction().rollback();
        }

        eManager.close();

    }

    /**
     *
     * @author Kevin
     */
    public void AddPrereqToDB(Prereq newPrereq) {
        EntityManager eManager = Persistence.createEntityManagerFactory("cmpt370group06PU").createEntityManager();

        try {
            eManager.getTransaction().begin();
            eManager.persist(newPrereq);
            eManager.getTransaction().commit();

        } catch (NoResultException e) {
            eManager.getTransaction().rollback();
        }

        eManager.close();
    }

    /**
     *
     * @author Kevin
     */
    public void AddTemplateToDB(Template newTemplate) {
        EntityManager eManager = Persistence.createEntityManagerFactory("cmpt370group06PU").createEntityManager();

        try {
            eManager.getTransaction().begin();
            eManager.persist(newTemplate);
            eManager.getTransaction().commit();

        } catch (NoResultException e) {
            eManager.getTransaction().rollback();
        }

        eManager.close();
    }

    /**
     *
     * @author Kevin
     */
    public void AddTranscriptToDB(Transcript newTranscript) {
        EntityManager eManager = Persistence.createEntityManagerFactory("cmpt370group06PU").createEntityManager();

        try {
            eManager.getTransaction().begin();
            eManager.persist(newTranscript);
            eManager.getTransaction().commit();

        } catch (NoResultException e) {
            eManager.getTransaction().rollback();
        }

        eManager.close();
    }

    /**
     *
     * @author Kevin
     */
    public void AddStudentToDB(Student newStudent) {
        EntityManager eManager = Persistence.createEntityManagerFactory("cmpt370group06PU").createEntityManager();

        try {
            eManager.getTransaction().begin();
            eManager.persist(newStudent);
            eManager.getTransaction().commit();

        } catch (NoResultException e) {
            eManager.getTransaction().rollback();
        }

        eManager.close();

    }

    /**
     *
     * @author Kevin
     */
    public void MergeCourseToDB(Course newCourse) {
        EntityManager eManager = Persistence.createEntityManagerFactory("cmpt370group06PU").createEntityManager();

        try {
            eManager.getTransaction().begin();
            eManager.merge(newCourse);
            eManager.getTransaction().commit();

        } catch (NoResultException e) {
            eManager.getTransaction().rollback();
        }

        eManager.close();

    }

    /**
     *
     * @author Kevin
     */
    public void MergePrereqToDB(Prereq newPrereq) {
        EntityManager eManager = Persistence.createEntityManagerFactory("cmpt370group06PU").createEntityManager();

        try {
            eManager.getTransaction().begin();
            eManager.merge(newPrereq);
            eManager.getTransaction().commit();

        } catch (NoResultException e) {
            eManager.getTransaction().rollback();
        }

        eManager.close();
    }

    /**
     *
     * @author Kevin
     */
    public List<Prereq> GetPrereqsFromDB(String courseName) {
        EntityManager eManager = Persistence.createEntityManagerFactory("cmpt370group06PU").createEntityManager();
        List<Prereq> results = new LinkedList<Prereq>();

        try {
            eManager.getTransaction().begin();
            Query prereqQ = (Query) eManager.createNamedQuery("Prereq.findByCourseName").setParameter("courseName", courseName);
            results = prereqQ.getResultList();
            eManager.getTransaction().commit();
        } catch (NoResultException e) {
            System.out.println("Course doesn't exist in the database!");
            eManager.getTransaction().rollback();
        }

        return results;
    }

    /**
     *
     * @author Kevin
     */
    public Course GetCourseFromDB(String courseName) {
        EntityManager eManager = Persistence.createEntityManagerFactory("cmpt370group06PU").createEntityManager();
        Course newCourse = new Course();

        try {
            eManager.getTransaction().begin();
            Query courseQ = (Query) eManager.createNamedQuery("Course.findByCourseName").setParameter("courseName", courseName);
            newCourse = (Course) courseQ.getSingleResult();
            eManager.getTransaction().commit();
        } catch (NoResultException e) {
            System.out.println("Course doesn't exist in the database!");
            eManager.getTransaction().rollback();
        }

        return newCourse;
    }

    /**
     *
     * @author Kevin
     */
    public List<Transcript> GetUserTranscriptFromDB(Student newStudent) {
        EntityManager eManager = Persistence.createEntityManagerFactory("cmpt370group06PU").createEntityManager();
        List<Transcript> results = new LinkedList<Transcript>();

        try {
            eManager.getTransaction().begin();
            Query transcriptQ = (Query) eManager.createNamedQuery("Transcript.findByUserName").setParameter("userName", newStudent.getUsername());
            results = transcriptQ.getResultList();
            eManager.getTransaction().commit();
        } catch (NoResultException e) {
            System.out.println("Transcript doesn't exist in the database!");
            eManager.getTransaction().rollback();
        }

        return results;
    }

    /**
     *
     * @author Kevin
     */
    public List<Template> GetTemplatesFromDB() {
        EntityManager eManager = Persistence.createEntityManagerFactory("cmpt370group06PU").createEntityManager();
        List<Template> results = new LinkedList<Template>();

        try {
            eManager.getTransaction().begin();
            Query templateQ = (Query) eManager.createNamedQuery("Template.findAll");
            results = templateQ.getResultList();
            eManager.getTransaction().commit();
        } catch (NoResultException e) {
            System.out.println("Template doesn't exist in the database!");
            eManager.getTransaction().rollback();
        }

        for (Template t : results) {
            if (!Template.templateNameList.contains(t.getTemplateName())) {
                Template.templateNameList.add(t.getTemplateName());
            }
        }

        return results;
    }

    /**
     *
     * @author Kevin
     */
    public Student GetStudentFromDB(String studentName) {
        EntityManager eManager = Persistence.createEntityManagerFactory("cmpt370group06PU").createEntityManager();
        Student newStudent = new Student();

        try {
            eManager.getTransaction().begin();
            Query studentQ = eManager.createNamedQuery("Student.findByUsername").setParameter("username", studentName);
            newStudent = (Student) studentQ.getSingleResult();
            eManager.getTransaction().commit();
        } catch (NoResultException e) {
            System.out.println("Course doesn't exist in the database!");
            eManager.getTransaction().rollback();
        }

        return newStudent;
    }

    /**
     *
     * @author Kevin
     */
    public void RemovePrereqFromDB(Prereq newPrereq) {
        EntityManager eManager = Persistence.createEntityManagerFactory("cmpt370group06PU").createEntityManager();

        Prereq prereqTwo = new Prereq(newPrereq.getCourseName(), newPrereq.getPrereq());
        try {
            eManager.getTransaction().begin();
            prereqTwo = eManager.merge(newPrereq);
            eManager.remove(prereqTwo);
            eManager.getTransaction().commit();

        } catch (NoResultException e) {
            eManager.getTransaction().rollback();
        }

        eManager.close();
    }

    /**
     *
     * @author Kevin
     */
    public void RemoveCourseFromDB(Course newCourse) {
        EntityManager eManager = Persistence.createEntityManagerFactory("cmpt370group06PU").createEntityManager();

        Course courseTwo = this.GetCourseFromDB(newCourse.getCourseName());
        try {
            eManager.getTransaction().begin();
            courseTwo = eManager.merge(newCourse);
            eManager.remove(courseTwo);
            eManager.getTransaction().commit();

        } catch (NoResultException e) {
            eManager.getTransaction().rollback();
        }

        eManager.close();
    }

    /**
     *
     * @author Kevin
     */
    public void SetCurrentStudent(Student newStudent) {
        Student.currentStudent.setName(newStudent.getName());
        Student.currentStudent.setUsername(newStudent.getUsername());
        Student.currentStudent.setPassword(newStudent.getPassword());
        Student.currentStudent.setEmail(newStudent.getEmail());
        Student.currentStudent.setStudentNo(newStudent.getStudentNo());
        Student.currentStudent.setAdminUser(newStudent.getAdminUser());
        Student.currentStudent.setProgramMajor(newStudent.getProgramMajor());
    }
}
