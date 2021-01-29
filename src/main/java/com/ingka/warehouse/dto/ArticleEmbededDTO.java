package com.ingka.warehouse.dto;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class ArticleEmbededDTO {

    @NotNull
    private String art_id;

    private String amount_of;
}
