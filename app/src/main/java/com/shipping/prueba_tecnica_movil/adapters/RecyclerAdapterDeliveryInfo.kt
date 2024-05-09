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
import com.shipping.prueba_tecnica_movil.ui.viewmodel.RemisionViewModel

class RecyclerAdapterDeliveryInfo (private val remisionViewModel:RemisionViewModel): RecyclerView.Adapter<RecyclerAdapterDeliveryInfo.ViewHolder>() {


    var deliveryInfo: MutableList<Country>  = ArrayList()
    lateinit var context:Context
    private var navController: NavController? = null

    fun clear() {
        deliveryInfo.clear()
        notifyDataSetChanged()
    }

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
            .imageButton
            .setOnClickListener{
            Log.d("MAP", "RecyclerAdapterDeliveryInfo: click ")
            this.navController?.let {
                    navController ->
                remisionViewModel.enviarRemision( this.deliveryInfo.get(position) )
               // navController.navigate(R.id.action_blankFragment2_to_view_maps_shipping)
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
        private var expandableLayout : RelativeLayout           = itemView.findViewById(R.id.expandable_layout)
        private var iconButtonExpandable                        = view.findViewById<ImageButton>(R.id.icon_button_expandable)
        private var itemInfoAreaCountryR                        = view.findViewById(R.id.item_info_area_country_r) as TextView
        private var itemInfoLanguagesR                          = view.findViewById(R.id.item_info_languages_r) as TextView
        private var itemInfoContinentsCountryR                  = view.findViewById(R.id.item_info_continents_country_r) as TextView
        private val itemInfoMoreInformationIcon                 = view.findViewById<ImageButton>(R.id.item_info_more_information_icon)
        private val itemInfoFlagsCountry                        = view.findViewById<ImageView>(R.id.item_info_flags_country)

        fun bind(remissionData:Country, context: Context){
            itemInfoOfficialName.text                  = remissionData.name.official
            itemInfoCapital.text                       = remissionData.capital.toString()
            itemInfoCurrenciesCountry.text             = remissionData.currencies.toString()
            itemInfoAreaCountryR.text                  = remissionData.area.toString()
            itemInfoLanguagesR.text                    = remissionData.languages.values.toString()
            itemInfoContinentsCountryR.text            = remissionData.subregion
            Glide.with(context)
                .load(remissionData.flags)
                .centerCrop()
                .into(itemInfoFlagsCountry)
        }

    }

}