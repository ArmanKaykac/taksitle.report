package io.ngss.taksitle.report.backoffice.manager.reports;

import io.ngss.taksitle.report.backoffice.model.reports.IncomeDocumentModel;
import io.ngss.taksitle.report.backoffice.model.reports.base.BaseReportModel;
import io.ngss.taksitle.report.backoffice.model.reports.search.IncomeDocumentSearchModel;
import io.ngss.taksitle.report.bank.LoanCategory;
import io.ngss.taksitle.report.bank.database.entity.LoanOffer;
import io.ngss.taksitle.report.shared.database.entity.Document;
import io.ngss.taksitle.report.transaction.database.Transaction;
import io.ngss.taksitle.report.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IncomeDocumentModelManager extends BaseReportModel<IncomeDocumentModel, IncomeDocumentSearchModel> {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<IncomeDocumentModel> filterModel(IncomeDocumentSearchModel searchModel) {

        if (searchModel == null)
            return null;

        List<Transaction> transactionList = transactionRepository.findAllByCreatedAtIsAfter(new Date(searchModel.startDate));
        if (transactionList == null) return null;

        if (searchModel.endDate != null) {
            Long oneMonth =30L * 24L * 60L * 60L * 1000L;
            Long distance = searchModel.endDate - searchModel.startDate;
            if (distance > oneMonth)
                searchModel.endDate = searchModel.startDate + oneMonth;

            transactionList = transactionList.stream().filter(x -> x.getCreatedAt().before(new Date(searchModel.endDate))).collect(Collectors.toList());
        }
        else {

            Long oneMonth =30L * 24L * 60L * 60L * 1000L;
            Long distance = System.currentTimeMillis() - searchModel.startDate;
            if (distance > oneMonth)
                searchModel.endDate = searchModel.startDate + oneMonth;

            transactionList = transactionList.stream().filter(x -> x.getCreatedAt().before(new Date(searchModel.endDate))).collect(Collectors.toList());
        }

        if (searchModel.dealerId != null && searchModel.dealerId > 0)
            transactionList = transactionList.stream().filter(x -> x.getDealer().getId().equals(searchModel.dealerId)).collect(Collectors.toList());

        if (searchModel.financialTypeId != null && searchModel.financialTypeId > 0)
            transactionList = transactionList.stream().filter(x -> x.getLoanRequest() != null && x.getLoanRequest().getLoanCategory() != null && x.getLoanRequest().getLoanCategory().equals(LoanCategory.getBySekerbankValue(searchModel.financialTypeId.toString()))).collect(Collectors.toList());

        transactionList = transactionList.stream().filter(x -> x.getDocuments() != null && x.getDocuments().size() > 0 && existIncomeDocument(x.getDocuments())).collect(Collectors.toList());
        List<IncomeDocumentModel> model = new ArrayList<>();

        transactionList.forEach(t -> {

            Optional<LoanOffer> loanOffer = t.getLoanOffers().stream().filter(x -> x.getKkbScore() != null).findFirst();
            String kkbScore = "";
            if (loanOffer.isPresent())
                kkbScore = loanOffer.get().getKkbScore();

            IncomeDocumentModel transaction = new IncomeDocumentModel(t.getToken(),
                    t.getCreatedAt().toString(),
                    t.getDealer() != null ? t.getDealer().getName() : "",
                    t.getTransactionState().toString(),
                    "Evet",
                    kkbScore,
                    t.getCustomer().getCustomerDetails().getWorkingType().toString(),
                    t.getCustomer().getCustomerDetails().getOccupation(),
                    t.getCustomer().getCustomerDetails().getSalary(),
                    t.getCustomer().getCustomerDetails().getCompany());
            model.add(transaction);
        });
        return model;
    }

    private Boolean existIncomeDocument(List<Document> documents) {

        if (documents.stream().anyMatch(x -> x.getDocumentName().equals("Gelir Belgesi")))
            return true;

        return false;
    }
}
