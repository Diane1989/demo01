package com.dyx.service;

import com.dyx.Result.Result;

public interface OrganizationalService {

    Result getDeptIdList() throws Exception;

    Result getParentDeptId() throws Exception;

    Result createDept() throws Exception;

    Result getDeptDetails() throws Exception;

    Result getGroupMember() throws Exception;

    Result getStepCount() throws Exception;

    Result isOpenStepCount() throws Exception;

    Result getUserDetails() throws Exception;

}
