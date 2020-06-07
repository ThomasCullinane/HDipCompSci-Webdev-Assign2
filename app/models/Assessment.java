package models;

import controllers.Accounts;
import play.Logger;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
public class Assessment extends Model
{
  public Timestamp timestamp;
  public float weight;
  public float chest;
  public float thigh;
  public float upperarm;
  public float waist;
  public float hips;
  public String comment;
  public String trend ;

/*  public Assessment()
  {
    Date date = new Date();
    this.timestamp = new Timestamp(date.getTime());
    this.weight = Accounts.getLoggedInMember().getStartWeight();

  }*/

  public Assessment(float weight, float chest, float thigh, float upperarm, float waist, float hips)
  {
    Date date = new Date();
    this.timestamp = new Timestamp(date.getTime());
    this.weight = weight;
    this.chest = chest;
    this.thigh = thigh;
    this.upperarm = upperarm;
    this.waist = waist;
    this.hips = hips;
  }

  public Assessment(String comment)
  {
    this.comment = comment;
  }

  public float getWeight() {
    return weight;
  }

  public void setWeight(float weight) {
    this.weight = weight;
  }

  public float getThigh() {
    return thigh;
  }

  public void setThigh(float thigh) {
    this.thigh = thigh;
  }

  public float getWaist() {
    return waist;
  }

  public void setWaist(float waist) {
    this.waist = waist;
  }

  public String getComment() {
    return comment;
  }

//  public void setComment(String comment) {
//    this.comment = comment;
//  }

  public static Assessment findByAssessmentID(Long id)
  {
    return find("id", id).first();
  }

  public String assessmentTrend()
  {
    return "Good";
  }
}
