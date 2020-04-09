package com.sushanthande.gocorona.ui.dashboard.fragments.setting

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pixplicity.easyprefs.library.Prefs
import com.sushanthande.gocorona.LanguageUtil
import com.sushanthande.gocorona.R
import com.sushanthande.gocorona.ui.splashscreen.SplashScreenActivity
import kotlinx.android.synthetic.main.fragment_setting.*

/**
 *Created by Sushant Hande on 25-03-2020
 */
class SettingFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cvLanguage.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.cvLanguage -> {
                showLanguagePicker()
            }
        }
    }

    private fun showLanguagePicker() {
        var selectedItem = -1
        val languageList = arrayOf(LanguageUtil.ENGLISH, LanguageUtil.HINDI, LanguageUtil.MARATHI)
        val materialDialog = MaterialAlertDialogBuilder(activity).apply {

                setSingleChoiceItems(languageList, selectedItem) { _, which ->
                    selectedItem = which
                }
                setTitle(R.string.select_langugae)
                setPositiveButton(R.string.ok) { dialog, _ ->
                    when (selectedItem) {
                        0 -> {
                            Prefs.putString(
                                SELECTED_LANGUAGE,
                                LanguageUtil.LanguagePrefix.ENGLISH.language
                            )
                            restartApp()
                            dialog.dismiss()
                        }

                        1 -> {
                            Prefs.putString(
                                SELECTED_LANGUAGE,
                                LanguageUtil.LanguagePrefix.HINDI.language
                            )
                            restartApp()
                            dialog.dismiss()
                        }

                        2 -> {
                            Prefs.putString(
                                SELECTED_LANGUAGE,
                                LanguageUtil.LanguagePrefix.MARATHI.language
                            )
                            restartApp()
                            dialog.dismiss()
                        }
                    }

                    dialog.dismiss()
                }

                setNegativeButton(R.string.cancel) { dialog, _ ->
                    dialog.dismiss()
                }
            }
            .show()

        context?.let {
            materialDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(it, R.color.blue))
            materialDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
                .setTextColor(ContextCompat.getColor(it, R.color.dark_gray))
        }
    }

    private fun restartApp() {
        activity?.let {
            it.finish()
            val intent = Intent(it, SplashScreenActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    companion object {
        const val SELECTED_LANGUAGE = "selectedLanguage"
    }
}
