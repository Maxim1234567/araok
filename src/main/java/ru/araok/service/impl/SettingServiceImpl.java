package ru.araok.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.araok.domain.Content;
import ru.araok.domain.Mark;
import ru.araok.domain.Setting;
import ru.araok.dto.ContentDto;
import ru.araok.dto.SettingDto;
import ru.araok.exception.NotFoundSettingException;
import ru.araok.repository.MarkRepository;
import ru.araok.repository.SettingRepository;
import ru.araok.service.SettingService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SettingServiceImpl implements SettingService {

    private final MarkRepository markRepository;

    private final SettingRepository settingRepository;

    @Override
    @Transactional
    public SettingDto save(SettingDto setting) {
        Setting oldSetting = settingRepository.findByContentId(setting.getContent().getId())
                .orElse(Setting.builder()
                        .content(Content.builder().build())
                        .marks(List.of())
                        .build());

        markRepository.deleteAllByIds(oldSetting.getMarks().stream().map(Mark::getId).toList());
        settingRepository.deleteByContentId(setting.getContent().getId());

        setting.setContent(ContentDto.toDto(oldSetting.getContent()));

        return SettingDto.toDto(
                settingRepository.save(SettingDto.toDomainObject(setting))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public SettingDto findByContentId(long contentId) {
        return settingRepository
                .findByContentId(contentId)
                .map(SettingDto::toDto)
                .orElseThrow(NotFoundSettingException::new);
    }
}
