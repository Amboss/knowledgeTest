package knowledgeTest.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class represents model of User entity
 * Table USER connected with table Rating by "One to One" mapping
 */
@Entity
@Table(name = "USER", catalog = "KNOWLEDGE_TEST_DB")
public class User implements Serializable {

    private Long userId;

    private String userName;

    private String password;

    /**
     * 0 - USER_ROLE
     * 1 - ADMIN_ROLE
     */
    private Integer access;

    /**
     * 0 - disabled
     * 1 - active
     */
    private Integer status;

    private Rating rating;

    /**
     * User model constructors
     */
    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }

    public User(Long userId, String userName, String password,
                Integer access, Integer status, Rating rating) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.access = access;
        this.status = status;
        this.rating = rating;
    }

    /**
     * User model getter/setter with Hibernate config annotations
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", unique = true, nullable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "USER_NAME", unique = true, nullable = false, length = 100)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "PASSWORD", unique = false, nullable = true, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "ACCESS", nullable = false)
    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }

    @Column(name = "STATUS", nullable = false)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", access=" + access +
                ", status=" + status +
                ", rating=" + rating +
                '}';
    }
}
