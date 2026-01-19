package io.example.billing.api

import io.example.billing.application.BillingService
import io.example.billing.api.dto.CreateInvoiceRequest
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid
import java.util.UUID

@RestController
@RequestMapping("/api/invoices")
class InvoiceController(private val billingService: BillingService) {

    @PostMapping
    fun create(@Valid @RequestBody req: CreateInvoiceRequest) = billingService.createInvoice(req.customerId!!, req.amount)

    @PostMapping("/{id}/pay")
    fun pay(@PathVariable id: UUID) = billingService.markPaid(id)
}