package com.inditraining.latihan_training

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class DetailCategoryFragment : Fragment(), View.OnClickListener {

    private lateinit var tvCategoryName: TextView
    private lateinit var tvCategoryDescription: TextView
    private lateinit var btnProfile: Button
    private lateinit var btnShowDialog: Button
    var name: String? = null
    var description: String? = null

    companion object {
        var EXTRA_NAME = "extra_name"
        var EXTRA_DESCRIPTION = "extra_description"
    }

    private lateinit var toProfile: Intent


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvCategoryName = view.findViewById(R.id.tv_category_name)
        tvCategoryDescription = view.findViewById(R.id.tv_category_description)
        btnProfile = view.findViewById(R.id.btn_profile)
        btnShowDialog = view.findViewById(R.id.btn_show_dialog)

        toProfile = Intent(requireContext(), ProfileActivity::class.java)

        btnShowDialog.setOnClickListener(this)

        btnProfile.setOnClickListener(this)

        if(savedInstanceState != null) {
//            val nameFromBundle = savedInstanceState.getString(EXTRA_NAME)
            val descFromBundle = savedInstanceState.getString(EXTRA_DESCRIPTION)
//            name = nameFromBundle
            description = descFromBundle
//            tvCategoryName.text = name
//            tvCategoryDescription.text = description
        }

        if(arguments != null) {
            val categoryName = arguments?.getString(EXTRA_NAME)
            tvCategoryName.text = categoryName
            tvCategoryDescription.text = description
        }

    }

    override fun onClick(view: View) {
        if(view.id == R.id.btn_show_dialog) {
            val mOptionDialogFragment = OptionDialogFragment()
            val mFragmentManager = childFragmentManager

            mOptionDialogFragment.show(mFragmentManager, OptionDialogFragment::class.java.simpleName)
        } else if(view.id == R.id.btn_profile) {
            toProfile.putExtra(ProfileActivity.EXTRA_APP, tvCategoryName.text.toString())
            startActivity(toProfile)
        }
    }

    internal val optionDialogListener: OptionDialogFragment.OnOptionDialogListener = object : OptionDialogFragment.OnOptionDialogListener {
        override fun onOptionChosen(text: String?) {
            Toast.makeText(requireActivity(), text, Toast.LENGTH_SHORT).show()
        }
    }
}