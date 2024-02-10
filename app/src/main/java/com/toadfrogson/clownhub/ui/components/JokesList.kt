package com.toadfrogson.clownhub.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.toadfrogson.clownhub.data.model.JokeModel

@Composable
fun JokesList(modifier: Modifier = Modifier, content: List<JokeModel>) {
    LazyColumn(modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 30.dp)
    ) {
        items(content) {
            JokeCard(data = it)
        }
    }
}