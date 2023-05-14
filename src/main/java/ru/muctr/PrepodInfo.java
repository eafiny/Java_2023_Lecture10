package ru.muctr;

import javax.persistence.*;

/**
 * @author Evgenia Skichko
 */

@Entity
@Table(name = "prepod_info")
public class PrepodInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @OneToOne(mappedBy = "info")
    private Prepod prepod;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Prepod getPrepod() {
        return prepod;
    }

    public void setPrepod(Prepod prepod) {
        this.prepod = prepod;
    }

    @Override
    public String toString() {
        return "PrepodInfo{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
