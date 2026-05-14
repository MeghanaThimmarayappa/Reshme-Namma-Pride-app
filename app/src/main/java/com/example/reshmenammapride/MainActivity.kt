package com.sericulture.reshmenammapride

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen(
                onLoginSuccess = {
                    // Navigate to next screen
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                },
                onSignupClick = {
                    startActivity(Intent(this, SignupActivity::class.java))
                }
            )
        }
    }
}

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, onSignupClick: () -> Unit) {

    // ✅ Changed from 'phone' to 'name'
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // App Title
        Text(
            text = "🐛Reshme Namma Pride",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6B2D2D)
        )

        Text(
            text = "ರೇಷ್ಮೆ ನಮ್ಮ ಹೆಮ್ಮೆ",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        // ✅ Name field (was Phone field)
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name | ಹೆಸರು") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(size = 12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password | ಪಾಸ್ವರ್ಡ್") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(size = 12.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Error message
        if (errorMsg.isNotEmpty()) {
            Text(text = errorMsg, color = Color.Red, fontSize = 13.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Login Button
        Button(
            onClick = {
                // ✅ Validation uses 'name' instead of 'phone'
                if (name.isEmpty() || password.isEmpty()) {
                    errorMsg = "Please fill all fields"
                } else {
                    onLoginSuccess()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6B2D2D)
            )
        ) {
            Text(text = "Login | ಲಾಗಿನ್", color = Color.White, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sign Up Link
        TextButton(onClick = onSignupClick) {
            Text(
                text = "Don't have account? Sign Up",
                color = Color(0xFF6B2D2D)
            )
        }
    }
}
