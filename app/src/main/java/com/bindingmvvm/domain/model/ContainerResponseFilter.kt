package com.bindingmvvm.domain.model
import org.json.JSONArray

interface ContainerResponseFilter {

    companion object {
         fun containerResponse(jsonArray: JSONArray): List<Container> {
            return  mutableListOf<Container>().apply {
                for (k in 0 until jsonArray.length()) {
                    add(
                        Container(
                            albumId = jsonArray.optJSONObject(k).optString("albumId"),
                            id = jsonArray.optJSONObject(k).optString("id"),
                            title = jsonArray.optJSONObject(k).optString("title"),
                            url = jsonArray.optJSONObject(k).optString("url"),
                            thumbnailUrl = jsonArray.optJSONObject(k).optString("thumbnailUrl")
                        )
                    )
                }
            }
        }

    }
}