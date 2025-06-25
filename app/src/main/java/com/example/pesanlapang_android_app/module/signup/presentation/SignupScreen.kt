package com.example.pesanlapang_android_app.module.signup.presentation

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
import com.example.pesanlapang_android_app.core.theme.CreamBackground
import com.example.pesanlapang_android_app.core.theme.BlackText1
import com.example.pesanlapang_android_app.core.theme.TextGray
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.RoundedCornerShape
@Composable
fun SignUpScreen(navController: NavHostController) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var fullNameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }
    var passwordMismatchError by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF5F5F5)  // ganti sesuai tema kamu
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "Create Account",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            OutlinedTextField(
                value = fullName,
                onValueChange = {
                    fullName = it
                    fullNameError = it.isBlank()
                },
                label = { Text("Full Name") },
                isError = fullNameError,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )
            if (fullNameError) Text("Full Name cannot be empty", color = MaterialTheme.colors.error)

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = it.isBlank()
                },
                label = { Text("Email") },
                isError = emailError,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
            )
            if (emailError) Text("Email cannot be empty", color = MaterialTheme.colors.error)

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = it.isBlank()
                    passwordMismatchError = it != confirmPassword && confirmPassword.isNotBlank()
                },
                label = { Text("Password") },
                isError = passwordError,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
            )
            if (passwordError) Text("Password cannot be empty", color = MaterialTheme.colors.error)

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    confirmPasswordError = it.isBlank()
                    passwordMismatchError = it != password
                },
                label = { Text("Confirm Password") },
                isError = confirmPasswordError || passwordMismatchError,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
            )
            when {
                confirmPasswordError -> Text("Confirm Password cannot be empty", color = MaterialTheme.colors.error)
                passwordMismatchError -> Text("Passwords do not match", color = MaterialTheme.colors.error)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    fullNameError = fullName.isBlank()
                    emailError = email.isBlank()
                    passwordError = password.isBlank()
                    confirmPasswordError = confirmPassword.isBlank()
                    passwordMismatchError = password != confirmPassword

                    if (!fullNameError && !emailError && !passwordError && !confirmPasswordError && !passwordMismatchError) {
                        // Logika signup, sekarang langsung ke login
                        navController.navigate("login") {
                            popUpTo("signup") { inclusive = true }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text("SIGN UP", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = { navController.navigate("login") }) {
                Text("Already have an account? Login")
            }
        }
    }
}
