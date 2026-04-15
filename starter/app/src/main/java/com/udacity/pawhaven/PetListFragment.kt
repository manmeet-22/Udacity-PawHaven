package com.udacity.pawhaven

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.udacity.pawhaven.data.Animal
import com.udacity.pawhaven.data.Repository

class PetListFragment : Fragment() {

    private var listener: OnPetSelectedListener? = null

    interface OnPetSelectedListener {
        fun onPetSelected(pet: Animal)
        fun onAddPetClicked()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPetSelectedListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnPetSelectedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pet_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.petRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = PetListAdapter(Repository.pets) { pet ->
            listener?.onPetSelected(pet)
        }

        val addFab = view.findViewById<FloatingActionButton>(R.id.addPetFab)
        val userRole = Repository.user?.role
        
        if (userRole == com.udacity.pawhaven.data.Role.VOLUNTEER) {
            addFab.show()
            addFab.setOnClickListener {
                listener?.onAddPetClicked()
            }
        } else {
            addFab.hide()
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun updateList() {
        view?.findViewById<RecyclerView>(R.id.petRecyclerView)?.adapter?.notifyDataSetChanged()
    }
}
