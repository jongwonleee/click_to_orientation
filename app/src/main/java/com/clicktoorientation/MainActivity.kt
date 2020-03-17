package com.clicktoorientation

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.System.canWrite(this)) {
                changeOrientation()
            } else {
                val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + this.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Toast.makeText(this,getString(R.string.help),Toast.LENGTH_LONG)
                startActivity(intent);
            }
        }

    }

    private fun changeOrientation(){
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Settings.System.putInt(
                contentResolver,
                Settings.System.USER_ROTATION,
                0
            )
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Settings.System.putInt(
                contentResolver,
                Settings.System.USER_ROTATION,
                1
            )
        }
        finish()
    }
    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.System.canWrite(this)) {
                changeOrientation()
            }else
            {
                val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + this.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Toast.makeText(this,getString(R.string.help),Toast.LENGTH_LONG)
                startActivity(intent);
            }
        }
    }
}
