package com.afkl.travel.exercise.internationalization;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class LocaleResolverTest {
    @InjectMocks
    private LocaleResolver resolver = new LocaleResolver();

    @Mock
    private HttpServletRequest request;


    @Test
    @DisplayName("should get locale default")
    void shouldGetDefaultLocale() {
        Locale result = resolver.resolveLocale(request);
        assertEquals(Locale.getDefault(), result);
    }

    @Test
    @DisplayName("should get en-EN as locale")
    void shouldGetEnLocale() {
        when(request.getHeader("Accept-Language")).thenReturn("en-EN,en;q=0.8");
        Locale result = resolver.resolveLocale(request);
        assertEquals(new Locale("en"), result);
    }

    @Test
    @DisplayName("should get nl-NL as locale")
    void shouldGetEsLocale() {
        when(request.getHeader("Accept-Language")).thenReturn("nl-NL,nl-NL;q=0.7,en;q=0.3");
        Locale result = resolver.resolveLocale(request);
        assertEquals(new Locale("nl"), result);
    }
}
