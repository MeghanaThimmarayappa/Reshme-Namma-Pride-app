package com.example.reshmenammapride

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reshmenammapride.ui.theme.ReshmeNammaPrideTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen(
                onLoginSuccess = {
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
        Text(
            text = "🐛 Reshme Namma Pride",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6B2D2D)
        )
        Text(text = "ರೇಷ್ಮೆ ನಮ್ಮ ಹೆಮ್ಮೆ", fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name | ಹೆಸರು") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password | ಪಾಸ್ವರ್ಡ್") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        if (errorMsg.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = errorMsg, color = Color.Red, fontSize = 13.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (name.isEmpty() || password.isEmpty()) {
                    errorMsg = "Please fill all fields"
                } else {
                    onLoginSuccess()
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6B2D2D))
        ) {
            Text(text = "Login | ಲಾಗಿನ್", color = Color.White, fontSize = 16.sp)
        }

        TextButton(onClick = onSignupClick) {
            Text("Don't have account? Sign Up", color = Color(0xFF6B2D2D))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ReshmeNammaPrideTheme {
        LoginScreen(onLoginSuccess = {}, onSignupClick = {})
    }
}
