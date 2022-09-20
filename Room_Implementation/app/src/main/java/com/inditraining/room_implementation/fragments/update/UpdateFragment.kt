package com.inditraining.room_implementation.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.inditraining.room_implementation.R
import com.inditraining.room_implementation.databinding.FragmentUpdateBinding
import com.inditraining.room_implementation.model.User
import com.inditraining.room_implementation.viewModel.UserViewModel

class UpdateFragment : Fragment(), View.OnClickListener {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding =  FragmentUpdateBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        val menuHost: MenuHost = requireActivity()
        binding.etUpdateFirstName.setText(args.currentUser.firstName)
        binding.etUpdateLastName.setText(args.currentUser.lastName)
        binding.etUpdateAge.setText(args.currentUser.age.toString())
        binding.btnUpdateSubmit.setOnClickListener(this)

        menuHost.addMenuProvider(object :MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.delete_menu, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menu_delete -> {
                        deleteUser()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onClick(v: View?) {
        updateItem()
    }

    private fun updateItem() {
        val firstName = binding.etUpdateFirstName.text.toString()
        val lastName = binding.etUpdateLastName.text.toString()
        val age = Integer.parseInt(binding.etUpdateAge.text.toString())
        if(inputCheck(firstName, lastName, binding.etUpdateAge.text)) {
            val updateUser = User(args.currentUser.id, firstName, lastName, age)
            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_list_fragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _,_ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "Successfully removed: ${args.currentUser.firstName}",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_list_fragment)
        }
        builder.setNegativeButton("No") {_,_ ->}
        builder.setTitle("Delete ${args.currentUser.firstName} ?")
        builder.setMessage("Are you sure want to delete ${args.currentUser.firstName}")
        builder.create().show()
    }
}