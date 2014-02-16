package com.jonathongrigg.logbook;

public class Entry {

    // logbook entry
    int id;
    long startTimeDate;
    long endTimeDate;
    int drivingTime;
    String supervisor;
    String roadConditions;
    String weatherConditions;
    String trafficConditions;

    public Entry(int id, long startTimeDate, long endTimeDate, String supervisor,
                 String roadConditions, String weatherConditions, String trafficConditions) {
        this.id = id;
        this.startTimeDate = startTimeDate;
        this.endTimeDate = endTimeDate;
        this.supervisor = supervisor;
        this.roadConditions = roadConditions;
        this.weatherConditions = weatherConditions;
        this.trafficConditions = trafficConditions;

        // driving time is in minutes, convert form milliseconds
        this.drivingTime = (int) ((endTimeDate - startTimeDate) / (1000 * 60));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStartTimeDate() {
        return startTimeDate;
    }

    public void setStartTimeDate(long startTimeDate) {
        this.startTimeDate = startTimeDate;
    }

    public long getEndTimeDate() {
        return endTimeDate;
    }

    public void setEndTimeDate(long endTimeDate) {
        this.endTimeDate = endTimeDate;
    }

    public int getDrivingTime() {
        return drivingTime;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getRoadConditions() {
        return roadConditions;
    }

    public void setRoadConditions(String roadConditions) {
        this.roadConditions = roadConditions;
    }

    public String getWeatherConditions() {
        return weatherConditions;
    }

    public void setWeatherConditions(String weatherConditions) {
        this.weatherConditions = weatherConditions;
    }

    public String getTrafficConditions() {
        return trafficConditions;
    }

    public void setTrafficConditions(String trafficConditions) {
        this.trafficConditions = trafficConditions;
    }
}
