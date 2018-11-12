package com.wff.wff_tool.eventbus

import com.orhanobut.logger.Logger

class Message constructor(msg: String) {
    var msg: String = ""

    init {
        this.msg = msg
    }

    fun logMessage(msg: String): String {
        Logger.d(msg)
        return msg;
    }

}