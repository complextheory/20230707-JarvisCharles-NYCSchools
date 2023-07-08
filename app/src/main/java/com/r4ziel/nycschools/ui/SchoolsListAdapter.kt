package com.r4ziel.nycschools.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.r4ziel.a20230707_jarvischarles_nycschools.databinding.ItemViewSchoolBinding
import com.r4ziel.nycschools.entitiy.School
import com.r4ziel.nycschools.utilities.SchoolClickListener

/**
 * Created by Jarvis Charles on 7/7/23.
 */
class SchoolsListAdapter(
    private val schoolClickListener: SchoolClickListener
): RecyclerView.Adapter<SchoolsListAdapter.ViewHolder>() {

    private val schools: MutableList<School> = mutableListOf()

    fun update(data: List<School>) {
        schools.clear()
        schools.addAll(data.sortedBy { it.borough })
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemViewSchoolBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val school = schools[position]
        holder.bind(school)
    }

    override fun getItemCount(): Int = schools.size

    inner class ViewHolder(
        private val binding: ItemViewSchoolBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(school: School) {
            binding.apply {
                binding.school = school
                schoolItemClick = schoolClickListener
                tvSchoolName.text = school.schoolName
                tvGradeLevels.text = school.finalGrades
                tvPhoneNumber.text = school.phoneNumber
                tvWebsite.text = school.website
                tvBorough.text = school.borough

            }
        }
    }
}