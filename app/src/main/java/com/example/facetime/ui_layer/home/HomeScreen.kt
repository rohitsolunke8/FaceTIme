package com.example.facetime.ui_layer.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    val context = LocalContext.current as MainActivity
    val user = FirebaseAuth.getInstance().currentUser
    LaunchedEffect(key1 = Unit) {

        Log.d("User", "HomeScreen: $user")
        user?.let {
            context.initZegoInviteServices(appId, appSign, it.email!!, it.email!!)
            Log.d("User", "HomeScreen: ${it.email}")
        }
    }

    var targetUserId by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FloatingActionButton(onClick = {}) {
                    IconButton(onClick = {}) {
                        CallButton(
                            Modifier
                                .size(16.dp)
                                .background(Color.Yellow),
                            isVideoCall = true
                        ) { button ->
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
                FloatingActionButton(onClick = {}) {
                    IconButton(onClick = {}) {
                        CallButton(
                            modifier = Modifier
                                .size(160.dp)
                                .background(Color.Green), isVideoCall = false
                        ) { button ->
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
                FloatingActionButton(onClick = {}) {
                    Icon(Icons.Outlined.Chat, "Chat Button")
                }
            }
        }
    ) {
        it
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("${user?.email}")
            OutlinedTextField(
                value = targetUserId,
                onValueChange = { targetUserId = it },
                label = { Text("Enter your email") }
            )
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


