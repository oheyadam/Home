package com.oheyadam.feature.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Comment
import androidx.compose.material.icons.outlined.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.oheyadam.core.design.R
import com.oheyadam.core.model.ReadableTime.JustNow
import com.oheyadam.feature.list.model.StoriesState
import com.oheyadam.feature.list.model.StoryItem
import com.oheyadam.feature.list.model.toChipsMetadata
import com.oheyadam.home.core.design.component.FilterChipGroup
import com.oheyadam.home.core.design.component.TopAppBar
import com.oheyadam.home.core.design.theme.HomeTheme
import kotlin.random.Random

@Composable fun StoriesScreen(
  modifier: Modifier = Modifier,
  viewModel: StoriesViewModel = viewModel(),
) {
  val state = viewModel.state.collectAsState()
  Content(
    modifier = modifier,
    state = state.value,
    onSettingsActionClick = {},
    onChipClick = { id -> viewModel.onViewTypeUpdated(id) },
    onStoryItemClick = {}
  )
}

@Composable private fun Content(
  modifier: Modifier = Modifier,
  state: StoriesState,
  onSettingsActionClick: () -> Unit,
  onChipClick: (Int) -> Unit,
  onStoryItemClick: (StoryItem) -> Unit,
) {
  val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
  Scaffold(
    modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = {
      TopAppBar(
        titleResId = R.string.title_home,
        onSettingsActionClick = { onSettingsActionClick() },
        scrollBehavior = scrollBehavior
      )
    }
  ) { innerPadding ->
    LazyColumn(contentPadding = innerPadding) {
      item(key = state.viewTypes) {
        FilterChipGroup(
          chipsMetadata = state.viewTypes.toChipsMetadata(),
          selectedChipId = state.selectedViewType.id,
          onChipSelected = { id -> onChipClick(id) }
        )
      }
      state.storyItems.forEach { storyItem ->
        item(key = storyItem.id) {
          StoryItem(model = storyItem) { onStoryItemClick(storyItem) }
        }
      }
    }
  }
}

@Composable private fun StoryItem(
  modifier: Modifier = Modifier,
  model: StoryItem,
  onClick: (StoryItem) -> Unit,
) {
  Column(
    modifier = modifier
      .clickable(onClick = { onClick(model) })
      .padding(all = dimensionResource(R.dimen.spacing_default)),
    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_small))
  ) {
    Row(
      horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_small))
    ) {
      Text(
        text = stringResource(R.string.list_item_points, model.score),
        style = MaterialTheme.typography.labelSmall,
      )
      Text(
        text = stringResource(R.string.list_item_author, model.author),
        style = MaterialTheme.typography.labelSmall,
      )
      Text(text = model.shortenedUrl(), style = MaterialTheme.typography.labelSmall)
    }
    Row(
      horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_medium)),
      verticalAlignment = Alignment.Top
    ) {
      Text(
        text = model.title,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.weight(1f)
      )
      Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_small)),
        horizontalAlignment = CenterHorizontally
      ) {
        Icon(
          imageVector = if (model.isTrending()) Icons.Outlined.TrendingUp else Icons.Outlined.Comment,
          contentDescription = stringResource(R.string.content_description_comments)
        )
        Text(text = model.score.toString(), style = MaterialTheme.typography.labelSmall)
      }
    }
  }
}

@Preview
@Composable
fun StoriesScreenPreview() {
  val items = mutableListOf<StoryItem>()
  repeat(10) {
    items.add(
      StoryItem(
        id = 1,
        author = "oheyadam",
        time = JustNow,
        kids = emptyList(),
        descendants = Random.nextInt(1, 100),
        score = Random.nextInt(1, 100),
        title = "Show HN: A Hacker News client for Android. What happens if this is really long?",
        url = "https://www.google.com"
      )
    )
  }
  HomeTheme {
    Surface {
      Content(
        state = StoriesState(storyItems = items),
        onSettingsActionClick = { },
        onChipClick = {},
        onStoryItemClick = {}
      )
    }
  }
}
