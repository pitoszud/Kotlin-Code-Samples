package File

import java.io.BufferedReader
import java.io.File
import java.io.FileReader


import java.util.Scanner


class FileReaderJ {
    fun readAndUpdatePriceFile() {
        val inFile = File("G:\\Kotlin\\KotlinStudy2\\src\\File\\Data\\171204151739Templates.csv")
        var fs: Scanner? = null
        try {
            var ls: Scanner? = null
            var currentLine: String
            fs = Scanner(BufferedReader(FileReader(inFile)))
            while (fs.hasNextLine()) {
                currentLine = fs.nextLine()
                ls = Scanner(currentLine)
                ls.useDelimiter(",")
                while (ls.hasNext()) {
                    print(ls.next())
                    print(System.lineSeparator())
                }
            }
        } catch (e: Exception) {
            System.err.println(e)
        }

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val fl = FileReaderJ()
            fl.readAndUpdatePriceFile()
        }
    }
}


