package com.example.week3_lab.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SentimentSatisfied
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
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgeCalculatorView() {
    var name by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var showInfo by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        content = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize()
            ) {
                Icon(
                    imageVector = Icons.Outlined.SentimentSatisfied,
                    contentDescription = "Smile:)",
                    tint = Color.Magenta,
                    modifier = Modifier
                        .size(100.dp)
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(
                            text = "Enter your name",
                        )
                    },
                    shape = RoundedCornerShape(40.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Magenta,
                        unfocusedBorderColor = Color.Magenta,
                    )
                )
                OutlinedTextField(
                    value = year,
                    onValueChange = {
                        if (it.isEmpty() || it.isDigitsOnly()) {
                            year = it
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    label = {
                        Text(
                            text = "Enter your birth year",
                        )
                    },
                    shape = RoundedCornerShape(40.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Magenta,
                        unfocusedBorderColor = Color.Magenta,
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Button(
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "Hi, $name! Your age is ${2023 - year.toInt()} years."
                            )
                        }
                        showInfo = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Magenta
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Text(
                        text = "CALCULATE YOUR AGE",
                        fontSize = 18.sp
                    )
                }
                if (showInfo) {
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        border = BorderStroke(2.dp, Color.Magenta),
                        shape = RoundedCornerShape(40.dp)
                    ) {
                        Text(
                            text = "Hi, $name! Your age is ${2023 - year.toInt()} years.",
                            modifier = Modifier
                                .padding(32.dp, 16.dp),
                            color = Color.Magenta,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AgeCalculatorPreview() {
    AgeCalculatorView()
}