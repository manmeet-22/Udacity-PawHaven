package com.udacity.pawhaven.components

import android.content.Context
import android.opengl.Visibility
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.udacity.pawhaven.R
import com.udacity.pawhaven.data.Animal

class PetRowComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val petImage: ImageView
    private val petName: TextView
    private val petAge: TextView
    private val petDescriptionSnippet: TextView
    private val playPauseComponent: PlayPauseComponent

    init {
        orientation = VERTICAL

        // Inflate the row layout into this custom view
        LayoutInflater.from(context).inflate(R.layout.view_pet_row, this, true)

        petImage = findViewById(R.id.petImage)
        petName = findViewById(R.id.petName)
        petAge = findViewById(R.id.petAge)
        petDescriptionSnippet = findViewById(R.id.petDescriptionSnippet)
        playPauseComponent = findViewById(R.id.playPauseComponent)
    }

    fun bind(pet: Animal) {
        petImage.setImageResource(pet.imageRes)
        petName.text = pet.name
        petAge.text = context.getString(R.string.age_years_format, pet.age)
        petDescriptionSnippet.text = pet.description
        playPauseComponent.setSound(pet.soundRes)
    }
}