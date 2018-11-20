package com.example.chaitanyadeshpande.sor.utilities;


import android.graphics.Bitmap;

import java.io.File;

public class DocumentUtility {
    private static final String LOG_TAG = "DocumentUtility";
    private static DocumentUtility instance;


    private File docCCFile;
    private Bitmap bitmapImageCC;

    private DocumentUtility() {
    }

    public static DocumentUtility getInstance() {
        if (instance == null) {
            instance = new DocumentUtility();
        }
        return instance;
    }



    public File getDocCCFile() {
        return docCCFile;
    }

    public void setDocCCFile(File docCCFile) {
        this.docCCFile = docCCFile;
    }



    public Bitmap getBitmapImageCC() {
        return bitmapImageCC;
    }

    public void setBitmapImageCC(Bitmap bitmapImageCC) {
        this.bitmapImageCC = bitmapImageCC;
    }

    public void clearDocuments(){
        docCCFile=null;
        bitmapImageCC=null;
    }
}

