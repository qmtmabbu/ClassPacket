package com.example.classpacket.IP

import com.google.gson.annotations.SerializedName

class IPResponse {
    @SerializedName("IP")
    var IP: IPData? = null
    @SerializedName("hostname")
    var hostname: Hostname? = null
    @SerializedName("continent")
    var continent: Place? = null
    @SerializedName("country")
    var country: Place? = null
    @SerializedName("region")
    var region: Place? = null
    @SerializedName("city")
    var city: Place? = null
    @SerializedName("longtitude")
    var longtitude: Coords? = null
    @SerializedName("latitude")
    var latitude: Coords? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("Sys")
    var sys: Sys? = null
}

class IPData {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("main")
    var main: String? = null
    @SerializedName("description")
    var description: String? = null
}

class Sys{
    @SerializedName("continent")
    var Sys: String? = null
}

class Hostname{
    @SerializedName("continent")
    var hostname: String? = null
}

class Place {
    @SerializedName("continent")
    var continent: String? = null
    @SerializedName("country")
    var country: String? = null
    @SerializedName("region")
    var region: String? = null
    @SerializedName("city")
    var city: String? = null
}

class Coords{
    @SerializedName("longtitude")
    var longtitude: Coords? = null
    @SerializedName("latitude")
    var latitude: Coords? = null
}
