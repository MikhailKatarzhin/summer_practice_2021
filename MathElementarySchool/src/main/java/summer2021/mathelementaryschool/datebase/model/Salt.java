package summer2021.mathelementaryschool.datebase.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "salt")
@Entity
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class Salt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Long  id;

    @Column(name = "salt", nullable = false, updatable = false)
    private String   salt;

    @OneToOne(mappedBy = "salt", cascade = CascadeType.ALL, orphanRemoval = true)
    private User    user;

    public Salt(String salt){
        this.salt = salt;
    }
}
