package com.app.pepens.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.model.Categories.CategoriesData
import com.app.pepens.utils.Utils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.custom_category_layout.view.*

/**
 * Created by Vikas Kumar Singh on 11/07/20.
 */

class CategoriesPagerAdapter : RecyclerView.Adapter<CategoriesPagerAdapter.ViewHolder> {

    private var context: Context
    private var categoriesDataArrayList: ArrayList<CategoriesData>

    constructor(context: Context, categoriesDataArrayList: ArrayList<CategoriesData>) : super() {
        this.context = context
        this.categoriesDataArrayList = categoriesDataArrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.custom_category_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (categoriesDataArrayList == null) {
            0
        } else {
            categoriesDataArrayList!!.count()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var sharedPreferences = context!!.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        Glide.with(context)
            .load(categoriesDataArrayList!![position].displayIconUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.imageView)
        holder.tvTitle.text = categoriesDataArrayList!![position].businessCategoryName

        holder.itemView.setOnClickListener {
            Utils().clickStream(context, sharedPreferences.getString("USER_ID", "0")!!, "8", "${categoriesDataArrayList!![position].businessCategoryId}", "3", "${categoriesDataArrayList!![position].businessCategoryId}", "")
//            var bundle = Bundle()
//            bundle.putParcelable("data", categoriesDataArrayList!![position])
//            if (it.findNavController().currentDestination?.id == R.id.categoriesFragment)
//                it.findNavController().navigate(R.id.action_categoriesFragment_to_categoryBusinessFragment, bundle)

//            val fragment = CategoryBusinessFragment()
//            val fragmentManager = (context as FragmentActivity).supportFragmentManager
//            val fragmentTransaction = fragmentManager!!.beginTransaction()
//            var bundle = Bundle()
//            bundle.putParcelable("data", categoriesDataArrayList!![position])
//            fragment.arguments = bundle
//            fragmentTransaction.replace(R.id.frameLayout, fragment)
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.commit()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.imageView
        val tvTitle: TextView = view.tvTitle
    }

}