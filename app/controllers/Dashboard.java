package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

import static  java.lang.Math.round;


public class Dashboard extends Controller
{

  public static void index()
  {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Assessment> assessments = member.assessments;

    double BMI;
    boolean isIdealBodyWeight;
    if(assessments.size()>0) {
       BMI = round(GymUtility.calculateBMI(member, assessments.get(0))*100d)/100d;
       isIdealBodyWeight = GymUtility.isIdealBodyWeight(member, assessments.get(0));
    }
    else
    {
      BMI = round(GymUtility.calculateBMI(member)*100d)/100d;
      isIdealBodyWeight = GymUtility.isIdealBodyWeight(member);
    }

    String BMICategory = GymUtility.determineBMICategory(BMI);

    render("dashboard.html", member, assessments, BMI, isIdealBodyWeight, BMICategory);
  }

  public static void deleteAssessment(Long id, Long assessmentid)
  {
    Member member = Member.findByID(id);
    Assessment assessment = Assessment.findById(assessmentid);
    Logger.info("Removing assessment" + assessment.id);
    member.assessments.remove(assessment);
    member.save();
    assessment.delete();
    redirect("/dashboard");
  }

  public static void addAssessment(float weight, float chest, float thigh, float upperarm, float waist, float hips)
  {
    Member member = Accounts.getLoggedInMember();
    Assessment assessment = new Assessment(weight, chest, thigh, upperarm, waist, hips);
    member.assessments.add(0,assessment);
    member.save();
    Logger.info("Adding Assessment");
    redirect("/dashboard");
  }

}

