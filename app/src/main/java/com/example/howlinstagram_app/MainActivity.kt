package com.example.howlinstagram_app

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.howlinstagram_app.navigation.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    lateinit var bottom_navigation : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottom_navigation.setOnNavigationItemSelectedListener { it ->
            when (it.itemId) {
                R.id.action_home -> {
                    val detailViewFragment = DetailViewFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.main_content, detailViewFragment).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_search -> {
                    val gridFragment = GridFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.main_content, gridFragment).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_add_photo -> {
                    if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                        startActivity(Intent(this, AddPhotoActivity::class.java))
                    }
                    true
                }
                R.id.action_favorite_alarm -> {
                    val alarmFragment = AlarmFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.main_content, alarmFragment).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_account -> {
                    val userFragment = UserFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.main_content, userFragment).commit()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }

        // 사진에 접근할 수 있는 권한
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
    }
}