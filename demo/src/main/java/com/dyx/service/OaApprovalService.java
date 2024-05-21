package com.dyx.service;

import com.dyx.Result.Result;
import com.dyx.domain.bo.ApprovalInstanceBo;
import com.dyx.domain.vo.ParmaVo;

import javax.servlet.http.HttpServletResponse;

public interface OaApprovalService {

    Result getApprovalInstanceDetails() throws Exception;


    Result getApprovalInstanceIdList() throws Exception;


    void printApprovalPdf(HttpServletResponse response) throws Exception;

    void printApprovalDetails(HttpServletResponse response) throws Exception;

    void printApprovalDetailsToPdf(HttpServletResponse response) throws Exception;



}
