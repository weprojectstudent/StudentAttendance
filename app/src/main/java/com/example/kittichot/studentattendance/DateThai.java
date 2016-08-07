package com.example.kittichot.studentattendance;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by kittichot on 30/6/2559.
 */
public class DateThai {
    private String getdate;
    private String Hour, Minute;

    public String dateThai(String strdate) {
        this.getdate = strdate;
        String Months[] = {

                "01", "02", "03", "04",

                "05", "06", "07", "08",

                "09", "10", "11", "12"};

                /*"ม.ค", "ก.พ", "มี.ค", "เม.ย",

                "พ.ค", "มิ.ย", "ก.ค", "ส.ค",

                "ก.ย", "ต.ค", "พ.ย", "ธ.ค"};*/
        String DATE[] = {
                "01","02","03","04","05",
                "06","07","08","09","10",
                "11","12","13","14","15",
                "16","17","18","19","20",
                "21","22","23","24","25",
                "26","27","28","29","30",
                "31"
        };

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        int year=0,month=0,day=0;

        try {

            java.util.Date date = df.parse(getdate);

            Calendar c = Calendar.getInstance();

            c.setTime(date);



            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);

            day = c.get(Calendar.DATE);



        } catch (ParseException e) {

// TODO Auto-generated catch block

            e.printStackTrace();

        }

        return String.format("%s-%s-%s", DATE[day-1],Months[month],year+543);
    }

    public String yearThai(int strdate) {
        this.getdate =String.valueOf(strdate) ;
        /*String Months[] = {

                "01", "02", "03", "04",

                "05", "06", "07", "08",

                "09", "10", "11", "12"};

                /*"ม.ค", "ก.พ", "มี.ค", "เม.ย",

                "พ.ค", "มิ.ย", "ก.ค", "ส.ค",

                "ก.ย", "ต.ค", "พ.ย", "ธ.ค"};*/
      /*  String DATE[] = {
                "01","02","03","04","05",
                "06","07","08","09","10",
                "11","12","13","14","15",
                "16","17","18","19","20",
                "21","22","23","24","25",
                "26","27","28","29","30",
                "31"
        };*/

        DateFormat df = new SimpleDateFormat("yyyy");

        int year=0,month=0,day=0;

        try {

            java.util.Date date = df.parse(getdate);

            Calendar c = Calendar.getInstance();

            c.setTime(date);



            year = c.get(Calendar.YEAR);
            //month = c.get(Calendar.MONTH);

           // day = c.get(Calendar.DATE);



        } catch (ParseException e) {

// TODO Auto-generated catch block

            e.printStackTrace();

        }

        return String.format("%s",year+543);
    }

    public String dateThaiSelect(String strdate) {
        this.getdate = strdate;
        String Months[] = {

                "01", "02", "03", "04",

                "05", "06", "07", "08",

                "09", "10", "11", "12"};

                /*"ม.ค", "ก.พ", "มี.ค", "เม.ย",

                "พ.ค", "มิ.ย", "ก.ค", "ส.ค",

                "ก.ย", "ต.ค", "พ.ย", "ธ.ค"};*/
        String DATE[] = {
              "01","02","03","04","05",
              "06","07","08","09","10",
              "11","12","13","14","15",
              "16","17","18","19","20",
              "21","22","23","24","25",
              "26","27","28","29","30",
              "31"
        };

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        int year = 0, month = 0, day = 0;

        try {

            java.util.Date date = df.parse(getdate);

            Calendar c = Calendar.getInstance();

            c.setTime(date);


            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);

            day = c.get(Calendar.DATE);


        } catch (ParseException e) {

// TODO Auto-generated catch block

            e.printStackTrace();

        }

        return String.format("%s-%s-%s", DATE[day-1], Months[month], year + 543);
    }

    public String fontDateThai(String strDate) {
        this.getdate = strDate;
        String Months[] = {
                "ม.ค", "ก.พ", "มี.ค", "เม.ย",

                "พ.ค", "มิ.ย", "ก.ค", "ส.ค",

                "ก.ย", "ต.ค", "พ.ย", "ธ.ค"};
        String DATE[] = {
                "01","02","03","04","05",
                "06","07","08","09","10",
                "11","12","13","14","15",
                "16","17","18","19","20",
                "21","22","23","24","25",
                "26","27","28","29","30",
                "31"
        };

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        int year=0,month=0,day=0;

        try {

            java.util.Date date = df.parse(getdate);

            Calendar c = Calendar.getInstance();

            c.setTime(date);



            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);

            day = c.get(Calendar.DATE);



        } catch (ParseException e) {

// TODO Auto-generated catch block

            e.printStackTrace();

        }

        return String.format("%s-%s-%s", DATE[day-1],Months[month],year);
    }

    public String setDateThai(String strDate) {
        this.getdate = strDate;
        String Months[] = {
                "01", "02", "03", "04",

                "05", "06", "07", "08",

                "09", "10", "11", "12"};
        String DATE[] = {
                "01","02","03","04","05",
                "06","07","08","09","10",
                "11","12","13","14","15",
                "16","17","18","19","20",
                "21","22","23","24","25",
                "26","27","28","29","30",
                "31"
        };

        DateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");

        int year=0,month=0,day=0;

        try {

            java.util.Date date = df.parse(getdate);

            Calendar c = Calendar.getInstance();

            c.setTime(date);



            year = c.get(Calendar.YEAR);

            month =c.get(Calendar.MONTH);

            day = c.get(Calendar.DATE);



        } catch (ParseException e) {

// TODO Auto-generated catch block

            e.printStackTrace();

        }

        return String.format("%s-%s-%s", DATE[day-1],Months[month],year);
    }

    public String dateThaiAddValue(String strdate) {
        this.getdate = strdate;
        String Months[] = {

                "01", "02", "03", "04",

                "05", "06", "07", "08",

                "09", "10", "11", "12"};

                /*"ม.ค", "ก.พ", "มี.ค", "เม.ย",

                "พ.ค", "มิ.ย", "ก.ค", "ส.ค",

                "ก.ย", "ต.ค", "พ.ย", "ธ.ค"};*/
        String DATE[] = {
                "01","02","03","04","05",
                "06","07","08","09","10",
                "11","12","13","14","15",
                "16","17","18","19","20",
                "21","22","23","24","25",
                "26","27","28","29","30",
                "31"
        };

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        int year = 0, month = 0, day = 0;

        try {

            java.util.Date date = df.parse(getdate);

            Calendar c = Calendar.getInstance();

            c.setTime(date);


            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);

            day = c.get(Calendar.DATE);


        } catch (ParseException e) {

// TODO Auto-generated catch block

            e.printStackTrace();

        }

        return String.format("%s-%s-%s", day, month, year);
    }



    public String dateThaiUPloadValue(String strdate) {
        this.getdate = strdate;
        String Months[] = {

                "01", "02", "03", "04",

                "05", "06", "07", "08",

                "09", "10", "11", "12"};

                /*"ม.ค", "ก.พ", "มี.ค", "เม.ย",

                "พ.ค", "มิ.ย", "ก.ค", "ส.ค",

                "ก.ย", "ต.ค", "พ.ย", "ธ.ค"};*/
        String DATE[] = {
                "01","02","03","04","05",
                "06","07","08","09","10",
                "11","12","13","14","15",
                "16","17","18","19","20",
                "21","22","23","24","25",
                "26","27","28","29","30",
                "31"
        };

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        int year=0,month=0,day=0;

        try {

            java.util.Date date = df.parse(getdate);

            Calendar c = Calendar.getInstance();

            c.setTime(date);



            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);

            day = c.get(Calendar.DATE);



        } catch (ParseException e) {

// TODO Auto-generated catch block

            e.printStackTrace();

        }

        return String.format("%s-%s-%s", year-543,Months[month],DATE[day-1]);
    }

    public String timesetAlert(String hour) {
        this.Hour = hour;
        String HOUR[] = {

                "00" ,"01" ,"02" ,"03",
                "04" ,"05" ,"06" ,"07",
                "08" ,"09" ,"10" ,"11",
                "12" ,"13" ,"14" ,"15",
                "16" ,"17" ,"18" ,"19",
                "20" ,"21" ,"22" ,"23"};


        String MINUTE[] = {
                "00","01","02","03","04","05",
                "06","07","08","09","10",
                "11","12","13","14","15",
                "16","17","18","19","20",
                "21","22","23","24","25",
                "26","27","28","29","30",
                "31","32","33","34","35",
                "36","37","38","39","40",
                "41","42","43","44","45",
                "46","47","48","49","50",
                "51","52","53","54","55",
                "56","57","58","59"
        };

        DateFormat df = new SimpleDateFormat("HH");
        //TimeFormatException tf = new TimeFormatException("");

        int newHour=0,newMinute=0;

        try {

            java.util.Date date = df.parse(String.valueOf(Hour));

            Calendar c = Calendar.getInstance();

            c.setTime(date);



            newHour = c.get(Calendar.HOUR_OF_DAY);
            //newMinute = c.get(Calendar.MINUTE);




        } catch (ParseException e) {

        // TODO Auto-generated catch block

            e.printStackTrace();

        }

        return String.format("%s",HOUR[newHour]);
    }

    public String timesetMinuteAlert(String hour) {
        this.Minute = hour;
        String HOUR[] = {

                "00" ,"01" ,"02" ,"03",
                "04" ,"05" ,"06" ,"07",
                "08" ,"09" ,"10" ,"11",
                "12" ,"13" ,"14" ,"15",
                "16" ,"17" ,"18" ,"19",
                "20" ,"21" ,"22" ,"23"};


        String MINUTE[] = {
                "00","01","02","03","04","05",
                "06","07","08","09","10",
                "11","12","13","14","15",
                "16","17","18","19","20",
                "21","22","23","24","25",
                "26","27","28","29","30",
                "31","32","33","34","35",
                "36","37","38","39","40",
                "41","42","43","44","45",
                "46","47","48","49","50",
                "51","52","53","54","55",
                "56","57","58","59"
        };

        DateFormat df = new SimpleDateFormat("mm");
        //TimeFormatException tf = new TimeFormatException("");

        int newHour=0,newMinute=0;

        try {

            java.util.Date date = df.parse(String.valueOf(Minute));

            Calendar c = Calendar.getInstance();

            c.setTime(date);



            //newHour = c.get(Calendar.HOUR_OF_DAY);
            newMinute = c.get(Calendar.MINUTE);




        } catch (ParseException e) {

        // TODO Auto-generated catch block

            e.printStackTrace();

        }

        return String.format("%s",MINUTE[newMinute]);
    }
}
