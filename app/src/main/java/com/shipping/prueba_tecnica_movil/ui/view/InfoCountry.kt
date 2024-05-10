package com.shipping.prueba_tecnica_movil.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.shipping.prueba_tecnica_movil.databinding.InfoCountryBinding
import com.shipping.prueba_tecnica_movil.R

class InfoCountry :Fragment() {
    private  var _binding: InfoCountryBinding? = null
    private val binding get() = _binding!!
    lateinit var frameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = InfoCountryBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonGeneralInformation = binding.root.findViewById<androidx.cardview.widget.CardView>(R.id.button_general_information)
        val buttonGeneralInformation2 = binding.root.findViewById<androidx.cardview.widget.CardView>(R.id.button_geography_information)

        frameLayout = binding.root.findViewById( R.id.frame_layout_info )
        buttonGeneralInformation.setOnClickListener{
            Log.d("click ", "click!!!!! " )

            inflateLayout( R.layout.general_datails )
        }

        buttonGeneralInformation2.setOnClickListener{
            Log.d("click ", "click!!!!! " )

            inflateLayout( R.layout.geography_datails )
        }



    }
    private fun inflateLayout(layoutId: Int) {
        // Inflar el layout en el FrameLayout
        val inflater = LayoutInflater.from(requireContext())
        val layout = inflater.inflate(layoutId, frameLayout, false)

        // Limpiar el contenido anterior del FrameLayout y añadir el nuevo layout
        frameLayout.removeAllViews()
        frameLayout.addView(layout)
    }
}
