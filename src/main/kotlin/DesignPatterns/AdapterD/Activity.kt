package DesignPatterns.AdapterD

class Activity {


}

class NameHolder(private val view: View): RecyclerView.ViewHolder(view){

    fun bind(name: String){
        view.name = "My holder name"
    }

}
class MyAdapter(val items: List<Int>): RecyclerView.Adapter<NameHolder>(){

    override fun onCreateViewHolder(view: View): NameHolder {
        return NameHolder(view)
    }

    override fun onBindViewHolder(holder: NameHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}


class View(var name: String){

}