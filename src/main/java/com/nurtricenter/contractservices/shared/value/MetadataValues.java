package com.nurtricenter.contractservices.shared.value;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class MetadataValues {

    private String companyTaxIdentifier;

    public MetadataValues(
            @Value("${company.tax.identifier}") String companyTaxIdentifier
    ) {
        this.companyTaxIdentifier = companyTaxIdentifier;
    }

}
