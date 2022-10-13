package com.oheyadam.home.core.design.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.oheyadam.home.core.design.theme.HomeTheme

data class NavigationItemMetadata(
  val id: Int,
  val label: String,
  val icon: ImageVector,
)

@Composable fun RowScope.NavigationBarItem(
  metadata: NavigationItemMetadata,
  selected: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  NavigationBarItem(
    icon = { Icon(metadata.icon, contentDescription = metadata.label) },
    selected = selected,
    onClick = onClick,
    modifier = modifier
  )
}

@Composable fun NavigationBar(
  modifier: Modifier = Modifier,
  itemsMetadata: List<NavigationItemMetadata>,
  selectedItemId: Int,
  onItemSelected: (NavigationItemMetadata) -> Unit,
) {
  check(itemsMetadata.size <= 5) { "Don't be Twitter. No more than 5 navigation bar items" }
  NavigationBar(modifier = modifier) {
    itemsMetadata.forEach { itemMetadata ->
      NavigationBarItem(
        metadata = itemMetadata,
        selected = itemMetadata.id == selectedItemId,
        onClick = { onItemSelected(itemMetadata) })
    }
  }
}

@Preview
@Composable
private fun NavigationBarPreview() {
  var selectedItemId: Int by remember { mutableStateOf(1) }
  val items = listOf(
    NavigationItemMetadata(id = 1, label = "Home", Icons.Outlined.Home),
    NavigationItemMetadata(id = 2, label = "Search", Icons.Outlined.Search),
    NavigationItemMetadata(id = 3, label = "Bookmarks", Icons.Outlined.Bookmarks),
    NavigationItemMetadata(id = 4, label = "Account", Icons.Outlined.AccountCircle),
  )
  HomeTheme {
    NavigationBar(
      itemsMetadata = items,
      selectedItemId = selectedItemId
    ) { selectedItem ->
      selectedItemId = selectedItem.id
    }
  }
}
