package io.ngss.taksitle.report.backoffice.customquery;

import io.ngss.taksitle.report.transaction.database.Transaction;

import java.util.List;

public interface BackOfficeCustomQuery {

    List<Transaction> search(CustomQueryRequest customQueryRequest, Boolean isLimited);

}