package com.example.strona.model;

import lombok.*;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Error {

    private Integer status;
    private String error;
    private String message;
    private Date timestamp;
}
