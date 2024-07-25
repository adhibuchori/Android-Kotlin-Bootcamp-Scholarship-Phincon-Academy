package com.adhibuchori.narumi.data.trip

import com.adhibuchori.narumi.R
import com.adhibuchori.narumi.data.trip.model.TripResponse

object DummyDataProvider {
    fun getTripResponses(): List<TripResponse> {
        val tripImages = listOf(
            R.drawable.iv_trip_image_01,
            R.drawable.iv_trip_image_02,
            R.drawable.iv_trip_image_03,
            R.drawable.iv_trip_image_04,
            R.drawable.iv_trip_image_05,
            R.drawable.iv_trip_image_06,
            R.drawable.iv_trip_image_07,
            R.drawable.iv_trip_image_08,
            R.drawable.iv_trip_image_09
        )

        val tripNames = listOf(
            "Kotlin Library",
            "Android Cafe",
            "Compose Sea",
            "Kotlin Conference",
            "Android Retreat",
            "Compose Workshop",
            "Kotlin Summit",
            "Android Expo",
            "Compose Meetup"
        )

        val tripLocations = listOf(
            "Kotlin City",
            "Android Town",
            "Compose Bay",
            "Kotlin Village",
            "Android Valley",
            "Compose Island",
            "Kotlin Heights",
            "Android Ridge",
            "Compose Haven"
        )

        val tripDates = listOf(
            "20 Juni 2025",
            "15 Juli 2025",
            "10 Agustus 2025",
            "5 September 2025",
            "1 Oktober 2025",
            "25 November 2025",
            "20 Desember 2025",
            "15 Januari 2026",
            "10 Februari 2026"
        )

        val tripOrderDates = listOf(
            "2025-06-01",
            "2025-06-20",
            "2025-06-30",
            "2025-07-10",
            "2025-07-20",
            "2025-07-30",
            "2025-08-10",
            "2025-08-20",
            "2025-08-30"
        )

        val tripDurations = listOf(
            "7 Days",
            "5 Days",
            "10 Days",
            "3 Days",
            "8 Days",
            "6 Days",
            "4 Days",
            "9 Days",
            "11 Days"
        )

        val tripCosts = listOf(
            1000,
            750,
            1200,
            500,
            1100,
            900,
            700,
            1300,
            1400
        )

        val tripDescriptions = listOf(
            "Explore the Kotlin Library: Unlock a world of powerful tools and resources to enhance your development skills. Discover the Kotlin Library: A comprehensive collection of functions and utilities to streamline your coding process.",
            "Relax at the Android Cafe: Enjoy a unique blend of coffee and coding in a vibrant atmosphere. Discover the Android Cafe: A haven for developers to unwind and collaborate.",
            "Dive into the Compose Sea: Experience the depth of Jetpack Compose with hands-on tutorials and projects. Discover the Compose Sea: An ocean of possibilities for building beautiful UIs.",
            "Join the Kotlin Conference: Engage with experts and enthusiasts to expand your knowledge and skills. Discover the latest trends and innovations in the Kotlin world.",
            "Retreat to the Android Valley: Recharge with a mix of coding and nature in a serene environment. Discover the Android Valley: A perfect blend of productivity and relaxation.",
            "Attend the Compose Workshop: Participate in interactive sessions to enhance your UI development capabilities. Discover the Compose Workshop: A platform for learning and growth.",
            "Summit at the Kotlin Heights: Reach new heights in your coding journey with advanced Kotlin sessions. Discover the Kotlin Heights: A summit for ambitious developers.",
            "Expo at the Android Ridge: Showcase and discover the latest Android innovations and technologies. Discover the Android Ridge: An expo for cutting-edge advancements.",
            "Meetup at the Compose Haven: Connect with fellow developers and share your Compose experiences. Discover the Compose Haven: A place for community and collaboration."
        )

        val tripCategories = listOf(
            "Popular",
            "Recommended",
            "Popular",
            "Recommended",
            "Popular",
            "Recommended",
            "Popular",
            "Recommended",
            "Popular"
        )

        return tripImages.indices.map { index ->
            TripResponse(
                tripImage = tripImages[index],
                tripName = tripNames[index],
                tripLocation = tripLocations[index],
                tripDate = tripDates[index],
                tripOrderDate = tripOrderDates[index],
                tripDuration = tripDurations[index],
                tripCost = tripCosts[index],
                tripDescription = tripDescriptions[index],
                tripCategory = tripCategories[index]
            )
        }
    }
}