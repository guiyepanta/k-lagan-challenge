package com.klagan.challenge.application.port.in.web;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PriceRequest {

    @NotNull(message = "Cannot be null.")
    private Integer brandId;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Cannot be null.")
    private LocalDateTime startDate;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Cannot be null.")
    private LocalDateTime endDate;
    @NotNull(message = "Cannot be null.")
    private Integer priceList;
    @NotNull(message = "Cannot be null.")
    private Integer productId;
    @NotNull(message = "Cannot be null.")
    private Integer priority;
    @NotNull(message = "Cannot be null.")
    private Float price;
    @NotNull(message = "Cannot be null.")
    private String curr;

}
