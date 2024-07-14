package com.adhibuchori.country.ui.screen.detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.adhibuchori.country.domain.CountryModel
import com.adhibuchori.country.ui.theme.CountryTheme
import com.adhibuchori.country.ui.theme.Typography

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    countryData: CountryModel,
    onBackClickListener: () -> Unit = {},
) {
    Column {
        Button(
            onClick = onBackClickListener,
            modifier = modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(text = "Back")
            }
        }
        AsyncImage(
            model = countryData.flagImageUrl, contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = countryData.name, style = Typography.headlineLarge.copy(
                fontWeight = FontWeight.Black
            ),
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Column {
                    Text(
                        text = "Native Name",
                        style = Typography.titleSmall.copy(
                            fontWeight = FontWeight.Black
                        ),
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 4.dp)
                    )
                    Text(
                        text = "Population",
                        style = Typography.titleSmall.copy(
                            fontWeight = FontWeight.Black
                        ),
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 4.dp)
                    )
                    Text(
                        text = "Region",
                        style = Typography.titleSmall.copy(
                            fontWeight = FontWeight.Black
                        ),
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 4.dp)
                    )
                    Text(
                        text = "Capital",
                        style = Typography.titleSmall.copy(
                            fontWeight = FontWeight.Black
                        ),
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 4.dp)
                    )
                    Text(
                        text = "Top Level Domain",
                        style = Typography.titleSmall.copy(
                            fontWeight = FontWeight.Black
                        ),
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 4.dp)
                    )
                }
                Column {
                    Text(
                        text = countryData.nativeName,
                        style = Typography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 4.dp)
                    )
                    Text(
                        text = countryData.population.toString(),
                        style = Typography.titleSmall,
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 4.dp)
                    )
                    Text(
                        text = countryData.region,
                        style = Typography.titleSmall,
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 4.dp)
                    )
                    Text(
                        text = countryData.capital,
                        style = Typography.titleSmall,
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 4.dp)
                    )
                    Text(
                        text = countryData.tld,
                        style = Typography.titleSmall,
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 4.dp)
                    )
                }
            }
        }
        if (countryData.borderCountries.isNotEmpty()) {
            Text(
                text = "Border Countries", style = Typography.titleMedium.copy(
                    fontWeight = FontWeight.Black
                ),
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = 20.dp)
            )
            LazyRow(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(countryData.borderCountries) {
                    Column(
                        modifier = Modifier.border(2.dp, Color.LightGray, RoundedCornerShape(5.dp))
                    ) {
                        Text(
                            text = it,
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun ShowDetailScreenPreview() {
    CountryTheme {
        DetailScreen(countryData = CountryModel.dummy)
    }
}