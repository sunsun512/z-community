package zaritalk.zaritalkcommunity.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum AccountType {
    LESSOR("LESSOR", "임대인"), REALTOR("REALTOR", "공인 중개사"), LESSEE( "LESSEE", "임차인");

    private String type;
    private String name;

    AccountType(String type, String name){
        this.type = type;
        this.name = name;
    }


}
