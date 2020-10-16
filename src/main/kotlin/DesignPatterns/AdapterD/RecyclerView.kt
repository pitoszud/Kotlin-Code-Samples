package DesignPatterns.AdapterD

class RecyclerView{

    abstract class ViewHolder(view: View) {

    }

    abstract class Adapter<VH : ViewHolder?> {

        abstract fun onCreateViewHolder(view: View): VH

        abstract fun onBindViewHolder(holder: VH, position: Int)

        abstract fun getItemCount(): Int

    }
}