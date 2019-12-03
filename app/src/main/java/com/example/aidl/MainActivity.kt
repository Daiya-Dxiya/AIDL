package com.example.aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val TAG: String = this::class.java.name
    private var mService: ISelectMonsterService? = null
    private val mConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            mService = ISelectMonsterService.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mService = null
        }
    }

    var levels: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")

        val intent = Intent(this@MainActivity, SelectMonsterService::class.java)
        intent.action = ISelectMonsterService::class.java.name
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)

        var level1Button = findViewById<ToggleButton>(R.id.Level1Button)
        var level2Button = findViewById<ToggleButton>(R.id.Level2Button)
        var level3Button = findViewById<ToggleButton>(R.id.Level3Button)

        level1Button.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked == true) {
                levels += 1
            } else {
                levels -= 1
            }
        }
        level2Button.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                levels += 2
            } else {
                levels -= 2
            }
        }
        level3Button.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                levels += 4
            } else {
                levels -= 4
            }
        }
    }

    fun onSelectButton(v: View) {
        var selectResult: String?
        try {
            selectResult = mService?.select(levels)
        } catch (e: RemoteException) {
            Log.d(TAG, "ServiceIsCrached")
            return
        }
        if (selectResult == null) return

        //setContentView(R.layout.activity_main)
        var targetName = findViewById<TextView>(R.id.TargetName)
        targetName.text = selectResult
        Log.d(TAG, "onClick: " + selectResult)
    }
}
