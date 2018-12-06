package com.offer.pass.aidlclient;

import android.os.Parcel;
import android.os.Parcelable;

public class Info implements Parcelable {

    private String strContent;

    public String getStrContent() {
        return strContent;
    }

    public void setStrContent(String strContent) {
        this.strContent = strContent;
    }

    public Info() {
    }

    public Info(Parcel in) {
        strContent = in.readString();
    }

    public static final Creator<Info> CREATOR = new Creator<Info>() {
        @Override
        public Info createFromParcel(Parcel parcel) {
            return new Info(parcel);
        }

        @Override
        public Info[] newArray(int i) {
            return new Info[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(strContent);
    }

    /**
     * 参数是一个Parcel,用它来存储与传输数据
     * @param dest
     */
    public void readFromParcel(Parcel dest){
        //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
        strContent = dest.readString();
    }

    @Override
    public String toString() {
        return "strContent: "+strContent;
    }
}
