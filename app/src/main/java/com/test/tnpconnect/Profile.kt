package com.test.tnpconnect

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.test.tnpconnect.Model.UserModel
import com.test.tnpconnect.Util.AndroidUtil
import com.test.tnpconnect.Util.FirebaseUtil
import de.hdodenhof.circleimageview.CircleImageView

class Profile : Fragment() {

    private lateinit var profilePic: CircleImageView
    private lateinit var userNameInput: TextInputEditText
    private lateinit var phoneInput: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var btnUpdate: Button
    private lateinit var txtLogout: TextView

    private lateinit var currentUserModel: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        profilePic = view.findViewById(R.id.profile_image)
        userNameInput = view.findViewById(R.id.txtName)
        phoneInput = view.findViewById(R.id.txtNumber)
        email = view.findViewById(R.id.txtEmail)
        btnUpdate = view.findViewById(R.id.btnUpdate)
        txtLogout = view.findViewById(R.id.txtLogout)

        getUserData()

        btnUpdate.setOnClickListener {
            updateBtnClick()
        }

        txtLogout.setOnClickListener {
            FirebaseUtil.logout()

            val intent = Intent(requireContext(), SplashScreen::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }


        return view
    }

    private fun updateBtnClick() {
        val newUserName: String = userNameInput.text.toString()

        if (newUserName.isEmpty() || newUserName.length < 3) {
            userNameInput.error = "user name length should be at least 3 chars"
            return
        }
        currentUserModel.userName = newUserName
        updateToFireStore()


    }

    private fun updateToFireStore() {
        FirebaseUtil.currentUserDetails().set(currentUserModel)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast("Updated Successfully")
                } else {
                    showToast("Update Failed")
                }
            }
    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun getUserData() {
        FirebaseUtil.currentUserDetails().get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document.exists()) {
                    currentUserModel = document.toObject(UserModel::class.java)!!
                    userNameInput.setText(currentUserModel.userName)
                    phoneInput.setText(currentUserModel.phone)
                    email.setText(currentUserModel.email)
                }
            }
        }
    }
}
