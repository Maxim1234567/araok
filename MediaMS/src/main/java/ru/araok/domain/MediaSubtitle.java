package ru.araok.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MEDIA_SUBTITLE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaSubtitle {
    @Id
    @Column(name = "content_id")
    private Long contentId;

    @Column(name = "LANGUAGE_CODE_2")
    private String languageCode2;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "SUBTITLE_ID")
    private Subtitle subtitle;
}
