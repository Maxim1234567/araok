package ru.araok;

import ru.araok.domain.AgeLimit;
import ru.araok.domain.Content;
import ru.araok.domain.Language;
import ru.araok.domain.User;
import ru.araok.dto.AgeLimitDto;
import ru.araok.dto.ContentDto;
import ru.araok.dto.LanguageDto;
import ru.araok.dto.UserDto;

import static org.assertj.core.api.Assertions.assertThat;

public class Utils {
    public static void assertEqualsContent(Content excepted, Content result) {
        AgeLimit limit = result.getLimit();
        User user = result.getUser();
        Language language = result.getLanguage();

        assertThat(limit).isNotNull()
                .matches(l -> l.getId().equals(excepted.getLimit().getId()))
                .matches(l -> l.getDescription().equals(excepted.getLimit().getDescription()))
                .matches(l -> l.getLimit().equals(excepted.getLimit().getLimit()));

        assertEqualsUser(excepted.getUser(), user);

        assertThat(language).isNotNull()
                .matches(l -> l.getId().equals(excepted.getLanguage().getId()))
                .matches(l -> l.getCode2().equals(excepted.getLanguage().getCode2()))
                .matches(l -> l.getLanguage().equals(excepted.getLanguage().getLanguage()));

        assertThat(result).isNotNull()
                .matches(c -> c.getId().equals(excepted.getId()))
                .matches(c -> c.getArtist().equals(excepted.getArtist()))
                .matches(c -> c.getName().equals(excepted.getName()))
                .matches(c -> c.getCreateDate().equals(excepted.getCreateDate()));
    }

    public static void assertEqualsUser(User excepted, User result) {
        assertThat(result).isNotNull()
                .matches(u -> u.getId().equals(excepted.getId()))
                .matches(u -> u.getName().equals(excepted.getName()))
                .matches(u -> u.getPhone().equals(excepted.getPhone()))
                .matches(u -> u.getPassword().equals(excepted.getPassword()))
                .matches(u -> u.getBirthDate().equals(excepted.getBirthDate()))
                .matches(u -> u.getRole().equals(excepted.getRole()));
    }

    public static void assertEqualsContentDto(Content excepted, ContentDto result) {
        AgeLimitDto limit = result.getLimit();
        UserDto user = result.getUser();
        LanguageDto language = result.getLanguage();

        assertThat(limit).isNotNull()
                .matches(l -> l.getId().equals(excepted.getLimit().getId()))
                .matches(l -> l.getDescription().equals(excepted.getLimit().getDescription()))
                .matches(l -> l.getLimit().equals(excepted.getLimit().getLimit()));

        assertEqualsUserDto(excepted.getUser(), user);

        assertThat(language).isNotNull()
                .matches(l -> l.getId().equals(excepted.getLanguage().getId()))
                .matches(l -> l.getCode2().equals(excepted.getLanguage().getCode2()))
                .matches(l -> l.getLanguage().equals(excepted.getLanguage().getLanguage()));

        assertThat(result).isNotNull()
                .matches(c -> c.getId().equals(excepted.getId()))
                .matches(c -> c.getArtist().equals(excepted.getArtist()))
                .matches(c -> c.getName().equals(excepted.getName()))
                .matches(c -> c.getCreateDate().equals(excepted.getCreateDate()));
    }

    public static void assertEqualsUserDto(User excepted, UserDto result) {
        assertThat(result).isNotNull()
                .matches(u -> u.getId().equals(excepted.getId()))
                .matches(u -> u.getName().equals(excepted.getName()))
                .matches(u -> u.getPhone().equals(excepted.getPhone()))
                .matches(u -> u.getPassword().equals(excepted.getPassword()))
                .matches(u -> u.getBirthDate().equals(excepted.getBirthDate()))
                .matches(u -> u.getRole().equals(excepted.getRole()));
    }
}
