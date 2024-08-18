package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import org.w3c.dom.Text
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember {mutableStateOf("Meters") }
    var outputUnit by remember {mutableStateOf("Meters") }
    var iExpanded by remember {mutableStateOf(false) }
    var oExpanded by remember {mutableStateOf(false) }
    val iConversionFactor = remember { mutableStateOf(1.0) }
    val oConversionFactor = remember { mutableStateOf(1.0) }

    fun converter(){
        val inputDouble = inputValue.toDoubleOrNull() ?: 0.0
        val Result = (inputDouble*iConversionFactor.value*100.0/oConversionFactor.value).roundToInt()/100.0
        outputValue = Result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //All Styling
        @Composable
        fun HeadingText() {
            Text(
                text = "Unit Converter",
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFF2C6689),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
        HeadingText()
        @Composable
        fun SubheadingText() {
            Text(
                text = "Convert units effortlessly",
                fontSize = 18.sp,
                color = Color(0xFF2C6689),
                modifier = Modifier.padding(top = 8.dp),
            )
        }
        SubheadingText()
        @Composable
        fun InputLabelText() {
            Text(
                text = "Enter value:",
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(16.dp)
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            converter()


        }, label = {Text("Enter number")})
        Spacer(modifier = Modifier.padding(10.dp))
        LocalContext.current

        Row {
            //Input box
            Box {
                //input Button
                Button(onClick = {iExpanded=true}) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Milind")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = {Text("Meter")}, onClick = {
                        iExpanded = false
                        inputUnit = "Meter"
                        iConversionFactor.value = 1.0
                        converter()
                    })
                    DropdownMenuItem(text = {Text("Centimeters")}, onClick = {
                        iExpanded = false
                        inputUnit = "Centimeter"
                        iConversionFactor.value = 0.01
                        converter()
                    })
                    DropdownMenuItem(text = {Text("Millimeters")}, onClick = {
                        iExpanded = false
                        inputUnit = "Millimeter"
                        iConversionFactor.value = 0.001
                        converter()
                    })
                    DropdownMenuItem(text = {Text("Feet")}, onClick = {
                        iExpanded = false
                        inputUnit = "Feet"
                        iConversionFactor.value = 0.3048
                        converter()
                    })
                    DropdownMenuItem(text = {Text("Inches")}, onClick = {
                        iExpanded = false
                        inputUnit = "Inches"
                        iConversionFactor.value = 0.0254
                        converter()
                    })


                }
            }
            Spacer(modifier = Modifier.padding(5.dp))
            //Output Box
            Box {
                //Output Button
                Button(onClick = {oExpanded=true}) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Ranjan")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(text = {Text("Meter")}, onClick = {
                        oExpanded = false
                        outputUnit = "Meter"
                        oConversionFactor.value = 1.0
                        converter()
                    })
                    DropdownMenuItem(text = {Text("Centimeters")}, onClick = {
                        oExpanded = false
                        outputUnit = "Centimeter"
                        oConversionFactor.value = 0.01
                        converter()
                    })
                    DropdownMenuItem(text = {Text("Millimeters")}, onClick = {
                        oExpanded = false
                        outputUnit = "Millimeter"
                        oConversionFactor.value = 0.001
                        converter() })
                    DropdownMenuItem(text = {Text("Feet")}, onClick = {
                        oExpanded = false
                        outputUnit = "Feet"
                        oConversionFactor.value = 0.3048
                        converter()
                    })
                    DropdownMenuItem(text = {Text("Inches")}, onClick = {
                        oExpanded = false
                        outputUnit = "Inches"
                        oConversionFactor.value = 0.0254
                        converter()
                    })

                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Result : ${outputValue} ${outputUnit}",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF2C6689),
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}

