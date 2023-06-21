package com.example.explorethecity.model

import com.squareup.moshi.Json
data class Event(
    val title: String,
    val date: Date,
    val address: List<String>?,
    val link: String?,
    val event_location_map: EventLocationMap?,
    val description: String?,
    val ticket_info: List<TicketInfo>?,
    val venue: Venue?,
    val thumbnail: String?,
    val image: String?
)

data class EventsResponse(
    val search_metadata: SearchMetadata,
    val search_parameters: SearchParameters,
    val search_information: SearchInformation,
    val events_results: List<Event>
)
data class SearchMetadata(
    val id: String,
    val status: String,
    val json_endpoint: String,
    val created_at: String,
    val processed_at: String,
    val google_events_url: String,
    val raw_html_file: String,
    val total_time_taken: Double
)

data class SearchParameters(
    val q: String,
    val engine: String
)

data class SearchInformation(
    val events_results_state: String
)

data class Date(
    val start_date: String,
    @Json(name="when")
    val whenDate: String
)

data class EventLocationMap(
    val image: String,
    val link: String,
    val serpapi_link: String
)

data class TicketInfo(
    val source: String,
    val link: String,
    val link_type: String
)

data class Venue(
    val name: String,
    val rating: Double,
    val reviews: Int,
    val link: String
)

data class Image (
    val ratio: String,
    val url: String
)

data class Place(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val location: String,
    val category: String
)
data class User(
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val city: String

)