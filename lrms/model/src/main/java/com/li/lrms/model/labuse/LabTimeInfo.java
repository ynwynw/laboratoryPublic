package com.li.lrms.model.labuse;



public class LabTimeInfo {
    public static void setLab1Monday(Labuse labuse) {       //实验室1一周的信息
        labuse.setFirstTime(30);
        labuse.setSecondTime(30);
        labuse.setThirdTime(0);
        labuse.setFourthTime(0);
    }

        public static void setLab1Thesday(Labuse labuse) {
            labuse.setFirstTime(30);
            labuse.setSecondTime(30);
            labuse.setThirdTime(0);
            labuse.setFourthTime(0);
        }

        public static void setLab1Wednesday(Labuse labuse) {
            labuse.setFirstTime(0);
            labuse.setSecondTime(0);
            labuse.setThirdTime(30);
            labuse.setFourthTime(30);
    }

    public static void setLab1Thursday(Labuse labuse) {
        labuse.setFirstTime(30);
        labuse.setSecondTime(0);
        labuse.setThirdTime(0);
        labuse.setFourthTime(0);
    }

    public static void setLab1Friday(Labuse labuse) {
        labuse.setFirstTime(30);
        labuse.setSecondTime(0);
        labuse.setThirdTime(30);
        labuse.setFourthTime(0);
    }

    public static void setLab1Saturday(Labuse labuse) {
        labuse.setFirstTime(30);
        labuse.setSecondTime(30);
        labuse.setThirdTime(30);
        labuse.setFourthTime(30);
    }

    public static void setLab1Sunday(Labuse labuse) {
        labuse.setFirstTime(30);
        labuse.setSecondTime(30);
        labuse.setThirdTime(30);
        labuse.setFourthTime(30);
    }



    public static void setLabMonday(String labId) {


    }
}