package com.udacity.pawhaven

import android.os.Bundle
import com.udacity.pawhaven.data.Animal
import com.udacity.pawhaven.data.IntentExtras

class PetDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_detail)

        val pet = intent.getParcelableExtra<Animal>(IntentExtras.EXTRA_ANIMAL)
        if (pet != null && savedInstanceState == null) {
            val fragment = PetDetailFragment.newInstance(pet)
            supportFragmentManager.beginTransaction()
                .replace(R.id.detailContainer, fragment)
                .commit()
        }
    }
}
