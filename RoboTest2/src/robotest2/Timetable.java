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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "tblTimetable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Timetable.findAll", query = "SELECT t FROM Timetable t"),
    @NamedQuery(name = "Timetable.findByUsername", query = "SELECT t FROM Timetable t WHERE t.username = :username"),
    @NamedQuery(name = "Timetable.findByCourseName", query = "SELECT t FROM Timetable t WHERE t.courseName = :courseName"),
    @NamedQuery(name = "Timetable.findByTimetableName", query = "SELECT t FROM Timetable t WHERE t.timetableName = :timetableName"),
    @NamedQuery(name = "Timetable.findByMajor", query = "SELECT t FROM Timetable t WHERE t.major = :major"),
    @NamedQuery(name = "Timetable.findByMajorClasses", query = "SELECT t FROM Timetable t WHERE t.majorClasses = :majorClasses"),
    @NamedQuery(name = "Timetable.findByCoreReqClasses", query = "SELECT t FROM Timetable t WHERE t.coreReqClasses = :coreReqClasses"),
    @NamedQuery(name = "Timetable.findByElectiveClasses", query = "SELECT t FROM Timetable t WHERE t.electiveClasses = :electiveClasses"),
    @NamedQuery(name = "Timetable.findById", query = "SELECT t FROM Timetable t WHERE t.id = :id"),
    @NamedQuery(name = "Timetable.findBySem", query = "SELECT t FROM Timetable t WHERE t.sem = :sem")})
    public class Timetable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Column(name = "courseName")
    private String courseName;
    @Column(name = "timetableName")
    private String timetableName;
    @Column(name = "major")
    private String major;
    @Column(name = "majorClasses")
    private String majorClasses;
    @Column(name = "coreReqClasses")
    private String coreReqClasses;
    @Column(name = "electiveClasses")
    private String electiveClasses;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "SEM")
    private Integer sem;

    static List<String> timetableNameList = new LinkedList<String>();
    static LinkedList<Timetable> currentTimetables = new LinkedList<Timetable>();
    
    public Timetable() {
    }

    public Timetable(String name) {
        this.courseName = name;
    }

    public Timetable(String courseName, String username) {
        this.courseName = courseName;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTimetableName() {
        return timetableName;
    }

    public void setTimetableName(String timetableName) {
        this.timetableName = timetableName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajorClasses() {
        return majorClasses;
    }

    public void setMajorClasses(String majorClasses) {
        this.majorClasses = majorClasses;
    }

    public String getCoreReqClasses() {
        return coreReqClasses;
    }

    public void setCoreReqClasses(String coreReqClasses) {
        this.coreReqClasses = coreReqClasses;
    }

    public String getElectiveClasses() {
        return electiveClasses;
    }

    public void setElectiveClasses(String electiveClasses) {
        this.electiveClasses = electiveClasses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSem() {
        return sem;
    }

    public void setSem(Integer sem) {
        this.sem = sem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Timetable)) {
            return false;
        }
        Timetable other = (Timetable) object;
//        
//        if (!this.timetableName.equals(other.timetableName)){
//            return false;
//        }
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
        if (this.courseName.equals(other.courseName) && this.username.equals(other.username)){
            return true;
        }
        return true;
    }

    @Override
    public String toString() {
        return "robotest2.Timetable[ id=" + id + " ]";
    }
    
}
