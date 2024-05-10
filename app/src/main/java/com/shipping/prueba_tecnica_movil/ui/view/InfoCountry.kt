package com.shipping.prueba_tecnica_movil.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.shipping.prueba_tecnica_movil.databinding.InfoCountryBinding
import com.shipping.prueba_tecnica_movil.R
import com.shipping.prueba_tecnica_movil.domain.model.Country

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


        val navController = findNavController()
        val args:InfoCountryArgs by navArgs()
        val amount = args.infoCountryData


        super.onViewCreated(view, savedInstanceState)
        val buttonGeneralInformation = binding.root.findViewById<androidx.cardview.widget.CardView>(R.id.button_general_information)
        val buttonGeographyInformation = binding.root.findViewById<androidx.cardview.widget.CardView>(R.id.button_geography_information)
        val buttonCultureInformation = binding.root.findViewById<androidx.cardview.widget.CardView>(R.id.button_culture_information)
        val buttonGovernmentInformation = binding.root.findViewById<androidx.cardview.widget.CardView>(R.id.button_government_information)
        val buttonPopulationsInformation = binding.root.findViewById<androidx.cardview.widget.CardView>(R.id.button_populations_information)








        frameLayout = binding.root.findViewById( R.id.frame_layout_info )
        buttonGeneralInformation.setOnClickListener{
            Log.d("click ", "click!!!!!}  " + amount.toString() )

            inflateLayout( R.layout.general_datails )
        }

        buttonGeographyInformation.setOnClickListener{
            Log.d("click ", "click!!!!! " )

            inflateLayout( R.layout.geography_datails )
        }

        buttonCultureInformation.setOnClickListener{
            inflateLayout( R.layout.datails_cultury )
        }
        buttonGovernmentInformation.setOnClickListener{
            Log.d("click ", "click!!!!! " )

            inflateLayout( R.layout.government_administration )
        }
        buttonPopulationsInformation.setOnClickListener{
            Log.d("click ", "click!!!!! " )

            inflateLayout( R.layout.datails_populations )
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




    fun setvaluesGeneralInformation( info:Country ){
        val textRegionValue: TextView        = binding.root.findViewById(R.id.text_region_value)
        val textSubRegionValue: TextView     = binding.root.findViewById(R.id.text_sub_region_value)
        val textNativeNameR: TextView        = binding.root.findViewById(R.id.text_native_name_r)
        val textCountryCodeValue: TextView   = binding.root.findViewById(R.id.text_country_code_value)
        val textNumericCodeValue: TextView   = binding.root.findViewById(R.id.text_numeric_code_value)
        val textCoaCodeR: TextView           = binding.root.findViewById(R.id.text_coa_code_r)
        val textIndependenceValue: TextView  = binding.root.findViewById(R.id.text_independence_value)
        val textStateValue: TextView         = binding.root.findViewById(R.id.text_state_value)
        val textUnMemberR: TextView          = binding.root.findViewById(R.id.text_un_member_r)

        // Usa las referencias para establecer el texto deseado
        textRegionValue.text        = info.region
        textSubRegionValue.text     = "Valor para la subregión"
        textNativeNameR.text        = "Valor para el nombre nativo"
        textCountryCodeValue.text   = "Valor para el código de país"
        textNumericCodeValue.text   = "Valor para el código numérico"
        textCoaCodeR.text           = "Valor para el código de país en el COI"
        textIndependenceValue.text  = "Valor para la independencia"
        textStateValue.text         = "Valor para el estado"
        textUnMemberR.text          = "Valor para el estado de miembro de las Naciones Unidas"
    }

}
