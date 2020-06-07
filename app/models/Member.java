package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.*;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.Iterator;
import java.util.TreeSet;

@Entity
public class Member extends Model
{
  public String name;
  public String email;
  public String password;
  public float height;
  public float startWeight;
  public String gender;
  public double BMI;
  public String BMICategory;


  @OneToMany(cascade = CascadeType.ALL)
  public List<Assessment> assessments = new ArrayList<Assessment>();


  public Member(String name, String email, String password, String gender)
  {
    this.name = name;
    this.email = email;
    this.password = password;
    setHeight(1.5f);
    setStartWeight(100f);
    setGender(gender);
  }

  public Member(String name, String email, String password, float height, float weight, String gender)
  {
    this.name = name;
    this.email = email;
    this.password = password;
    setHeight(height);
    setStartWeight(weight);
    setGender(gender);
  }
  public float getHeight() {
    return height;
  }

  public void setHeight(float height) {
    if(height>=1.0 && height<=3.0) {
      this.height = height;
    }
    else if(height<1.0){
      this.height = 1.0f;
    }
    else if(height>3.0){
      this.height = 3.0f;
    }
    else {
      this.height = 1.5f;
    }
  }
  public float getStartWeight() {
    return startWeight;
  }

  public void setStartWeight(float startWeight) {
    if(startWeight>=35.0 && startWeight<=250.0) {
      this.startWeight = startWeight;
    }
    else if(startWeight<35.0){
      this.startWeight = 35.0f;
    }
    else if(startWeight>250.0){
      this.startWeight = 250.0f;
    }
    else{
      this.startWeight = 100.0f;
    }
  }

  public Assessment latestAssessment() {
    return assessments.get(0);
  }



  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    if(gender.equals("F") || gender.equals("M")){
      this.gender = gender;
    }
    else {
      this.gender = "Unspecified";
    }
  }


  public static Member findByEmail(String email)
  {
    return find("email", email).first();
  }

  public static Member findByID(Long id)
  {
    return find("id", id).first();
  }

  public boolean checkPassword(String password)
  {
    return this.password.equals(password);
  }
}
