package com.cjw.demo1.navigation.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.cjw.demo1.R
import com.cjw.demo1.bean.TeacherClassesBean

class TeacherAdapter(data: List<TeacherClassesBean>) : BaseQuickAdapter<TeacherClassesBean, BaseViewHolder>(
    R.layout.item_teacher, data
) {
  override fun convert(
    helper: BaseViewHolder,
    item: TeacherClassesBean
  ) {
    helper.setText(R.id.id_tv, item.teacher?.teacherId.toString())
    helper.setText(R.id.name_tv, item.teacher?.teacherName)
    helper.setText(R.id.classes_id_tv, item.classesList?.joinToString { it.classesId.toString() })
    helper.setText(R.id.classes_name_tv, item.classesList?.joinToString { it.name })
  }
}