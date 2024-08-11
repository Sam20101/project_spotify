package com.example.project_spotify.Utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

class CommonUtils {

    fun getPreference(context: Context): SharedPreferences {
        val sp: SharedPreferences = context.getSharedPreferences(
            ConstantUtils.preferences().PREF_NAME,
            Context.MODE_PRIVATE
        )
        return sp
    }

    fun setTokenToPreferences(context: Context,token:String)
    {
        getPreference(context).edit().putString(ConstantUtils.preferences().SP_TOKE,token).apply()
    }

    fun getTokenFromPreferences(context: Context):String
    {
        return getPreference(context).getString(ConstantUtils.preferences().SP_TOKE,"Token Not Available")?:"Token is null for now"
    }

}