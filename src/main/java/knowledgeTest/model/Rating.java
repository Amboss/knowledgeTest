package knowledgeTest.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Class represents rating model for User entity
 * Table RATING connected with table TASK by "One to Many" mapping
 */
@Entity
@Table(name = "RATING", catalog = "KNOWLEDGE_TEST_DB")
public class Rating implements Serializable {

    private Long ratingId;

    private Date ratingDate;

    private Integer score;

    private List<Task> taskList = new ArrayList<>();

    /**
     * Rating model constructors
     */
    public Rating() { }

    public Rating(Long ratingId, Date ratingDate, Integer score, List<Task> taskList) {
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
    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "RATING_DATE", nullable = true)
    public Date getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(Date ratingDate) {
        this.ratingDate = ratingDate;
    }

    @Column(name = "SCORE", nullable = true)
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
    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
