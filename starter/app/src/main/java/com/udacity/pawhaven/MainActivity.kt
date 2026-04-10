package com.udacity.pawhaven

import `android.content.Intent
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Connects this Activity to activity_main.xml
        setContentView(R.layout.activity_main)

        // Find the Get Started button
        setUpOnGetStartedButtonClicked()
    }

    private fun setUpOnGetStartedButtonClicked() {
        //TODO Handle starting ProfileActivity
    }
}
