package ec.uti.edu.utifact.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ec.uti.edu.utifact.R
import ec.uti.edu.utifact.database
import ec.uti.edu.utifact.entity.EmisorAdapter

class FragmentEmisor: Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var clienteAdapter: EmisorAdapter
    private lateinit var dbHelper: database
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_emisor, container, false)
    }
}