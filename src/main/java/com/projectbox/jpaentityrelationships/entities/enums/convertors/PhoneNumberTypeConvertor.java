package com.projectbox.jpaentityrelationships.entities.enums.convertors;

import com.projectbox.jpaentityrelationships.entities.PhoneNumber;
import com.projectbox.jpaentityrelationships.entities.enums.PhoneNumberType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class PhoneNumberTypeConvertor implements AttributeConverter<PhoneNumberType, String> {

    @Override
    public String convertToDatabaseColumn(PhoneNumberType phoneNumberType) {
        if (phoneNumberType == null) {
            return null;
        }

        return phoneNumberType.getCode();
    }

    @Override
    public PhoneNumberType convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(PhoneNumberType.values())
                .filter(phoneNumberType -> phoneNumberType.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
