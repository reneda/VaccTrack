package com.example.vacctrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataItems {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableDetailList = new HashMap<String, List<String>>();

        List<String> question1 = new ArrayList<String>();
        question1.add("At present the option of choice of vaccine is not available in govt. centers due to supply and distribution. However paid vaccines at private hospitals provide choice based on availability.");


        List<String> question2 = new ArrayList<String>();
        question2.add("Covishield");
        question2.add("Covaxin");
        question2.add("Sputnik - V");

        List<String> question3 = new ArrayList<String>();
        question3.add("Vaccines will be introduced in the country only after the regulatory bodies clear it based on its safety and efficacy.");

        List<String> question4 = new ArrayList<String>();
        question4.add("Register on the Co-WIN Portal and schedule your vaccination appointment. https://www.cowin.gov.in/home");

        List<String> question5 = new ArrayList<String>();
        question5.add("Yes, a provisional certificate would be provided after the first dose. On completion of second dose, when you receive the message for completion of schedule it would include a link to download digital certificate of vaccination for your perusal.");
        List<String> question6 = new ArrayList<String>();
        question6.add("Vaccination for COVID-19 is voluntary. However, it is advisable to receive the complete schedule of COVID-19 vaccine for protecting oneself against this disease and also to limit the spread of this disease to the close contacts including family members, friends, relatives and co-workers.");
        List<String> question7 = new ArrayList<String>();
        question7.add("COVID-19 affects all age groups; however, morbidity & mortality is several times higher in adults particularly in those above the age of 50 years. Children have either asymptomatic or mild infection.There are some clinical trials now underway to test the effectiveness and safety of the vaccines in children.");


        expandableDetailList.put("Do I have a choice of which COVID-19 vaccine I will receive?", question1);
        expandableDetailList.put("Which vaccines are been authorised for use in India?", question2);
        expandableDetailList.put("Will the vaccine be safe as it is being tested and introduced in a short span of time?", question3);
        expandableDetailList.put("Where should I register for the vaccination?",question4);
        expandableDetailList.put("Will I get any certificate that I am vaccinated?",question5);
        expandableDetailList.put("Is it mandatory to take the vaccine?",question6);
        expandableDetailList.put("Why vaccination is not provided to children who are usual target?",question7);
        return expandableDetailList;
    }
}