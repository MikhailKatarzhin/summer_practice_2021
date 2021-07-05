package summer2021.mathelementaryschool.datebase.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "fieldOfMath")
@Entity
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class FieldOfMath {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_fieldOfMath")
    public Integer  id_fieldOfMath;

    @Column(name = "nameFieldOfMath", nullable = false, updatable = false, unique = true)
    public String   nameFieldOfMath;
}
