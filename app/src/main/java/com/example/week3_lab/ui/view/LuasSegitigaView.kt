package com.example.week3_lab.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week3_lab.ui.theme.Week3_LabTheme


@Composable
fun CalculateView() {
    var base by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }

    val area: Double =
        if (base.isNotBlank() && height.isNotBlank()){
            (base.toDouble() * height.toDouble()) / 2
        } else {
            0.0
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = Icons.Outlined.PlayArrow,
            tint = Color.Blue,
            contentDescription = "Icon",
            modifier = Modifier
                .rotate(-90f)
                .size(200.dp)
        )
//        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = base,
            onValueChange = { base = it},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(40.dp),
            label = { Text(text = "Base")},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Blue,
            )
        )

//        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = height,
            onValueChange = { height = it},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(40.dp),
            label = { Text(text = "Height")},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Blue,
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "The Triangle Area is",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        Text(
            text = area.toString(),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 28.sp
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    Week3_LabTheme {
        CalculateView()
    }
}