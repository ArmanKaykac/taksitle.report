package io.ngss.test;

import io.ngss.taksitle.report.backoffice.customquery.CustomQueryRequest;
import io.ngss.taksitle.report.transaction.database.Transaction;

import java.util.List;

public interface BackOfficeCustomQueryNewDB {

    List<Transaction> search(CustomQueryRequest customQueryRequest, Boolean isLimited);

}