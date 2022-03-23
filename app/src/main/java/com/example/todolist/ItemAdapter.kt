package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(var clickListener: ClickListener): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(), Filterable {

    var itemList = ArrayList<Item>()
    var itemListFilter = ArrayList<Item>()

    fun setData(items: ArrayList<Item>) {
        this.itemList = items
        this.itemListFilter = items
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tvName)
        val description = itemView.findViewById<TextView>(R.id.tvDesc)
        val image = itemView.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = inflater.inflate(R.layout.row_item, parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.name.text = itemList[position].name
        holder.description.text = itemList[position].desc
        holder.image.setImageResource(itemList[position].image)

        holder.itemView.setOnClickListener {
            clickListener.clickedItem(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    interface ClickListener {
        fun clickedItem(itemsModal: Item)
    }

    override fun getFilter(): Filter {

        return object: Filter() {
            override fun performFiltering(charsequence: CharSequence?): FilterResults {

                val filterResults = FilterResults()
                if(charsequence == null || charsequence.length < 0) {
                    filterResults.count = itemListFilter.size
                    filterResults.values = itemListFilter
                } else {
                    var searchChr = charsequence.toString().lowercase()

                    val data = ArrayList<Item>()

                    for(item in itemListFilter) {
                        if(item.name.contains(searchChr) || item.desc.contains(searchChr)) {
                            data.add(item)
                        }
                    }
                    filterResults.count = data.size
                    filterResults.values = data
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                itemList = results!!.values as ArrayList<Item>
                notifyDataSetChanged()

            }

        }

    }
}