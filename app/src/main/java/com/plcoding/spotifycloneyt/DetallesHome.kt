package com.plcoding.spotifycloneyt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
var recyclerPlaylist : RecyclerView? = null
var btnBack: androidx.appcompat.widget.Toolbar?= null
/**
 * A simple [Fragment] subclass.
 * Use the [DetallesHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetallesHome : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmentHomeDetalles = inflater.inflate(R.layout.fragment_detalles_home, container, false)
       recyclerPlaylist = fragmentHomeDetalles.findViewById(R.id.recycler_playlist)
        btnBack = fragmentHomeDetalles.findViewById(R.id.toolbar_detalles_home)
        // Inflate the layout for this fragment
        return fragmentHomeDetalles
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listaItemsPlaylist = arrayListOf<Cancion>()
        for (i in 1..20) {
            listaItemsPlaylist.add(Cancion("hola","IMAGEN"))
        }

        //DEBO CREAR UNA LISTA PARA CADA RECYCLER
       iniciarRecyclerView(listaItemsPlaylist,this, recyclerPlaylist!!,
           AdaptadorPlaylist(this,listaItemsPlaylist, recyclerPlaylist!!)
       )

        //BOTON BACK
        btnBack?.setNavigationOnClickListener {
            val cambioFragment = Home()
            this.activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_container,cambioFragment).commit()
            }
        }
    }

    fun iniciarRecyclerView(lista: List<*>, actividad: DetallesHome, recyclerView: RecyclerView, adaptador:RecyclerView.Adapter<*>){
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator= androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(actividad.context, LinearLayoutManager.VERTICAL, false)
        adaptador.notifyDataSetChanged()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetallesHome.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetallesHome().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



}