package com.example.movieapp.ui.screens.home_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.data.models.NetworkResponse
import com.example.movieapp.data.models.PostDataItem
import com.example.movieapp.data.repogistory.MovieRepogistory
import com.example.movieapp.utils.showToast


val homeViewModel = HomeViewModel(MovieRepogistory())

@Composable
fun HomeScreen() {


    val context = LocalContext.current

    Column(Modifier.fillMaxSize()) {
        val response = homeViewModel.movieResponse.collectAsState()
        when (response.value) {
            is NetworkResponse.Loading -> {
                ShowLoader()
            }
            is NetworkResponse.Error -> {
                context.showToast(response.value.toString())
            }
            is NetworkResponse.Success -> {
                response.value.data?.let { ShowListOfMovie(it) }
            }
        }
    }
}

@Composable
fun ShowListOfMovie(postList : List<PostDataItem>) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Today's posts",
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(16.dp))
    PostListView(postList)
}

@Composable
fun ShowLoader() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            color = colorResource(id = R.color.purple_200),
            strokeWidth = 1.dp
        )
    }
}

@Composable
fun PostListView(postList: List<PostDataItem>) {
    LazyColumn {
        items(postList.size) { index ->
            val postData = postList.get(index)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                    .border(width = 1.dp, color = Color.LightGray)
            ) {
                Column(Modifier.padding(all = 8.dp)) {
                    Text(
                        text = "Title: ${postData.title}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Text(text = "Description: ${postData.body}", fontSize = 13.sp)
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewHomePage(){
    HomeScreen( )
}


