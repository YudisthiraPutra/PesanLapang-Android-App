package com.example.pesanlapang_android_app.module.login.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pesanlapang_android_app.core.theme.Blue
import com.example.pesanlapang_android_app.core.theme.BlackText1
import com.example.pesanlapang_android_app.core.theme.TextGray
import androidx.compose.foundation.text.KeyboardOptions

@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome Back",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = BlackText1,
            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = it.isBlank()
                },
                label = { Text("Email") },
                isError = emailError,
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Blue,
                    unfocusedBorderColor = TextGray,
                )
            )
            if (emailError) {
                Text("Email cannot be empty", color = MaterialTheme.colors.error, modifier = Modifier.align(Alignment.Start))
            }
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = it.isBlank()
                },
                label = { Text("Password") },
                isError = passwordError,
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Blue,
                    unfocusedBorderColor = TextGray,
                )
            )
            if (passwordError) {
                Text("Password cannot be empty", color = MaterialTheme.colors.error, modifier = Modifier.align(Alignment.Start))
            }
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    emailError = email.isBlank()
                    passwordError = password.isBlank()
                    if (!emailError && !passwordError) {
                        // Login success, navigate to Home
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Blue),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "LOGIN",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = { navController.navigate("signup") }
            ) {
                Text(
                    text = "Don't have an account? Sign Up",
                    fontSize = 14.sp,
                    color = TextGray
                )
            }
        }
    }
}
