package com.nurtricenter.contractservices.domain.aggregatecontract.invoice;

import java.util.UUID;

public interface InvoiceRepository {

    Invoice createInvoice(Invoice invoice);
    Invoice getInvoice(UUID invoiceId);
    Invoice updateInvoice(UUID invoiceId, Invoice invoice);

}
