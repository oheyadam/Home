package com.oheyadam.feature.detail.model

data class BreedDetail(
  val id: Int,
  val name: String,
  val thumbnailUrl: String,
  val temperament: String
) {
  companion object {
    fun initial(): BreedDetail {
      return BreedDetail(
        id = -1,
        name = "",
        thumbnailUrl = "",
        temperament = ""
      )
    }
  }
}
