package com.dyx.service;

import com.dyx.Result.Result;

public interface FileTransferService {


    Result getUploadingFilesDetails() throws  Exception;

    Result uploadingFiles() throws Exception;

    Result getUuid() throws  Exception;

    Result commitFiles() throws  Exception;

}
