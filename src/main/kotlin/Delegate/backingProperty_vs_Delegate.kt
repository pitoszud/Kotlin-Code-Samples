package Delegate


// ------------------- Example using backing property ----------------------

class Document(val docId: Int){
    override fun toString(): String {
        return "document with ID: $docId"
    }
}


class Subject(val name: String){
    private var _documents: List<Document>? = null

    val documents: List<Document>
    get(){
        if (_documents == null){
            _documents = loadDocument(this)
        }
        return _documents!!
    }


    private fun loadDocument(subject: Subject): List<Document>{
        println("returning documents for subject: ${subject.name}")
        return listOf(Document(1), Document(2), Document(3))
    }
}



// ---------------- Example using delegate -----------------------------


class Document2(val docId: Int){
    override fun toString(): String {
        return "document with ID: $docId"
    }
}


class Subject2(val name: String){
    val documents by lazy { loadDocument2(this) }


    private fun loadDocument2(subject: Subject2): List<Document2>{
        println("returning documents for subject: ${subject.name}")
        return listOf(Document2(1), Document2(2), Document2(3))
    }
}


fun main() {
    val p = Subject("kotlin")
    p.documents


    val p2 = Subject2("java")
    p2.documents
}