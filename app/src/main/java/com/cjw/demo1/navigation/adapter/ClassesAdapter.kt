package com.cjw.demo1.navigation.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.cjw.demo1.R
import com.cjw.demo1.room.data.Classes

class ClassesAdapter(data: List<Classes>) : BaseQuickAdapter<Classes, BaseViewHolder>(
    R.layout.item_classes, data
) {
  override fun convert(
    helper: BaseViewHolder,
    item: Classes
  ) {
    helper.setText(R.id.id_tv, item.classesId.toString())
    helper.setText(R.id.name_tv, item.name)
    helper.setText(R.id.address_tv, item.address)
  }
}