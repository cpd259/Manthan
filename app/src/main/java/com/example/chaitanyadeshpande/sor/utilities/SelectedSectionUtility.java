package com.example.chaitanyadeshpande.sor.utilities;

import com.example.chaitanyadeshpande.sor.request_response.ReadingLevel;
import com.example.chaitanyadeshpande.sor.request_response.Section;

public class SelectedSectionUtility {


    private static final String LOG_TAG = "SelectedSectionUtility";
    private static SelectedSectionUtility instance;


    Section selectedSection;

    private SelectedSectionUtility() {
    }

    public static SelectedSectionUtility getInstance() {
        if (instance == null) {
            instance = new SelectedSectionUtility();
        }
        return instance;
    }

    public Section getSelectedSection() {
        return selectedSection;
    }

    public void setSelectedSection(Section selectedSection) {
        this.selectedSection = selectedSection;
    }
}

