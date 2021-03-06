package com.example.a160418047_advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160418047_advweek4.R
import com.example.a160418047_advweek4.databinding.FragmentStudentListItemBinding
import com.example.a160418047_advweek4.model.Student
import com.example.a160418047_advweek4.util.loadImage
import kotlinx.android.synthetic.main.fragment_student_detail.view.*
import kotlinx.android.synthetic.main.fragment_student_list_item.view.*

class StudentListAdapter(val studentList: ArrayList<Student>) :
    RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonDetailClickListener {
    class StudentViewHolder(var view: FragmentStudentListItemBinding) :
        RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType:
        Int
    ): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.fragment_student_list_item, parent, false)
        val view = DataBindingUtil.inflate<FragmentStudentListItemBinding>(
            inflater,
            R.layout.fragment_student_list_item,
            parent,
            false
        )
        return StudentViewHolder(view)
    }

    override fun onButtonDetailClick(v: View) {
        val action =
            StudentListFragmentDirections.actionStudentDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
//        holder.view.imageView.loadImage(studentList[position].photourl,
//            holder.view.progressBar)
//        holder.view.txtIdCard.text = studentList[position].id.toString()
//        holder.view.txtNameCard.text = studentList[position].name
//        holder.view.btnDetail.setOnClickListener {
//            val action = StudentListFragmentDirections.actionStudentDetail(studentList[position].id.toString())
//            Navigation.findNavController(it).navigate(action)
//        }
        holder.view.student = studentList[position]
        holder.view.listerner=this



    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun updateStudentList(newStudentList: List<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }


}