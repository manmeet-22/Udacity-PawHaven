package com.udacity.pawhaven

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.udacity.pawhaven.data.Animal
import com.udacity.pawhaven.data.IntentExtras
import com.udacity.pawhaven.data.Repository

class PetListActivity : BaseActivity(), PetListFragment.OnPetSelectedListener {

    private var isTwoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pet_list_activity)

        // Determine if we're in two-pane mode
        isTwoPane = findViewById<View>(R.id.detailContainer) != null

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.listContainer, PetListFragment())
                .commit()
        }
    }

    override fun onPetSelected(pet: Animal) {
        if (isTwoPane) {
            val fragment = PetDetailFragment.newInstance(pet)
            supportFragmentManager.beginTransaction()
                .replace(R.id.detailContainer, fragment)
                .commit()
        } else {
            val intent = Intent(this, PetDetailActivity::class.java).apply {
                putExtra(IntentExtras.EXTRA_ANIMAL, pet)
            }
            startActivity(intent)
        }
    }

    override fun onAddPetClicked() {
        val intent = Intent(this, AddAnimalActivity::class.java)
        startActivityForResult(intent, ADD_PET_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_PET_REQUEST_CODE && resultCode == RESULT_OK) {
            val pet = data?.getParcelableExtra<Animal>(IntentExtras.EXTRA_ANIMAL)
            if (pet != null) {
                Repository.pets.add(pet)
                val fragment = supportFragmentManager.findFragmentById(R.id.listContainer) as? PetListFragment
                fragment?.updateList()
            }
        }
    }

    companion object {
        const val ADD_PET_REQUEST_CODE = 1001
    }
}
