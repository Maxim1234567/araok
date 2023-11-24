package ru.araok.service;

import ru.araok.dto.SettingDto;

public interface SettingService {
    SettingDto save(SettingDto setting);

    SettingDto findByContentId(long contentId);
}
