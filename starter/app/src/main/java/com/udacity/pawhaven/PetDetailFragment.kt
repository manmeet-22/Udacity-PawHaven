package com.udacity.pawhaven

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.udacity.pawhaven.components.PlayPauseComponent
import com.udacity.pawhaven.components.SoundPulseView
import com.udacity.pawhaven.data.Animal
import com.udacity.pawhaven.data.IntentExtras

class PetDetailFragment : Fragment() {

    private var pet: Animal? = null
    private lateinit var pulseView: SoundPulseView

    companion object {
        fun newInstance(pet: Animal): PetDetailFragment {
            val args = Bundle().apply {
                putParcelable(IntentExtras.EXTRA_ANIMAL, pet)
            }
            return PetDetailFragment().apply {
                arguments = args
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pet = arguments?.getParcelable(IntentExtras.EXTRA_ANIMAL)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pet_detail, container, false)

        pet?.let { pet ->
            view.findViewById<ImageView>(R.id.petDetailImage).setImageResource(pet.imageRes)
            view.findViewById<TextView>(R.id.petDetailName).text = pet.name
            view.findViewById<TextView>(R.id.petDetailAge).text = getString(R.string.age_years_format, pet.age)
            view.findViewById<TextView>(R.id.petDetailDescription).text = pet.description

            pulseView = view.findViewById(R.id.petDetailPulse)
            val playPause = view.findViewById<PlayPauseComponent>(R.id.petDetailPlayPause)
            playPause.setSound(pet.soundRes)

            playPause.setOnPlayStateChangedListener { isPlaying ->
                if (isPlaying) {
                    pulseView.startAnimation()
                } else {
                    pulseView.stopAnimation()
                }
            }
        }

        view.findViewById<MaterialButton>(R.id.adoptButton).setOnClickListener {
            Toast.makeText(context, R.string.adoption_coming_soon, Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
