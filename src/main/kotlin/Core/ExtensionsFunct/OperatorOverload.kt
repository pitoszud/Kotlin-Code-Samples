package Core.ExtensionsFunct

class Article(val name: String){
    operator fun plus(article: Article) =
            News(mapOf(name to this, article.name to article))
}

class News(val sections: Map<String, Article>)

fun main(args: Array<String>) {
    val cityLib: News =
            Article("Kaczka") + Article("Dziwaczka")

}
