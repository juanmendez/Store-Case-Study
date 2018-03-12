package com.target.dealbrowserpoc.dealbrowser.ui.utils

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.View

/**
 * Created by juan on 3/1/18.
 * This is a handy class which shows notifications based on a question, or a view
 * You can enable ok, and cancel buttons, and also pass a handler
 */
class ConfirmDialog( var mContext:Context) {

    private var mQuestion:String? = null
    private var mView: View? = null
    private var mConsumer: (Boolean)->Unit = {}

    private var mShowDialogButtons = true
    private var mJustOk = false

    companion object {

        fun newInstance(context: Context, question: String, consumer: (Boolean)->Unit, justOk: Boolean): ConfirmDialog {
            return ConfirmDialog(context).applyQuestion(question).applyConsumer(consumer).applyJustOk(justOk)
        }

        fun newInstance(context: Context, question: String, consumer: (Boolean)->Unit): ConfirmDialog {
            return newInstance(context, question, consumer, false)
        }
    }

    fun applyQuestion(question: String): ConfirmDialog {
        mQuestion = question
        return this
    }

    fun applyConsumer(response: (Boolean)->Unit): ConfirmDialog {
        mConsumer = response
        return this
    }

    fun applyButtons(show: Boolean): ConfirmDialog {
        mShowDialogButtons = show
        return this
    }

    fun applyJustOk(justOk: Boolean): ConfirmDialog {
        mJustOk = justOk
        return this
    }

    fun applyView(view: View): ConfirmDialog {
        mView = view
        return this
    }

    fun show() {

       val dialogBuilder = AlertDialog.Builder(mContext)

        if (mQuestion?.isNotEmpty()==true) {
            dialogBuilder.setMessage(mQuestion)
        }

        if (mView != null) {
            dialogBuilder.setView(mView)
        }

        dialogBuilder.setCancelable(true)

        if (mShowDialogButtons) {
            if (mJustOk) {
                dialogBuilder.setPositiveButton(
                        mContext.getString(android.R.string.ok) ) { dialog, _ ->
                    confirm(true)
                    dialog.cancel()
                }
            } else {
                dialogBuilder.setPositiveButton(
                        mContext.getString(android.R.string.ok) ) { dialog, _ ->
                    confirm(true)
                    dialog.cancel()
                }


                dialogBuilder.setNegativeButton(
                        mContext.getString(android.R.string.cancel) ) { dialog, _ ->
                    confirm(false)
                    dialog.cancel()
                }
            }
        }

        val alert = dialogBuilder.create()
        alert.show()
    }

    private fun confirm(answer: Boolean) {
        mConsumer( answer )
    }
}