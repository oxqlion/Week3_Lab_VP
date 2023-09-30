package com.example.week3_lab.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.week3_lab.R

@Composable
fun BMICalculator() {

    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }

    var dialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(36.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_face_24),
            contentDescription = "Face Icon",
            tint = Color.Blue,
            modifier = Modifier.size(100.dp)
        )

        OutlinedTextField(
            value = weight,
            onValueChange = {
                if (it.isEmpty() || it.isDigitsOnly()) {
                    weight = it
                }
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = "Weight in kg",
                    color = Color.Blue
                )
            },
            shape = RoundedCornerShape(40.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Blue,
                textColor = Color.Blue
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        if (weight.isEmpty()) {
            Text(
                text = "Please enter a valid weight greater than 0",
                color = Color.Red,
                fontSize = 16.sp,
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = height,
            onValueChange = {
                if (it.isEmpty() || it.isDigitsOnly()) {
                    height = it
                }
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = "Height in cm ",
                    color = Color.Blue
                )
            },
            shape = RoundedCornerShape(40.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Blue,
                textColor = Color.Blue
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        if (height.isEmpty()) {
            Text(
                text = "Please enter a valid weight greater than 0",
                color = Color.Red,
                fontSize = 16.sp,
                modifier = Modifier.padding(8.dp)
            )
        }

        Button(
            onClick = {dialog = true},
            enabled = weight.isNotEmpty() && height.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Cyan,
                contentColor = Color.Black
            )
        ) {
            Text(text = "Calculate BMI")
        }

        if(weight.isNotEmpty() && height.isNotEmpty() && dialog) {
            var result: Double = weight.toDouble() / ((height.toDouble() / 100) * (height.toDouble() / 100))
            var BMI: String

            if (result < 18.5) {
                BMI = "Underweight"
            } else if (result > 18.5 && result < 24.9) {
                BMI = "Normal"
            } else if (result > 25 && result < 29.9) {
                BMI = "Overweight"
            } else {
                BMI = "Obese"
            }

            AlertDialog(
                onDismissRequest = {dialog = false},
                title = {
                        Text(text = "Your BMI Analysis")
                },
                text = {
                        Text(text = """
                            Your Height : ${height.toDouble() / 100} m 
                            Your Weight : ${weight.toDouble()} kg
                            Your BMI Score : ${String.format("%.2f", result)}
                            You are $BMI
                        """.trimIndent())
                },
                confirmButton = {
                    Button(onClick = {dialog = false}) {
                        Text(text = "OK")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BMIPreview() {
    BMICalculator()
}