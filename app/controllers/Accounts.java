package controllers;

import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

import java.util.concurrent.atomic.AtomicBoolean;

public class Accounts extends Controller
{
  public static void signup()
  {
    render("signup.html");
  }

  public static void login()
  {
    render("login.html");
  }

  public static void register(String name, String email, String password, float height, float weight, String gender)
  {
    Logger.info("Registering new user " + email);
    Member member = new Member(name, email, password, height, weight, gender);

    member.save();
    redirect("/");
  }

  public static void authenticate(String email, String password)
  {
    Logger.info("Attempting to authenticate with " + email + ":" + password);

    Member member = Member.findByEmail(email);
    Trainer trainer = Trainer.findByEmail(email);
    AtomicBoolean isTrainer = new AtomicBoolean(false);
    try {
      if (trainer != null)
      {
        isTrainer.set(true);
      }

    }
    catch (Exception Member)
    {
      isTrainer.set(false);
    }

    if ((member != null) && (member.checkPassword(password) == true)) {
      Logger.info("Authentication successful");

      if(isTrainer.get())
      {
        session.put("logged_in_Trainerid", trainer.id);
        redirect("/trainer");
      }
      session.put("logged_in_Memberid", member.id);
      redirect ("/dashboard");
    }
    else {
      Logger.info("Authentication failed");
      redirect("/login");
    }
  }

  public static void logout()
  {
    session.clear();
    redirect ("/");
  }

  public static Member getLoggedInMember()
  {
    Member member = null;
    if (session.contains("logged_in_Memberid")) {
      String memberId = session.get("logged_in_Memberid");
      member = Member.findById(Long.parseLong(memberId));
    } else {
      login();
    }
    return member;
  }
  public static Trainer getLoggedInTrainer()
  {
    Trainer trainer = null;
    if (session.contains("logged_in_Trainerid")) {
      String trainerId = session.get("logged_in_Trainerid");
      trainer = Trainer.findById(Long.parseLong(trainerId));
    } else {
      login();
    }
    return trainer;
  }
}