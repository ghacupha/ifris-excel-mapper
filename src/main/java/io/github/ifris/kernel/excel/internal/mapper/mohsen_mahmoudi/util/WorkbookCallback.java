/*
 * code https://github.com/mohsen-mahmoudi/excel-object-mapping
 */
package io.github.ifris.kernel.excel.internal.mapper.mohsen_mahmoudi.util;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author Mohsen.Mahmoudi
 */
public interface WorkbookCallback {

	void getWorkbook(Workbook workbook) throws Throwable;
}
