package com.oheyadam.home.core.design.component

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oheyadam.home.core.design.theme.HomeTheme
import kotlin.random.Random

data class ChipMetadata(
  val id: Int,
  val label: String,
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
    label = { Text(text = metadata.label) },
    modifier = modifier
  )
}

@Composable fun FilterChipGroup(
  modifier: Modifier = Modifier,
  chipsMetadata: List<ChipMetadata>,
  selectedChipId: Int,
  onChipSelected: (ChipMetadata) -> Unit,
) {
  Row(
    modifier = modifier
      .padding(16.dp)
      .horizontalScroll(rememberScrollState()),
    horizontalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    chipsMetadata.forEach { chipMetadata ->
      FilterChip(
        metadata = chipMetadata,
        selected = chipMetadata.id == selectedChipId,
        onClick = { onChipSelected(chipMetadata) }
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
      metadata = ChipMetadata(id = 1, label = boolean.toString()),
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
    ChipMetadata(id = 1, label = "Top"),
    ChipMetadata(id = 2, label = "New"),
    ChipMetadata(id = 3, label = "Best"),
    ChipMetadata(id = 4, label = "Ask"),
    ChipMetadata(id = 5, label = "Show"),
    ChipMetadata(id = 6, label = "Bookmarks"),
  )
  HomeTheme {
    FilterChipGroup(
      chipsMetadata = chips,
      selectedChipId = selectedChipId
    ) { selectedChip ->
      selectedChipId = selectedChip.id
    }
  }
}
