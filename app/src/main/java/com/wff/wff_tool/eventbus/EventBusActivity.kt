package com.wff.wff_tool.eventbus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wff.wff_tool.R
import kotlinx.android.synthetic.main.activity_event_bus.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class EventBusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus)
        sendMsg.setOnClickListener {
            if (it.id == R.id.sendMsg) {
                EventBus.getDefault().post(Message("hello"))
            }
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(msg: Message) {
        if (msg != null) {
            msg.logMessage("收到消息啦！")
        }
    }


}
