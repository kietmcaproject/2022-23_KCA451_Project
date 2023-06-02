package com.app.pepens.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.activity.MainActivity
import com.app.pepens.fragment.AddressListFragment
import com.app.pepens.model.Address.Addresses
import com.app.pepens.model.Address.AddressData
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.custom_addresses_layout.view.*


class AddressesAdapter : RecyclerView.Adapter<AddressesAdapter.ViewHolder> {

    private val context: Context
    private val addresses: Addresses
    private val clickEvent: ClickEvent

    constructor(
        context: Context,
        addresses: Addresses,
        addressListFragment: AddressListFragment
    ) : super() {
        this.context = context
        this.addresses = addresses
        this.clickEvent = addressListFragment
    }

    interface ClickEvent {
        fun editAddress(position: Int)
        fun deleteAddress(position: Int)
        fun deliverToAddress(position: Int)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.custom_addresses_layout,
                viewGroup,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return if (addresses.addressData == null) {
            0
        } else {
            addresses.addressData.size
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvType.text = addresses.addressData[position].addressType
        var add = ""
        if (!addresses.addressData[position].firstName.isNullOrEmpty())
            add += "${addresses.addressData[position].firstName}"
        if (!addresses.addressData[position].lastName.isNullOrEmpty())
            add += " ${addresses.addressData[position].lastName}"
        if (!addresses.addressData[position].addressLine1.isNullOrEmpty())
            add += "\n${addresses.addressData[position].addressLine1}, "
        if (!addresses.addressData[position].addressLine2.isNullOrEmpty())
            add += "${addresses.addressData[position].addressLine2}"
//        if (!addresses.addressData[position].landmark.isNullOrEmpty())
//            add += "\nLandmark: ${addresses.addressData[position].landmark}"
        if (!addresses.addressData[position].city.isNullOrEmpty())
            add += "\n${addresses.addressData[position].city}"
        if (!addresses.addressData[position].zipcode.isNullOrEmpty())
            add += "(${addresses.addressData[position].zipcode})"
        if (!addresses.addressData[position].state.isNullOrEmpty())
            add += ", ${addresses.addressData[position].state}"
        if (!addresses.addressData[position].country.isNullOrEmpty())
            add += ", ${addresses.addressData[position].country}"
        viewHolder.tvAddress.text = add

        if (addresses.addressData[position].isDefault) {
            viewHolder.tvDefault.visibility = View.VISIBLE
        } else {
            viewHolder.tvDefault.visibility = View.GONE
        }

        if (AddressListFragment.goBackForAddress) {
            if (addresses.addressData[position].areaId != MainActivity.login!!.loginData!!.areaId.toInt()) {
                viewHolder.tvType.setTextColor(Color.parseColor("#888888"))
                viewHolder.tvAddress.setTextColor(Color.parseColor("#888888"))
                viewHolder.tvDefault.setTextColor(Color.parseColor("#888888"))
            }
        }

        viewHolder.btnEdit.setOnClickListener {
            clickEvent.editAddress(position)
        }

        viewHolder.btnDelete.setOnClickListener {
            clickEvent.deleteAddress(position)
        }

        viewHolder.itemView.setOnClickListener {
            clickEvent.deliverToAddress(position)
        }
    }

    fun removeItem(position: Int) {
        addresses.addressData.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: AddressData, position: Int) {
        addresses.addressData.add(position, item)
        notifyItemInserted(position)
    }

    fun getData(): ArrayList<AddressData> {
        return addresses.addressData
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDefault: TextView = view.tvDefault
        val tvType: TextView = view.tvType
        val tvAddress: TextView = view.tvAddress
        val btnEdit: MaterialButton = view.btnEdit
        val btnDelete: MaterialButton = view.btnDelete
    }
}