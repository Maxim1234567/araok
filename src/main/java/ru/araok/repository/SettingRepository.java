package ru.araok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.araok.domain.Setting;

public interface SettingRepository extends JpaRepository<Setting, Long> {
}
