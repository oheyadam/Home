package com.oheyadam.home.core.design.component

import androidx.annotation.StringRes
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.oheyadam.core.design.R
import com.oheyadam.home.core.design.theme.HomeTheme
import kotlin.random.Random

data class ChipMetadata(
  val id: Int,
  @StringRes val labelResId: Int,
)

@Composable fun FilterChip(
  metadata: ChipMetadata,
  selected: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  FilterChip(
    selected = selected,
    onClick = onClick,
    label = { Text(text = stringResource(metadata.labelResId)) },
    modifier = modifier
  )
}

@Composable fun FilterChipGroup(
  modifier: Modifier = Modifier,
  chipsMetadata: List<ChipMetadata>,
  selectedChipId: Int,
  onChipSelected: (Int) -> Unit,
) {
  Row(
    modifier = modifier
      .padding(dimensionResource(R.dimen.spacing_default))
      .horizontalScroll(rememberScrollState()),
    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_default))
  ) {
    chipsMetadata.forEach { chipMetadata ->
      FilterChip(
        metadata = chipMetadata,
        selected = chipMetadata.id == selectedChipId,
        onClick = { onChipSelected(chipMetadata.id) }
      )
    }
  }
}

@Preview
@Composable
private fun FilterChipPreview() {
  HomeTheme {
    val boolean = Random.nextBoolean()
    FilterChip(
      metadata = ChipMetadata(id = 1, labelResId = R.string.chip_top_stories),
      selected = boolean,
      onClick = {},
    )
  }
}

@Preview
@Composable
private fun ChipGroupPreview() {
  var selectedChipId: Int by remember { mutableStateOf(1) }
  val chips = listOf(
    ChipMetadata(id = 1, labelResId = R.string.chip_top_stories),
    ChipMetadata(id = 2, labelResId = R.string.chip_new_stories),
    ChipMetadata(id = 3, labelResId = R.string.chip_best_stories),
    ChipMetadata(id = 4, labelResId = R.string.chip_top_stories),
    ChipMetadata(id = 5, labelResId = R.string.chip_new_stories),
    ChipMetadata(id = 6, labelResId = R.string.chip_top_stories),
  )
  HomeTheme {
    FilterChipGroup(
      chipsMetadata = chips,
      selectedChipId = selectedChipId
    ) { id ->
      selectedChipId = id
    }
  }
}
