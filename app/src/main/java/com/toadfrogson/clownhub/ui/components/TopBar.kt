package com.toadfrogson.clownhub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.toadfrogson.clownhub.app.model.JokesCategoriesList.jokesList
import com.toadfrogson.clownhub.app.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(viewModel: MainViewModel = koinViewModel(), onThemeSwitched: () -> Unit) {
    TopAppBar(
        modifier = Modifier.height(56.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {

        },
        actions = {
            var themeSetToDark by remember {
                mutableStateOf(false)
            }

            Switch(
                modifier = Modifier.padding(end = 8.dp),
                checked = themeSetToDark,
                onCheckedChange = {
                    onThemeSwitched()
                    themeSetToDark = !themeSetToDark
                })

            var showDropDownMenu by remember {
                mutableStateOf(false)
            }

            IconButton(
                onClick = { showDropDownMenu = !showDropDownMenu }) {
                Icon(Icons.Outlined.List, "filter results")
            }
            val state by viewModel.uiState.collectAsState()
            val selectedJokeType = state.selectedFilter
            DropdownMenu(
                modifier = Modifier.background(MaterialTheme.colorScheme.background),
                expanded = showDropDownMenu,
                onDismissRequest = { showDropDownMenu = !showDropDownMenu }) {
                jokesList.forEach {
                    DropdownMenuItem(
                        text = { Text(text = stringResource(it.translatedValue)) },
                        modifier = Modifier.background(
                            if (selectedJokeType == it) MaterialTheme.colorScheme.tertiary
                            else MaterialTheme.colorScheme.background
                        ),
                        onClick = {
                            viewModel.filterContent(it)
                        })
                }
            }
        }

    )
}