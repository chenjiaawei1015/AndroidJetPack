package com.cjw.demo1.navigation.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.cjw.demo1.R
import com.cjw.demo1.room.data.Student

class StudentAdapter(data: List<Student>) : BaseQuickAdapter<Student, BaseViewHolder>(
    R.layout.item_student, data
) {
  override fun convert(
    helper: BaseViewHolder,
    item: Student
  ) {
    helper.setText(R.id.id_tv, item.studentId.toString())
    helper.setText(R.id.name_tv, item.studentName)
    helper.setText(R.id.classes_id_tv, item.classesId.toString())
  }
}