package Algorithms.Area

import java.util.ArrayList



class FromCoordinatesB{
    private val EARTH_RADIUS = 6371000.0// meters

    fun calculateAreaOfGPSPolygonOnEarthInSquareMeters(locations: List<Location>): Double {
        return calculateAreaOfGPSPolygonOnSphereInSquareMeters(locations, EARTH_RADIUS)
    }

    private fun calculateAreaOfGPSPolygonOnSphereInSquareMeters(locations: List<Location>, radius: Double): Double {
        if (locations.size < 3) {
            return 0.0
        }

        val diameter = radius * 2
        val circumference = diameter * Math.PI
        val listY = ArrayList<Double>()
        val listX = ArrayList<Double>()
        val listArea = ArrayList<Double>()
        // calculate segment x and y in degrees for each point
        val latitudeRef = locations[0].latitude
        val longitudeRef = locations[0].longitude
        for (i in 1 until locations.size) {
            val latitude = locations[i].latitude
            val longitude = locations[i].longitude

            listY.add(calculateYSegment(latitudeRef!!, latitude!!, circumference))
            //Log.d(TAG, String.format("Y %s: %s", listY.size - 1, listY[listY.size - 1]))
            println(String.format("Y %s: %s", listY.size - 1, listY[listY.size - 1]))

            listX.add(calculateXSegment(longitudeRef!!, longitude!!, latitude, circumference))
            //Log.d(TAG, String.format("X %s: %s", listX.size - 1, listX[listX.size - 1]))
            println(String.format("X %s: %s", listX.size - 1, listX[listX.size - 1]))
        }

        // calculate areas for each triangle segment
        for (i in 1 until listX.size) {
            val x1 = listX[i - 1]
            val y1 = listY[i - 1]
            val x2 = listX[i]
            val y2 = listY[i]

            listArea.add(calculateAreaInSquareMeters(x1, x2, y1, y2))
            //Log.d(LOG_TAG, String.format("area %s: %s", listArea.size - 1, listArea[listArea.size - 1]))
            println(String.format("area %s: %s", listArea.size - 1, listArea[listArea.size - 1]))
        }

        // sum areas of all triangle segments
        var areasSum = 0.0
        for (area in listArea) {
            areasSum += area
        }

        // get abolute value of area, it can't be negative
        return Math.abs(areasSum)// Math.sqrt(areasSum * areasSum);
    }

    private fun calculateAreaInSquareMeters(x1: Double, x2: Double, y1: Double, y2: Double): Double {
        return (y1 * x2 - x1 * y2) / 2
    }

    private fun calculateYSegment(latitudeRef: Double, latitude: Double, circumference: Double): Double {
        return (latitude - latitudeRef) * circumference / 360.0
    }

    private fun calculateXSegment(
        longitudeRef: Double, longitude: Double, latitude: Double,
        circumference: Double
    ): Double {
        return (longitude - longitudeRef) * circumference * Math.cos(Math.toRadians(latitude)) / 360.0
    }
}