package com.udacity.pawhaven

import android.content.Intent
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.udacity.pawhaven.data.Person
import com.udacity.pawhaven.data.Repository
import com.udacity.pawhaven.data.Role

class ProfileActivity : BaseActivity() {

    private lateinit var firstNameEditText: TextInputEditText
    private lateinit var lastNameEditText: TextInputEditText
    private lateinit var ageEditText: TextInputEditText
    private lateinit var roleRadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        firstNameEditText = findViewById(R.id.first_name_edit_text)
        lastNameEditText = findViewById(R.id.last_name_edit_text)
        ageEditText = findViewById(R.id.age_edit_text)
        roleRadioGroup = findViewById(R.id.role_radio_group)

        findViewById<MaterialButton>(R.id.save_profile_button).setOnClickListener {
            saveProfile()
        }
    }

    private fun saveProfile() {
        val firstName = firstNameEditText.text.toString().trim()
        val lastName = lastNameEditText.text.toString().trim()
        val ageString = ageEditText.text.toString().trim()

        if (firstName.isEmpty() || lastName.isEmpty() || ageString.isEmpty()) {
            Toast.makeText(this, R.string.error_required, Toast.LENGTH_SHORT).show()
            return
        }

        val age = ageString.toIntOrNull() ?: 0
        val role = if (roleRadioGroup.checkedRadioButtonId == R.id.radio_volunteer) {
            Role.VOLUNTEER
        } else {
            Role.INTERESTED_PARENT
        }

        val person = Person(firstName, lastName, age, role)

        if (role == Role.VOLUNTEER) {
            val isValid = Repository.validVolunteers.any {
                it.firstName.equals(firstName, ignoreCase = true) &&
                        it.lastName.equals(lastName, ignoreCase = true)
            }

            if (!isValid) {
                Toast.makeText(this, R.string.no_volunteer_match, Toast.LENGTH_SHORT).show()
                return
            }
        }

        Repository.user = person
        Toast.makeText(this, R.string.entered_success, Toast.LENGTH_SHORT).show()

        val intent = Intent(this, PetListActivity::class.java)
        startActivity(intent)
        finish()
    }
}
