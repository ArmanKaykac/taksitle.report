package io.ngss.taksitle.report.backoffice.manager.reports;

import io.ngss.taksitle.report.backoffice.model.reports.TransactionStateCountModel;
import io.ngss.taksitle.report.backoffice.model.reports.base.BaseReportModel;
import io.ngss.taksitle.report.backoffice.model.reports.search.TransactionStateCountSearchModel;
import io.ngss.taksitle.report.bank.LoanCategory;
import io.ngss.taksitle.report.dealer.TransactionState;
import io.ngss.taksitle.report.transaction.database.Transaction;
import io.ngss.taksitle.report.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionStateCountModelManager extends BaseReportModel<TransactionStateCountModel, TransactionStateCountSearchModel> {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<TransactionStateCountModel> filterModel(TransactionStateCountSearchModel searchModel) {
        return filterModelWithState(searchModel, true);
    }

    public List<TransactionStateCountModel> filterModelWithState(TransactionStateCountSearchModel searchModel, boolean includeCancelState) {
        if (searchModel == null || searchModel.startDate == null) return null;

        List<Transaction> list = transactionRepository.findAllByStateUpdateDateAfter(new Date(searchModel.startDate)).stream()
                .filter(x -> x.getTransactionState() != null)
                .collect(Collectors.toList());
        if (list == null) return null;

        if (!includeCancelState)
            list = list.stream().filter(x -> x.getTransactionState() != TransactionState.IPTAL).collect(Collectors.toList());

        if (searchModel.endDate != null)
            list = list.stream().filter(x -> x.getStateUpdateDate().before(new Date(searchModel.endDate))).collect(Collectors.toList());

        if (searchModel.dealerId != null)
            list = list.stream().filter(x -> x.getDealer() != null && x.getDealer().getId().equals(searchModel.dealerId)).collect(Collectors.toList());

        if (searchModel.financialTypeId != null)
            list = list.stream().filter(x -> x.getLoanRequest() != null && x.getLoanRequest().getLoanCategory() != null && x.getLoanRequest().getLoanCategory().equals(LoanCategory.getBySekerbankValue(searchModel.financialTypeId.toString()))).collect(Collectors.toList());

        Map<TransactionState, Long> map = list.stream().collect(Collectors.groupingBy(Transaction::getTransactionState, Collectors.counting()));
        if (map == null) return null;

        List<TransactionStateCountModel> model = new ArrayList<>();
        for (Map.Entry<TransactionState, Long> entry : map.entrySet()) {
            model.add(new TransactionStateCountModel(entry.getValue(), entry.getKey()));
        }
        return model;
    }
}
