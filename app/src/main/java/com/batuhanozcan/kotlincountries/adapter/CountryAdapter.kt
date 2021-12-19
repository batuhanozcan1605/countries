package com.batuhanozcan.kotlincountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.batuhanozcan.kotlincountries.R
import com.batuhanozcan.kotlincountries.databinding.ItemRowBinding
import com.batuhanozcan.kotlincountries.model.Country
import com.batuhanozcan.kotlincountries.util.downloadFromUrl
import com.batuhanozcan.kotlincountries.util.placeholderProgressBar
import com.batuhanozcan.kotlincountries.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.item_row.view.*

class CountryAdapter(val countryList: ArrayList<Country>):RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), CountryClickListener {
    class CountryViewHolder(var view: ItemRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.item_row,parent,false)
        val view = DataBindingUtil.inflate<ItemRowBinding>(inflater,R.layout.item_row,parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.view.country = countryList[position]
        holder.view.listener = this
    /*
        holder.view.name.text = countryList[position].countryName
        holder.view.region.text = countryList[position].countryRegion
        holder.view.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.imageView.downloadFromUrl(countryList[position].imageUrl, placeholderProgressBar(holder.view.context))
    */
    }

    override fun getItemCount(): Int {
      return countryList.size
    }

    fun updateCountryList(newCountryList : List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        val uuid = v.countryUuidText.text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}