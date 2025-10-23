package com.nurtricenter.contractservices.domain.invoice;

import java.util.UUID;

public interface InvoiceRepository {

    InvoiceDomain createInvoice(InvoiceDomain invoiceDomain);
    InvoiceDomain getInvoice(UUID invoiceId);
    InvoiceDomain updateInvoice(UUID invoiceId, InvoiceDomain invoiceDomain);

}
