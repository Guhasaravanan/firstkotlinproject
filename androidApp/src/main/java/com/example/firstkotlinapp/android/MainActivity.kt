package com.example.firstkotlinapp.android

import TodoRepository
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import model.TodoItem

class MainActivity : ComponentActivity() {
    private val repository = TodoRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodoApp()
                }
            }
        }
    }

   @Composable
   fun TodoApp(){
       var text by remember {
           mutableStateOf("")
       }
       var todos by remember {
           mutableStateOf(listOf<String>())
       }

       Column(modifier = Modifier
           .fillMaxSize()
           .padding(16.dp), verticalArrangement = Arrangement.Top,         horizontalAlignment = Alignment.CenterHorizontally
       ) {
TextField(value = text, onValueChange ={text = it}, label = {Text("Enter Todo")}, modifier = Modifier.fillMaxWidth() )
      Spacer(modifier = Modifier.height(8.dp))
           Button(onClick = {
               if(text.isNotBlank()){
                   todos = todos + text
                   text = ""
               }
           }) {
Text(text = "Add Todo")

           }
           Spacer(modifier = Modifier.height(16.dp))
LazyColumn {
    items(todos) { todo ->
        Text("- $todo", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
    }
}
           Card(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(8.dp),
               colors = CardDefaults.cardColors(
                   containerColor = MaterialTheme.colorScheme.errorContainer
               ),
               elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
           ) {
               Column(modifier = Modifier.padding(16.dp)) {
                   Text("Todo Title", style = MaterialTheme.typography.titleMedium)
                   Spacer(modifier = Modifier.height(8.dp))
                   Text("This is a sample description for the todo item.", style = MaterialTheme.typography.bodyMedium)
               }
           }

       }
   }
}
