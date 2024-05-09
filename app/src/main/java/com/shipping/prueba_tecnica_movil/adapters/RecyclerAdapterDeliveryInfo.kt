package com.shipping.prueba_tecnica_movil.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
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

        holder.expendableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.linearLayout.setOnClickListener{
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

        val codigoRemision                      = view.findViewById(R.id.codigo_remision_item) as TextView
        val direccionDestinatario               = view.findViewById(R.id.direccion_destinatario_item) as TextView
        val nombreTerminalDestino               = view.findViewById(R.id.nombre_terminal_destino_item) as TextView
        var expendableLayout : RelativeLayout   = itemView.findViewById(R.id.expandable_layout)
        var linearLayout                        = view.findViewById<ImageButton>(R.id.icon_button_user)
        var cellPone                            = view.findViewById(R.id.telefono_item_r) as TextView
        var ownerDestination                    = view.findViewById(R.id.destinatario_item_r) as TextView
        var origen                              = view.findViewById(R.id.origen_Info_item_r) as TextView
        val imageButton                         = view.findViewById<ImageButton>(R.id.icon_button_map_iem)

        fun bind(remissionData:Country, context: Context){
            codigoRemision.text             = remissionData.name.common
            direccionDestinatario.text      = remissionData.capital.toString()
            nombreTerminalDestino.text      = remissionData.positionCounter.toString()
            cellPone.text                   = remissionData.currencies
            ownerDestination.text           = remissionData.region
            origen.text                     = remissionData.subregion
        }

    }

}