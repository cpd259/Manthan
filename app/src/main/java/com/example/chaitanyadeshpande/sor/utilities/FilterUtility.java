package com.example.chaitanyadeshpande.sor.utilities;


public class FilterUtility {
    private static final String LOG_TAG = "FilterUtility";
    private static FilterUtility instance;

    String startDate;
    String endDate;
    String status;
    String qualityInspector;
    String partNumber;

    private FilterUtility() {
    }

    public static FilterUtility getInstance() {
        if (instance == null) {
            instance = new FilterUtility();
        }
        return instance;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = this.status + status;
    }

    public String getQualityInspector() {
        return qualityInspector;
    }

    public void setQualityInspector(String qualityInspector) {
        this.qualityInspector = qualityInspector;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public void clearFilter() {
        startDate = "";
        endDate = "";
        status = "";
        qualityInspector = "";
        partNumber = "";
    }
}


