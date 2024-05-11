package com.shipping.prueba_tecnica_movil.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shipping.prueba_tecnica_movil.R
import com.shipping.prueba_tecnica_movil.domain.model.Country
import com.shipping.prueba_tecnica_movil.ui.view.HomeDirections
import com.shipping.prueba_tecnica_movil.ui.viewmodel.RemisionViewModel

class RecyclerAdapterDeliveryInfo (private val remisionViewModel:RemisionViewModel): RecyclerView.Adapter<RecyclerAdapterDeliveryInfo.ViewHolder>() {


    var deliveryInfo: MutableList<Country>  = ArrayList()
    lateinit var context:Context
    private var navController: NavController? = null

    fun clear() {
        deliveryInfo.clear()
        notifyDataSetChanged()
    }
    fun getSizeList():Int = deliveryInfo.size
    fun add(newDeliveryInfo: Country) {
        val existingItem = deliveryInfo.find { it.name == newDeliveryInfo.name }
        if (existingItem == null) {
            deliveryInfo.add(newDeliveryInfo)
            notifyItemInserted(deliveryInfo.size - 1)
        }
    }
    fun RecyclerAdapter(superheros : MutableList<Country>, context: Context){
        this.context = context
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = deliveryInfo.get(position)
        holder.bind(item, context)

        val isExpandable: Boolean = deliveryInfo[position].statusExpandable

        holder.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.iconButtonExpandable.setOnClickListener{
            val version = deliveryInfo[position]
            version.statusExpandable = !item.statusExpandable
            notifyItemChanged(position)
        }
        holder
            .itemInfoMoreInformationIcon
            .setOnClickListener{
            Log.d("MAP", "RecyclerAdapterDeliveryInfo: click ")
            this.navController?.let {
                    navController ->

                val dd = HomeDirections.actionBlankFragment2ToInfoCountry( holder.data )
                remisionViewModel.enviarRemision( this.deliveryInfo.get(position) )
                navController.navigate( dd )
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)



        return ViewHolder(layoutInflater.inflate(R.layout.textview_layout, parent, false))
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }




    override fun getItemCount(): Int {
        return deliveryInfo.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private  val itemInfoOfficialName                       = view.findViewById(R.id.item_info_official_name) as TextView
        private val itemInfoCapital                             = view.findViewById(R.id.item_info_capital) as TextView
        private val itemInfoCurrenciesCountry                   = view.findViewById(R.id.item_info_currencies_country) as TextView
        var expandableLayout : RelativeLayout                   = itemView.findViewById(R.id.expandable_layout)
        var iconButtonExpandable                                = view.findViewById<ImageButton>(R.id.icon_button_expandable)
        private var itemInfoAreaCountryR                        = view.findViewById(R.id.item_info_area_country_r) as TextView
        private var itemInfoLanguagesR                          = view.findViewById(R.id.item_info_languages_r) as TextView
        private var itemInfoContinentsCountryR                  = view.findViewById(R.id.item_info_continents_country_r) as TextView
        val itemInfoMoreInformationIcon                         = view.findViewById<ImageButton>(R.id.item_info_more_information_icon)
        private val itemInfoFlagsCountry                        = view.findViewById<ImageView>(R.id.item_info_flags_country)
        lateinit var data :Country
        fun bind(CountryData:Country, context: Context){
            data = CountryData
            if(CountryData.name.official.length > 22 )
                itemInfoOfficialName.text                  = CountryData.name.official.substring(0,20)+"..."
            else
                itemInfoOfficialName.text                  = CountryData.name.official
            itemInfoCapital.text                       = CountryData.capital.toString()
            itemInfoCurrenciesCountry.text             = CountryData.currencies.toString()
            itemInfoAreaCountryR.text                  = CountryData.area.toString()
            itemInfoLanguagesR.text                    = CountryData.languages.values.toString()
            itemInfoContinentsCountryR.text            = CountryData.subregion
            Glide.with(context)
                .load(CountryData.flags)
                .centerCrop()
                .into(itemInfoFlagsCountry)

        }

    }

}