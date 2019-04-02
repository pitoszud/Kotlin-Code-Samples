package Algorithms.Area

class FromCoordinates{
    companion object {
        const val ER = 6371000 // Earth radius in meters

        fun CalculatePolygonArea(coordinates: List<Location>): Double {
            var area = 0.0

            if (coordinates.size > 2) {
                for (i in 0 until coordinates.size - 1) {
                    val p1: Location = coordinates[i]
                    val p2: Location = coordinates[i + 1]
                    area += Math.toRadians(p2.longitude!! - p1.longitude!!) * (2.0 + Math.sin(Math.toRadians(p1.latitude!!))
                            + Math.sin(Math.toRadians(p2.latitude!!)))

                }
                area = area * ER * ER / 2
            }

            return Math.abs(area)
        }
    }
}

class Location{
    var longitude: Double? = null
    var latitude: Double? = null
}