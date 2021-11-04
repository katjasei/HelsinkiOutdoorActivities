package com.example.helsinkioutdooractivities.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.helsinkioutdooractivities.data.liveData.FirebaseUserLiveData

class LoginViewModel : ViewModel() {

    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED
    }

    val authenticationState = FirebaseUserLiveData().map { user ->
        if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }

}