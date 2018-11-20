package com.example.chaitanyadeshpande.sor.utilities;
public class Status {
    private static final String LOG_TAG = "Status";
    private static Status instance;

    private Status() {
    }

    public static Status getInstance() {
        if (instance == null) {
            instance = new Status();
        }
        return instance;
    }

    public String CREATED(){
        return "Request Initiated";
    }

    public String CANCELLED_BY_CC(){
        return "Cancelled By CC";
    }

    public String ASSIGNED(){
        return "Assigned";
    }

    public String CARRY_FORWARDED(){
        return "Carry Forwarded";
    }

    public String CANCELLED_BY_VENDOR(){
        return "Cancelled By Vendor";
    }

    public String CALL_ACKNOWLEDGED(){
        return  "Call Acknowledged";
    }

    public String INSPECTION_IN_PROGRESS(){
        return "In Progress";
    }

    public String COMPLETED(){
        return "Completed";
    }

    public String CHECK_IN(){
        return "Check In";
    }

    public String JOB_NOT_READY(){
        return "Job Not Ready";
    }

    public String ACCEPTED(){
        return "Accepted";
    }

    public String REJECTED(){
        return "Rejected";
    }

    public String ON_HOLD(){
        return "On Hold";
    }


}


