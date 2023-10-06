package ru.araok.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ContentMediaId implements Serializable {
    @Column(name = "content_id", updatable = false, insertable = false)
    private Long contentId;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "TYPE_ID", insertable = false, updatable = false)
    private MediaType mediaType;
}
