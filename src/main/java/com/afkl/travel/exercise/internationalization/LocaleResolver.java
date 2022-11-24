package com.afkl.travel.exercise.internationalization;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
@Component
public class LocaleResolver extends AcceptHeaderLocaleResolver {
    private static final List<Locale> LOCALES = Arrays.asList(new Locale("en"), new Locale("nl"));
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String language = request.getHeader("Accept-Language");
        if (language == null || language.isEmpty()) {
            return Locale.getDefault();
        }
        List<Locale.LanguageRange> list = Locale.LanguageRange.parse(language);
        Locale locale = Locale.lookup(list, LOCALES);
        return locale;
    }

}
