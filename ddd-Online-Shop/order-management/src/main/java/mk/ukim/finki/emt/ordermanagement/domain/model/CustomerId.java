package mk.ukim.finki.emt.ordermanagement.domain.model;

import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.valueObject.ProductId;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class CustomerId  extends DomainObjectId {
    protected CustomerId(@NonNull String uuid) {
        super(uuid);
    }
    private CustomerId() {
        super(CustomerId.randomId(CustomerId.class).getId());
    }


    public static CustomerId of(String uuid) {
        CustomerId c = new CustomerId(uuid);
        return c;
    }

}
