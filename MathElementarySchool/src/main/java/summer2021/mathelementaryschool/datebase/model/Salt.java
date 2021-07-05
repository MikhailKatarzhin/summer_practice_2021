package summer2021.mathelementaryschool.datebase.model;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "salt")
@Entity
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Salt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_salt", updatable = false)
    public Integer  id_salt;

    @Column(name = "salt", nullable = false, updatable = false)
    public String   salt;
}
