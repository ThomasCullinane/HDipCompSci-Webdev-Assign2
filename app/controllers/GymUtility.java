package controllers;

import models.Assessment;
import models.Member;

public class GymUtility {
    public static double calculateBMI(Member member, Assessment assessment) {
        try {


            return assessment.getWeight() / (member.getHeight() * member.getHeight());
            //BMI is weight divided by the square of the height.
        } catch (Exception invalidAssessment) {
            try {
                return member.getStartWeight() / (member.getHeight() * member.getHeight());
            }
            catch (Exception e)
            {
                return 0;
            }
        }
    }

    public static double calculateBMI(Member member) {
        try {
            return member.getStartWeight() / (member.getHeight() * member.getHeight());
        }
        catch (Exception e)
        {
            return 0;
        }
    }


    public static String determineBMICategory(double bmiValue) {
        //Returns the category the BMI belongs to, based on the following values:
        //
        //BMI less than 16 (exclusive) is "SEVERELY UNDERWEIGHT"
        //BMI between 16 (inclusive) and 18.5 (exclusive) is "UNDERWEIGHT"
        //BMI between 18.5 (inclusive) and 25(exclusive) is "NORMAL"
        //BMI between 25 (inclusive) and 30 (exclusive) is "OVERWEIGHT"
        //BMI between 30 (inclusive) and 35 (exclusive) is "MODERATELY OBESE"
        //BMI greater than 35 (inclusive) and is "SEVERELY OBESE"

        if(bmiValue<16.0d)
        {
            return "SEVERELY UNDERWEIGHT";
        }
        else if (bmiValue<18.5d)
        {
            return "UNDERWEIGHT";
        }
        else if (bmiValue<25.0d)
        {
            return "NORMAL";
        }
        else if (bmiValue<30.0d)
        {
            return "OVERWEIGHT";
        }
        else if (bmiValue<35.0d)
        {
            return "MODERATELY OBESE";
        }
        else if (bmiValue>=35.0d)
        {
            return "SEVERELY OBESE";
        }
        else {
            return "NA";
        }
    }

    public static boolean isIdealBodyWeight(Member member, Assessment assessment)
    {
        double absDiff;
        if(assessment == null)
        {
            absDiff = java.lang.Math.abs(idealBodyWeight(member) - member.getStartWeight());
        }
        else
        {
            absDiff = java.lang.Math.abs(idealBodyWeight(member) - assessment.getWeight());
        }

        if(absDiff>0.2d) {
            return false;
        }
        else {return true;}
        //Returns a boolean to indicate if the member has an ideal body weight based on the Devine formula:
        //
        //For males, an ideal body weight is: 50 kg + 2.3 kg for each inch over 5 feet.
        //For females, an ideal body weight is: 45.5 kg + 2.3 kg for each inch over 5 feet.
        //Note: if no gender is specified, return the result of the female calculation.
        //Note: if the member is 5 feet or less, return 50kg for male and 45.5kg for female.
        //To allow for different calculations and rounding, when testing for the ideal body weight,
        //if it is +/- .2 of the devine formula, return true
    }

    public static boolean isIdealBodyWeight(Member member)
    {
        double absDiff;
        absDiff = java.lang.Math.abs(idealBodyWeight(member) - member.getStartWeight());

        if(absDiff>0.2d) {
            return false;
        }
        else {return true;}
        //Returns a boolean to indicate if the member has an ideal body weight based on the Devine formula:
        //
        //For males, an ideal body weight is: 50 kg + 2.3 kg for each inch over 5 feet.
        //For females, an ideal body weight is: 45.5 kg + 2.3 kg for each inch over 5 feet.
        //Note: if no gender is specified, return the result of the female calculation.
        //Note: if the member is 5 feet or less, return 50kg for male and 45.5kg for female.
        //To allow for different calculations and rounding, when testing for the ideal body weight,
        //if it is +/- .2 of the devine formula, return true
    }

    public static double idealBodyWeight(Member member)
    {
        double memberHeightInch = metresToInch(member.getHeight());
        if ("M".equals(member.getGender()) && memberHeightInch/12 <= 5)
        {
            return 50d;
        }
        else if (memberHeightInch/12 <= 5)
        {
            return 45.5d;
        }
        else if ("M".equals(member.getGender()))
        {
            return ( ( (memberHeightInch-(5*12)) * 2.3) + 50 );
        }
        else
        {
            return ( ( (memberHeightInch-(5*12)) * 2.3) + 45.5 );
        }
    }

    public static double metresToInch(float metres)
    {
        return (double) metres * 39.37d;
    }

    public static double kgToLb(float kg)
    {
        return (double) kg * 2.2d;
    }
}
