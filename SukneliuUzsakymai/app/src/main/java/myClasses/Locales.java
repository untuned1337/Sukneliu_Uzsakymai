package myClasses;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Locale;

public class Locales {
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void LT_locale(Context context)
    {
        Locale locale = new Locale("lt_LT");
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
    }
}
