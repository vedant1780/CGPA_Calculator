package com.example.cgpa_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cgpa_calculator.ui.theme.CGPA_CalculatorTheme

data class Semester(val grade: String,val credit: Int)
class MainActivity : ComponentActivity() {
    private var semesters:MutableList<Semester> = mutableListOf()
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CGPA_CalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    CGPA(semesters )
                }
            }
        }
    }
}

@Composable
fun CGPA(semesters:MutableList<Semester>) {
    var grade1 by remember { mutableStateOf("") }
    var credit1 by remember { mutableStateOf<Int?>(null) }
    var grade2 by remember { mutableStateOf("") }
    var credit2 by remember { mutableStateOf<Int?>(null) }
    var grade3 by remember { mutableStateOf("") }
    var credit3 by remember { mutableStateOf<Int?>(null) }
    var grade4 by remember { mutableStateOf("") }
    var credit4 by remember { mutableStateOf<Int?>(null) }
    var cgpa by remember { mutableStateOf(0.0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)

    ) {
        Text(
            text = "CGPA Calculator\n Appka ank,Aapka Bhavishya",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                color = Color(0xFF000000)
            )
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        SubjectText("Subject 1")
        GradeTextField(grade1){grade1=it}
        Spacer8dp()
        CreditTextField(credit1){credit1=it}
        SubjectText("Subject 2")
        GradeTextField(grade2){grade2=it}
        Spacer8dp()
        CreditTextField(credit2) {credit2=it}
        SubjectText("Subject 3")
        GradeTextField(grade3){grade3=it}
        Spacer8dp()
        CreditTextField(credit3) {credit3=it}
        SubjectText("Subject 4")
        GradeTextField(grade4){grade4=it}
        Spacer8dp()
        CreditTextField(credit4) {credit4=it}
        Spacer(Modifier.height(15.dp))
        Row (){
            Column(modifier = Modifier.wrapContentHeight().weight(1f).padding(5.dp), verticalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = {val semester=Semester(grade1,credit1?:0)
                    semesters.add(semester)
                    val totalCredit=semesters.sumOf { it.credit }
                    val totalGrade=semesters.sumOf { calculateGradePoint(it.grade,it.credit) }
                    if(totalCredit>0)
                    {
                        cgpa= (totalGrade/totalCredit)
                    }
                    else
                    {
                        cgpa=0.0
                    }
                    grade1=""
                    credit1=null
                    grade2=""
                    credit2=null
                    grade3=""
                    credit3=null
                    grade4=""
                    credit4=null

                                 }, colors = ButtonDefaults.buttonColors(Color(0xffbeabe0)), shape = RoundedCornerShape(15.dp)) {
                    Text("Calculate CGPA", fontSize = 16.sp, color = Color.Black)
                }
                Surface(modifier = Modifier.width(160.dp).wrapContentHeight(), color = Color(0xff263238), shape = RoundedCornerShape(15.dp)) {

                    Text("Your All Time\n CGPA:${cgpa}", fontSize = 16.sp, color = Color(0xffffffff), modifier = Modifier.padding(start=10.dp))


                }
            }
            Surface(modifier = Modifier.width(160.dp).wrapContentHeight().padding(5.dp), color = Color(0xff263238), shape = RoundedCornerShape(15.dp)) {
                Column {
                    Text("Previous Semester CGPA:", fontSize = 16.sp, color = Color(0xffffffff), modifier = Modifier.padding(start=10.dp))
                    if (semesters.isNotEmpty())
                    {
                        for(semester in semesters)
                        {
                            Text("Grade:${semester.grade} ,Credit:${semester.credit}", fontSize = 16.sp, color = Color(0xffffffff), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                        }
                    }

                }
            }
        }


    }
}

@Composable
fun Spacer8dp() {
    Spacer(modifier = Modifier.padding(top = 15.dp))
}

@Composable
fun GradeTextField(grade: String, onValueChange: (String) -> Unit) {
    TextField(
        value = grade,
        onValueChange = { text -> onValueChange(text) },
        modifier = Modifier
            .fillMaxWidth()
            .height(47.dp),
        label = { Text("Enter Grade", color = Color.White, fontSize = 12.sp) },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            unfocusedContainerColor = Color(0xFF689A9A),
            focusedContainerColor = Color(0xFF689A9A)
        ),
        shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(fontSize = 12.sp, color = Color.White)
    )
}

@Composable
fun CreditTextField(credit: Int?, onValueChange: (Int?) -> Unit) {
    TextField(
        value = credit?.toString()?:"",
        onValueChange = { text -> onValueChange(text.toIntOrNull()) },
        modifier = Modifier
            .fillMaxWidth()
            .height(47.dp),
        label = { Text("Enter Credit", color = Color.Black, fontSize = 12.sp) },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            unfocusedContainerColor = Color(0xff7d8cced),
            focusedContainerColor = Color(0xff7d8cced)
        ),
        shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(fontSize = 12.sp, color = Color.White)
    )
}

@Composable
fun SubjectText(Subject: String) {
    Text(
        text = Subject, modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), style = TextStyle(
            fontSize = 16.sp, fontFamily = FontFamily.SansSerif, color = Color(0xFF000000)
        )
    )

}
fun  calculateGradePoint(grade:String,credit:Int):Double
{
    return when(grade.uppercase())
    {
        "A"->4.0
        "B"->3.0
        "C"->2.0
        "D"->1.0
        else->0.0
    }*credit
}




