package com.nada.app.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.nada.app.pojo.UserPOJO;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UtilityFunction {

//    public static Map<String, List<String>> sportDisciplineMap = new LinkedHashMap<>();

    public static UserPOJO getUserPOJO(Context context) {
        try {
            UserPOJO userPOJO = new Gson().fromJson(Pref.GetStringPref(context, StringUtils.LOGIN_DATA, ""), UserPOJO.class);
            return userPOJO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCurrentTime() {
        try {
            return new SimpleDateFormat("hh:mm:ss").format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static Map<String, List<String>> addSportList() {

        Map<String, List<String>> sportDisciplineMap = new LinkedHashMap<>();

//        sportDisciplineMap.put("Select Sport", Arrays.asList(new String[]{"Select Discipline"}));

        sportDisciplineMap.put("Aikido", Arrays.asList(new String[]{"Aikido"}));

        sportDisciplineMap.put("Aquatics", Arrays.asList(new String[]{"IPC Swimming Sprint 100m or less", "Swimming Sprint 100m or less", "Swimming Long Distance 800m or greater", "Open Water", "Water Polo", "Synchronized Swimming", "Swimming Middle Distance 200-400m"}));

        sportDisciplineMap.put("Archery", Arrays.asList(new String[]{"All"}));

        sportDisciplineMap.put("Athletics", Arrays.asList(new String[]{"Sprint 400m or less", "Throws", "Jumps", "Long Distance 3000m or greater", "Combined Events", "IPC Sprint 400m or less", "IPC Jumps", "IPC Throws", "Middle Distance 800-1500m"}));

        sportDisciplineMap.put("Automobile Sports", Arrays.asList(new String[]{"All"}));

        sportDisciplineMap.put("Badminton", Arrays.asList(new String[]{"Badminton"}));

        sportDisciplineMap.put("Bandy", Arrays.asList(new String[]{"Bandy"}));

        sportDisciplineMap.put("Baseball", Arrays.asList(new String[]{"Baseball"}));

        sportDisciplineMap.put("Basketball", Arrays.asList(new String[]{"3 on 3", "Basketball"}));

        sportDisciplineMap.put("Basque Pelota", Arrays.asList(new String[]{"Basque Pelota"}));

        sportDisciplineMap.put("Biathlon", Arrays.asList(new String[]{"Biathlon"}));

        sportDisciplineMap.put("Bobsleigh", Arrays.asList(new String[]{"Bobsleigh"}));

        sportDisciplineMap.put("Bodybuilding", Arrays.asList(new String[]{"Bodybuilding", "Fitness"}));

        sportDisciplineMap.put("Boxing", Arrays.asList(new String[]{"Boxing"}));

        sportDisciplineMap.put("Canoe / Kayak", Arrays.asList(new String[]{"Wildwater", "Canoe Polo", "Ocean Racing", "Canoe Slalom", "Sprint(200m)", "Long Distance (1000 m)", "Marathon", "Freestyle", "Middle Distance (500 m)"}));

        sportDisciplineMap.put("Cheer", Arrays.asList(new String[]{"Cheer"}));

        sportDisciplineMap.put("Cricket", Arrays.asList(new String[]{"All"}));

        sportDisciplineMap.put("Cycling", Arrays.asList(new String[]{"Track Sprint", "Mountain Bike", "Track Endurance", "Road", "BMX", "Cyclo -Cross", "Trials", "Artistic", "Cycle -Ball"}));

        sportDisciplineMap.put("Dance Sport", Arrays.asList(new String[]{"All"}));

        sportDisciplineMap.put("Dragon Boat", Arrays.asList(new String[]{"Dragon Boat"}));

        sportDisciplineMap.put("Equestrian", Arrays.asList(new String[]{"Endurance", "Vaulting", "Eventing", "Jumping"}));

        sportDisciplineMap.put("Fencing", Arrays.asList(new String[]{"Foil", "Epee", "Sabre", "All"}));

        sportDisciplineMap.put("Field Hockey", Arrays.asList(new String[]{"Field Hockey", "Indoor"}));

        sportDisciplineMap.put("Fistball", Arrays.asList(new String[]{"Fistball"}));

        sportDisciplineMap.put("Floorball", Arrays.asList(new String[]{"Floorball"}));

        sportDisciplineMap.put("Flying Disc", Arrays.asList(new String[]{"Ultimate"}));

        sportDisciplineMap.put("Football", Arrays.asList(new String[]{"Football", "Futsal", "Beach Football"}));

        sportDisciplineMap.put("Golf", Arrays.asList(new String[]{"Golf"}));

        sportDisciplineMap.put("Gymnastics", Arrays.asList(new String[]{"Tumbling", "Artistic", "Acrobatic", "Aerobic", "Trampoline", "Rhythmic"}));

        sportDisciplineMap.put("Handball", Arrays.asList(new String[]{"Beach", "Indoor"}));

        sportDisciplineMap.put("Ice Hockey", Arrays.asList(new String[]{"Ice Hockey"}));

        sportDisciplineMap.put("Judo", Arrays.asList(new String[]{"Judo"}));

        sportDisciplineMap.put("Ju - Jitsu", Arrays.asList(new String[]{"All"}));

        sportDisciplineMap.put("Kabaddi", Arrays.asList(new String[]{"Kabaddi"}));

        sportDisciplineMap.put("Karate", Arrays.asList(new String[]{"Karate"}));

        sportDisciplineMap.put("Kendo", Arrays.asList(new String[]{"Kendo"}));

        sportDisciplineMap.put("Kickboxing", Arrays.asList(new String[]{"All"}));

        sportDisciplineMap.put("Korfball", Arrays.asList(new String[]{"Korfball"}));

        sportDisciplineMap.put("Lacrosse", Arrays.asList(new String[]{"Lacrosse"}));

        sportDisciplineMap.put("Modern Pentathlon", Arrays.asList(new String[]{"Modern Pentathlon"}));

        sportDisciplineMap.put("Life Saving", Arrays.asList(new String[]{"Ocean", "Ocean"}));

        sportDisciplineMap.put("Motorcycle Racing", Arrays.asList(new String[]{"All"}));

        sportDisciplineMap.put("Mountaineering and Climbing", Arrays.asList(new String[]{"All"}));

        sportDisciplineMap.put("Muaythai", Arrays.asList(new String[]{"All"}));

        sportDisciplineMap.put("Netball", Arrays.asList(new String[]{"Netball"}));

        sportDisciplineMap.put("Orienteering", Arrays.asList(new String[]{"All"}));

        sportDisciplineMap.put("Polo", Arrays.asList(new String[]{"All"}));

        sportDisciplineMap.put("Powerboating", Arrays.asList(new String[]{"Aquabike"}));

        sportDisciplineMap.put("Powerlifting", Arrays.asList(new String[]{"All", "IPC Powerlifting"}));

        sportDisciplineMap.put("Racquetball", Arrays.asList(new String[]{"Racquetball"}));

        sportDisciplineMap.put("Roller Sports", Arrays.asList(new String[]{"Inline Speed Skating Sprint 1000 m or less", "Inline Speed Skating Distance greater than 1000 m", "Hockey", "Artistic"}));

        sportDisciplineMap.put("Rowing", Arrays.asList(new String[]{"Rowing"}));

        sportDisciplineMap.put("Rugby League", Arrays.asList(new String[]{"Rugby League"}));

        sportDisciplineMap.put("Rugby Union", Arrays.asList(new String[]{"Sevens", "Fifteens"}));

        sportDisciplineMap.put("Sailing", Arrays.asList(new String[]{"All"}));

        sportDisciplineMap.put("Sambo", Arrays.asList(new String[]{"All"}));

        sportDisciplineMap.put("Savate", Arrays.asList(new String[]{"All"}));

        sportDisciplineMap.put("Skating", Arrays.asList(new String[]{"Speed 1500 m or less", "Speed greater than 1500 m", "Figure Skating", "Synchronized Skating", "Short Track"}));

        sportDisciplineMap.put("Ski Mountaineering", Arrays.asList(new String[]{"Ski Mountaineering"}));

        sportDisciplineMap.put("Skiing", Arrays.asList(new String[]{"Cross -Country", "Nordic Combined", "Snowboard", "Freestyle", "Alpine"}));

        sportDisciplineMap.put("Soft Tennis", Arrays.asList(new String[]{"Soft Tennis"}));

        sportDisciplineMap.put("Softball", Arrays.asList(new String[]{"Softball"}));

        sportDisciplineMap.put("Sport Climbing", Arrays.asList(new String[]{"Speed", "Lead", "Boulder"}));

        sportDisciplineMap.put("Squash", Arrays.asList(new String[]{"Squash"}));

        sportDisciplineMap.put("Sumo", Arrays.asList(new String[]{"Sumo"}));

        sportDisciplineMap.put("Surfing", Arrays.asList(new String[]{"Surfing"}));

        sportDisciplineMap.put("Table Tennis", Arrays.asList(new String[]{"Table Tennis"}));

        sportDisciplineMap.put("Taekwondo", Arrays.asList(new String[]{"Sparring", "Poomsae"}));

        sportDisciplineMap.put("Tennis", Arrays.asList(new String[]{"Tennis"}));

        sportDisciplineMap.put("Triathlon", Arrays.asList(new String[]{"All"}));

        sportDisciplineMap.put("Tug of War", Arrays.asList(new String[]{"Tug of War"}));

        sportDisciplineMap.put("Underwater Sports", Arrays.asList(new String[]{"Finswimming Open Water", "Aquathlon(Underwater Wrestling)", "Free Immersion", "Spearfishing", "UW Hockey", "UW Rugby", "Finswimming Pool"}));

        sportDisciplineMap.put("Volleyball", Arrays.asList(new String[]{"All"}));

        sportDisciplineMap.put("Waterskiing", Arrays.asList(new String[]{"All", "Cable Wakeboard", "Racing Water Ski", "Slalom", "Tricks &JumpsTournament", "Wakeboard Boat", "Cableski"}));

        sportDisciplineMap.put("Weightlifting", Arrays.asList(new String[]{"Weightlifting"}));

        sportDisciplineMap.put("Wrestling", Arrays.asList(new String[]{"All"}));

        sportDisciplineMap.put("Wushu", Arrays.asList(new String[]{"Sanda,Taolu"}));

        sportDisciplineMap.put("BCO", Arrays.asList(new String[]{"BCO"}));

        sportDisciplineMap.put("Chaperone", Arrays.asList(new String[]{"Chaperone"}));

        sportDisciplineMap.put("DCO", Arrays.asList(new String[]{"DCO"}));

        sportDisciplineMap.put("Lead DCO", Arrays.asList(new String[]{"Lead DCO"}));

        sportDisciplineMap.put("NADA OFFICIALS", Arrays.asList(new String[]{"NADA OFFICIALS"}));

//        sportDisciplineMap.put("ADMIN", Arrays.asList(new String[]{"ADMIN"}));

        return sportDisciplineMap;
    }

    public static String parseDTtoYYYYMMDD(String dt) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date d = simpleDateFormat.parse(dt);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    public static String getMonth(int month) {
        switch (month) {
            case 1:
                return "Jan";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Aug";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dec";

        }
        return "";
    }


}
