package com.toadfrogson.clownhub.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.toadfrogson.clownhub.data.model.JokeModel

@Composable
fun JokesList(modifier: Modifier = Modifier, content: List<JokeModel>) {

    LazyColumn(modifier = modifier) {
        items(content) {
            JokeCard1(data = it) {

            }
        }
    }
}