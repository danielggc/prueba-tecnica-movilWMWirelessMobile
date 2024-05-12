package com.shipping.prueba_tecnica_movil.ui.view

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.shipping.prueba_tecnica_movil.databinding.InfoCountryBinding
import com.shipping.prueba_tecnica_movil.R
import com.shipping.prueba_tecnica_movil.domain.model.Country
import com.shipping.prueba_tecnica_movil.ui.viewmodel.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class InfoCountry :Fragment() {
    private  var _binding: InfoCountryBinding? = null

    private val countryViewModel: CountryViewModel by viewModels()

    private val binding get() = _binding!!
    lateinit var frameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        countryViewModel.onCreate()

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
        binding.loadingHome.isVisible = false

        val args:InfoCountryArgs by navArgs()
        val info = args.infoCountryData


        super.onViewCreated(view, savedInstanceState)
        val buttonGeneralInformation = binding.root.findViewById<androidx.cardview.widget.CardView>(R.id.button_general_information)
        val buttonGeographyInformation = binding.root.findViewById<androidx.cardview.widget.CardView>(R.id.button_geography_information)
        val buttonCultureInformation = binding.root.findViewById<androidx.cardview.widget.CardView>(R.id.button_culture_information)
        val buttonGovernmentInformation = binding.root.findViewById<androidx.cardview.widget.CardView>(R.id.button_government_information)
        val buttonPopulationsInformation = binding.root.findViewById<androidx.cardview.widget.CardView>(R.id.button_populations_information)








        frameLayout = binding.root.findViewById( R.id.frame_layout_info )
        buttonGeneralInformation.setOnClickListener{
            inflateLayout( R.layout.general_datails )
            setVValuesGeneralInformation(info)

        }

        buttonGeographyInformation.setOnClickListener{
            inflateLayout( R.layout.geography_datails )
            setValuesGeography(info)

        }

        buttonCultureInformation.setOnClickListener{
            inflateLayout( R.layout.datails_cultury )
            setValueCulture(info)
        }

        buttonGovernmentInformation.setOnClickListener{
            inflateLayout( R.layout.government_administration )
            setValuesGovernment(info)
        }
        buttonPopulationsInformation.setOnClickListener{
            inflateLayout( R.layout.datails_populations )
            setValuesPopulation(info)
        }




    }
    private fun inflateLayout(layoutId: Int) {
        val inflater = LayoutInflater.from(requireContext())
        val layout = inflater.inflate(layoutId, frameLayout, false)

        frameLayout.removeAllViews()
        frameLayout.addView(layout)
    }




    fun setVValuesGeneralInformation( info:Country ){
        val textRegionValue: TextView        = binding.root.findViewById(R.id.text_common_name_value)
        val textSubRegionValue: TextView     = binding.root.findViewById(R.id.text_official_name_value)
        val textNativeNameR: TextView        = binding.root.findViewById(R.id.text_native_name_r)
        val textCountryCodeValue: TextView   = binding.root.findViewById(R.id.text_country_code_value)
        val textNumericCodeValue: TextView   = binding.root.findViewById(R.id.text_numeric_code_value)
        val textCoaCodeR: TextView           = binding.root.findViewById(R.id.text_coa_code_r)
        val textIndependenceValue: TextView  = binding.root.findViewById(R.id.text_independence_value)
        val textStateValue: TextView         = binding.root.findViewById(R.id.text_state_value)
        val textUnMemberR: TextView          = binding.root.findViewById(R.id.text_un_member_r)

        textRegionValue.text        = info.name.common
        textSubRegionValue.text     = info.name.official
        textNativeNameR.text        = info.name.nativeName
        textCountryCodeValue.text   = info.postalCode.format
        textNumericCodeValue.text   = info.cca2
        textCoaCodeR.text           = info.cca3
        textIndependenceValue.text  = info.independent.toString()
        textStateValue.text         = info.status
        textUnMemberR.text          = info.unMember.toString()
    }

    fun setValuesGeography( info: Country  ) {


        val textRegionValue: TextView           =  binding.root.findViewById(R.id.text_region_value)
        val textSubRegionValue: TextView        = binding.root.findViewById(R.id.text_sub_region_value)
        val textLatLongValues: TextView         = binding.root.findViewById(R.id.text_lat_long_values)
        val textNeighborsValues: LinearLayout   = binding.root.findViewById(R.id.buttons_neighbors_container_value)
        val textAreaValue: TextView             = binding.root.findViewById(R.id.text_area_value)
        val textLandlockedValue: TextView       = binding.root.findViewById(R.id.text_landlocked_value)
        for (name in info.borders) {
            val button: Button = Button(this.context)
            button.textSize =5f
            button.setBackgroundColor(Color.WHITE )
            button.setTextColor(Color.BLACK)
            val layoutParams = ConstraintLayout.LayoutParams(  150, 70)
            layoutParams.setMargins(10  , 20 , 10, 20)
            button.layoutParams = layoutParams
            button.text = name
            button.setOnClickListener {
                val data  = countryViewModel.getCountryByCoutryCode( name )
                data    .observe(viewLifecycleOwner) { country ->
                    findNavController().navigate( InfoCountryDirections.actionInfoCountrySelf( country.first() )  )
                }

            }
            textNeighborsValues.addView(button)


        }

        textRegionValue.text        = info.region
        textSubRegionValue.text     = info.subregion
        textLatLongValues.text      = info.latlng.toString()
        textAreaValue.text          = info.area.toString()
        textLandlockedValue.text    = info.landlocked.toString()
    }



    fun setValuesGovernment( info: Country ){
        val textCapitalValue: TextView           =  binding.root.findViewById(R.id.text_capital_value)
        val textLanguageValue: TextView          =  binding.root.findViewById(R.id.text_language_value)
        val textCurrencyValue: TextView          =  binding.root.findViewById(R.id.text_currency_value)
        val textTimezoneValue: TextView          =  binding.root.findViewById(R.id.text_timezone_value)
        val textStartDayValue: TextView          =  binding.root.findViewById(R.id.text_start_day_value)
        val textFifaMemberValue: TextView        =  binding.root.findViewById(R.id.text_fifa_member_value)

        textCapitalValue.text           = info.capital.toString()
        textLanguageValue.text          = info.languages.toString()
        textCurrencyValue.text          = info.currencies
        textTimezoneValue.text          = info.timezones.toString()
        textStartDayValue.text          = info.startOfWeek
        textFifaMemberValue.text        = info.fifa
    }

    fun setValuesPopulation( info: Country ){
        val textPopulationValue: TextView       = binding.root.findViewById(R.id.text_population_value)
        val textGiniValue: TextView             = binding.root.findViewById(R.id.text_gini_value)
        val textDemonymsValue: TextView         = binding.root.findViewById(R.id.text_demonyms_value)

        textPopulationValue.text        = info.population.toString()
        textGiniValue.text              = info.gini.toString()
        textDemonymsValue.text          = info.demonyms
    }

    fun setValueCulture( info: Country ) {
        var state:Boolean = false
        binding.loadingHome.isVisible = true
        val textFlag              = binding.root.findViewById<ImageView>(R.id.flag_country_value)
        val flagCoatArmsValue: ImageView   = binding.root.findViewById(R.id.flag_coat_arms_value)

        Glide.with(  this )
            .load(info.flags)
            .centerCrop()
            .listener(object : RequestListener<Drawable> {

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    state = true
                    return  false
                }
            }
            )
            .into(textFlag)

        Glide.with(  this )
            .load(info.coatOfArms)
            .centerCrop()

            .listener(object : RequestListener<Drawable> {

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        if( state )
                            binding.loadingHome.isVisible =false
                        return  false
                    }
                }
            )
            .into(flagCoatArmsValue)

        val textMapLinksValue: TextView     = binding.root.findViewById(R.id.text_map_links_value)
        val textCountryCodesValue: TextView = binding.root.findViewById(R.id.text_country_codes_value)
        val textRoadSideValue: TextView     = binding.root.findViewById(R.id.text_road_side_value)
        val textPostalCodeValue: TextView   = binding.root.findViewById(R.id.text_postal_code_value)

        textMapLinksValue.text      = info.maps.values.toString()
        textCountryCodesValue.text  = info.ccn3
        textRoadSideValue.text      = info.car
        textPostalCodeValue.text    = info.postalCode.regex
    }
}
