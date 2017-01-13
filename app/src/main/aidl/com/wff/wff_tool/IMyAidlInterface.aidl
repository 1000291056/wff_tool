// IMyAidlInterface.aidl
package com.wff.wff_tool;
import com.wff.wff_tool.bean.MessageBean;
import com.wff.wff_tool.ICallBack;
// Declare any non-default types here with import statements

interface IMyAidlInterface {
    void setMessage(in MessageBean m);
    void setICallBacl(ICallBack c);
}
