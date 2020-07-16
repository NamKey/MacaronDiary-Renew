package com.example.macarondiary.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.macarondiary.R
import com.example.macarondiary.adapter.DiaryAdapter
import com.example.macarondiary.dataset.DiaryDataset
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DiaryFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    var datalist = arrayListOf<DiaryDataset>()
    var diary1 = DiaryDataset(diary_title = "Macaron1",diary_shopname = "shop1",diary_date = "020202",diary_imagepath = "file",diary_content = "Hello")
    var diary2 = DiaryDataset(diary_title = "Macaron2",diary_shopname = "shop2",diary_date = "020202",diary_imagepath = "file",diary_content = "Hello")
    var diary3 = DiaryDataset(diary_title = "Macaron3",diary_shopname = "shop3",diary_date = "020202",diary_imagepath = "file",diary_content = "Hello")

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_diary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        datalist.add(diary1)
        datalist.add(diary2)
        datalist.add(diary3)
        viewManager = LinearLayoutManager(view.context)
        viewAdapter = DiaryAdapter(view.context,datalist)
        recyclerView = view.findViewById<RecyclerView>(R.id.diary_recyclerview).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        super.onViewCreated(view, savedInstanceState)
    }
}