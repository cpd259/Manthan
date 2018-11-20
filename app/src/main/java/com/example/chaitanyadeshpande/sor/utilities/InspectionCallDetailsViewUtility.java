package com.example.chaitanyadeshpande.sor.utilities;


public class InspectionCallDetailsViewUtility {
    private static final String LOG_TAG = "InspectionCallDetailsViewUtility";
    private static InspectionCallDetailsViewUtility instance;


    String inspectionCallFlow;



    private InspectionCallDetailsViewUtility() {
    }

    public static InspectionCallDetailsViewUtility getInstance() {
        if (instance == null) {
            instance = new InspectionCallDetailsViewUtility();
        }
        return instance;
    }

    public String getInspectionCallFlow() {
        return inspectionCallFlow;
    }

    public void setInspectionCallFlow(String inspectionCallFlow) {
        this.inspectionCallFlow = inspectionCallFlow;
    }
}
