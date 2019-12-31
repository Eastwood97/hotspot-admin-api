package com.jsc.hotspot.api.service;

import java.io.InputStream;
import java.util.List;

public interface ImportService {
    List getBankListByExcel(InputStream in, String fileName) throws Exception;
}
