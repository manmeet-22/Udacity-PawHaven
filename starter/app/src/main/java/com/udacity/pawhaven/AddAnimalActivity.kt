package com.udacity.pawhaven

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.udacity.pawhaven.data.AnimalType
import com.udacity.pawhaven.data.IntentExtras

class AddAnimalActivity : BaseActivity() {

    private lateinit var nameEditText: TextInputEditText
    private lateinit var ageEditText: TextInputEditText
    private lateinit var descriptionEditText: TextInputEditText
    private lateinit var typeSpinner: Spinner
    private lateinit var previewImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_animal)

        nameEditText = findViewById(R.id.animal_name_edit_text)
        ageEditText = findViewById(R.id.animal_age_edit_text)
        descriptionEditText = findViewById(R.id.animal_description_edit_text)
        typeSpinner = findViewById(R.id.animal_type_spinner)
        previewImage = findViewById(R.id.animal_preview_image)

        setupSpinner()

        findViewById<MaterialButton>(R.id.add_button).setOnClickListener {
            addAnimal()
        }
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            AnimalType.values().map { it.label }
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        typeSpinner.adapter = adapter

        typeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedType = AnimalType.values()[position]
                previewImage.setImageResource(selectedType.defaultIconRes)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun addAnimal() {
        val name = nameEditText.text.toString().trim()
        val ageString = ageEditText.text.toString().trim()
        val description = descriptionEditText.text.toString().trim()
        val selectedType = AnimalType.values()[typeSpinner.selectedItemPosition]

        if (name.isEmpty() || ageString.isEmpty()) {
            Toast.makeText(this, R.string.error_required, Toast.LENGTH_SHORT).show()
            return
        }

        val age = ageString.toIntOrNull() ?: 0
        val newAnimal = if (description.isNotEmpty()) {
            selectedType.createAnimal(name, age, description)
        } else {
            selectedType.createAnimal(name, age)
        }

        val resultIntent = Intent().apply {
            putExtra(IntentExtras.EXTRA_ANIMAL, newAnimal)
        }
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}
