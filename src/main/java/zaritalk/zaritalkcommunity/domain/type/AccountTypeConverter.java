package zaritalk.zaritalkcommunity.domain.type;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;



@Converter
public class AccountTypeConverter implements AttributeConverter<AccountType, String> {
    @Override
    public String convertToDatabaseColumn(AccountType attribute) {
        return attribute != null ? attribute.getName() : null;
    }

    @Override
    public AccountType convertToEntityAttribute(String dbData) {
        return Arrays.stream(AccountType.values())
                .filter(v -> v.getName().equals(dbData))
                .findAny()
                .orElseThrow(() -> new RuntimeException("convertToEntityAttribute fail to convert : " + dbData));
    }
}
