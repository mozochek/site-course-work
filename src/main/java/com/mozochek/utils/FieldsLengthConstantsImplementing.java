package com.mozochek.utils;

import org.springframework.ui.Model;

/**
 * Интерфейс, добавляющий длину полей в модель данных (сделано для Freemarker'а)
 */
public interface FieldsLengthConstantsImplementing {
    default void addFieldsLengthConstantsIn(Model model) {
        model.addAttribute("fieldLength", LengthConstantsImplementation.getInstance());
    }
}
