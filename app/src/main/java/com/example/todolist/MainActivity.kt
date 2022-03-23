package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ItemAdapter.ClickListener {

    val itemList = ArrayList<Item>()
    lateinit var mainList: RecyclerView
    lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemList.add(Item("Animals", "Animals is the tenth studio album by the English rock band Pink Floyd, released on 23 January 1977[2] through Harvest and Columbia Records. It was recorded at the band's Britannia Row Studios in London throughout 1976, and was produced by the band", R.drawable.image1))
        itemList.add(Item("Division Bell", "The Division Bell is the fourteenth album by the English progressive rock band Pink Floyd, released on 28 March 1994 by EMI Records in the United Kingdom and on 4 April by Columbia Records in the United States.", R.drawable.image2))
        itemList.add(Item("Atom heart mother","Atom Heart Mother is the fifth studio album by the English progressive rock band Pink Floyd. It was released by Harvest on 2 October 1970 in the UK, and by Capitol on 10 October 1970 in the US.[2] It was recorded at Abbey Road Studios in London, England, and was the band's first album to reach number 1 in the UK, while it reached number 55 in the US, eventually going gold there.", R.drawable.image3))
        itemList.add(Item("Meddle", "Meddle is the sixth studio album by English rock band Pink Floyd, released by Harvest Records. The album was produced between the band's touring commitments, from January to August 1971 at a series of locations around London, including Abbey Road Studios and Morgan Studios", R.drawable.image4))
        itemList.add(Item("Obscured by clouds", "Obscured by Clouds is the seventh studio album by the English progressive rock band Pink Floyd, released on 2 June 1972 by Harvest and Capitol Records. It serves as the soundtrack for the French film La Vallée, by Barbet Schroeder. It was recorded in two sessions in France, while Pink Floyd were in the midst of touring, and produced by the band", R.drawable.image5))
        itemList.add(Item("Dark side of the moon", "The Dark Side of the Moon is the eighth studio album by the English rock band Pink Floyd, released on 1 March 1973 by Harvest Records. Primarily developed during live performances, the band premiered an early version of the suite several months before recording began", R.drawable.image6))
        itemList.add(Item("Wish you were here", "Wish You Were Here is the ninth studio album by the English rock band Pink Floyd, released on 12 September 1975 through Harvest Records and Columbia Records, their first release for the latter. Based on material Pink Floyd composed while performing in Europe, Wish You Were Here was recorded over numerous sessions throughout 1975 at Abbey Road Studios in London", R.drawable.image7))
        itemList.add(Item("The Wall", "The Wall is the eleventh studio album by the English progressive rock band Pink Floyd, released on 30 November 1979 by Harvest and Columbia Records. It is a rock opera that explores Pink, a jaded rock star whose eventual self-imposed isolation from society forms a figurative wall.", R.drawable.image8))

        itemAdapter = ItemAdapter(this)
        itemAdapter.setData(itemList)

        mainList = findViewById(R.id.mainList)

        mainList.layoutManager = LinearLayoutManager(this)
        mainList.adapter = itemAdapter

    }

    override fun clickedItem(itemsModal: Item) {
        Toast.makeText(this, itemsModal.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)

        val menuItem = menu!!.findItem(R.id.search)
        val searchView = menuItem.actionView as SearchView

        searchView.maxWidth = Int.MAX_VALUE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                itemAdapter.filter.filter(query)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                itemAdapter.filter.filter(newText)

                return true
            }

        })


        return true
    }
}