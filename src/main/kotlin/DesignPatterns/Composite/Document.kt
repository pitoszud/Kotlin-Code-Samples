package DesignPatterns.Composite

import java.lang.UnsupportedOperationException

abstract class Document(val _name: String, val _size: Long = 0L) {

    abstract fun add(document: Document)
    abstract fun remove(document: Document)
    abstract fun getAll(): List<Document>
    abstract fun ls()
}

// Leaf node
class Sheet(private val sheetName: String, val fileSize: Long):
    Document(sheetName, fileSize){

    // View component has an empty implementation, since it is the smallest unit possible.
    override fun add(document: Document) {
        throw UnsupportedOperationException("Leaf node doesn't support additions")
    }

    override fun remove(document: Document) {
        throw UnsupportedOperationException("Leaf node doesn't support removal")
    }

    override fun getAll(): List<Document> {
        throw UnsupportedOperationException("Leaf node doesn't support getting all")
    }


    override fun ls() {
        println("$sheetName, $fileSize")
    }
}

// Composite
class Doc(private val documentName: String):
    Document(documentName){

    var documentList = mutableListOf<Document>()

    override fun add(document: Document) {
        documentList.add(document)
    }

    override fun remove(document: Document) {
        documentList.remove(document)
    }

    override fun getAll(): List<Document> = documentList

    override fun ls() {
        documentList.forEach {
            it.ls()
        }
    }

}

fun main() {
    val googleSheet = Sheet("Google Sheet", 23)
    val microsoftSheet = Sheet("Microsoft Excel", 31)

    val document = Doc("Documents")
    document.run {
        add(googleSheet)
        add(microsoftSheet)
        ls()
    }

}