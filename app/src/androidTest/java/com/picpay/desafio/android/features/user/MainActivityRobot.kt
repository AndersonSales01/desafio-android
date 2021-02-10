package com.picpay.desafio.android.features.user

import android.app.Activity
import com.picpay.desafio.android.features.BaseTestRobot
import com.picpay.desafio.android.util.MockWebServerUtil
import com.picpay.desafio.android.util.RecyclerViewMatchers


fun UsersScreen(func: MainActivityRobot.() -> Unit) = MainActivityRobot()
    .apply { func() }

class MainActivityRobot: BaseTestRobot() {
    fun checkingTitleDisplayed(text: String) = checkTextDisplayed(text)
    fun await(time: Long) = sleep(time)
    fun checkIfItemListExists(resId: Int) = RecyclerViewMatchers.getCountFromRecyclerView(resId) > 0
    fun checkIfUserInUserList (resId: Int, position: Int, text: String) = checkTextInList(resId,position,text)
    fun checkDisplayToastError(context: Activity, text: Int) = checkTextToast(context,text)
    fun startMockWebServer() = MockWebServerUtil.startServerMockWebService()
    fun stopMockWebServer() = MockWebServerUtil.stopServerMockWebService()
}