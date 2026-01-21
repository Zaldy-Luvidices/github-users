package com.test.githubusers.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.githubusers.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentMain, UsersListFragment()).commit()
        }
    }
}