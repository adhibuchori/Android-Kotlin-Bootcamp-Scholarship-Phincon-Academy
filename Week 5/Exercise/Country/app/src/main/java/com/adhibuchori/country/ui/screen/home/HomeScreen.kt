package com.adhibuchori.country.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adhibuchori.country.domain.CountryModel
import com.adhibuchori.country.ui.components.CountryListItem
import com.adhibuchori.country.ui.components.SearchBar
import com.adhibuchori.country.ui.theme.CountryTheme
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.job

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    onClickListener: (CountryModel) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val coroutineJob = rememberCoroutineScope()

    val uiState by viewModel.uiState.collectAsState()

    var searchValue by remember { mutableStateOf("") }

    LaunchedEffect(key1 = searchValue) {
        val job = coroutineJob.coroutineContext.job
        job.cancelAndJoin()
        delay(1000)
        viewModel.filterCountries(searchValue)
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchCountries()
    }

    Box {
        rememberCoroutineScope()
        val listState = rememberLazyListState()

        LazyColumn(
            state = listState,
        ) {
            stickyHeader {
                Row(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SearchBar(
                        query = searchValue,
                        onQueryChange = { searchValue = it },
                        modifier = Modifier
                            .weight(1f)
                    )

                }
            }
            when (val state = uiState.state) {
                is HomeState.Error -> {}
                HomeState.Loading -> {}
                is HomeState.Success -> {
                    if (state.filteredData.isEmpty()) {
                        items(state.data) {
                            CountryListItem(
                                countryModel = it,
                                modifier = Modifier
                                    .clickable { onClickListener(it) }
                                    .padding(horizontal = 16.dp,  vertical = 8.dp),
                            )
                        }
                    } else {
                        items(state.filteredData) {
                            CountryListItem(
                                countryModel = it,
                                modifier = Modifier
                                    .clickable { onClickListener(it) }
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                            )
                        }
                    }
                }

                else -> {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeComponentPreview() {
    CountryTheme {
        HomeScreen(
            onClickListener = {}
        )
    }
}
