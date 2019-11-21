package com.jsc.hotspot.api.dto;

import lombok.Data;

import java.util.List;

/**
 * @author huixing
 * @description
 * @date 2019/11/20
 */
@Data
public class FilesResultDTO {
    private Integer total;
    private List<FileRowsDTO> fileRowsDTOList;
}
