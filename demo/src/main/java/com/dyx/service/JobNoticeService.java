package com.dyx.service;

import com.dyx.Result.Result;
import com.dyx.domain.vo.ParmaVo;

public interface JobNoticeService {

    Result submitMessage(ParmaVo parmaVo) throws Exception;

}
