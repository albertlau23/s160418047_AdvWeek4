package com.example.a160418047_advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.a160418047_advweek4.R
import com.example.a160418047_advweek4.databinding.FragmentStudentDetailBinding
import com.example.a160418047_advweek4.util.loadImage
import com.example.a160418047_advweek4.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_detail.view.*
import java.util.*
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StudentDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentDetailFragment : Fragment(),ButtonNotifLClickistener {
    // TODO: Rename and change types of parameters
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding:FragmentStudentDetailBinding

    override fun onButtonNotifClick(v: View) {
        Observable.timer(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Messages", "five seconds")
                MainActivity.showNotification(
                    v.tag.toString(),
                    "A new notification created",
                    R.drawable.ic_baseline_account_circle_24
                )
            }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val sid = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch(sid)
            viewModel.studentLD.observe(viewLifecycleOwner, Observer {
                dataBinding.student=it
                dataBinding.listerner=this
//                view.txtName.setText(it.name)
//                view.txtID.setText(sid)
//                view.txtBod.setText(it.bod)
//                view.txtPhone.setText(it.phone)
                view.imageViewDetail.loadImage(it.photourl, view.detailProgress)

//                btnNotif.setOnClickListener {
//                    Observable.timer(5, TimeUnit.SECONDS)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe {
//                            Log.d("Messages", "five seconds")
//                            MainActivity.showNotification(
//                                dataBinding.student.name.toString(),
//                                "A new notification created",
//                                R.drawable.ic_baseline_account_circle_24
//                            )
//                        }
//
//                }

            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding=DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater,R.layout.fragment_student_detail, container, false)
        return dataBinding.root
    }


}