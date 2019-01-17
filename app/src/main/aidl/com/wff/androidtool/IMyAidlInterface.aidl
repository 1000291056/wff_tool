// IMyAidlInterface.aidl
package com.wff.androidtool;
import com.wff.androidtool.bean.MessageBean;
import com.wff.androidtool.ICallBack;
// Declare any non-default types here with import statements

interface IMyAidlInterface {
    void setMessage(in MessageBean m);
    void setICallBacl(ICallBack c);
}
