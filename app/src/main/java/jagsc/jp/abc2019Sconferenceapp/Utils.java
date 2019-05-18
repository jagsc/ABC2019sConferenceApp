package jagsc.jp.abc2019Sconferenceapp;

import android.content.Context;
import android.content.SharedPreferences;

public class Utils {
    static final String JSON_BODY = "JSON_BODY";

    public static void saveJson(Context context, String json) {
        SharedPreferences data = context.getSharedPreferences(JSON_BODY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putString(JSON_BODY, json);
        editor.apply();
    }

    public static String getJson(Context context) {
        SharedPreferences data = context.getSharedPreferences(JSON_BODY, Context.MODE_PRIVATE);
        return data.getString(JSON_BODY, "");
    }
}
