package sv.edu.udb.desafiopractico2dsm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(private val medicsList : ArrayList<Medicina>, private val context: Context) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val Nombre : TextView = itemView.findViewById(R.id.medicName)
        val Precio : TextView = itemView.findViewById(R.id.medicPrice)
        val Indicaciones : TextView = itemView.findViewById(R.id.medicIndic)
        val Contraindicaciones : TextView = itemView.findViewById(R.id.medicContra)
        val img : ImageView = itemView.findViewById(R.id.medicImg)
        val Agregar : Button = itemView.findViewById(R.id.btnAgregar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.medic_items, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return medicsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = medicsList[position]

        holder.Nombre.text = currentitem.Nombre
        holder.Precio.text = "$" + currentitem.Precio.toString()
        holder.Indicaciones.text = currentitem.Indicaciones
        holder.Contraindicaciones.text = currentitem.Contraindicaciones
        Glide.with(context).load(medicsList[position].img).into(holder.img)
    }

}