package com.development.mybottomsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.development.mybottomsheet.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var categoryName =  ObservableField("")
    private var categoryList = ArrayList<CategoryData>()
    private var categoryId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.fragment = this

        categoryList.add(
            CategoryData(
                "My Memorials",1
            )
        )
        categoryList.add(
            CategoryData(
                "Memorials",2
            )
        )
        categoryList.add(
            CategoryData(
                "Joined Memorials",3
            )
        )
        categoryList.add(
            CategoryData(
                "Received Requests",4
            )
        )

        binding.tvCategory.setOnClickListener {
            showCategoryPopup()
        }
    }

    private fun showCategoryPopup(){
        val bottomSheetDialog =  BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.dialog_listview);
        val recyclerView = bottomSheetDialog.findViewById<RecyclerView>(R.id.rvList)
        val mCategoryAdapter = CategoryListAdapter( this,
            categoryList, object:RecyclerViewItemOnClickListener{
                override fun onViewClick(position: Int) {
                    categoryId = categoryList[position]._id.toString()
                    //binding.tvCategory.text = categoryList[position].name
                    categoryName.set(categoryList[position].name)
                    bottomSheetDialog.dismiss()
                    //mCategoryAdapter.notifyDataSetChanged()
                }
            })
        recyclerView?.adapter = mCategoryAdapter

        bottomSheetDialog.show()
    }
}