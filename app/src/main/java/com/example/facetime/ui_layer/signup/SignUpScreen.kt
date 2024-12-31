package com.example.facetime.ui_layer.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.facetime.ui_layer.routes.login

@Composable
fun SignUpScreen(navController: NavHostController) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmedPassword by remember { mutableStateOf("") }

    val showPassword by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    val icon = if (showPassword) {
        Icons.Outlined.Visibility
    } else {
        Icons.Outlined.VisibilityOff
    }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Signup to connect",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = Color.Red
        )
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            singleLine = true,
            label = { Text("Enter Username") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next, showKeyboardOnFocus = true
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            })
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Enter Password") },
            trailingIcon = {
                IconButton(
                    onClick = { showPassword != showPassword }
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Password Visibility Icon"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (showPassword) VisualTransformation.None
            else PasswordVisualTransformation()
        )

        OutlinedTextField(
            value = confirmedPassword,
            onValueChange = { confirmedPassword = it },
            label = { Text("Confirm Password") },
            trailingIcon = {
                IconButton(
                    onClick = { showPassword != showPassword }
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Password Visibility Icon"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (showPassword) VisualTransformation.None
            else PasswordVisualTransformation()
        )
        Button(
            onClick = {}
        ) {
            Text("Signup")
        }
        TextButton(
            onClick = {
                navController.navigate(login)
            }
        ) {
            Text("Already have an account? Login!")
        }
    }
}

@Preview
@Composable
fun SignupScreenPreview() {
    SignUpScreen(rememberNavController())
}