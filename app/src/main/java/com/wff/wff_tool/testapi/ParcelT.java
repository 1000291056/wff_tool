package com.wff.wff_tool.testapi;

import android.os.Parcel;

public class ParcelT {
    public void parcel(){
        Parcel parcel=Parcel.obtain();
        parcel.readStrongBinder();
    }
}
