/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robotest2;

import java.io.Serializable;
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
@Table(name = "tblPrereq")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prereq.findAll", query = "SELECT p FROM Prereq p"),
    @NamedQuery(name = "Prereq.findById", query = "SELECT p FROM Prereq p WHERE p.id = :id"),
    @NamedQuery(name = "Prereq.findByCourseName", query = "SELECT p FROM Prereq p WHERE p.courseName = :courseName"),
    @NamedQuery(name = "Prereq.findByPrereq", query = "SELECT p FROM Prereq p WHERE p.prereq = :prereq")})
public class Prereq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "courseName")
    private String courseName;
    @Column(name = "prereq")
    private String prereq;

    public Prereq() {
    }
    
        
    public Prereq(String courseName,String prereq) {
        this.courseName = courseName;
        this.prereq = prereq;
    }

    public Prereq(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getPrereq() {
        return prereq;
    }

    public void setPrereq(String prereq) {
        this.prereq = prereq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Prereq)) {
            return false;
        }
        Prereq other = (Prereq) object;
        if(this.courseName.equals(other.courseName) && this.prereq.equals(other.prereq))
        {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "robotest2.Prereq[ courseName=" + courseName + " prereq=" + prereq + " ]";
    }
    
}
