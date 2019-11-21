package com.jsc.hotspot.api.dto;

import lombok.Data;

import java.util.List;

/**
 * @author huixing
 * @description
 * @date 2019/11/20
 */
@Data
public class FolderResultDTO {

    Integer total;
    List<RowResultDTO> rows;
}
