package com.toadfrogson.clownhub.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onThemeSwitched: () -> Unit) {
    var themeSetToDark by remember {
        mutableStateOf(false)
    }
    TopAppBar(
        modifier = Modifier.height(56.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {

        },
        actions = {
            Switch(
                modifier = Modifier.padding(end = 8.dp),
                checked = themeSetToDark,
                onCheckedChange = {
                    onThemeSwitched()
                    themeSetToDark = !themeSetToDark
                })
        }

    )
}