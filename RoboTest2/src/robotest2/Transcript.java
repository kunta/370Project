/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robotest2;

import java.io.Serializable;
import java.util.LinkedList;
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
@Table(name = "tblTranscript")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transcript.findAll", query = "SELECT t FROM Transcript t"),
    @NamedQuery(name = "Transcript.findByUserName", query = "SELECT t FROM Transcript t WHERE t.userName = :userName"),
    @NamedQuery(name = "Transcript.findByCourseName", query = "SELECT t FROM Transcript t WHERE t.courseName = :courseName"),
    @NamedQuery(name = "Transcript.findById", query = "SELECT t FROM Transcript t WHERE t.id = :id")})
public class Transcript implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "userName")
    private String userName;
    @Column(name = "courseName")
    private String courseName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    static LinkedList<String> currentTranscript = new LinkedList<String>();
    
    public Transcript() {
    }

    public Transcript(Integer id) {
        this.id = id;
    }

    public Transcript(Integer id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof Transcript)) {
            return false;
        }
        Transcript other = (Transcript) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "robotest2.Transcript[ id=" + id + " ]";
    }
    
}
