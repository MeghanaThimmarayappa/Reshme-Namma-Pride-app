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

class SignupActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupScreen(
                onSignupSuccess = {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                },
                onLoginClick = { finish() }
            )
        }
    }
}

@Composable
fun SignupScreen(onSignupSuccess: () -> Unit, onLoginClick: () -> Unit) {

    val RoseGoldDark = Color(0xFF4A1530)
    val RoseGoldMid = Color(0xFFB76E79)
    val GoldColor = Color(0xFFFFD700)

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "🌿 Create Account",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = RoseGoldDark
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name | ಹೆಸರು") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone | ಫೋನ್") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password | ಪಾಸ್‌ವರ್ಡ್") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            if (errorMsg.isNotEmpty()) {
                Text(text = errorMsg, color = Color.Red, fontSize = 13.sp)
            }

            Button(
                onClick = {
                    if (name.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                        errorMsg = "Please fill all fields"
                    } else {
                        onSignupSuccess()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RoseGoldDark)
            ) {
                Text("Sign Up | ನೋಂದಣಿ", color = GoldColor, fontWeight = FontWeight.Bold)
            }

            TextButton(onClick = onLoginClick) {
                Text("Already have account? Login", color = RoseGoldMid)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    ReshmeNammaPrideTheme {
        SignupScreen(
            onSignupSuccess = {},
            onLoginClick = {}
        )
    }
}