package com.example.chaitanyadeshpande.sor.utilities;


import com.example.chaitanyadeshpande.sor.request_response.ReadingLevel;

public class SelectedReadingLevelUtility {


    private static final String LOG_TAG = "SelectedReadingLevelUtility";
    private static SelectedReadingLevelUtility instance;


    ReadingLevel selectedReadingLevel;

    private SelectedReadingLevelUtility() {
    }

    public static SelectedReadingLevelUtility getInstance() {
        if (instance == null) {
            instance = new SelectedReadingLevelUtility();
        }
        return instance;
    }

    public ReadingLevel getSelectedReadingLevel() {
        return selectedReadingLevel;
    }

    public void setSelectedReadingLevel(ReadingLevel selectedReadingLevel) {
        this.selectedReadingLevel = selectedReadingLevel;
    }
}
