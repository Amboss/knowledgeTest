package knowledgeTest.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Class represents rating model for User entity
 * Table RATING connected with table TASK by "One to Many" mapping
 */
@Entity
@Table(name = "RATING", catalog = "KNOWLEDGE_TEST_DB")
public class Rating implements Serializable {

    private long ratingId;

    private Date ratingDate;

    private Integer score;

    private Set<Task> taskList = new HashSet<>(0);

    /**
     * Rating model constructors
     */
    public Rating() { }

    public Rating(long ratingId, Date ratingDate, Integer score, Set<Task> taskList) {
        this.ratingId = ratingId;
        this.ratingDate = ratingDate;
        this.score = score;
        this.taskList = taskList;
    }

    /**
     * Rating model getter/setter with Hibernate config annotations
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RATING_ID", unique = true, nullable = false)
    public long getRatingId() {
        return ratingId;
    }

    public void setRatingId(long ratingId) {
        this.ratingId = ratingId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "RATING_DATE", nullable = false)
    public Date getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(Date ratingDate) {
        this.ratingDate = ratingDate;
    }

    @Column(name = "SCORE", nullable = false)
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "RATING_TASK",
            joinColumns = { @JoinColumn(name = "TASK_ID") },
            inverseJoinColumns = { @JoinColumn(name = "RATING_ID") })
    public Set<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(Set<Task> taskList) {
        this.taskList = taskList;
    }
}
