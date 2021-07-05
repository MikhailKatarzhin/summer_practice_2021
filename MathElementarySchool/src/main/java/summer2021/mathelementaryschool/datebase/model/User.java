package summer2021.mathelementaryschool.datebase.model;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "user")
@Entity
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user", updatable = false)
    public Integer  id_user;

    @Column(name = "email", nullable = false, unique = true, updatable = false)
    public String   email;

    @Column(name = "password", nullable = false, updatable = false)
    public String   password;

    @OneToOne(targetEntity = Salt.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_salt", nullable = false, updatable = false, referencedColumnName = "id_salt")
    public Integer  id_salt;

}
