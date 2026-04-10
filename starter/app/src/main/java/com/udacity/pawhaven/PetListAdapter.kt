package com.udacity.pawhaven

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.pawhaven.components.PetRowComponent
import com.udacity.pawhaven.data.Animal

class PetListAdapter(
    private val pets: List<Animal>,
    private val onPetClicked: (Animal) -> Unit
) : RecyclerView.Adapter<PetListAdapter.PetViewHolder>() {

    class PetViewHolder(val petRow: PetRowComponent) : RecyclerView.ViewHolder(petRow)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val petRow = PetRowComponent(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        return PetViewHolder(petRow)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val pet = pets[position]
        holder.petRow.bind(pet)
        holder.petRow.setOnClickListener { onPetClicked(pet) }
    }

    override fun getItemCount(): Int = pets.size
}
