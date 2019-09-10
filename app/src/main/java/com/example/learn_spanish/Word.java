package com.example.learn_spanish;

public class Word {

    private String mdefaultTranslation;

    private String mspanishTranslation;

    private int maudioresource;

    private int resource=0;

    public Word(String defaultTranslation,String spanishTranslation,int audio){

        mdefaultTranslation = defaultTranslation;

        mspanishTranslation = spanishTranslation;

        maudioresource = audio;

    }

    public Word(String defaultTranslation,String spanishTranslation,int resourceID,int audio){

        mdefaultTranslation=defaultTranslation;

        mspanishTranslation=spanishTranslation;

        resource=resourceID;

        maudioresource = audio;
    }


    public String getDefaultTranslation(){
        return mdefaultTranslation;
    }

    public String getSpanishTranslation(){
        return mspanishTranslation;
    }

    public int getImageResouceId(){
        return resource;
    }

    public int getaudio(){
        return maudioresource;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mdefaultTranslation='" + mdefaultTranslation + '\'' +
                ", mspanishTranslation='" + mspanishTranslation + '\'' +
                ", maudioresource=" + maudioresource +
                ", resource=" + resource +
                '}';
    }


}
