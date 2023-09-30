package com.example.week3_lab.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.SentimentSatisfied
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RataRataSiswaView() {
    var siswa1 by remember { mutableStateOf("") }
    var siswa2 by remember { mutableStateOf("") }
    var siswa3 by remember { mutableStateOf("") }

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
                    imageVector = Icons.Outlined.School,
                    contentDescription = "Smile:)",
                    tint = Color.Magenta,
                    modifier = Modifier
                        .size(100.dp)
                )
                OutlinedTextField(
                    value = siswa1,
                    onValueChange = {
                        if (it.isEmpty() || it.isDigitsOnly()) {
                            siswa1 = it
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(
                            text = "Shawn's Score",
                        )
                    },
                    shape = RoundedCornerShape(40.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Magenta,
                        unfocusedBorderColor = Color.Magenta,
                    )
                )
                OutlinedTextField(
                    value = siswa2,
                    onValueChange = {
                        if (it.isEmpty() || it.isDigitsOnly()) {
                            siswa2 = it
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(
                            text = "Pete's Score",
                        )
                    },
                    shape = RoundedCornerShape(40.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Magenta,
                        unfocusedBorderColor = Color.Magenta,
                    )
                )
                OutlinedTextField(
                    value = siswa3,
                    onValueChange = {
                        if (it.isEmpty() || it.isDigitsOnly()) {
                            siswa3 = it
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(
                            text = "Ardhito's Score",
                        )
                    },
                    shape = RoundedCornerShape(40.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Magenta,
                        unfocusedBorderColor = Color.Magenta,
                    )
                )
                Button(
                    onClick = {
                        var res: Float =
                            AverageScore(siswa1.toFloat(), siswa2.toFloat(), siswa3.toFloat())
                        if (res < 70.toFloat()) {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    "Siswa perlu diberi soal tambahan"
                                )
                            }
                        } else if (res >= 70.toFloat()) {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    "Siswa mengerti soal pembelajaran"
                                )
                            }
                        }
                        showInfo = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Magenta
                    ),
                    modifier = Modifier
                        .padding(8.dp),
                    enabled = siswa1.isNotEmpty() && siswa2.isNotEmpty() && siswa3.isNotEmpty()
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
                            text = "Average Score: ${
                                AverageScore(
                                    siswa1.toFloat(),
                                    siswa2.toFloat(),
                                    siswa3.toFloat()
                                )
                            }",
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

fun AverageScore(siswa1: Float, siswa2: Float, siswa3: Float): Float {
    return (siswa1 + siswa2 + siswa3) / 3
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RataRataSiswaPreview() {
    RataRataSiswaView()
}