package com.trimix.backend.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDefault {
    private String action;
    private String message;
    private LocalDateTime date = LocalDateTime.now();
    private String messageType;
    private Object detalle;
}
