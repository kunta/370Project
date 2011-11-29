/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robotest2;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kevin
 */
@Entity
@Table(name = "tblCourse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
    @NamedQuery(name = "Course.findByCourseName", query = "SELECT c FROM Course c WHERE c.courseName = :courseName"),
    @NamedQuery(name = "Course.findByCourseTitle", query = "SELECT c FROM Course c WHERE c.courseTitle = :courseTitle"),
    @NamedQuery(name = "Course.findByCrn", query = "SELECT c FROM Course c WHERE c.crn = :crn"),
    @NamedQuery(name = "Course.findByDays", query = "SELECT c FROM Course c WHERE c.days = :days"),
    @NamedQuery(name = "Course.findByStartTime", query = "SELECT c FROM Course c WHERE c.startTime = :startTime"),
    @NamedQuery(name = "Course.findByEndTime", query = "SELECT c FROM Course c WHERE c.endTime = :endTime"),
    @NamedQuery(name = "Course.findBySemester", query = "SELECT c FROM Course c WHERE c.semester = :semester"),
    @NamedQuery(name = "Course.findByProfessor", query = "SELECT c FROM Course c WHERE c.professor = :professor"),
    @NamedQuery(name = "Course.findByDescription", query = "SELECT c FROM Course c WHERE c.description = :description")})
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "courseName")
    private String courseName;
    @Column(name = "courseTitle")
    private String courseTitle;
    @Column(name = "CRN")
    private Integer crn;
    @Column(name = "Days")
    private String days;
    @Column(name = "startTime")
    private Integer startTime;
    @Column(name = "endTime")
    private Integer endTime;
    @Column(name = "semester")
    private Integer semester;
    @Column(name = "professor")
    private String professor;
    @Column(name = "description")
    private String description;

    static List<Course> CourseCatalog = new LinkedList<Course>();
    
    public Course() {
    }

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Integer getCrn() {
        return crn;
    }

    public void setCrn(Integer crn) {
        this.crn = crn;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courseName != null ? courseName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        Course other = (Course) object;

        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course)) {
            return false;
        }
        
        if ((this.courseName == null && other.courseName != null)) {
            return false;
        }
        if (this.isSame(other)){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return courseName;
    }
    
    public boolean isSame(Course courseTwo){
        if(!this.courseName.equals(courseTwo.courseName)){
            return false;
        }
        else if((this.courseName.equals(courseTwo.courseName)) && (!this.courseTitle.equals(courseTwo.courseTitle)
                || !this.crn.equals(courseTwo.crn)
                || !this.days.equals(courseTwo.days)
                || !this.description.equals(courseTwo.description)
                || !this.professor.equals(courseTwo.professor)
                || !this.semester.equals(courseTwo.semester)
                || !this.startTime.equals(courseTwo.startTime))){
            return false;
        }
        else
            return false;
    }
    
}
