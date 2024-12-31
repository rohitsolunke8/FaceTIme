package com.example.facetime.ui_layer.signup

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val signupState = MutableStateFlow<SignupState>(SignupState.Normal)
    val signupStateFlow = signupState.asStateFlow()

    fun signup(useName: String, password: String) {

        val auth = FirebaseAuth.getInstance()
        signupState.value = SignupState.Loading
        auth.createUserWithEmailAndPassword(useName, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        signupState.value = SignupState.Success
                    } else {
                        signupState.value = SignupState.Error
                    }
                } else {
                    signupState.value = SignupState.Error
                }
            }
    }
}

sealed class SignupState {
    object Normal : SignupState()
    object Loading : SignupState()
    object Success : SignupState()
    object Error : SignupState()
}