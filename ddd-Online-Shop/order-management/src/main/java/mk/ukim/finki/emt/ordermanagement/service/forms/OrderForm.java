package mk.ukim.finki.emt.ordermanagement.service.forms;

import lombok.Data;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;


import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@Data
public class OrderForm {

    @NonNull
    private Currency currency;

@Valid
@NotEmpty
    private List<OrderItemForm> items = new ArrayList<>();
}

