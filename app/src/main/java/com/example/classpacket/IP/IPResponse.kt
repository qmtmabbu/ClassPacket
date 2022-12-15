package com.example.classpacket.IP

import com.google.gson.annotations.SerializedName

class IPResponse {
    @SerializedName("IP")
    var ip: IPData? = null
    @SerializedName("postal")
    var postal: Place? = null
    @SerializedName("continent")
    var continent_code: Place? = null
    @SerializedName("country")
    var country: Place? = null
    @SerializedName("region")
    var region: Place? = null
    @SerializedName("city")
    var city: Place? = null
    @SerializedName("longitude")
    var longitude: Coords? = null
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
}

class Sys{
    @SerializedName("continent")
    var Sys: String? = null
}


class Place {
    @SerializedName("continent")
    var continent_code: String? = null
    @SerializedName("country")
    var country: String? = null
    @SerializedName("region")
    var region: String? = null
    @SerializedName("city")
    var city: String? = null
    @SerializedName("postal")
    var postal: String? = null
}

class Coords{
    @SerializedName("longitude")
    var longitude: Coords? = null
    @SerializedName("latitude")
    var latitude: Coords? = null
}
