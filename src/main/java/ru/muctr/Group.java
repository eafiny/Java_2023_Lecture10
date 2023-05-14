package ru.muctr;

import javax.persistence.*;
import java.util.List;

/**
 * @author Evgenia Skichko
 */
@Entity ()
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    private int number;

    @ManyToMany
    @JoinTable(
            name = "groups_prepods",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "prepod_id")
    )
    private List<Prepod> prepods;

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", namber=" + number +
                '}';
    }
}
