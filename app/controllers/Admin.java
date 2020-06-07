package controllers;

import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

import java.util.Collections;
import java.util.List;

public class Admin extends Controller {
  public static void index() {
    Logger.info("Rendering Trainer");
    Trainer trainer = Accounts.getLoggedInTrainer();
    List<Member> memberList = Member.findAll();
    boolean bool = false;
    render("trainer.html", trainer, memberList, bool);
  }

  public static void member(Long id) {
    Logger.info("Rendering Trainer");
    Trainer trainer = Accounts.getLoggedInTrainer();
    List<Member> memberList = Collections.singletonList(Member.findByID(id));
    List<Assessment> assessments = Member.findByID(id).assessments;
    boolean bool = true;
    render("trainer.html", trainer, memberList, bool, assessments);
  }


  public static void setComment(String comment, Long assessmentid) {
    Logger.info("Adding Comment");
    Assessment assessment = Assessment.findByAssessmentID(assessmentid);
    // Assessment assessment = new Assessment(timestamp, weight, chest, thigh, upperarm, waist, hips);
    assessment.comment = comment;
    assessment.save();
    Trainer trainer = Accounts.getLoggedInTrainer();
    session.put("logged_in_Trainerid", trainer.id);
    redirect("/trainer");
  }


  public static void deleteMember(Long id) {
    Member member = Member.findByID(id);
    Logger.info("Removing member" + member.id);
    member.save();
    member.delete();
    redirect("/trainer");
  }
}