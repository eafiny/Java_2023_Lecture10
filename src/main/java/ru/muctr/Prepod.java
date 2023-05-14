package ru.muctr;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @author Evgenia Skichko
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "prepods")
public class Prepod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private int salary;

    @Transient
    private String rang;

    @OneToOne
    @JoinColumn(name = "info_id")
    private PrepodInfo info;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "groups_prepods",
            joinColumns = @JoinColumn(name = "prepod_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<Group> groups;

    @Override
    public String toString() {
        return "Prepod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    public Prepod(String name, int salary){
        this.name = name;
        this.salary = salary;
    }
}
