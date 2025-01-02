package com.example.facetime.ui_layer.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.facetime.MainActivity
import com.example.facetime.constants.appId
import com.example.facetime.constants.appSign
import com.google.firebase.auth.FirebaseAuth
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton
import com.zegocloud.uikit.service.defines.ZegoUIKitUser

@Composable
fun HomeScreen(navController: NavHostController) {

    val context = LocalContext.current as MainActivity

    LaunchedEffect(key1 = Unit) {
        val user = FirebaseAuth.getInstance().currentUser
        Log.d("User", "HomeScreen: $user")
        user?.let {
            context.initZegoInviteServices(appId, appSign, it.email!!, it.email!!)
            Log.d("User", "HomeScreen: ${it.email}")
        }
    }

    var targetUserId by remember { mutableStateOf("") }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = targetUserId,
            onValueChange = { targetUserId = it },
            label = { Text("Enter your email") }
        )

        Row(
            Modifier
                .fillMaxWidth(1f)
                .background(color = Color.Green),
//                .align(alignment = Alignment.Bottom),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            CallButton(modifier = Modifier.size(16.dp), isVideoCall = false) { button ->
                if (targetUserId.isNotEmpty()) button.setInvitees(
                    mutableListOf(
                        ZegoUIKitUser(
                            targetUserId
                        )
                    )
                )
            }

            CallButton(modifier = Modifier.size(16.dp), isVideoCall = true) { button ->
                if (targetUserId.isNotEmpty()) button.setInvitees(
                    mutableListOf(
                        ZegoUIKitUser(
                            targetUserId
                        )
                    )
                )
            }
        }
    }
}

@Composable
fun CallButton(
    modifier: Modifier = Modifier,
    isVideoCall: Boolean,
    onClick: (ZegoSendCallInvitationButton) -> Unit,
) {
    AndroidView(factory = { context ->

        val button = ZegoSendCallInvitationButton(context)

        button.setIsVideoCall(isVideoCall)
        button.resourceID = "zego_data"
        button

    }) { zegoCallButton ->
        zegoCallButton.setOnClickListener { _ -> onClick(zegoCallButton) }
    }
}


