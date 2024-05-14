package mk.ukim.finki.emt.ordermanagement.domain.model;



import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;

import java.util.List;

@Getter

public class Custmer extends AbstractEntity<CustomerId> {
    private String ClientId;
    private String name;
    private String contact;
    private String address;
    private String customerProblem;
    private List<String> historyOrder;


}

