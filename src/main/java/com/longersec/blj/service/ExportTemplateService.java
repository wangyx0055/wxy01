package com.longersec.blj.service;

import java.io.File;
import java.io.IOException;

public interface ExportTemplateService {
    File createTempFile_user() throws IOException;

    File createTempFile_group()throws IOException;

    File createTempFile_device()throws IOException;

    File createTempFile_department()throws IOException;

    File createTempFile_operation()throws IOException;

	File createTempFile_apppubserver() throws IOException;

    File createTempFile_apppubAccount() throws IOException;
}
