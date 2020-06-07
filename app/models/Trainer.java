package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity

public class Trainer extends Member {
    public  String specialty;

    public Trainer(String email, String name, String password, String gender,
                   String specialty) {
        super(name, email, password, gender);
        this.specialty = specialty;
    }
    public Trainer(String email, String name, String password, String gender
                   ) {
        super(name, email, password, gender);
        this.specialty = "specialty";
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String toString() {
        return super.toString()+"\t"
                + "Speciality : " + specialty;
    }

    public static Trainer findByEmail(String email)
    {
        return find("email", email).first();
    }

    public static Trainer findByID(Long id)
    {
        return find("id", id).first();
    }

    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }
}
