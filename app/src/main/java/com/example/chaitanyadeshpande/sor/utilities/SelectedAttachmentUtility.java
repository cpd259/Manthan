package com.example.chaitanyadeshpande.sor.utilities;

import com.example.chaitanyadeshpande.sor.request_response.Attachment;

public class SelectedAttachmentUtility {


    private static final String LOG_TAG = "SelectedAttachmentUtility";
    private static SelectedAttachmentUtility instance;


    private Attachment selectedAttachment;

    private SelectedAttachmentUtility() {
    }

    public static SelectedAttachmentUtility getInstance() {
        if (instance == null) {
            instance = new SelectedAttachmentUtility();
        }
        return instance;
    }

    public Attachment getSelectedAttachment() {
        return selectedAttachment;
    }

    public void setSelectedAttachment(Attachment selectedAttachment) {
        this.selectedAttachment = selectedAttachment;
    }
}
