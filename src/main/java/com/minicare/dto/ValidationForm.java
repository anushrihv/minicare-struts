package com.minicare.dto;

import javax.servlet.http.HttpServletRequest;

public interface ValidationForm {
    boolean validate(HttpServletRequest req);
}
