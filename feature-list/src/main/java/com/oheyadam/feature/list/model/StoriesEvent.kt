package com.oheyadam.feature.list.model

sealed class StoriesEvent {
  data class UpdateViewType(val viewTypeId: Int) : StoriesEvent()
  data class Load(val viewTypeId: Int) : StoriesEvent()
  data class OpenItem(val storyItem: StoryItem) : StoriesEvent()
  object LoadMore : StoriesEvent()
  object OpenSettings : StoriesEvent()
}
