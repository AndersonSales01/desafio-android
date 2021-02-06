package com.picpay.desafio.android.features.user

import android.app.Activity
import com.picpay.desafio.android.features.BaseTestRobot
import com.picpay.desafio.android.util.RecyclerViewCustom


fun UsersScreen(func: MainActivityRobot.() -> Unit) = MainActivityRobot()
    .apply { func() }

class MainActivityRobot: BaseTestRobot() {
    fun checkingTitleDisplayed(text: String) = checkTextDisplayed(text)
    fun await(time: Long) = sleep(time)
    fun checkIfItemListExists(resId: Int) = RecyclerViewCustom.getCountFromRecyclerView(resId) > 0
    fun checkIfUserInUserList (resId: Int, position: Int, text: String) = checkTextInList(resId,position,text)
    fun checkDisplayToastError(context: Activity, text: Int) = checkTextToast(context,text)
}