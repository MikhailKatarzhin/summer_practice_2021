package summer2021.mathelementaryschool.datebase.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "user")
@Entity
@ToString
@AllArgsConstructor
@Getter
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Long    id;

    @Column(name = "email", nullable = false, unique = true, updatable = false)
    private String  email;

    @Column(name = "password", nullable = false, updatable = false)
    private String  password;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Salt.class, orphanRemoval = true)
    @JoinColumn(name = "id_salt", nullable = false, updatable = false, referencedColumnName = "id")
    private Salt    salt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Game    game;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResolvedProblem>   resolvedProblems = new ArrayList<>();

    public void addResolvedProblem(ResolvedProblem resolvedProblem){
        this.resolvedProblems.add(resolvedProblem);
        resolvedProblem.setUser(this);
    }

    public void removeResolvedProblem(ResolvedProblem resolvedProblem){
        this.resolvedProblems.remove(resolvedProblem);
        resolvedProblem.setUser(null);
    }

    public User(String email, String password_hash, Salt salt) {
        this.email      = email;
        this.password   = password_hash;
        this.salt       = salt;
    }
}
